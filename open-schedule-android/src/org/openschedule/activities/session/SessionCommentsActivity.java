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
import org.openschedule.api.Comment;
import org.openschedule.controllers.NavigationManager;

import android.os.AsyncTask;
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
public class SessionCommentsActivity extends AbstractOpenScheduleListActivity {

	private static final String TAG = SessionCommentsActivity.class.getSimpleName();
	private List<Comment> currentComments;
	
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

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	public void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();
	    
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
	    case R.id.comments_menu_about:
	    	NavigationManager.startActivity( this, AboutActivity.class );

	    	Log.d( TAG, "onOptionsItemSelected : exit, about option selected" );
	    	return true;
	    default:
	    	Log.d( TAG, "onOptionsItemSelected : exit, default option selected" );
	        return super.onOptionsItemSelected( item );
	    }
	}

	//***************************************
    // Private methods
    //***************************************
	private class DownloadBlockCommentsTask extends AsyncTask<Object[], Void, List<Comment>> {
		
		private Exception exception;
		
		@Override
		protected void onPreExecute() {
			showProgressDialog(); 
		}
		
		@Override
		protected List<Comment> doInBackground( Object[]... params ) {
			try {
				return getApplicationContext().getOpenScheduleApi().sessionOperations().getBlockComments( (String) params[ 0 ][ 0 ], (Integer) params[ 0 ][ 1 ], (Integer) params[ 0 ][ 2 ], (Integer) params[ 0 ][ 3 ] );
			} catch( Exception e ) {
				Log.e( TAG, e.getLocalizedMessage(), e );
				exception = e;
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute( List<Comment> result ) {
			dismissProgressDialog();
			processException( exception );
			setCurrentComments( result );
		}
	}

	private void refreshComments() {
		Log.d( TAG, "refreshComments : enter" );

		String selectedEvent = getApplicationContext().getSelectedEventShorName();
		
		Object[] params = new Object[] { selectedEvent, getApplicationContext().getSelectedDay().getId(), getApplicationContext().getSelectedSchedule().getId(), getApplicationContext().getSelectedBlock().getId() };
		new DownloadBlockCommentsTask().execute( params );
		
		Log.d( TAG, "refreshComments : exit" );
	}

	private void setCurrentComments( List<Comment> comments ) {
		Log.d( TAG, "setCurrentComments : enter" );

		this.currentComments = comments;
		updateView();

		Log.d( TAG, "setCurrentComments : enter" );
	}
	
	private void updateView() {
		Log.d( TAG, "updateView : enter" );

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
		Log.d( TAG, "updateView : exit" );
	}

}
