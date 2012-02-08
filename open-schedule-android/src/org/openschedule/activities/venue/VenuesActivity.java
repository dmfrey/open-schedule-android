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
package org.openschedule.activities.venue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openschedule.R;
import org.openschedule.activities.AboutActivity;
import org.openschedule.activities.AbstractOpenScheduleListActivity;
import org.openschedule.api.Event;
import org.openschedule.api.Venue;

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
 * @author dmfrey
 *
 */
public class VenuesActivity extends AbstractOpenScheduleListActivity {

	private static final String TAG = VenuesActivity.class.getSimpleName();
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
	}
	
	@Override
	public void onStart() {
		Log.d( TAG, "onStart : enter" );

		super.onStart();
		
		refreshVenues();

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	public void onResume() {
		Log.d( TAG, "onResume : enter" );

		super.onStart();
		
		refreshVenues();

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
		
		Venue venue = getApplicationContext().getSelectedEvent().getVenues().get( position );
		getApplicationContext().setSelectedVenue( venue );
		
		Intent intent = new Intent();
		intent.setClass( this, VenueDetailsActivity.class );
		startActivity( intent );

		Log.d( TAG, "onListItemClick : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshVenues() {
		Log.d( TAG, "Refreshing Venues : enter" );
		
		Event event = getApplicationContext().getSelectedEvent();
		
		if( null == event ) {
			Log.d( TAG, "Refreshing Venues : exit, no event" );
			return;
		}
		
		List<Map<String,String>> venues = new ArrayList<Map<String,String>>();

		// TODO: Is there w way to populate the table from an Event instead of a Map?
		for( Venue venue : event.getVenues() ) {
			Map<String, String> map = new HashMap<String, String>();
			map.put( "hotel_name", venue.getName() );
			map.put( "hotel_phone", venue.getPhone() );
			venues.add( map );
		}		

		SimpleAdapter adapter = new SimpleAdapter(
				this,
				venues,
				R.layout.venues_list_item,
				new String[] { "hotel_name", "hotel_phone" },
				new int[] { R.id.hotel_name, R.id.hotel_phone } );

		setListAdapter( adapter );
		
		Log.d( TAG, "Refreshing Venues : exit" );
	}	

}
