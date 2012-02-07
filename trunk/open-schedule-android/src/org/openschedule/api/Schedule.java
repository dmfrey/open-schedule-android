/**
 *  This file is part of OpenSchedule for Android
 * 
 *  OpenSchedule for Android is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenSchedule for Android is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSchedule for Android.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Daniel Frey <dmfrey at gmail dot com>
 * 
 * This software can be found at <http://code.google.com/p/open-schedule-android/>
 *
 */
package org.openschedule.api;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

	private Integer id;
	private Integer blocksPerSchedule;
	private Track track;
	private Day day;
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
