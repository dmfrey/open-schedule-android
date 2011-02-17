package org.openschedule.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Speaker {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String email;

	@JsonProperty
	private String webSite;

	@JsonProperty
	private String phone;

	@JsonProperty
	private String bio;

	public Speaker() {}

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

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite( String webSite ) {
		this.webSite = webSite;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone( String phone ) {
		this.phone = phone;
	}

	public String getBio() {
		return bio;
	}

	public void setBio( String bio ) {
		this.bio = bio;
	}
	
}
