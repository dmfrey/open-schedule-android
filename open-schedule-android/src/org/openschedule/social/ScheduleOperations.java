/**
 * 
 */
package org.openschedule.social;

import java.util.List;

import org.openschedule.domain.Comment;
import org.openschedule.domain.Event;

/**
 * @author dmfrey
 *
 */
public interface ScheduleOperations {

	List<Event> getPublishedEvents();
	
	Event getEvent( String shortName );

	void addEventComment( String shortName, Comment comment );
	
	List<Comment> getEventComments( String shortName );
	
	void addBlockComment( String shortName, Integer dayId, Integer scheduleId, Integer blockId, Comment comment );

	List<Comment> getBlockComments( String shortName, Integer dayId, Integer scheduleId, Integer blockId );
	
}
