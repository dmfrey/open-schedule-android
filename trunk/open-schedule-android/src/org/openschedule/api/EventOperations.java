/**
 * 
 */
package org.openschedule.api;

import java.util.List;


/**
 * @author dmfrey
 *
 */
public interface EventOperations {

	List<Event> getPublishedEvents();

	Event getEvent( String shortName );
	
	void addEventComment( String shortName, Comment comment );
	
	List<Comment> getEventComments( String shortName );
	
	List<Notification> getNotifications( String shortName );

}
