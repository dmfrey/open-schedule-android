/**
 * 
 */
package org.openschedule.activities;

import org.openschedule.R;
import org.openschedule.domain.Notification;
import org.openschedule.util.SharedDataManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author dmfrey
 *
 */
public class NotificationDetailsActivity extends Activity {

	private static final String TAG = "VenueDetailsActivity";
	
	//***************************************
	// Activity methods
	//***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );

		super.onCreate( savedInstanceState );
		
		setContentView( R.layout.notification_details );
		
		Log.d( TAG, "onCreate : exit" );
	}
	
	@Override
	public void onStart() {
		Log.d( TAG, "onStart : enter" );

		super.onStart();

		refreshNotificationDetails();

		Log.d( TAG, "onStart : exit" );
	}

	//***************************************
	// Private methods
	//***************************************
	private void refreshNotificationDetails() {
		Log.d( TAG, "Refreshing Notification Details : enter" );

		final TextView notificationTitle = (TextView) findViewById( R.id.notification_title );
		final TextView notificationMessage = (TextView) findViewById( R.id.notification_message );
		
		Notification notification = SharedDataManager.getCurrentNotification();
		Log.d( TAG, "Refreshing Notification Details : notification=" + notification.getTitle() + " - " + notification.getMessage() );
		
		if( null == notification ) {
			Log.d( TAG, "Refreshing Notification Details : exit, no notification" );
			return;
		}
		
		notificationTitle.setText( notification.getTitle() );
		notificationMessage.setText( notification.getMessage() );

		Log.d( TAG, "Refreshing Notification Details : exit" );
	}

}
