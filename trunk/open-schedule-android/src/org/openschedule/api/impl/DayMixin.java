/**
 * 
 */
package org.openschedule.api.impl;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openschedule.api.Schedule;

/**
 * Mixin class for adding Jackson annotations to Day.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class DayMixin {

	@JsonCreator
	DayMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "date" ) Date date,
			@JsonProperty( "numberOfSchedules" ) Integer numberOfSchedules,
			@JsonProperty( "schedules" ) List<Schedule> schedules
	) {}

}
