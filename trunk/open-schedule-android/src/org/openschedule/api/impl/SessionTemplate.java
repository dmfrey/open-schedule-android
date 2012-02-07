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
 * @author dmfrey
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
