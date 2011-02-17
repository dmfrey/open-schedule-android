package org.openschedule.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Label {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private String name;

	public Label() {}

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

}
