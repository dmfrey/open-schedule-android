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

import java.util.ArrayList;
import java.util.List;

import org.openschedule.api.Comment;
import org.openschedule.api.SessionOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * @author Daniel Frey
 */
public class SessionTemplate extends AbstractOpenScheduleOperations implements SessionOperations {
	
	private final RestTemplate restTemplate;

	public SessionTemplate( RestTemplate restTemplate, String apiUrlBase) {
		super( apiUrlBase );
		this.restTemplate = restTemplate;
	}
	

	public void addBlockComment( String shortName, Integer dayId, Integer scheduleId, Integer blockId, Comment comment ) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType( new MediaType( "application","json" ) );

		HttpEntity<Comment> requestEntity = new HttpEntity<Comment>( comment, requestHeaders );

		restTemplate.exchange( "public/" + shortName + "/days/" + dayId + "/schedules/" + scheduleId + "/blocks/" + blockId + "/comments", HttpMethod.POST, requestEntity, String.class, shortName, dayId, scheduleId, blockId ).getBody();
	}

	public List<Comment> getBlockComments( String shortName, Integer dayId, Integer scheduleId, Integer blockId ) {
		return restTemplate.getForObject( buildUri( "public/" + shortName + "/days/" + dayId + "/schedules/" + scheduleId + "/blocks/" + blockId + "/comments" ), CommentList.class );
	}

	@SuppressWarnings( "serial" )
	private static class CommentList extends ArrayList<Comment> {}

}
