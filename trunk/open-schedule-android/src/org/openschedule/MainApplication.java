/**
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
 * @author dmfrey
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
