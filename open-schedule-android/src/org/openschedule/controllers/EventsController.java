/**
 * 
 */
package org.openschedule.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.openschedule.domain.Comment;
import org.openschedule.domain.Event;

import android.app.Activity;
import android.util.Log;

/**
 * @author dmfrey
 *
 */
public class EventsController extends BaseController {

	private static final String TAG = "EventsController";
	
	//***************************************
    // Public methods
    //***************************************
	public static List<Event> getPublishedEvents( Activity activity ) {
		Log.d( TAG, "getPublishedEvents : enter" );
		
		showProgressDialog( activity );
		try {
			Log.d( TAG, "getPublishedEvents : exit" );
			return getScheduleOperations( activity ).getPublishedEvents();
		} catch( Exception e ) {
			Log.e( TAG, e.getMessage(), e );
			Writer result = new StringWriter();
			e.printStackTrace( new PrintWriter( result ) );
		} finally {
			dismissProgressDialog();
		}
		
		Log.d( TAG, "getPublishedEvents : exit, null" );
		return null;
	}

	public static Event getEvent( Activity activity, String shortName ) {
		Log.d( TAG, "getEvent : enter" );

		showProgressDialog( activity );
		try {
			Log.d( TAG, "getEvent : exit" );
			return getScheduleOperations( activity ).getEvent( shortName );
		} catch( Exception e ) {
			Log.e( TAG, e.getMessage(), e );
			Writer result = new StringWriter();
			e.printStackTrace( new PrintWriter( result ) );
		} finally {
			dismissProgressDialog();
		}
		
		Log.d( TAG, "getEvent : exit, null" );
		return null;
	}
	
	public static void addEventComment( Activity activity, String shortName, Comment comment ) {
		Log.d( TAG, "addEventComment : enter" );

		showProgressDialog( activity );
		try {
			getScheduleOperations( activity ).addEventComment( shortName, comment );
		} catch( Exception e ) {
			Log.e( TAG, e.getMessage(), e );
			Writer result = new StringWriter();
			e.printStackTrace( new PrintWriter( result ) );
		} finally {
			dismissProgressDialog();
		}
		
		Log.d( TAG, "addEventComment : exit" );
	}
	
	public static List<Comment> getEventComments( Activity activity, String shortName ) {
		Log.d( TAG, "getEventComments : enter" );

		showProgressDialog( activity );
		try {
			Log.d( TAG, "getEventComments : exit" );
			return getScheduleOperations( activity ).getEventComments( shortName );
		} catch( Exception e ) {
			Log.e( TAG, e.getMessage(), e );
			Writer result = new StringWriter();
			e.printStackTrace( new PrintWriter( result ) );
		} finally {
			dismissProgressDialog();
		}
		
		Log.d( TAG, "getEventComments : exit, null" );
		return null;
	}
	
	public static void addBlockComment( Activity activity, String shortName, Integer dayId, Integer scheduleId, Integer blockId, Comment comment ) {
		Log.d( TAG, "addBlockComment : enter" );

		showProgressDialog( activity );
		try {
			getScheduleOperations( activity ).addBlockComment( shortName, dayId, scheduleId, blockId, comment );
		} catch( Exception e ) {
			Log.e( TAG, e.getMessage(), e );
			Writer result = new StringWriter();
			e.printStackTrace( new PrintWriter( result ) );
		} finally {
			dismissProgressDialog();
		}
		
		Log.d( TAG, "addBlockComment : exit" );
	}
	
	public static List<Comment> getBlockComments( Activity activity, String shortName, Integer dayId, Integer scheduleId, Integer blockId ) {
		Log.d( TAG, "getBlockCommentsgetBlockComments : enter" );

		showProgressDialog( activity );
		try {
			Log.d( TAG, "getSessionComments : exit" );
			return getScheduleOperations( activity ).getBlockComments( shortName, dayId, scheduleId, blockId );
		} catch( Exception e ) {
			Log.e( TAG, e.getMessage(), e );
			Writer result = new StringWriter();
			e.printStackTrace( new PrintWriter( result ) );
		} finally {
			dismissProgressDialog();
		}
		
		Log.d( TAG, "getBlockComments : exit" );
		return null;
	}

}
