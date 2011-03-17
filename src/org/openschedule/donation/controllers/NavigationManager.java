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
