package org.openschedule.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openschedule.api.Room;
import org.openschedule.api.Sponsor;

/**
 * Mixin class for adding Jackson annotations to Track.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class TrackMixin {

	@JsonCreator
	TrackMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "name" ) String name,
			@JsonProperty( "room" ) Room room,
			@JsonProperty( "sponsor" ) Sponsor sponsor
	) {}

}
