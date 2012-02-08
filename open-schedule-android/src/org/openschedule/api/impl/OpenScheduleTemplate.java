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
 * @author Daniel Frey
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