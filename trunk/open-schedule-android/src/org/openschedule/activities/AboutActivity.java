/**
 * 
 */
package org.openschedule.activities;

import org.openschedule.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author dmfrey
 *
 */
public class AboutActivity extends Activity {

	private static final String TAG = "AboutActivity";
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );
		
		super.onCreate( savedInstanceState );

	    setContentView( R.layout.about );

		
		Log.d( TAG, "onCreate : exit" );
	}

}
