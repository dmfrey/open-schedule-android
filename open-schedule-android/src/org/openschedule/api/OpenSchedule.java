/**
 * 
 */
package org.openschedule.api;


/**
 * @author dmfrey
 *
 */
public interface OpenSchedule {

	EventOperations eventOperations();
	
	SessionOperations sessionOperations();
	
	VenueOperations venueOperations();
	
}
