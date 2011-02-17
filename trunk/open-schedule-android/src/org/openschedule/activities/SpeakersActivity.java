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
import org.openschedule.domain.Block;
import org.openschedule.domain.Speaker;
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
public class SpeakersActivity extends ListActivity {

	private static final String TAG = "EventActivity";
	private List<Speaker> sessionSpeakers;

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
	public void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();
		refreshSpeakers();

		Log.d( TAG, "onResume : exit" );
	}

	//***************************************
    // ListActivity methods
    //***************************************
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
	    Log.d( TAG, "onListItemClick : enter" );

	    super.onListItemClick( l, v, position, id );
		
		Speaker speaker = sessionSpeakers.get( position );
		SharedDataManager.setCurrentSpeaker( speaker );
		
		NavigationManager.startActivity( v.getContext(), SpeakerActivity.class );

		Log.d( TAG, "onListItemClick : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshSpeakers() {
		Log.d( TAG, "Refreshing Speakers : enter" );

		Block block = SharedDataManager.getCurrentBlock();
		if( null == block ) {
			Log.d( TAG, "Refreshing Speakers : exit, no block" );

			return;
		}

		sessionSpeakers = block.getSession().getSpeakers();

		List<Map<String,String>> speakers = new ArrayList<Map<String,String>>();
		
		for( Speaker speaker : sessionSpeakers ) {
			
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
