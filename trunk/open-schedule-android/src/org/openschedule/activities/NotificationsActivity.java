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
import org.openschedule.controllers.NavigationManager;
import org.openschedule.domain.Notification;
import org.openschedule.util.Prefs;
import org.openschedule.util.SharedDataManager;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
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
public class NotificationsActivity extends ListActivity {

	private static final String TAG = "NotificationsActivity";
	private List<Notification> currentNotifications;
	
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
		SharedDataManager.setCurrentNotification( notification );
		
		NavigationManager.startActivity( v.getContext(), NotificationDetailsActivity.class );

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
	    default:
	    	Log.d( TAG, "onOptionsItemSelected : exit, default option selected" );
	        return super.onOptionsItemSelected( item );
	    }
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshNotifications() {
		Log.d( TAG, "Refreshing Notifications : enter" );
		
		String selectedEvent = Prefs.getSelectedEvent( _settings );
		if( null != selectedEvent ) {
			Log.v( TAG, "Refreshing Notifications : get selected event" );
			currentNotifications = EventsController.getNotifications( this, selectedEvent );
		}

		if( null == currentNotifications ) {
			Log.d( TAG, "Refreshing Notifications : exit, no notifications" );

			return;
		}

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
		Log.d( TAG, "Refreshing Notifications : exit" );
	}

}
