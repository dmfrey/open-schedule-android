/**
 * 
 */
package org.openschedule.api;

import java.util.List;

/**
 * @author dmfrey
 *
 */
public interface SessionOperations {

	void addBlockComment( String shortName, Integer dayId, Integer scheduleId, Integer blockId, Comment comment );

	List<Comment> getBlockComments( String shortName, Integer dayId, Integer scheduleId, Integer blockId );
	
}
