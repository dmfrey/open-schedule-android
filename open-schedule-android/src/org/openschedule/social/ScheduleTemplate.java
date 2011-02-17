/**
 * 
 */
package org.openschedule.social;

import java.util.Arrays;
import java.util.List;

import org.openschedule.domain.Comment;
import org.openschedule.domain.Event;
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

	@Override
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
	@Override
	public Event getEvent( String shortName ) {
		Log.d( TAG, "getEvent : enter" );
		
		Log.d( TAG, "getEvent : shortName=" + shortName );

		String url = baseUrl + EVENT_PATH;
		Log.d( TAG, "getEvent : url=" + url );
		
		Log.d( TAG, "getEvent : exit" );
		return restOperations.exchange( url, HttpMethod.GET,
				new HttpEntity<Object>( jsonAcceptingHeaders ), Event.class, shortName ).getBody();
	}

	@Override
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

	@Override
	public List<Comment> getEventComments( String shortName ) {
		Log.d( TAG, "getEventComments : enter" );
		
		Log.d( TAG, "getEventComments : shortName=" + shortName );

		String url = baseUrl + EVENT_COMMENTS_PATH;
		Log.d( TAG, "getEventComments : url=" + url );
		
		Log.d( TAG, "getEventComments : exit" );
		return Arrays.asList( restOperations.exchange( url, HttpMethod.GET,
				new HttpEntity<Object>( jsonAcceptingHeaders ), Comment[].class, shortName ).getBody() );
	}

	@Override
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

	@Override
	public List<Comment> getBlockComments( String shortName, Integer dayId, Integer scheduleId, Integer blockId ) {
		Log.d( TAG, "getSessionComments : enter" );
		
		Log.d( TAG, "getSessionComments : shortName=" + shortName + ", dayId=" + dayId + ", scheduleId=" + scheduleId + ", blockId=" + blockId );

		String url = baseUrl + BLOCK_COMMENTS_PATH;
		Log.d( TAG, "getSessionComments : url=" + url );
		
		Log.d( TAG, "getSessionComments : exit" );
		return Arrays.asList( restOperations.exchange( url, HttpMethod.GET,
				new HttpEntity<Object>( jsonAcceptingHeaders ), Comment[].class, shortName, dayId, scheduleId, blockId ).getBody() );
	}

	static final String DEFAULT_BASE_URL = "http://stream.tllts.org:8080/schedule";
	static final String EVENTS_PATH = "/public";
	static final String EVENT_PATH = "/public/{shortName}";
	static final String PUBLIC_EVENT_PATH = "/public/{shortName}";
	static final String EVENT_COMMENTS_PATH = PUBLIC_EVENT_PATH + "/comments";
	static final String BLOCK_COMMENTS_PATH = PUBLIC_EVENT_PATH + "/days/{dayId}/schedules/{scheduleId}/blocks/{blockId}/comments";
	
	private MultiValueMap<String, String> jsonAcceptingHeaders;

}
