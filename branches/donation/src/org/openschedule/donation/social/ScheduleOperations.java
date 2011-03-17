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
package org.openschedule.donation.social;

import java.util.List;

import org.openschedule.donation.domain.Comment;
import org.openschedule.donation.domain.Event;
import org.openschedule.donation.domain.Notification;

public interface ScheduleOperations {

	List<Event> getPublishedEvents();
	
	Event getEvent( String shortName );

	void addEventComment( String shortName, Comment comment );
	
	List<Comment> getEventComments( String shortName );
	
	void addBlockComment( String shortName, Integer dayId, Integer scheduleId, Integer blockId, Comment comment );

	List<Comment> getBlockComments( String shortName, Integer dayId, Integer scheduleId, Integer blockId );
	
	List<Notification> getNotifications( String shortName );

}
