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
package org.openschedule.activities.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openschedule.R;
import org.openschedule.activities.AboutActivity;
import org.openschedule.activities.AbstractOpenScheduleListActivity;
import org.openschedule.api.Block;
import org.openschedule.api.Speaker;
import org.openschedule.controllers.NavigationManager;

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
public class SpeakersActivity extends AbstractOpenScheduleListActivity {

	private static final String TAG = SpeakersActivity.class.getSimpleName();

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
		
	    refreshSpeakers();

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
	    	NavigationManager.startActivity( this, AboutActivity.class );

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
		
		Speaker speaker = getApplicationContext().getSelectedBlock().getSession().getSpeakers().get( position );
		getApplicationContext().setSelectedSpeaker( speaker );
		
		NavigationManager.startActivity( v.getContext(), SpeakerActivity.class );

		Log.d( TAG, "onListItemClick : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshSpeakers() {
		Log.d( TAG, "Refreshing Speakers : enter" );

		Block block = getApplicationContext().getSelectedBlock();
		if( null == block ) {
			Log.d( TAG, "Refreshing Speakers : exit, no block" );

			return;
		}

		List<Map<String,String>> speakers = new ArrayList<Map<String,String>>();
		
		for( Speaker speaker : block.getSession().getSpeakers() ) {
			
			Map<String, String> map = new HashMap<String, String>();
			map.put( "speaker_name", speaker.getName() );
			speakers.add( map );
		}
		
		SimpleAdapter adapter = new SimpleAdapter(
			this,
			speakers,
			R.layout.speaker_list_item,
			new String[] { "speaker_name" },
			new int[] { R.id.speaker_name } 
		);
		
		setListAdapter( adapter );
		Log.d( TAG, "Refreshing Speakers : exit" );
	}
	
}
