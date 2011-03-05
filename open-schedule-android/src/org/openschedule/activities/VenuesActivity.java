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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openschedule.R;
import org.openschedule.controllers.NavigationManager;
import org.openschedule.domain.Event;
import org.openschedule.domain.Venue;
import org.openschedule.util.SharedDataManager;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author dmfrey
 *
 */
public class VenuesActivity extends ListActivity {

	private static final String TAG = "VenuesActivity";
	private List<Venue> eventVenues;
	
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

	//***************************************
    // ListActivity methods
    //***************************************
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
		Log.d( TAG, "onListItemClick : enter" );

		super.onListItemClick( l, v, position, id );
		
		Venue venue = eventVenues.get( position );
		SharedDataManager.setCurrentVenue( venue );
		
		NavigationManager.startActivity( v.getContext(), VenueDetailsActivity.class );

		Log.d( TAG, "onListItemClick : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshVenues() {
		Log.d( TAG, "Refreshing Venues : enter" );
		
		Event event = SharedDataManager.getCurrentEvent();
		
		if( null == event ) {
			Log.d( TAG, "Refreshing Venues : exit, no event" );
			return;
		}
		
		eventVenues = event.getVenues();
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
