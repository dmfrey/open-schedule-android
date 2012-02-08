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
package org.openschedule.activities;

import org.openschedule.MainApplication;
import org.springframework.web.client.ResourceAccessException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author Daniel Frey
 */
public abstract class AbstractOpenScheduleActivity extends Activity implements OpenScheduleActivity {
	
	protected static final String TAG = AbstractOpenScheduleActivity.class.getSimpleName();
	
	private ProgressDialog progressDialog;
	
	
	//***************************************
    // OpenScheduleActivity methods
    //***************************************
	
	/* (non-Javadoc)
	 * @see android.content.ContextWrapper#getApplicationContext()
	 */
	public MainApplication getApplicationContext() {
		return (MainApplication) super.getApplicationContext();
	}
	
	/* (non-Javadoc)
	 * @see org.openschedule.OpenScheduleActivity#showProgressDialog()
	 */
	public void showProgressDialog() {
		showProgressDialog( "Loading. Please wait..." );
	}
	
	/* (non-Javadoc)
	 * @see org.openschedule.OpenScheduleActivity#showProgressDialog(java.lang.String)
	 */
	public void showProgressDialog( String message ) {
		if( progressDialog == null ) {
			progressDialog = new ProgressDialog( this );
			progressDialog.setIndeterminate( true );
		}
		
		progressDialog.setMessage( message );
		progressDialog.show();
	}
	
	/* (non-Javadoc)
	 * @see org.openschedule.OpenScheduleActivity#dismissProgressDialog()
	 */
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
