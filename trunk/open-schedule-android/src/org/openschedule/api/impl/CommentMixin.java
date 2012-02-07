/**
 * 
 */
package org.openschedule.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Mixin class for adding Jackson annotations to Comment.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class CommentMixin {

	@JsonCreator
	CommentMixin(
			@JsonProperty( "id" ) Integer id,			
			@JsonProperty( "name" ) String name,
			@JsonProperty( "email" ) String email,
			@JsonProperty( "comment" ) String comment
	) {}

}
