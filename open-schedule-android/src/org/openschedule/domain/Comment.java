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