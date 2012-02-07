package org.openschedule.api.impl;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.openschedule.api.EventOperations;
import org.openschedule.api.OpenSchedule;
import org.openschedule.api.SessionOperations;
import org.openschedule.api.VenueOperations;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import android.util.Log;

/**
 * This is the central class for interacting with Greenhouse.
 * 
 * @author dmfrey
 */
public class OpenScheduleTemplate extends AbstractOpenSchedulApiBinding implements OpenSchedule {
	
	private static final String TAG = OpenScheduleTemplate.class.getSimpleName();
	
	private final String apiUrlBase;
		
	private EventOperations eventOperations;
	
	private SessionOperations sessionOperations;
	
	private VenueOperations venueOperations;
	
	/**
	 * Create a new instance of OpenScheduleTemplate.
	 */
	public OpenScheduleTemplate( String apiUrlBase ) {
		Log.v( TAG, "initialize : enter" );
		
		this.apiUrlBase = apiUrlBase;
		registerOpenScheduleJsonModule();
		getRestTemplate().setErrorHandler( new OpenScheduleErrorHandler() );
		initSubApis();

		Log.v( TAG, "initialize : exit" );
	}
	
	public EventOperations eventOperations() {
		return eventOperations;
	}
	
	public SessionOperations sessionOperations() {
		return sessionOperations;
	}
	
	public VenueOperations venueOperations() {
		return venueOperations;
	}
	
	// private helper 

	private void registerOpenScheduleJsonModule() {
		Log.v( TAG, "registerOpenScheduleJsonModule : enter" );

		List<HttpMessageConverter<?>> converters = getRestTemplate().getMessageConverters();
		for (HttpMessageConverter<?> converter : converters) {
			if( converter instanceof MappingJacksonHttpMessageConverter ) {
				MappingJacksonHttpMessageConverter jsonConverter = (MappingJacksonHttpMessageConverter) converter;
				ObjectMapper objectMapper = new ObjectMapper();				
				objectMapper.registerModule( new OpenScheduleModule() );
				jsonConverter.setObjectMapper( objectMapper );
			}
		}

		Log.v( TAG, "registerOpenScheduleJsonModule : exit" );
	}
	
	private String getApiUrlBase() {
		return apiUrlBase;
	}
	
	private void initSubApis() {
		Log.v( TAG, "initSubApis : enter" );

		this.eventOperations = new EventTemplate( getRestTemplate(), getApiUrlBase() );
		this.sessionOperations = new SessionTemplate( getRestTemplate(), getApiUrlBase() );
		this.venueOperations = new VenueTemplate( getRestTemplate(), getApiUrlBase() );

		Log.v( TAG, "initSubApis : exit" );
	}
	
}