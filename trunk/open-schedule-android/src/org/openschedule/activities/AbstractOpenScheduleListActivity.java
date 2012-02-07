package org.openschedule.activities;

import org.openschedule.MainApplication;
import org.springframework.web.client.ResourceAccessException;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author dmfrey
 */
public abstract class AbstractOpenScheduleListActivity extends ListActivity implements OpenScheduleActivity {
	
	protected static final String TAG = AbstractOpenScheduleListActivity.class.getSimpleName();
	
	private ProgressDialog progressDialog;
	
	
	//***************************************
    // GreenhouseActivity methods
    //***************************************
	public MainApplication getApplicationContext() {
		return (MainApplication) super.getApplicationContext();
	}
	
	public void showProgressDialog() {
		showProgressDialog( "Loading. Please wait..." );
	}
	
	public void showProgressDialog( String message) {
		if( progressDialog == null ) {
			progressDialog = new ProgressDialog( this );
			progressDialog.setIndeterminate( true );
		}
		
		progressDialog.setMessage( message );
		progressDialog.show();
	}
	
	public void dismissProgressDialog() {
		if( progressDialog != null ) {
			progressDialog.dismiss();
		}
	}

	
	//***************************************
    // Protected methods
    //***************************************	
	protected void processException(Exception e) {
		if( null != e ) {
			if( e instanceof ResourceAccessException ) {
				displayNetworkError();
			}
		}
	}
	
	protected void displayNetworkError() {
		Toast toast = Toast.makeText( this, "A problem occurred with the network connection while attempting to communicate with Greenhouse.", Toast.LENGTH_LONG );
		toast.setGravity( Gravity.CENTER, 0, 0 );
		toast.show();
	}
	
}
