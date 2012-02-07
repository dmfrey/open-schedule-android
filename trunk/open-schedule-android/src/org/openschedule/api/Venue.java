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

import java.util.ArrayList;
import java.util.List;

public class Venue {

	private Integer id;
	private String name;
	private String addressOne;
	private String addressTwo;
	private String city;
	private String state;
	private String zip;
	private String webSite;
	private String email;
	private String phone;
	private List<Room> rooms = new ArrayList<Room>();

	public Venue() { }

	
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

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne( String addressOne ) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo( String addressTwo ) {
		this.addressTwo = addressTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity( String city ) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState( String state ) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip( String zip ) {
		this.zip = zip;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite( String webSite ) {
		this.webSite = webSite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone( String phone ) {
		this.phone = phone;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms( List<Room> rooms ) {
		this.rooms = rooms;
	}
	
}
