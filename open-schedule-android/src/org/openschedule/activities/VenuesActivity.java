/**
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
