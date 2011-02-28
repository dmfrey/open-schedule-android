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
import java.util.GregorianCalendar;
import java.util.List;

import org.openschedule.R;
import org.openschedule.controllers.EventsController;
import org.openschedule.controllers.NavigationManager;
import org.openschedule.domain.Event;
import org.openschedule.util.Prefs;
import org.openschedule.util.SharedDataManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
public class InfoActivity extends Activity {

	private static final String TAG = "InfoActivity";
	private List<Event> events;

	private SharedPreferences _settings;

	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
	    Log.d( TAG, "onCreate : enter" );

	    super.onCreate( savedInstanceState );
		
		_settings = getSharedPreferences( Prefs.PREFS, Context.MODE_PRIVATE );

		setContentView( R.layout.info );

		final ListView listView = (ListView) findViewById( R.id.event_comments_menu );
		
		String[] menu_items = getResources().getStringArray( R.array.comments_options_array );
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.menu_list_item, menu_items );
		listView.setAdapter(arrayAdapter);
		
		listView.setOnItemClickListener( new OnItemClickListener() {
		    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
		    	
      			Event event = SharedDataManager.getCurrentEvent();

      			Calendar now = new GregorianCalendar();
      			
  				Calendar startTime = new GregorianCalendar();
  				startTime.setTime( event.getStartDate() );

  				switch( position ) {
		    		case 0:
		    			Log.d( TAG, "add comment" );

		    			if( now.before( startTime ) ) {
		      				Toast toast = Toast.makeText( view.getContext(), "Event has not started yet, Comments are only available after the event has started.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
		    			} else {
		    				NavigationManager.startActivity( view.getContext(), EventCommentFormActivity.class );
		    			}
		    			
		    			break;
			      	case 1:
			      		Log.d( TAG, "view comment" );

		    			NavigationManager.startActivity( view.getContext(), EventCommentsActivity.class );
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
		
		refreshEvent();

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
	    Log.d( TAG, "onCreateOptionsMenu : enter" );

	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate( R.menu.info_menu, menu );

	    Log.d( TAG, "onCreateOptionsMenu : exit" );
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {		
		Log.d( TAG, "onOptionsItemSelected : enter" );

		// Handle item selection
	    switch( item.getItemId() ) {
	    case R.id.events_menu_select:
	    	openPublishedEventsDialog();
	    	
		    Log.d( TAG, "onOptionsItemSelected : exit, select option selected" );
		    return true;
	    case R.id.events_menu_refresh:
	    	SharedDataManager.setCurrentEvent( null );
	    	
	    	refreshEvent();

	    	Log.d( TAG, "onOptionsItemSelected : exit, refresh option selected" );
	    	return true;
	    default:
		    Log.d( TAG, "onOptionsItemSelected : exit, default option selected" );
	        return super.onOptionsItemSelected( item );
	    }
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshEvent() {
		Log.d( TAG, "Refreshing Event : enter" );

		String selectedEvent = Prefs.getSelectedEvent( _settings );
		if( null == selectedEvent ) {
			Log.v( TAG, "Refreshing Event : no selected event stored in preferences" );
			
			openPublishedEventsDialog();
		} else {
			Log.v( TAG, "Refreshing Event : selected event stored in preferences" );
			
			if( null == SharedDataManager.getCurrentEvent() ) {
				Log.v( TAG, "Refreshing Event : no currentEvent stored in SharedDataManager, get the latest." );

				Event event = EventsController.getEvent( this, selectedEvent );
				SharedDataManager.setCurrentEvent( event );
			}
			
			updateView();
		}
		
		Log.d( TAG, "Refreshing Event : exit" );
	}

	private void openPublishedEventsDialog() {
		Log.v( TAG, "openPublishedEventsDialog : enter" );
		
		events = EventsController.getPublishedEvents( this );
		if( null == events ) {
			Log.d( TAG, "Refreshing Events : exit, no events" );

			return;
		}

		SharedDataManager.setCurrentEvents( events );

		String[] eventsArray = new String[ events.size()];
		int i = 0;
		for( Event event : events ) {
			Log.v( TAG, "openPublishedEventsDialog : event=" + event.getName() );
			eventsArray[ i ] = event.getName();
			i++;
		}
		
		new AlertDialog.Builder( this )
			.setTitle( R.string.title_published_events_select )
			.setItems( eventsArray,
				new DialogInterface.OnClickListener() {
					public void onClick( DialogInterface dialoginterface, int i ) {
						setSelectedEvent( i );
					}
				}).show();

		Log.v( TAG, "openPublishedEventsDialog : exit" );
	}
	
	private void setSelectedEvent( int i ) {
		Log.v( TAG, "setSelectedEvent : enter" );
		
		Event event = EventsController.getEvent( this, events.get( i ).getShortName() );
		SharedDataManager.setCurrentEvent( event );
		Prefs.setSelectedEvent( _settings, event.getShortName() );

		updateView();
		
		Log.v( TAG, "setSelectedEvent : exit" );
	}
	
	private void updateView() {
		Log.v( TAG, "updateView : enter" );

		final TextView eventNameTextView = (TextView) findViewById( R.id.event_name );
		final TextView eventDatesTextView = (TextView) findViewById( R.id.event_dates );
		
		Event event = SharedDataManager.getCurrentEvent();
		
		eventNameTextView.setText( event.getName() );
		
		String startDate = new SimpleDateFormat( Prefs.DATE_FORMAT ).format( event.getStartDate() );
		String endDate = new SimpleDateFormat( Prefs.DATE_FORMAT ).format( event.getEndDate() );
		eventDatesTextView.setText( startDate + " - " + endDate );

		Log.v( TAG, "updateView : exit" );
	}
	
}