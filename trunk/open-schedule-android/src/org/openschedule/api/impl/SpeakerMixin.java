package org.openschedule.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Mixin class for adding Jackson annotations to Speaker.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class SpeakerMixin {

	@JsonCreator
	SpeakerMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "name" ) String name,
			@JsonProperty( "email" ) String email,
			@JsonProperty( "webSite" ) String webSite,
			@JsonProperty( "phone" ) String phone,
			@JsonProperty( "bio" ) String bio
	) {}

}
