/**
 * 
 */
package org.openschedule.api.impl;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Mixin class for adding Jackson annotations to Notification.
 * 
 * @author dmfrey
 */
@JsonIgnoreProperties( ignoreUnknown = true )
abstract class NotificationMixin {

	@JsonCreator
	NotificationMixin(
			@JsonProperty( "id" ) Long id,			
			@JsonProperty( "statusTitle" ) String statusTitle,		
			@JsonProperty( "statusMessage" ) String statusMessage,			
			@JsonProperty( "title" ) String title,			
			@JsonProperty( "message" ) String message,			
			@JsonProperty( "created" ) Date created,
			@JsonProperty( "duration" )Integer duration,			
			@JsonProperty( "active" ) Boolean active
	) {}

}
