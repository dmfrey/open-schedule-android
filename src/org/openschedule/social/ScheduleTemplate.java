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
package org.openschedule.social;

import java.util.Arrays;
import java.util.List;

import org.openschedule.domain.Comment;
import org.openschedule.domain.Event;
import org.openschedule.domain.Notification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

/**
 * @author dmfrey
 *
 */
public class ScheduleTemplate implements ScheduleOperations {

	private static final String TAG = "ScheduleTemplate";
	
	RestOperations restOperations;
	private String baseUrl;

	public ScheduleTemplate( ) {
        this( DEFAULT_BASE_URL );
    }

	public ScheduleTemplate( String baseUrl ) {
		RestTemplate restTemplate = new RestTemplate();
		this.restOperations = restTemplate;
		jsonAcceptingHeaders = new LinkedMultiValueMap<String, String>();
		jsonAcceptingHeaders.add( "Accept", "application/json" );
		this.baseUrl = baseUrl;
	}

	/* (non-Javadoc)
	 * @see org.openschedule.social.ScheduleOperations#getPublishedEvents()
	 */
	public List<Event> getPublishedEvents() {
		Log.d( TAG, "getPublishedEvents : enter" );
		
		String url = baseUrl + EVENTS_PATH;
		Log.d( TAG, "getPublishedEvents : url=" + url );
		
		Log.d( TAG, "getPublishedEvents : exit" );
		return Arrays.asList( restOperations.exchange( url, HttpMethod.GET,
				new HttpEntity<Object>(jsonAcceptingHeaders ), Event[].class ).getBody() );
	}

	/* (non-Javadoc)
	 * @see org.openschedule.social.ScheduleOperations#getEvent(java.lang.String)
	 */
	public Event getEvent( String shortName ) {
		Log.d( TAG, "getEvent : enter" );
		
		Log.d( TAG, "getEvent : shortName=" + shortName );

		String url = baseUrl + EVENT_PATH;
		Log.d( TAG, "getEvent : url=" + url );
		
		Log.d( TAG, "getEvent : exit" );
		return restOperations.exchange( url, HttpMethod.GET,
				new HttpEntity<Object>( jsonAcceptingHeaders ), Event.class, shortName ).getBody();
	}

	/* (non-Javadoc)
	 * @see org.openschedule.social.ScheduleOperations#addEventComment(java.lang.String, org.openschedule.domain.Comment)
	 */
	public void addEventComment( String shortName, Comment comment ) {
		Log.d( TAG, "addEventComment : enter" );
		
		Log.d( TAG, "addEventComment : shortName=" + shortName );

		String url = baseUrl + EVENT_COMMENTS_PATH;
		Log.d( TAG, "addEventComment : url=" + url );

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType( new MediaType( "application","json" ) );

		HttpEntity<Comment> requestEntity = new HttpEntity<Comment>( comment, requestHeaders );

		String result = restOperations.exchange( url, HttpMethod.POST, requestEntity, String.class, shortName ).getBody();
		Log.d( TAG, "addEventComment : result=" + result );
		
		Log.d( TAG, "addEventComment : exit" );
	}

	/* (non-Javadoc)
	 * @see org.openschedule.social.ScheduleOperations#getEventComments(java.lang.String)
	 */
	public List<Comment> getEventComments( String shortName ) {
		Log.d( TAG, "getEventComments : enter" );
		
		Log.d( TAG, "getEventComments : shortName=" + shortName );

		String url = baseUrl + EVENT_COMMENTS_PATH;
		Log.d( TAG, "getEventComments : url=" + url );
		
		Log.d( TAG, "getEventComments : exit" );
		return Arrays.asList( restOperations.exchange( url, HttpMethod.GET,
				new HttpEntity<Object>( jsonAcceptingHeaders ), Comment[].class, shortName ).getBody() );
	}

	/* (non-Javadoc)
	 * @see org.openschedule.social.ScheduleOperations#addBlockComment(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, org.openschedule.domain.Comment)
	 */
	public void addBlockComment( String shortName, Integer dayId, Integer scheduleId, Integer blockId, Comment comment ) {
		Log.d( TAG, "addSessionComment : enter" );
		
		Log.d( TAG, "addSessionComment : shortName=" + shortName + ", dayId=" + dayId + ", scheduleId=" + scheduleId + ", blockId=" + blockId );

		String url = baseUrl + BLOCK_COMMENTS_PATH;
		Log.d( TAG, "addSessionComment : url=" + url );

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType( new MediaType( "application","json" ) );

		HttpEntity<Comment> requestEntity = new HttpEntity<Comment>( comment, requestHeaders );

		String result = restOperations.exchange( url, HttpMethod.POST, requestEntity, String.class, shortName, dayId, scheduleId, blockId ).getBody();
		Log.d( TAG, "addSessionComment : result=" + result );
		
		Log.d( TAG, "addSessionComment : exit" );
	}

	/* (non-Javadoc)
	 * @see org.openschedule.social.ScheduleOperations#getBlockComments(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public List<Comment> getBlockComments( String shortName, Integer dayId, Integer scheduleId, Integer blockId ) {
		Log.d( TAG, "getSessionComments : enter" );
		
		Log.d( TAG, "getSessionComments : shortName=" + shortName + ", dayId=" + dayId + ", scheduleId=" + scheduleId + ", blockId=" + blockId );

		String url = baseUrl + BLOCK_COMMENTS_PATH;
		Log.d( TAG, "getSessionComments : url=" + url );
		
		Log.d( TAG, "getSessionComments : exit" );
		return Arrays.asList( restOperations.exchange( url, HttpMethod.GET,
				new HttpEntity<Object>( jsonAcceptingHeaders ), Comment[].class, shortName, dayId, scheduleId, blockId ).getBody() );
	}

	/* (non-Javadoc)
	 * @see org.openschedule.social.ScheduleOperations#getNotifications(java.lang.String)
	 */
	public List<Notification> getNotifications( String shortName ) {
		Log.d( TAG, "getNotifications : enter" );
		
		Log.d( TAG, "getNotifications : shortName=" + shortName );

		String url = baseUrl + NOTIFICATIONS_PATH;
		Log.d( TAG, "getNotifications : url=" + url );
		
		Log.d( TAG, "getNotifications : exit" );
		return Arrays.asList( restOperations.exchange( url, HttpMethod.GET,
				new HttpEntity<Object>( jsonAcceptingHeaders ), Notification[].class, shortName ).getBody() );
	}

	static final String DEFAULT_BASE_URL = "http://stream.tllts.org:8080/schedule";
	static final String EVENTS_PATH = "/public";
	static final String EVENT_PATH = EVENTS_PATH + "/{shortName}";
	static final String EVENT_COMMENTS_PATH = EVENT_PATH + "/comments";
	static final String BLOCK_COMMENTS_PATH = EVENT_PATH + "/days/{dayId}/schedules/{scheduleId}/blocks/{blockId}/comments";
	static final String NOTIFICATIONS_PATH = EVENT_PATH + "/notifications";
	
	private MultiValueMap<String, String> jsonAcceptingHeaders;

}
