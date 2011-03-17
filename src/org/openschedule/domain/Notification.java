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

import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Notification {

	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String statusTitle;
	
	@JsonProperty
	private String statusMessage;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private String message;
	
	@JsonProperty
	private Date created;
	
	@JsonProperty
	private Integer duration;
	
	@JsonProperty
	private Boolean active;

	public Notification() { }

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Long id ) {
		this.id = id;
	}

	/**
	 * @return the statusTitle
	 */
	public String getStatusTitle() {
		return statusTitle;
	}

	/**
	 * @param statusTitle the statusTitle to set
	 */
	public void setStatusTitle( String statusTitle ) {
		this.statusTitle = statusTitle;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage( String statusMessage ) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle( String title ) {
		this.title = title;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage( String message ) {
		this.message = message;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		Calendar cal = Calendar.getInstance();
		cal.setTime( created );
		cal.add( Calendar.DATE, 1 );
		
		return cal.getTime();
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated( Date created ) {
		this.created = created;
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration( Integer duration ) {
		this.duration = duration;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive( Boolean active ) {
		this.active = active;
	}
	
}
