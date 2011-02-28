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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * @author dmfrey
 *
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class Comment {

	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private String name;

	@JsonProperty
	private String email;

	@JsonProperty
	private String comment;

	public Comment() { }

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

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment( String comment ) {
		this.comment = comment;
	}
	
}
