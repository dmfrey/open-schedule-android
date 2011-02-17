package org.openschedule.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Venue {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String addressOne;

	@JsonProperty
	private String addressTwo;

	@JsonProperty
	private String city;

	@JsonProperty
	private String state;

	@JsonProperty
	private String zip;

	@JsonProperty
	private String webSite;

	@JsonProperty
	private String email;

	@JsonProperty
	private String phone;

	@JsonProperty
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
