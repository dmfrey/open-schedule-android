/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openschedule.api.impl;

import java.net.URI;

import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author dmfrey
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