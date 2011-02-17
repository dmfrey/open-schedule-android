/**
 * 
 */
package org.openschedule.activities;

import org.openschedule.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

/**
 * @author dmfrey
 *
 */
public class MainTabWidget extends TabActivity {

	private static final String TAG = "MainTabWidget";

	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );
		super.onCreate( savedInstanceState );
		
		setContentView( R.layout.main );
		
		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec tabSpec;
		Intent intent;
				
		// add events tab
		intent = new Intent();
		intent.setClass( this, InfoActivity.class );
		
		tabSpec = tabHost.newTabSpec( "event" );
		tabSpec.setIndicator( "Event", ( Build.VERSION.SDK_INT > 4 ) ? res.getDrawable( R.drawable.ic_tab_schedule ) : res.getDrawable( R.drawable.ic_tab_schedule_1x ) );
		tabSpec.setContent( intent );
		tabHost.addTab( tabSpec );

		// add events tab
		intent = new Intent();
		intent.setClass( this, EventActivity.class );
		
		tabSpec = tabHost.newTabSpec( "schedule" );
		tabSpec.setIndicator( "Schedule", res.getDrawable( R.drawable.ic_tab_events ) );
		tabSpec.setContent( intent );
		tabHost.addTab( tabSpec );
		
		// add venues tab
		intent = new Intent();
		intent.setClass( this, VenuesActivity.class );
		
		tabSpec = tabHost.newTabSpec( "venues" );
		tabSpec.setIndicator( "Venues", ( Build.VERSION.SDK_INT > 4 ) ? res.getDrawable( R.drawable.ic_tab_venue ) : res.getDrawable( R.drawable.ic_tab_venue_1x ) );
		tabSpec.setContent( intent );
		tabHost.addTab( tabSpec );

		Log.d( TAG, "onCreate : exit" );
	}

}
