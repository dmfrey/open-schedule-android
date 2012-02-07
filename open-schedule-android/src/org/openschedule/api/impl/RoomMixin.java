package org.openschedule.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Mixin class for adding Jackson annotations to Room.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class RoomMixin {

	@JsonCreator
	RoomMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "name" ) String name
	) {}

}
