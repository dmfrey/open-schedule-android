package org.openschedule.api.impl;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.openschedule.api.Block;
import org.openschedule.api.Day;
import org.openschedule.api.Track;

/**
 * Mixin class for adding Jackson annotations to Schedule.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class ScheduleMixin {

	@JsonCreator
	ScheduleMixin(
			@JsonProperty( "id" ) Integer id,
			@JsonProperty( "blocksPerSchedule" ) Integer blocksPerSchedule,
			@JsonProperty( "track" ) Track track,
			@JsonProperty( "day" ) Day day,
			@JsonProperty( "blocks" ) List<Block> blocks
	) {}

}
