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
package org.openschedule.util;

import java.util.List;

import org.openschedule.domain.Block;
import org.openschedule.domain.Day;
import org.openschedule.domain.Event;
import org.openschedule.domain.Schedule;
import org.openschedule.domain.Speaker;
import org.openschedule.domain.Venue;

/**
 * @author dmfrey
 *
 */
public class SharedDataManager {

	private static List<Event> currentEvents;
	private static Event currentEvent;
	private static Day currentDay;
	private static Schedule currentSchedule;
	private static Block currentBlock;
	private static Speaker currentSpeaker;
	private static Venue currentVenue;

	public static List<Event> getCurrentEvents() {
		return currentEvents;
	}

	public static void setCurrentEvents( List<Event> currentEvents ) {
		SharedDataManager.currentEvents = currentEvents;
	}

	public static Event getCurrentEvent() {
		return currentEvent;
	}

	public static void setCurrentEvent( Event currentEvent ) {
		SharedDataManager.currentEvent = currentEvent;
	}

	public static Day getCurrentDay() {
		return currentDay;
	}

	public static void setCurrentDay( Day currentDay ) {
		SharedDataManager.currentDay = currentDay;
	}

	public static Schedule getCurrentSchedule() {
		return currentSchedule;
	}

	public static void setCurrentSchedule( Schedule currentSchedule ) {
		SharedDataManager.currentSchedule = currentSchedule;
	}

	public static Block getCurrentBlock() {
		return currentBlock;
	}

	public static void setCurrentBlock( Block currentBlock ) {
		SharedDataManager.currentBlock = currentBlock;
	}

	public static Speaker getCurrentSpeaker() {
		return currentSpeaker;
	}

	public static void setCurrentSpeaker( Speaker currentSpeaker ) {
		SharedDataManager.currentSpeaker = currentSpeaker;
	}

	public static Venue getCurrentVenue() {
		return currentVenue;
	}

	public static void setCurrentVenue( Venue currentVenue ) {
		SharedDataManager.currentVenue = currentVenue;
	}
	
}
