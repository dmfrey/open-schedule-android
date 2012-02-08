/**
 * 
 */
package org.openschedule.activities.event;

import org.openschedule.R;
import org.openschedule.activities.AboutActivity;
import org.openschedule.activities.AbstractOpenScheduleActivity;
import org.openschedule.api.Notification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * @author dmfrey
 *
 */
public class NotificationDetailsActivity extends AbstractOpenScheduleActivity {

	private static final String TAG = NotificationDetailsActivity.class.getSimpleName();
	
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
	private void refreshNotificationDetails() {
		Log.d( TAG, "Refreshing Notification Details : enter" );

		final TextView notificationTitle = (TextView) findViewById( R.id.notification_title );
		final TextView notificationMessage = (TextView) findViewById( R.id.notification_message );
		
		Notification notification = getApplicationContext().getSelectedNotification();
		Log.d( TAG, "Refreshing Notification Details : notification=" + notification.getTitle() + " - " + notification.getMessage() );
		
		notificationTitle.setText( notification.getTitle() );
		notificationMessage.setText( notification.getMessage() );

		Log.d( TAG, "Refreshing Notification Details : exit" );
	}

}
