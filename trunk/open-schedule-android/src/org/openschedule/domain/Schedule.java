package org.openschedule.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties( ignoreUnknown = true )
public class Schedule {

	@JsonProperty
	private Integer id;

	@JsonProperty
	private Integer blocksPerSchedule;

	@JsonProperty
	private Track track;

	@JsonProperty
	private Day day;

	@JsonProperty
	private List<Block> blocks = new ArrayList<Block>();

	public Schedule() { }

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Integer getBlocksPerSchedule() {
		return blocksPerSchedule;
	}

	public void setBlocksPerSchedule( Integer blocksPerSchedule ) {
		this.blocksPerSchedule = blocksPerSchedule;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack( Track track ) {
		this.track = track;
	}

	public Day getDay() {
		return day;
	}

	public void setDay( Day day ) {
		this.day = day;
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks( List<Block> blocks ) {
		this.blocks = blocks;
	}
	
}
