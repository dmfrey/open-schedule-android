package org.openschedule.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Sponsor {
	
	@JsonProperty
	private Integer id;

	@JsonProperty
	private String companyName;

	@JsonProperty
	private String contactName;

	@JsonProperty
	private String contactEmail;

	@JsonProperty
	private String contactPhone;

	@JsonProperty
	private String webSite;

	public Sponsor() { }

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName( String companyName ) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName( String contactName ) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail( String contactEmail ) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone( String contactPhone ) {
		this.contactPhone = contactPhone;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite( String webSite ) {
		this.webSite = webSite;
	}
	
}
