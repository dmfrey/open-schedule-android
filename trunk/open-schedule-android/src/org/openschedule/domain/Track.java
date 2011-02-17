/**
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
public class Track {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	@JsonProperty
	private Room room;

	@JsonProperty
	private Sponsor sponsor;

	public Track() {}

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

	public Room getRoom() {
		return room;
	}

	public void setRoom( Room room ) {
		this.room = room;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor( Sponsor sponsor ) {
		this.sponsor = sponsor;
	}
	
}
