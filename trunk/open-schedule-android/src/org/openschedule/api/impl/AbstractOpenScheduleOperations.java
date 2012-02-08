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

import java.net.URI;

import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Daniel Frey
 *
 */
class AbstractOpenScheduleOperations {
	
	private final String apiUrlBase;

	public AbstractOpenScheduleOperations( String apiUrlBase ) {
		this.apiUrlBase = apiUrlBase;
	}
	
	protected URI buildUri( String path ) {
		return buildUri( path, EMPTY_PARAMETERS );
	}
	
	protected URI buildUri( String path, String parameterName, String parameterValue ) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.set( parameterName, parameterValue );
		return buildUri( path, parameters );
	}
	
	protected URI buildUri( String path, MultiValueMap<String, String> parameters ) {
		return URIBuilder.fromUri( getApiUrlBase() + path ).queryParams( parameters ).build();
	}
	
	protected String getApiUrlBase() {
		return apiUrlBase;
	}
	
	private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();
}