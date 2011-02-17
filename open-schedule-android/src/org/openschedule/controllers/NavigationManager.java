/**
 * 
 */
package org.openschedule.controllers;

import java.util.concurrent.RejectedExecutionException;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author dmfrey
 *
 */
public class NavigationManager {
	
	private static final String TAG = "NavigationManager";

	//***************************************
    // Public methods
    //***************************************
	public static boolean startActivity( Context context, Class<?> activity ) {
		Log.d( TAG, "startActivity( context, activity ) : enter" );
		
		try {				
			Intent intent = new Intent();
		    intent.setClass( context, activity );
		    startActivity( context, intent );
		} catch( RejectedExecutionException ex ) {
			Log.e( TAG, "startActivity( context, activity ) : error", ex );
		}
		
		Log.d( TAG, "startActivity( context, activity ) : exit" );
	    return true;
	}	

	public static boolean startActivity( Context context, Intent intent ) {
		Log.d( TAG, "startActivity( context, intent ) : enter" );

		try {
		    context.startActivity( intent );
		} catch( RejectedExecutionException ex ) { 
			Log.e( TAG, "startActivity( context, intent ) : error", ex );
		}
		
		Log.d( TAG, "startActivity( context, intent ) : exit" );
	    return true;
	}	

}
