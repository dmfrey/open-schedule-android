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
package org.openschedule.api;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Event {

	private Integer id;
	private String name;
	private String shortName;
	private Date publishDate;
	private Date startDate;
	private Date endDate;
	private Integer numberOfDays;
	private String username;
	private List<Day> days;
	private List<Venue> venues;
	private List<Comment> comments;
	
	public Event() { }

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName( String shortName ) {
		this.shortName = shortName;
	}

	public Date getPublishDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime( publishDate );
		cal.add( Calendar.DATE, 1 );
		
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );
		
		return cal.getTime();
	}

	public void setPublishDate( Date publishDate ) {
		this.publishDate = publishDate;
	}

	public Date getStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime( startDate );
		cal.add( Calendar.DATE, 1 );
		
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );
		
		return cal.getTime();
	}

	public void setStartDate( Date startDate ) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime( endDate );
		cal.add( Calendar.DATE, 1 );
		
		cal.set( Calendar.HOUR_OF_DAY, 23 );
		cal.set( Calendar.MINUTE, 59 );
		cal.set( Calendar.SECOND, 59 );
		
		return cal.getTime();
	}

	public void setEndDate( Date endDate ) {
		this.endDate = endDate;
	}

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays( Integer numberOfDays ) {
		this.numberOfDays = numberOfDays;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername( String username ) {
		this.username = username;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays( List<Day> days ) {
		this.days = days;
	}

	public List<Venue> getVenues() {
		return venues;
	}

	public void setVenues( List<Venue> venues ) {
		this.venues = venues;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments( List<Comment> comments ) {
		this.comments = comments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		
		builder.append( "Event [" );
		
		if( id != null ) {
			builder.append( "id=" ).append( id ).append( ", " );
		}
		
		if( name != null ) {
			builder.append( "name=" ).append( name ).append( ", " );
		}
		
		if( shortName != null ) {
			builder.append( "shortName=" ).append( shortName ).append( ", " );
		}
		
		if( publishDate != null ) {
			builder.append( "publishDate=" ).append( publishDate ).append( ", " );
		}
		
		if( startDate != null ) {
			builder.append( "startDate=" ).append( startDate ).append( ", " );
		}
		
		if( endDate != null ) {
			builder.append( "endDate=" ).append( endDate ).append( ", " );
		}
		
		if( numberOfDays != null ) {
			builder.append( "numberOfDays=" ).append( numberOfDays ).append( ", " );
		}
		
		if( username != null ) {
			builder.append( "username=" ).append( username ).append( ", " );
		}
		
		if( days != null ) {
			builder.append( "days=" ).append( days.size() ).append(", ");
		}
		
		if( venues != null ) {
			builder.append( "venues=" ).append( venues.size() ).append(", ");
		}
		
		if( comments != null ) {
			builder.append( "comments=" ).append( comments.size() );
		}
		
		builder.append( "]" );
		
		return builder.toString();
	}

}
 