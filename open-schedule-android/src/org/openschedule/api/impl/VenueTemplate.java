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

import org.openschedule.api.Venue;
import org.openschedule.api.VenueOperations;
import org.springframework.web.client.RestTemplate;

/**
 * @author Daniel Frey
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
