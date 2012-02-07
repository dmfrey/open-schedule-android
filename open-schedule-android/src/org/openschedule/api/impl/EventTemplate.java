package org.openschedule.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.openschedule.api.Comment;
import org.openschedule.api.Event;
import org.openschedule.api.EventOperations;
import org.openschedule.api.Notification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

/**
 * @author dmfrey
 */
public class EventTemplate extends AbstractOpenScheduleOperations implements EventOperations {
	
	private static final String TAG = EventTemplate.class.getSimpleName();
	
	private MultiValueMap<String, String> jsonAcceptingHeaders;
	private final RestTemplate restTemplate;

	public EventTemplate( RestTemplate restTemplate, String apiUrlBase) {
		super( apiUrlBase );
		Log.v( TAG, "initialize : enter" );
		
		this.restTemplate = restTemplate;
		
		jsonAcceptingHeaders = new LinkedMultiValueMap<String, String>();
		jsonAcceptingHeaders.add( "Accept", "application/json" );

		Log.v( TAG, "initialize : exit" );
	}
	
	public List<Event> getPublishedEvents() {
		Log.v( TAG, "getPublishedEvents : enter" );

		Log.v( TAG, "getPublishedEvents : exit" );
		return restTemplate.getForObject( buildUri( "public" ), EventList.class );
	}

	public Event getEvent( String shortName ) {
		Log.v( TAG, "getEvent : enter" );

		if( Log.isLoggable( TAG, Log.DEBUG ) ) {
			Log.d( TAG, "getEvent : shortName=" + shortName );
		}

		Event event = restTemplate.getForObject( buildUri( "public/" + shortName ), Event.class );
		Log.v( TAG, "getEvent : event=" + event.toString() );
		
		Log.v( TAG, "getEvent : exit" );
		return event;
	}

	public void addEventComment( String shortName, Comment comment ) {
		Log.v( TAG, "addEventComment : enter" );

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType( new MediaType( "application","json" ) );

		HttpEntity<Comment> requestEntity = new HttpEntity<Comment>( comment, requestHeaders );

		restTemplate.exchange( "public/" + shortName + "/comments", HttpMethod.POST, requestEntity, String.class, shortName ).getBody();
		
		Log.v( TAG, "addEventComment : exit" );
	}

	public List<Comment> getEventComments( String shortName ) {
		Log.v( TAG, "getEventComments : enter" );

		if( Log.isLoggable( TAG, Log.DEBUG ) ) {
			Log.d( TAG, "getEventComments : shortName=" + shortName );
		}

		Log.v( TAG, "getEventComments : exit" );
		return restTemplate.getForObject( buildUri( "public/" + shortName + "/comments" ), CommentList.class );
	}

	public List<Notification> getNotifications( String shortName ) {
		Log.d( TAG, "getNotifications : enter" );
		
		Log.d( TAG, "getNotifications : shortName=" + shortName );

		Log.d( TAG, "getNotifications : exit" );
		return restTemplate.getForObject( buildUri( "public/" + shortName + "/notifications" ), NotificationList.class );
	}

	@SuppressWarnings( "serial" )
	private static class EventList extends ArrayList<Event> {}

	@SuppressWarnings( "serial" )
	private static class CommentList extends ArrayList<Comment> {}

	@SuppressWarnings( "serial" )
	private static class NotificationList extends ArrayList<Notification> {}

}
