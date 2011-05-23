/**
 *  This file is part of OpenSchedule for Android
 * 
 *  OpenSchedule for Android is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenSchedule for Android is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSchedule for Android.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Daniel Frey <dmfrey at gmail dot com>
 * 
 * This software can be found at <http://code.google.com/p/open-schedule-android/>
 *
 */
package org.openschedule.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

	private static final String TAG = "SessionActivity";

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
	      		Log.d( TAG, "onItemClick : enter" );
		    	
		    	Day day = SharedDataManager.getCurrentDay();
      			Block block = SharedDataManager.getCurrentBlock();

      			Calendar now = Calendar.getInstance();
      			
  				Calendar startTime = Calendar.getInstance();
  				startTime.setTime( day.getDate() );
  				StringTokenizer st = new StringTokenizer( block.getLabel().getName(), ":" );
  				int hour = Integer.parseInt( st.nextToken() );
  				int minute = Integer.parseInt( st.nextToken() );
  				startTime.set( Calendar.HOUR_OF_DAY, hour );
  				startTime.set( Calendar.MINUTE, minute );

  				Calendar endTime = Calendar.getInstance();
  				endTime.setTime( startTime.getTime() );
  				endTime.add( Calendar.MINUTE, block.getDuration() );

				Log.d( TAG, "now=" + now.get( Calendar.YEAR ) + "-" + ( now.get( Calendar.MONTH ) + 1 ) + "-" + now.get( Calendar.DATE ) + " " + now.get( Calendar.HOUR_OF_DAY ) + ":" + now.get( Calendar.MINUTE ) + ":" + now.get( Calendar.SECOND ) );
				Log.d( TAG, "startTime=" + startTime.get( Calendar.YEAR ) + "-" + ( startTime.get( Calendar.MONTH ) + 1 ) + "-" + startTime.get( Calendar.DATE ) + " " + startTime.get( Calendar.HOUR_OF_DAY ) + ":" + startTime.get( Calendar.MINUTE ) + ":" + startTime.get( Calendar.SECOND ) );
				Log.d( TAG, "endTime=" + endTime.get( Calendar.YEAR ) + "-" + ( endTime.get( Calendar.MONTH ) + 1 ) + "-" + endTime.get( Calendar.DATE ) + " " + endTime.get( Calendar.HOUR_OF_DAY ) + ":" + endTime.get( Calendar.MINUTE ) + ":" + endTime.get( Calendar.SECOND ) );
				Log.d( TAG, "now after startTime? : " + ( now.after( startTime ) ) );

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
	      				    Log.d( TAG, "sendTweet : tweeting is disabled, session has not started yet." );

		      				Toast toast = Toast.makeText( view.getContext(), "Session has not started yet, tweeting is only available during the session.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
	      				} else if( now.after( endTime ) ) {
	      				    Log.d( TAG, "sendTweet : tweeting is disabled, session has ended." );

		      				Toast toast = Toast.makeText( view.getContext(), "Session has ended, tweeting is only available during the session.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
	      				} else {
	      				    Log.d( TAG, "sendTweet : tweeting is enabled, session in progress." );

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

		      				String title = event.getName();
		      				if( null != block.getSession().getName() ) {
		      					title += ": " + block.getSession().getName();
		      				}
		      				contentValues.put( "title", title  );

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
		      				
		      				contentValues.put( "allDay", 0 );
		      				contentValues.put( "eventStatus", 1 );
		      				contentValues.put( "visibility", 0 );
		      				contentValues.put( "transparency", 0 );
		      				contentValues.put( "hasAlarm", 1 );
		      				
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
	      				    Log.d( TAG, "sendTweet : add comment is disabled, session has not started yet." );

		      				Toast toast = Toast.makeText( view.getContext(), "Session has not started yet, Comments are only available after the session has started.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
	      				} else {
	      				    Log.d( TAG, "sendTweet : adding comment is enabled, session has started or is completed." );

	      					NavigationManager.startActivity( view.getContext(), SessionCommentFormActivity.class );
	      				}
	      				
			      		break;
			      	default:
			      		Log.d( TAG, "default option" );
			      		break;
		    	}

  				Log.d( TAG, "onItemClick : exit" );
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

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
	    Log.d( TAG, "onCreateOptionsMenu : enter" );

	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate( R.menu.about_menu, menu );

	    Log.d( TAG, "onCreateOptionsMenu : exit" );
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {		
	    Log.d( TAG, "onOptionsItemSelected : enter" );

	    // Handle item selection
	    switch( item.getItemId() ) {
	    case R.id.about_menu:
	    	NavigationManager.startActivity( this, AboutActivity.class );

	    	Log.d( TAG, "onOptionsItemSelected : exit, about option selected" );
	    	return true;
	    default:
	    	Log.d( TAG, "onOptionsItemSelected : exit, default option selected" );
	        return super.onOptionsItemSelected( item );
	    }
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshSession() {
		Log.d( TAG, "Refreshing Session : enter" );

		Day day = SharedDataManager.getCurrentDay();
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
		
		if( null != day.getDate() ) {
			Log.d( TAG, "Refreshing Session : block date is not null" );
			
			String time = new SimpleDateFormat( Prefs.DATE_FORMAT ).format( day.getDate() );
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
