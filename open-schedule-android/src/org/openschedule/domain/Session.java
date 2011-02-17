package org.openschedule.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Session {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	@JsonProperty
	private String description;

	@JsonProperty
	private List<Speaker> speakers = new ArrayList<Speaker>();

	private List<Comment> comments = new ArrayList<Comment>();
	
	public Session() {}

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

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public List<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers( List<Speaker> speakers ) {
		this.speakers = speakers;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments( List<Comment> comments ) {
		this.comments = comments;
	}
	
}
