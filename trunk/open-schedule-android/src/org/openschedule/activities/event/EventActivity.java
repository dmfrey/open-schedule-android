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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openschedule.MainApplication;
import org.openschedule.R;
import org.openschedule.activities.AbstractOpenScheduleListActivity;
import org.openschedule.api.Day;
import org.openschedule.api.Event;

import android.content.Intent;
import android.os.AsyncTask;
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
public class EventActivity extends AbstractOpenScheduleListActivity {

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

	    updateView();
	    
		Log.d( TAG, "onStart : exit" );
	}

	@Override
	public void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();

	    updateView();
	    
		Log.d( TAG, "onResume : exit" );
	}

	//***************************************
    // ListActivity methods
    //***************************************
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
	    Log.d( TAG, "onListItemClick : enter" );

	    super.onListItemClick( l, v, position, id );
		
		Day day = getApplicationContext().getSelectedEvent().getDays().get( position );
		getApplicationContext().setSelectedDay( day );
		
		Intent intent = new Intent();
		intent.setClass( v.getContext(), DayActivity.class );
		startActivity( intent );
		
		Log.d( TAG, "onListItemClick : exit" );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
	    Log.d( TAG, "onCreateOptionsMenu : enter" );

	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate( R.menu.schedule_menu, menu );

	    Log.d( TAG, "onCreateOptionsMenu : exit" );
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {		
	    Log.d( TAG, "onOptionsItemSelected : enter" );

	    // Handle item selection
	    switch( item.getItemId() ) {
	    case R.id.schedule_menu_refresh:
	    	getApplicationContext().setSelectedEvent( null );
	    	
	    	refreshEvent();

	    	Log.d( TAG, "onOptionsItemSelected : exit, refresh option selected" );
	    	return true;
	    case R.id.schedule_menu_about:
			Intent intent = new Intent();
			intent.setClass( this, DayActivity.class );
			startActivity( intent );

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
	private void refreshEvent() {
		Log.d( TAG, "Refreshing Event : enter" );

		String eventShortName = getApplicationContext().getSelectedEventShorName();
		new RefreshEventTask().execute( eventShortName );

		Log.d( TAG, "Refreshing Event : exit" );
	}
	
	private class RefreshEventTask extends AsyncTask<String, Void, Event> {
		
		private Exception exception;
		
		@Override
		protected void onPreExecute() {
			showProgressDialog(); 
		}
		
		@Override
		protected Event doInBackground( String... params ) {
			try {
				return getApplicationContext().getOpenScheduleApi().eventOperations().getEvent( params[ 0 ] );
			} catch( Exception e ) {
				Log.e( TAG, e.getLocalizedMessage(), e );
				exception = e;
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute( Event result ) {
			dismissProgressDialog();
			processException( exception );
			
			if( null != result ) {
				setSelectedEvent( result );
				updateView();
			}
		}
	}

	private void setSelectedEvent( Event event ) {
		Log.v( TAG, "setSelectedEvent : enter" );
		
		if( Log.isLoggable( TAG, Log.VERBOSE ) ) {
			Log.v( TAG, "event=" + event.toString() );
		}
		
		getApplicationContext().setSelectedEvent( event );

		Log.v( TAG, "setSelectedEvent : exit" );
	}

	private void updateView() {
		Log.v( TAG, "updateView : enter" );

		List<Map<String,String>> days = new ArrayList<Map<String,String>>();
		
		Event event = getApplicationContext().getSelectedEvent();
		for( Day day : event.getDays() ) {
			Log.d( TAG, "Refreshing Event : date=" + day.getDate() );
			
			Map<String, String> map = new HashMap<String, String>();
			map.put( "date", new SimpleDateFormat( MainApplication.DATE_FORMAT ).format( day.getDate() ) );
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
		
		Log.v( TAG, "updateView : exit" );
	}
	
}
