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
package org.openschedule.activities.event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openschedule.MainApplication;
import org.openschedule.R;
import org.openschedule.activities.AboutActivity;
import org.openschedule.activities.AbstractOpenScheduleActivity;
import org.openschedule.api.Event;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
 * @author Daniel Frey
 *
 */
public class EventsActivity extends AbstractOpenScheduleActivity {

	private static final String TAG = EventsActivity.class.getSimpleName();
	private static final String SELECTED_EVENT_KEY = "SELECTED_EVENT";
	
	private SharedPreferences openSchedulePreferences;
	
	private List<Event> upcomingEvents;

	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
	    Log.d( TAG, "onCreate : enter" );

	    super.onCreate( savedInstanceState );
		
		setContentView( R.layout.events );
		
		openSchedulePreferences = getSharedPreferences( "OpenSchedulePreferences", Context.MODE_PRIVATE );
		
		final ListView listView = (ListView) findViewById( R.id.event_comments_menu );
		
		String[] menu_items = getResources().getStringArray( R.array.comments_options_array );
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.menu_list_item, menu_items );
		listView.setAdapter( arrayAdapter );
		
		listView.setOnItemClickListener( new OnItemClickListener() {
		    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
		    	
      			Event event = getApplicationContext().getSelectedEvent();

      			if( null != event ) {
      				
      				try {
      					Calendar now = Calendar.getInstance();

      					Calendar startTime = Calendar.getInstance();
      					startTime.setTime( event.getStartDate() );

      					Log.d( TAG, "now=" + now.get( Calendar.YEAR ) + "-" + ( now.get( Calendar.MONTH ) + 1 ) + "-" + now.get( Calendar.DATE ) + " " + now.get( Calendar.HOUR_OF_DAY ) + ":" + now.get( Calendar.MINUTE ) + ":" + now.get( Calendar.SECOND ) );
      					Log.d( TAG, "startTime=" + startTime.get( Calendar.YEAR ) + "-" + ( startTime.get( Calendar.MONTH ) + 1 ) + "-" + startTime.get( Calendar.DATE ) + " " + startTime.get( Calendar.HOUR_OF_DAY ) + ":" + startTime.get( Calendar.MINUTE ) + ":" + startTime.get( Calendar.SECOND ) );
      					Log.d( TAG, "now after startTime? : " + ( now.after( startTime ) ) );

      					Intent intent = new Intent();
      					switch( position ) {
      					case 0:
      						Log.d( TAG, "add comment" );

      						if( now.after( startTime ) ) {
      							Log.d( TAG, "adding comments are enabled, the event has started" );

      							intent.setClass( view.getContext(), EventCommentFormActivity.class );
      						} else {
      							Log.d( TAG, "adding comments is disabled, the event has not started" );

      							Toast toast = Toast.makeText( view.getContext(), "Event has not started yet, Comments are only available after the event has started.", Toast.LENGTH_LONG );
      							toast.setGravity( Gravity.CENTER, 0, 0 );
      							toast.show();
      						}

      						break;
      					case 1:
      						Log.d( TAG, "view comments" );

      						intent.setClass( view.getContext(), EventCommentsActivity.class );
      						break;
      					case 2:
      						Log.d( TAG, "view notifications" );

      						intent.setClass( view.getContext(), NotificationsActivity.class );
      						break;
      					default:
      						Log.d( TAG, "default option" );
      						break;
      					}

      					startActivity( intent );
    		    	} catch( ActivityNotFoundException e ) {
    		    		Log.w( TAG, "onItemClick : no activity specified" );
    		    	}
				} else {
					Log.d( TAG, "no event selected" );

					//Toast toast = Toast.makeText( view.getContext(), "You have not selected an Event.", Toast.LENGTH_LONG );
  					//toast.setGravity( Gravity.CENTER, 0, 0 );
  					//toast.show();
					
					downloadEvents();
      			}
		    }
		});

		Log.d( TAG, "onCreate : exit" );
	}

	@Override
	protected void onStart() {
	    Log.d( TAG, "onStart : enter" );

	    super.onStart();

	    String eventShortName = openSchedulePreferences.getString( SELECTED_EVENT_KEY, null );
	    Log.d( TAG, "onStart : eventShortName=" + eventShortName );
	    if( null != eventShortName && !"".equals( eventShortName ) ) {
	    	if( null == getApplicationContext().getSelectedEvent() ) {
		    	downloadEvent( eventShortName );
	    	} else {
	    		updateView();
	    	}
	    } else {
	    	if( null == upcomingEvents ) {
				downloadEvents();
			}
	    }
	    
		Log.d( TAG, "onStart : exit" );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
	    Log.d( TAG, "onCreateOptionsMenu : enter" );

	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate( R.menu.events_menu, menu );

	    Log.d( TAG, "onCreateOptionsMenu : exit" );
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {		
		Log.d( TAG, "onOptionsItemSelected : enter" );

		// Handle item selection
	    switch( item.getItemId() ) {
	    case R.id.events_menu_select:
	    	
	    	downloadEvents();
	    	
		    Log.d( TAG, "onOptionsItemSelected : exit, select option selected" );
		    return true;
	    case R.id.events_menu_about:
			Intent intent = new Intent();
			intent.setClass( this, AboutActivity.class );
			startActivity( intent );

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

	private void refreshEvents( List<Event> upcomingEvents ) {	
		this.upcomingEvents = upcomingEvents;
	}
		
	private void downloadEvents() {		
		upcomingEvents = null;
		
		new DownloadEventsTask().execute();
	}

	private void downloadEvent( String shortName ) {		
		
		new DownloadEventTask().execute( shortName );
	}

	//***************************************
    // Private classes
    //***************************************
	private class DownloadEventsTask extends AsyncTask<Void, Void, List<Event>> {
		
		private Exception exception;
		
		@Override
		protected void onPreExecute() {
			showProgressDialog(); 
		}
		
		@Override
		protected List<Event> doInBackground( Void... params ) {
			try {
				return getApplicationContext().getOpenScheduleApi().eventOperations().getPublishedEvents();
			} catch( Exception e ) {
				Log.e( TAG, e.getLocalizedMessage(), e );
				exception = e;
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute( List<Event> result ) {
			dismissProgressDialog();
			processException( exception );
			refreshEvents( result );
			openPublishedEventsDialog();
		}
	}

	private class DownloadEventTask extends AsyncTask<String, Void, Event> {
		
		private Exception exception;
		
		@Override
		protected void onPreExecute() {
			showProgressDialog(); 
		}
		
		@Override
		protected Event doInBackground( String... params ) {
			try {
				return getApplicationContext().getOpenScheduleApi().eventOperations().getEvent( params[ 0 ] );
			} catch( Exception e ) {
				Log.e( TAG, e.getLocalizedMessage(), e );
				exception = e;
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute( Event result ) {
			dismissProgressDialog();
			processException( exception );
			
			if( null != result ) {
				setSelectedEvent( result );
				updateView();
			}
		}
	}

	private void openPublishedEventsDialog() {
		Log.v( TAG, "openPublishedEventsDialog : enter" );
		
		String[] eventsArray = new String[ upcomingEvents.size() ];
		int i = 0;
		for( Event event : upcomingEvents ) {
			Log.v( TAG, "openPublishedEventsDialog : event=" + event.getName() );
			eventsArray[ i ] = event.getName();
			i++;
		}
		
		new AlertDialog.Builder( this )
			.setTitle( R.string.title_published_events_select )
			.setItems( eventsArray,
				new DialogInterface.OnClickListener() {
					public void onClick( DialogInterface dialoginterface, int i ) {
						setSelectedUpcomingEvent( i );
					}
				}).show();

		Log.v( TAG, "openPublishedEventsDialog : exit" );
	}
	
	private void setSelectedUpcomingEvent( int i ) {
		Log.v( TAG, "setSelectedUpcomingEvent : enter" );
		
		new DownloadEventTask().execute( upcomingEvents.get( i ).getShortName() );
		
		Log.v( TAG, "setSelectedUpcomingEvent : exit" );
	}
	
	private void setSelectedEvent( Event event ) {
		Log.v( TAG, "setSelectedEvent : enter" );
		
		if( Log.isLoggable( TAG, Log.VERBOSE ) ) {
			Log.v( TAG, "event=" + event.toString() );
		}
		
		getApplicationContext().setSelectedEvent( event );
		getApplicationContext().setSelectedEventShorName( event.getShortName() );

		SharedPreferences.Editor editor = openSchedulePreferences.edit();
		editor.putString( SELECTED_EVENT_KEY, event.getShortName() );
		editor.commit();

		Log.v( TAG, "setSelectedEvent : exit" );
	}

	private void updateView() {
		Log.v( TAG, "updateView : enter" );

		final TextView eventNameTextView = (TextView) findViewById( R.id.events_event_name );
		final TextView eventDatesTextView = (TextView) findViewById( R.id.events_event_dates );
		
		Event event = getApplicationContext().getSelectedEvent();
		
		if( null != event ) {
			if( null != eventNameTextView ) {
				eventNameTextView.setText( event.getName() );
			}
			
			if( null != eventDatesTextView ) {
				String startDate = new SimpleDateFormat( MainApplication.DATE_FORMAT ).format( event.getStartDate() );
				String endDate = new SimpleDateFormat( MainApplication.DATE_FORMAT ).format( event.getEndDate() );
				eventDatesTextView.setText( startDate + " - " + endDate );
			}
		} else {
			eventNameTextView.setText( "Event is unavailable at this time." );
			eventDatesTextView.setText( "" );
		}
		
		Log.v( TAG, "updateView : exit" );
	}
	
}
