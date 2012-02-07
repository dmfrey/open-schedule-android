/**
 * 
 */
package org.openschedule.api.impl;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openschedule.api.Label;
import org.openschedule.api.Session;

/**
 * Mixin class for adding Jackson annotations to Block.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class BlockMixin {

	@JsonCreator
	BlockMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "date" )	Date date,
			@JsonProperty( "duration" ) Integer duration,
			@JsonProperty( "label" ) Label label,
			@JsonProperty( "session" ) Session session
	) {}

}
