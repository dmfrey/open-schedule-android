/**
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
import org.openschedule.api.Notification;

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
 * @author dmfrey
 *
 */
public class NotificationsActivity extends AbstractOpenScheduleListActivity {

	private static final String TAG = NotificationsActivity.class.getSimpleName();
	
	private List<Notification> currentNotifications;
	
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
		refreshNotifications();

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	public void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();
		refreshNotifications();

		Log.d( TAG, "onResume : exit" );
	}

	//***************************************
    // ListActivity methods
    //***************************************
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
		Log.d( TAG, "onListItemClick : enter" );

		super.onListItemClick( l, v, position, id );
		
		Notification notification = currentNotifications.get( position );
		getApplicationContext().setSelectedNotification( notification );
		
		Intent intent = new Intent();
		intent.setClass( this, NotificationDetailsActivity.class );
		startActivity( intent );

		Log.d( TAG, "onListItemClick : exit" );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
	    Log.d( TAG, "onCreateOptionsMenu : enter" );

	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate( R.menu.notifications_menu, menu );

	    Log.d( TAG, "onCreateOptionsMenu : exit" );
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {		
	    Log.d( TAG, "onOptionsItemSelected : enter" );

	    // Handle item selection
	    switch( item.getItemId() ) {
	    case R.id.notifications_menu_refresh:
	    	
	    	refreshNotifications();

	    	Log.d( TAG, "onOptionsItemSelected : exit, refresh option selected" );
	    	return true;
	    case R.id.notifications_menu_about:
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
    // Private methods
    //***************************************
	private class DownloadNotificationsTask extends AsyncTask<String, Void, List<Notification>> {
		
		private Exception exception;
		
		@Override
		protected void onPreExecute() {
			showProgressDialog(); 
		}
		
		@Override
		protected List<Notification> doInBackground( String... params ) {
			try {
				return getApplicationContext().getOpenScheduleApi().eventOperations().getNotifications( params[ 0 ] );
			} catch( Exception e ) {
				Log.e( TAG, e.getLocalizedMessage(), e );
				exception = e;
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute( List<Notification> result ) {
			dismissProgressDialog();
			processException( exception );
			setCurrentNotifications( result );
		}
	}

	private void refreshNotifications() {
		Log.d( TAG, "refreshNotifications : enter" );
		
		String eventShortName = getApplicationContext().getSelectedEventShorName();
		new DownloadNotificationsTask().execute( eventShortName );

		Log.d( TAG, "refreshNotifications : exit" );
	}

	private void setCurrentNotifications( List<Notification> notifications ) {
		Log.v( TAG, "setCurrentNotifications : enter" );
		
		currentNotifications = notifications;
		updateView();

		Log.v( TAG, "setCurrentNotifications : exit" );
	}

	private void updateView() {
		Log.v( TAG, "updateView : enter" );

		List<Map<String,String>> notifications = new ArrayList<Map<String,String>>();
		
		for( Notification notification : currentNotifications ) {
			Map<String, String> map = new HashMap<String, String>();
			map.put( "title", notification.getTitle() );
			notifications.add( map );
		}
		
		if( notifications.isEmpty() ) {
			Map<String, String> map = new HashMap<String, String>();
			map.put( "title", "There are no Notifications." );
			notifications.add( map );
		}
		
		SimpleAdapter adapter = new SimpleAdapter(
			this,
			notifications,
			R.layout.notification_list_item,
			new String[] { "title", "message" },
			new int[] { R.id.notification_title } 
		);
		
		setListAdapter( adapter );
		
		Log.d( TAG, "updateView : exit" );
	}

}
