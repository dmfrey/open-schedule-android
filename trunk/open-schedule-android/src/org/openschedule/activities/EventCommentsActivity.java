/**
 * 
 */
package org.openschedule.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openschedule.R;
import org.openschedule.controllers.EventsController;
import org.openschedule.domain.Comment;
import org.openschedule.util.Prefs;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

/**
 * @author dmfrey
 *
 */
public class EventCommentsActivity extends ListActivity {

	private static final String TAG = "EventCommentsActivity";
	private List<Comment> currentComments;
	
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
		refreshComments();

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	public void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onStart();
		refreshComments();

		Log.d( TAG, "onResume : exit" );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
	    Log.d( TAG, "onCreateOptionsMenu : enter" );

	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate( R.menu.comments_menu, menu );

	    Log.d( TAG, "onCreateOptionsMenu : exit" );
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {		
	    Log.d( TAG, "onOptionsItemSelected : enter" );

	    // Handle item selection
	    switch( item.getItemId() ) {
	    case R.id.comments_menu_refresh:
	    	
	    	refreshComments();

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
	private void refreshComments() {
		Log.d( TAG, "Refreshing Comments : enter" );

		String selectedEvent = Prefs.getSelectedEvent( _settings );
		if( null != selectedEvent ) {
			Log.v( TAG, "Refreshing Comments : get selected event" );
			currentComments = EventsController.getEventComments( this, selectedEvent );
		}

		if( null == currentComments ) {
			Log.d( TAG, "Refreshing Comments : exit, no comments" );

			return;
		}

		List<Map<String,String>> comments = new ArrayList<Map<String,String>>();
		
		for( Comment comment : currentComments ) {
			
			Map<String, String> map = new HashMap<String, String>();
			map.put( "name", comment.getName() );
			map.put( "comment", comment.getComment() );
			comments.add( map );
		}
		
		if( comments.isEmpty() ) {
			Map<String, String> map = new HashMap<String, String>();
			map.put( "name", "No Comments have been entered." );
			map.put( "comment", "" );
			comments.add( map );
		}
		
		SimpleAdapter adapter = new SimpleAdapter(
			this,
			comments,
			R.layout.comment_list_item,
			new String[] { "name", "comment" },
			new int[] { R.id.comment_name, R.id.comment_comment } 
		);
		
		setListAdapter( adapter );
		Log.d( TAG, "Refreshing Comments : exit" );
	}

}
