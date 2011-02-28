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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openschedule.R;
import org.openschedule.controllers.EventsController;
import org.openschedule.controllers.NavigationManager;
import org.openschedule.domain.Day;
import org.openschedule.domain.Event;
import org.openschedule.util.Prefs;
import org.openschedule.util.SharedDataManager;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author dmfrey
 *
 */
public class EventActivity extends ListActivity {

	private static final String TAG = "EventActivity";
	private List<Day> eventDays;

	private SharedPreferences _settings;

	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
	    Log.d( TAG, "onCreate : enter" );

	    super.onCreate( savedInstanceState );

		_settings = getSharedPreferences( Prefs.PREFS, Context.MODE_PRIVATE );

	    Log.d( TAG, "onCreate : exit" );
	}

	@Override
	public void onStart() {
	    Log.d( TAG, "onStart : enter" );

	    super.onStart();
		refreshEvent();

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	public void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();
		refreshEvent();

		Log.d( TAG, "onResume : exit" );
	}

	//***************************************
    // ListActivity methods
    //***************************************
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
	    Log.d( TAG, "onListItemClick : enter" );

	    super.onListItemClick( l, v, position, id );
		
		Day day = eventDays.get( position );
		SharedDataManager.setCurrentDay( day );
		
		NavigationManager.startActivity( v.getContext(), DayActivity.class );

		Log.d( TAG, "onListItemClick : exit" );
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

		Event event = SharedDataManager.getCurrentEvent();
		if( null == event ) {
			Log.v( TAG, "Refreshing Event : event is null, check for event in preferences" );

			String selectedEvent = Prefs.getSelectedEvent( _settings );
			if( null != selectedEvent ) {
				Log.v( TAG, "Refreshing Event : get selected event" );
				event = EventsController.getEvent( this, selectedEvent );
			}
		}

		if( null == event ) {
			Log.d( TAG, "Refreshing Event : exit, no event" );

			return;
		}

		eventDays = event.getDays();

		List<Map<String,String>> days = new ArrayList<Map<String,String>>();
		
		for( Day day : event.getDays() ) {
			Log.d( TAG, "Refreshing Event : date=" + day.getDate() );
			
			Map<String, String> map = new HashMap<String, String>();
			map.put( "date", new SimpleDateFormat( Prefs.DATE_FORMAT ).format( day.getDate() ) );
			days.add( map );
		}
		
		SimpleAdapter adapter = new SimpleAdapter(
			this,
			days,
			R.layout.day_list_item,
			new String[] { "date" },
			new int[] { R.id.event_date } 
		);
		
		setListAdapter( adapter );
		Log.d( TAG, "Refreshing Event : exit" );
	}
	
}
