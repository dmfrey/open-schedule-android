package org.openschedule.api.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openschedule.api.Room;

/**
 * Mixin class for adding Jackson annotations to Venue.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
public class VenueMixin {

	@JsonCreator
	VenueMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "name" )	String name,
			@JsonProperty( "addressOne" ) String addressOne,
			@JsonProperty( "addressTwo" ) String addressTwo,
			@JsonProperty( "city" ) String city,
			@JsonProperty( "state" ) String state,
			@JsonProperty( "zip" ) String zip,
			@JsonProperty( "webSite" ) String webSite,
			@JsonProperty( "email" ) String email,
			@JsonProperty( "phone" ) String phone,
			@JsonProperty( "rooms" ) List<Room> rooms
	) {}

}
