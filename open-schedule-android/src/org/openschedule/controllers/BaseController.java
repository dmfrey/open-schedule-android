/**
 * 
 */
package org.openschedule.controllers;

import org.openschedule.social.ScheduleOperations;
import org.openschedule.util.Prefs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author dmfrey
 *
 */
public class BaseController {

	private static final String TAG = "BaseController";
	private static ProgressDialog progressDialog;

	//***************************************
    // Protected methods
    //***************************************
	protected static ScheduleOperations getScheduleOperations( Context context ) { 
		return Prefs.getScheduleOperations( getSharedPreferences( context ) );
	}

	protected static void displayNetworkError( Context context ) {
		Toast toast = Toast.makeText( context, "A problem occurred with the network connection while attempting to communicate with Greenhouse.", Toast.LENGTH_LONG );
		toast.setGravity( Gravity.CENTER, 0, 0 );
		toast.show();
	}
	
	protected static void showProgressDialog( Context context ) {
		Log.d( TAG, "showing progress dialog" );
		BaseController.progressDialog = ProgressDialog.show( context, "",  "Loading. Please wait...", true );
	}
	
	protected static void dismissProgressDialog() {
		Log.d( TAG, "dismissing progress dialog" );
		if( BaseController.progressDialog != null ) {
			BaseController.progressDialog.dismiss();
		}
	}

	//***************************************
    // Private methods
    //***************************************
	private static SharedPreferences getSharedPreferences( Context context ) {
		return context.getSharedPreferences( Prefs.PREFS, Context.MODE_PRIVATE );
	}	

}
