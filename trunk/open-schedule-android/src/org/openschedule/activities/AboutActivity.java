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
package org.openschedule.activities;

import org.openschedule.R;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

/**
 * @author Daniel Frey
 *
 */
public class AboutActivity extends AbstractOpenScheduleActivity {

	private static final String TAG = AboutActivity.class.getSimpleName();
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );
		
		super.onCreate( savedInstanceState );

	    setContentView( R.layout.about );

	    final TextView description = (TextView) findViewById( R.id.about_description_text_view );
	    description.setMovementMethod( LinkMovementMethod.getInstance() );
		
	    final TextView freeSoftwareDescription = (TextView) findViewById( R.id.about_free_software_description_text_view );
	    freeSoftwareDescription.setMovementMethod( LinkMovementMethod.getInstance() );

	    final TextView sourceCodeDescription = (TextView) findViewById( R.id.about_source_code_description_text_view );
	    sourceCodeDescription.setMovementMethod( LinkMovementMethod.getInstance() );

	    final TextView sourceCodeUrlOneDescription = (TextView) findViewById( R.id.about_source_code_url_1_text_view );
	    sourceCodeUrlOneDescription.setMovementMethod( LinkMovementMethod.getInstance() );

	    final TextView sourceCodeUrlTwoDescription = (TextView) findViewById( R.id.about_source_code_url_2_text_view );
	    sourceCodeUrlTwoDescription.setMovementMethod( LinkMovementMethod.getInstance() );

	    final TextView springDescription = (TextView) findViewById( R.id.about_spring_description_text_view );
	    springDescription.setMovementMethod( LinkMovementMethod.getInstance() );

	    final TextView createdDescription = (TextView) findViewById( R.id.about_created_description_text_view );
	    createdDescription.setMovementMethod( LinkMovementMethod.getInstance() );

	    final TextView specialThanksHeader = (TextView) findViewById( R.id.about_thanks_header_text_view );
	    specialThanksHeader.setMovementMethod( LinkMovementMethod.getInstance() );

	    final TextView specialThanksDescription = (TextView) findViewById( R.id.about_thanks_description_site_text_view );
	    specialThanksDescription.setMovementMethod( LinkMovementMethod.getInstance() );

	    Log.d( TAG, "onCreate : exit" );
	}

}
