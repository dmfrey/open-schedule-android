/**
 * 
 */
package org.openschedule.service;

import java.util.List;

import org.openschedule.controllers.EventsController;
import org.openschedule.domain.Notification;
import org.openschedule.util.Prefs;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author dmfrey
 *
 */
public class NotificationIntentService extends IntentService {

	private static final String TAG = "NotificationIntentService";
	
	private SharedPreferences _settings;

	public NotificationIntentService( String name ) {
		super( name );
		Log.d( TAG, "enter" );
		
		_settings = getSharedPreferences( Prefs.PREFS, Context.MODE_PRIVATE );

		Log.d( TAG, "exit" );
	}

	//***************************************
    // Activity methods
    //***************************************
	/* (non-Javadoc)
	 * @see android.app.IntentService#onHandleIntent(android.content.Intent)
	 */
	@Override
	protected void onHandleIntent( Intent intent ) {
		Log.d( TAG, "onHandleIntent : enter" );

		refreshNotifications();
		
		Log.d( TAG, "onHandleIntent : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshNotifications() {
		Log.d( TAG, "refreshNotifications : enter" );

		String selectedEvent = Prefs.getSelectedEvent( _settings );
		if( null != selectedEvent ) {
			List<Notification> notifications = EventsController.getNotifications( getBaseContext(), selectedEvent );
		}
		
		Log.d( TAG, "refreshNotifications : exit" );
	}
	
}
