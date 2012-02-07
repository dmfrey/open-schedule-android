package org.openschedule.api.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openschedule.api.Comment;
import org.openschedule.api.Speaker;

/**
 * Mixin class for adding Jackson annotations to Session.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class SessionMixin {

	@JsonCreator
	SessionMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "name" ) String name,
			@JsonProperty( "description" ) String description,
			@JsonProperty( "speakers" ) List<Speaker> speakers,
			@JsonProperty( "comments" ) List<Comment> comments
	) {}

}
