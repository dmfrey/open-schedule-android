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
package org.openschedule.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Day {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private Date date;

	@JsonProperty
	private Integer numberOfSchedules;

	@JsonProperty
	private List<Schedule> schedules = new ArrayList<Schedule>();

	public Day() { }
	
	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Day( Date date ) {
		this.date = date;
	}

	public Date getDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		cal.add( Calendar.DATE, 1 );
				
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );

		return cal.getTime();
	}

	public void setDate( Date date ) {
		this.date = date;
	}

	public Integer getNumberOfSchedules() {
		return numberOfSchedules;
	}

	public void setNumberOfSchedules( Integer numberOfSchedules ) {
		this.numberOfSchedules = numberOfSchedules;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules( List<Schedule> schedules ) {
		this.schedules = schedules;
	}
	
}
