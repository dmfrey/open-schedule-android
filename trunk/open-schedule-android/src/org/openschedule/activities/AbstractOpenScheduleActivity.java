/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openschedule.activities;

import org.openschedule.MainApplication;
import org.springframework.web.client.ResourceAccessException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author Roy Clarkson
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
