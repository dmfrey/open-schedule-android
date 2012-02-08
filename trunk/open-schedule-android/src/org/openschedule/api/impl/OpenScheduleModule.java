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
package org.openschedule.api.impl;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;
import org.openschedule.api.Block;
import org.openschedule.api.Comment;
import org.openschedule.api.Day;
import org.openschedule.api.Event;
import org.openschedule.api.Label;
import org.openschedule.api.Room;
import org.openschedule.api.Schedule;
import org.openschedule.api.Session;
import org.openschedule.api.Speaker;
import org.openschedule.api.Sponsor;
import org.openschedule.api.Track;
import org.openschedule.api.Venue;

/**
 * @author Daniel Frey
 *
 */
public class OpenScheduleModule extends SimpleModule {
	
	public OpenScheduleModule() {
		super( "OpenScheduleModule", new Version( 1, 0, 0, null ) );
	}
	
	@Override
	public void setupModule( SetupContext context ) {
		context.setMixInAnnotations( Event.class, EventMixin.class );
		context.setMixInAnnotations( Day.class, DayMixin.class );
		context.setMixInAnnotations( Schedule.class, ScheduleMixin.class );
		context.setMixInAnnotations( Track.class, TrackMixin.class );
		context.setMixInAnnotations( Sponsor.class, SponsorMixin.class );
		context.setMixInAnnotations( Label.class, LabelMixin.class );
		context.setMixInAnnotations( Block.class, BlockMixin.class );
		context.setMixInAnnotations( Session.class, SessionMixin.class );
		context.setMixInAnnotations( Speaker.class, SpeakerMixin.class );
		context.setMixInAnnotations( Venue.class, VenueMixin.class );
		context.setMixInAnnotations( Room.class, RoomMixin.class );
		context.setMixInAnnotations( Comment.class, CommentMixin.class );
	}

}
