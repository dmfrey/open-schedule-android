package org.openschedule.activities;


import android.content.Intent;
import android.os.Bundle;

/**
 * @author dmfrey
 */
public class MainActivity extends AbstractOpenScheduleActivity {
	
	@SuppressWarnings( "unused" )
	private static final String TAG = MainActivity.class.getSimpleName();
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		
		Intent intent = new Intent( this, MainTabWidget.class );

		startActivity( intent );
		finish();
	}
	
}
