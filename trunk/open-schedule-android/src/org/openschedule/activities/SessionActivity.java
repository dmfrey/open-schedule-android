/**
 * 
 */
package org.openschedule.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.openschedule.R;
import org.openschedule.activities.twitter.TwitterActivity;
import org.openschedule.controllers.NavigationManager;
import org.openschedule.domain.Block;
import org.openschedule.domain.Day;
import org.openschedule.domain.Event;
import org.openschedule.domain.Schedule;
import org.openschedule.domain.Speaker;
import org.openschedule.util.Prefs;
import org.openschedule.util.SharedDataManager;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author dmfrey
 *
 */
public class SessionActivity extends Activity {

	private static final String TAG = "InfoActivity";

	@Override
	public void onCreate( Bundle savedInstanceState ) {
	    Log.d( TAG, "onCreate : enter" );

	    super.onCreate( savedInstanceState );
		
		setContentView( R.layout.session_details );

		final ListView listView = (ListView) findViewById( R.id.session_details_menu );
		
		String[] menu_items = getResources().getStringArray( R.array.session_options_array );
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.menu_list_item, menu_items );
		listView.setAdapter(arrayAdapter);
		
		listView.setOnItemClickListener( new OnItemClickListener() {
		    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
		    	
		    	Day day = SharedDataManager.getCurrentDay();
      			Block block = SharedDataManager.getCurrentBlock();

      			Calendar now = new GregorianCalendar();
      			
  				Calendar startTime = new GregorianCalendar();
  				startTime.setTime( day.getDate() );
  				StringTokenizer st = new StringTokenizer( block.getLabel().getName(), ":" );
  				int hour = Integer.parseInt( st.nextToken() );
  				int minute = Integer.parseInt( st.nextToken() );
  				startTime.set( Calendar.HOUR_OF_DAY, hour );
  				startTime.set( Calendar.MINUTE, minute );

  				Calendar endTime = new GregorianCalendar();
  				endTime.setTime( startTime.getTime() );
  				endTime.add( Calendar.MINUTE, block.getDuration() );

  				switch( position ) {
		    		case 0:
		    			Log.d( TAG, "show Description" );

		    			NavigationManager.startActivity( view.getContext(), SessionDescriptionActivity.class );
		    			break;
			      	case 1:
			      		Log.d( TAG, "show Speakers" );

			      		if( null != block ) {
			      			if( null != block.getSession() ) {
			      				if( null != block.getSession().getSpeakers() && !block.getSession().getSpeakers().isEmpty() ) {
			      					if( block.getSession().getSpeakers().size() > 1 ) {
			      						NavigationManager.startActivity( view.getContext(), SpeakersActivity.class );
			      					} else {
			      						SharedDataManager.setCurrentSpeaker( block.getSession().getSpeakers().get( 0 ) );
			      						NavigationManager.startActivity( view.getContext(), SpeakerActivity.class );
			      					}
			      				} else {
				      				Toast toast = Toast.makeText( view.getContext(), "This Session has no speakers.", Toast.LENGTH_LONG );
				      				toast.setGravity( Gravity.CENTER, 0, 0 );
				      				toast.show();
			      				}
			      			}
			      		}
			      		break;
			      	case 2:
			      		Log.d( TAG, "view comments" );
			      		
		    			NavigationManager.startActivity( view.getContext(), SessionCommentsActivity.class );
			      		break;
			      	case 3:
			      		Log.d( TAG, "send tweet" );

	      				if( now.before( startTime ) ) {
		      				Toast toast = Toast.makeText( view.getContext(), "Session has not started yet, tweeting is only available during the session.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
	      				} else if( now.after( endTime ) ) {
		      				Toast toast = Toast.makeText( view.getContext(), "Session has ended, tweeting is only available during the session.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
	      				} else {
	      					NavigationManager.startActivity( view.getContext(), TwitterActivity.class );
	      				}
			      		break;
			      	case 4:
			      		Log.d( TAG, "add to calendar" );

		      			Event event = SharedDataManager.getCurrentEvent();
		      			Schedule schedule = SharedDataManager.getCurrentSchedule();

		      			if( null != block.getSession() ) {
		      				String[] projection = new String[] { "_id" };
		      				String contentUri = "content://calendar/calendars";
		      				if( Build.VERSION.SDK_INT > 7 ) {
			      				Log.v( TAG, "switching calendar to android 2.2 and greater model" );

			      				projection = new String[] { "_id" };
		      					contentUri = "content://com.android.calendar/events";
		      				}
		      				Cursor cursor = managedQuery( Uri.parse( contentUri ), projection, "selected=1", null, null );
		      				if( null == cursor ) {
			      				Log.v( TAG, "cursor is null" );

			      				Toast toast = Toast.makeText( view.getContext(), "Could not add session to calendar.", Toast.LENGTH_LONG );
			      				toast.setGravity( Gravity.CENTER, 0, 0 );
			      				toast.show();

			      				return;
		      				}
		      				cursor.moveToFirst();
		      				int[] CalIds = new int[cursor.getCount()];
		      				for (int i = 0; i < CalIds.length; i++) {
		      				    CalIds[i] = cursor.getInt(0);
		      				    cursor.moveToNext();
		      				}
		      				cursor.close();
		      				
		      				ContentValues contentValues = new ContentValues();
		      				contentValues.put( "calendar_id", CalIds[ 0 ] );

		      				contentValues.put( "title", event.getName() + ": " + block.getSession().getName()  );

		      				String speakers = "Presented By: ";
		      				boolean slash = false;
		      				for( Speaker speaker : block.getSession().getSpeakers() ) {
		      					if( slash ) {
		      						speakers += " / ";
		      					}

		      					speakers += speaker.getName();
		      					slash = true;
		      				}
		      				speakers += "\n\n";

		      				String description = "Description:\n";
		      				description += block.getSession().getDescription();
		      				contentValues.put( "description", ( speakers + description ) );

		      				String location = "";
		      				if( null != schedule ) {
		      					if( null != schedule.getTrack() ) {
		      						location = schedule.getTrack().getName();
		      						if( null != schedule.getTrack().getRoom() ) {
		      							location += " - Room: " + schedule.getTrack().getRoom().getName(); 
		      						}
		      						contentValues.put( "eventLocation", location );
		      					}
		      				}

		      				contentValues.put( "dtstart", startTime.getTimeInMillis() );
		      				contentValues.put( "dtend", endTime.getTimeInMillis() );

		      				Log.v( TAG, "adding event to calendar" );
		      				getContentResolver().insert( Uri.parse( contentUri ), contentValues );

		      				Toast toast = Toast.makeText( view.getContext(), "Session added to your calendar.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
		      			} else {
		      				Toast toast = Toast.makeText( view.getContext(), "Could not add session to calendar.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
		      			}
			      		
			      		break;
			      	case 5:
			      		Log.d( TAG, "add comment" );
			      		
	      				if( now.before( startTime ) ) {
		      				Toast toast = Toast.makeText( view.getContext(), "Session has not started yet, Comments are only available after the session has started.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
	      				} else {
	      					NavigationManager.startActivity( view.getContext(), SessionCommentFormActivity.class );
	      				}
	      				
			      		break;
			      	default:
			      		Log.d( TAG, "default option" );
			      		break;
		    	}
		    }
		});

		Log.d( TAG, "onCreate : exit" );
	}

	@Override
	protected void onStart() {
	    Log.d( TAG, "onStart : enter" );

	    super.onStart();
		
		refreshSession();

		Log.d( TAG, "onStart : exit" );
	}
	
	@Override
	protected void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();
		
		refreshSession();

		Log.d( TAG, "onResume : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshSession() {
		Log.d( TAG, "Refreshing Session : enter" );

		Block block = SharedDataManager.getCurrentBlock();
		if( null == block ) {
			Log.d( TAG, "Refreshing Session : exit, no block" );

			return;
		}
		
		final TextView sessionNameTextView = (TextView) findViewById( R.id.session_name );
		final TextView sessionTimeTextView = (TextView) findViewById( R.id.session_time );
		final TextView sessionTrackTextView = (TextView) findViewById( R.id.session_track );
		final TextView sessionTrackSponsorTextView = (TextView) findViewById( R.id.session_track_sponsor );
		final TextView sessionRoomTextView = (TextView) findViewById( R.id.session_room );

		if( null != block.getSession() ) {
			Log.d( TAG, "Refreshing Session : block session is not null" );
			
			sessionNameTextView.setText( block.getSession().getName() );
		}
		
		if( null != block.getDate() ) {
			Log.d( TAG, "Refreshing Session : block date is not null" );
			
			String time = new SimpleDateFormat( Prefs.DATE_FORMAT ).format( block.getDate() );
			if( null != block.getLabel() ) {
				Log.d( TAG, "Refreshing Session : block label is not null" );
				
				time += " " + block.getLabel().getName() + " (" + block.getDuration() + " minutes)";
			}
			sessionTimeTextView.setText( time );
		} else {
			Log.d( TAG, "Refreshing Session : block date is null" );

			sessionTimeTextView.setText( "Date Unavailable!" );
		}
		
		Schedule schedule = SharedDataManager.getCurrentSchedule();
		if( null != schedule.getTrack() ) {
			Log.d( TAG, "Refreshing Session : schedule track is not null" );
			
			sessionTrackTextView.setText( schedule.getTrack().getName() );
			
			if( null != schedule.getTrack().getSponsor() ) {
				Log.d( TAG, "Refreshing Session : schedule track sponsor is not null" );

				sessionTrackSponsorTextView.setText( "Sponsored by: " + schedule.getTrack().getSponsor().getCompanyName() );
			}
			
			if( null != schedule.getTrack().getRoom() ) {
				Log.d( TAG, "Refreshing Session : schedule track room is not null" );

				sessionRoomTextView.setText( schedule.getTrack().getRoom().getName() );
			}
		}
		
		Log.d( TAG, "Refreshing Session : exit" );
	}
	
}
