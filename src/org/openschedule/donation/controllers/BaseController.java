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
package org.openschedule.donation.controllers;

import org.openschedule.donation.social.ScheduleOperations;
import org.openschedule.donation.util.Prefs;

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
