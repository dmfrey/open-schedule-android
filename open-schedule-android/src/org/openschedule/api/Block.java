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

public class Block {

	private Integer id;
	private Date date;
	private Integer duration;
	private Label label;
	private Session session;

	public Block() {}
	
	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Date getDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		cal.add( Calendar.DATE, 1 );
		
		return cal.getTime();
	}

	public void setDate( Date date ) {
		this.date = date;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration( Integer duration ) {
		this.duration = duration;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel( Label label ) {
		this.label = label;
	}

	public Session getSession() {
		return session;
	}

	public void setSession( Session session ) {
		this.session = session;
	}

}
