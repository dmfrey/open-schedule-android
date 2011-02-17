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
import org.openschedule.domain.Schedule;
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
public class ScheduleActivity extends ListActivity {

	private static final String TAG = "ScheduleActivity";
	private List<Block> scheduleBlocks;
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
	}
	
	@Override
	public void onStart() {
		super.onStart();
		refreshBlocks();
	}

	@Override
	public void onResume() {
		super.onResume();
		refreshBlocks();
	}

	//***************************************
    // ListActivity methods
    //***************************************
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
		super.onListItemClick( l, v, position, id );
		
		Block block = scheduleBlocks.get( position );
		SharedDataManager.setCurrentBlock( block );
		
		NavigationManager.startActivity( v.getContext(), SessionActivity.class );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshBlocks() {
		Log.d( TAG, "Refreshing Blocks : enter" );
		
		Schedule schedule = SharedDataManager.getCurrentSchedule();
		
		if( null == schedule ) {
			Log.d( TAG, "Refreshing Blocks : exit, no day" );
			return;
		}
		
		scheduleBlocks = schedule.getBlocks();
		List<Map<String,String>> blocks = new ArrayList<Map<String,String>>();
		
		// TODO: Is there w way to populate the table from an Event instead of a Map?
		for( Block block : schedule.getBlocks() ) {
			if( null != block.getLabel() && null != block.getSession() ) {
				Map<String, String> map = new HashMap<String, String>();
				map.put( "block_label", block.getLabel().getName() );
				map.put( "block_name", block.getSession().getName() );
				blocks.add( map );
			}
		}		
		
		SimpleAdapter adapter = new SimpleAdapter(
				this,
				blocks,
				R.layout.block_list_item,
				new String[] { "block_label", "block_name" },
				new int[] { R.id.block_label, R.id.block_name } );
		
		setListAdapter( adapter );

		Log.d( TAG, "Refreshing Blocks : exit" );
	}	

}
