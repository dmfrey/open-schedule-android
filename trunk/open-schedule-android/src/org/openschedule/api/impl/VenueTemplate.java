package org.openschedule.api.impl;

import java.util.ArrayList;

import org.openschedule.api.Venue;
import org.openschedule.api.VenueOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author dmfrey
 */
public class VenueTemplate extends AbstractOpenScheduleOperations implements VenueOperations {
	
	private final RestTemplate restTemplate;

	public VenueTemplate( RestTemplate restTemplate, String apiUrlBase) {
		super( apiUrlBase );
		this.restTemplate = restTemplate;
	}
	

	@SuppressWarnings( "serial" )
	private static class VenueList extends ArrayList<Venue> {}
}
