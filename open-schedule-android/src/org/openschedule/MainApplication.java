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
package org.openschedule;

import org.openschedule.api.Block;
import org.openschedule.api.Day;
import org.openschedule.api.Event;
import org.openschedule.api.Notification;
import org.openschedule.api.OpenSchedule;
import org.openschedule.api.Schedule;
import org.openschedule.api.Speaker;
import org.openschedule.api.Venue;
import org.openschedule.api.impl.OpenScheduleTemplate;

import android.app.Application;

/**
 * @author Daniel Frey
 *
 */
public class MainApplication extends Application {

	public static final String DATE_FORMAT = "EEE, MMM d yyyy";

	private OpenSchedule openScheduleApi;
	
	private String selectedEventShorName;
	private Event selectedEvent;
	private Day selectedDay;
	private Schedule selectedSchedule;
	private Block selectedBlock;
	private Speaker selectedSpeaker;
	private Venue selectedVenue;
	private Notification selectedNotification;

	//***************************************
    // Application methods
    //***************************************
	@Override
	public void onCreate() {
		super.onCreate();

		openScheduleApi = new OpenScheduleTemplate( getApiUrlBase() );
	}


	//***************************************
    // Private methods
    //***************************************
	private String getApiUrlBase() {
		return getString(R.string.base_url);
	}

	
	//***************************************
    // Public methods
    //***************************************

	public OpenSchedule getOpenScheduleApi() {
		return openScheduleApi;
	}
	
	/**
	 * @return the selectedEventShorName
	 */
	public String getSelectedEventShorName() {
		return selectedEventShorName;
	}


	/**
	 * @param selectedEventShorName the selectedEventShorName to set
	 */
	public void setSelectedEventShorName(String selectedEventShorName) {
		this.selectedEventShorName = selectedEventShorName;
	}


	/**
	 * @return the selectedEvent
	 */
	public Event getSelectedEvent() {
		return selectedEvent;
	}

	/**
	 * @param selectedEvent the selectedEvent to set
	 */
	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	/**
	 * @return the selectedDay
	 */
	public Day getSelectedDay() {
		return selectedDay;
	}

	/**
	 * @param selectedDay the selectedDay to set
	 */
	public void setSelectedDay(Day selectedDay) {
		this.selectedDay = selectedDay;
	}

	/**
	 * @return the selectedSchedule
	 */
	public Schedule getSelectedSchedule() {
		return selectedSchedule;
	}

	/**
	 * @param selectedSchedule the selectedSchedule to set
	 */
	public void setSelectedSchedule(Schedule selectedSchedule) {
		this.selectedSchedule = selectedSchedule;
	}

	/**
	 * @return the selectedBlock
	 */
	public Block getSelectedBlock() {
		return selectedBlock;
	}

	/**
	 * @param selectedBlock the selectedBlock to set
	 */
	public void setSelectedBlock(Block selectedBlock) {
		this.selectedBlock = selectedBlock;
	}

	/**
	 * @return the selectedSpeaker
	 */
	public Speaker getSelectedSpeaker() {
		return selectedSpeaker;
	}

	/**
	 * @param selectedSpeaker the selectedSpeaker to set
	 */
	public void setSelectedSpeaker(Speaker selectedSpeaker) {
		this.selectedSpeaker = selectedSpeaker;
	}

	/**
	 * @return the selectedVenue
	 */
	public Venue getSelectedVenue() {
		return selectedVenue;
	}

	/**
	 * @param selectedVenue the selectedVenue to set
	 */
	public void setSelectedVenue(Venue selectedVenue) {
		this.selectedVenue = selectedVenue;
	}


	/**
	 * @return the selectedNotification
	 */
	public Notification getSelectedNotification() {
		return selectedNotification;
	}


	/**
	 * @param selectedNotification the selectedNotification to set
	 */
	public void setSelectedNotification(Notification selectedNotification) {
		this.selectedNotification = selectedNotification;
	}

}
