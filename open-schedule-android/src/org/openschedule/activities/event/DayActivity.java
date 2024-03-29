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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openschedule.R;
import org.openschedule.activities.AboutActivity;
import org.openschedule.activities.AbstractOpenScheduleListActivity;
import org.openschedule.api.Day;
import org.openschedule.api.Schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author Daniel Frey
 *
 */
public class DayActivity extends AbstractOpenScheduleListActivity {

	private static final String TAG = EventActivity.class.getSimpleName();
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );
		
		super.onCreate( savedInstanceState );

		Log.d( TAG, "onCreate : exit" );
	}
	
	@Override
	public void onStart() {
		Log.d( TAG, "onStart : enter" );

		super.onStart();
		
		refreshSchedules();

		Log.d( TAG, "onStart : exit" );
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
    // ListActivity methods
    //***************************************
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
		Log.d( TAG, "onListItemClick : enter" );
		
		super.onListItemClick( l, v, position, id );
		
		Schedule schedule = getApplicationContext().getSelectedDay().getSchedules().get( position );
		getApplicationContext().setSelectedSchedule( schedule );
		
		Intent intent = new Intent();
		intent.setClass( v.getContext(), ScheduleActivity.class );
		startActivity( intent );

		Log.d( TAG, "onListItemClick : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshSchedules() {
		Log.d( TAG, "Refreshing Schedules : enter" );
		
		Day day = getApplicationContext().getSelectedDay();
		
		if( null == day ) {
			Log.d( TAG, "Refreshing Schedules : exit, no day" );
			return;
		}
		
		List<Map<String,String>> schedules = new ArrayList<Map<String,String>>();
		
		// TODO: Is there w way to populate the table from an Event instead of a Map?
		for( Schedule schedule : day.getSchedules() ) {
			Log.d( TAG, "Refreshing Schedules : schedule iteration" );

			Map<String, String> map = new HashMap<String, String>();
			map.put( "track_name", schedule.getTrack().getName() );
			
			if( null != schedule.getTrack().getSponsor() ) {
				Log.d( TAG, "Refreshing Schedules : sponsor is not null" );

				map.put( "track_sponsor", schedule.getTrack().getSponsor().getCompanyName() );
			} else {
				Log.d( TAG, "Refreshing Schedules : sponsor is null" );

				map.put( "track_sponsor", "" );
			}

			if( null != schedule.getTrack().getRoom() ) {
				Log.d( TAG, "Refreshing Schedules : room is not null" );

				map.put( "track_room", schedule.getTrack().getRoom().getName() );
			} else {
				Log.d( TAG, "Refreshing Schedules : room is null" );

				map.put( "track_room", "" );
			}
			schedules.add( map );
		}		
		
		SimpleAdapter adapter = new SimpleAdapter(
			this,
			schedules,
			R.layout.schedule_list_item,
			new String[] { "track_name", "track_sponsor", "track_room" },
			new int[] { R.id.track_name, R.id.track_sponsor, R.id.track_room } 
		);
		
		setListAdapter( adapter );

		Log.d( TAG, "Refreshing Schedules : exit" );
	}	

}
