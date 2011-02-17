/**
 * 
 */
package org.openschedule.util;

import org.openschedule.social.ScheduleOperations;
import org.openschedule.social.ScheduleTemplate;

import android.content.SharedPreferences;
import android.net.Uri;

/**
 * @author dmfrey
 *
 */
public class Prefs {

	public static final String SCHEDULE_SERVER_BASE_URL = "http://stream.tllts.org:8080/schedule"; //"http://10.0.2.2:8080/schedule";

	public static final String PREFS = "SchedulePreferences";

	public static final String CALLBACK_URI_STRING = "x-org-dmfrey-schedule://oauth-response";

	public static final String DATE_FORMAT = "EEE, MMM d yyyy";
	private static final String SELECTED_EVENT_KEY = "SELECTED_EVENT";
	
	private static ScheduleOperations scheduleOperations;

	public static String getUrlBase() {
		return SCHEDULE_SERVER_BASE_URL;
	}

	public static void setSelectedEvent( final SharedPreferences settings, final String shortName ) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putString( SELECTED_EVENT_KEY, shortName );
		editor.commit();
	}
	
	public static String getSelectedEvent( final SharedPreferences settings ) {
		return settings.getString( SELECTED_EVENT_KEY, null );
	}

	public static Uri getCallbackUri() {
		return Uri.parse( CALLBACK_URI_STRING );
	}

	public static ScheduleOperations getScheduleOperations( final SharedPreferences settings ) {
		if( null == scheduleOperations ) {
			scheduleOperations = new ScheduleTemplate( getUrlBase() );
		}
		
		return scheduleOperations;
	}

}
