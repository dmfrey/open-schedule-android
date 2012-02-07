package org.openschedule.api.impl;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Mixin class for adding Jackson annotations to Sponsor.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class SponsorMixin {

	@JsonCreator
	SponsorMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "companyName" ) String companyName,
			@JsonProperty( "contactName" ) String contactName,
			@JsonProperty( "contactEmail" ) String contactEmail,
			@JsonProperty( "contactPhone" ) String contactPhone,
			@JsonProperty( "webSite" ) String webSite
	) {}

}
