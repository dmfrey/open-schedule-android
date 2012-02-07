/**
 * 
 */
package org.openschedule.api.impl;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openschedule.api.Comment;
import org.openschedule.api.Day;
import org.openschedule.api.Venue;

/**
 * Mixin class for adding Jackson annotations to Event.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class EventMixin {
	@JsonCreator
	EventMixin(
			@JsonProperty( "id" ) Integer id, 
			@JsonProperty( "name" ) String name, 
			@JsonProperty( "shortName" ) String shortName,
			@JsonProperty( "publishDate" ) Date publishDate,
			@JsonProperty( "startDate" ) Date startDate,
			@JsonProperty( "endDate" ) Date endDate,
			@JsonProperty( "endTime" ) Date endTime,
			@JsonProperty( "numberOfDays" ) Integer numberOfDays,
			@JsonProperty( "username" ) String username,
			@JsonProperty( "days" ) List<Day> days,
			@JsonProperty( "venues" ) List<Venue> venues,
			@JsonProperty( "comments" ) List<Comment> comments
	) {}
	
}
