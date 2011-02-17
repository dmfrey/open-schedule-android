/**
 * 
 */
package org.openschedule.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author dmfrey
 *
 */
public class FrontDoorActivity extends Activity {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		Intent intent = new Intent( this, MainTabWidget.class );		
		startActivity(intent);
		finish();
	}

}
