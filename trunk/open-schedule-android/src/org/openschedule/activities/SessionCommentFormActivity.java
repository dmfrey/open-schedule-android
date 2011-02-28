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
import org.openschedule.controllers.EventsController;
import org.openschedule.domain.Comment;
import org.openschedule.util.Prefs;
import org.openschedule.util.SharedDataManager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author dmfrey
 *
 */
public class SessionCommentFormActivity extends Activity {

	private static final String TAG = "SessionCommentFormActivity";

	private SharedPreferences _settings;

	public void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );
		
	    super.onCreate( savedInstanceState );
	    setContentView( R.layout.comment_form );

		_settings = getSharedPreferences( Prefs.PREFS, Context.MODE_PRIVATE );

	    TextView title = (TextView) findViewById( R.id.comment_form_label_title );
	    title.setText( getString( R.string.session_comment_form_label_text_title ) );

	    final Button button = (Button) findViewById( R.id.comment_form_submit_button );
        button.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                createComment( v );
                
                finish();
            }
        });
        
	    Log.d( TAG, "onCreate : exit" );
	}

	private void createComment( View v ) {
	
		final EditText nameField = (EditText) findViewById( R.id.comment_form_name );
		String name = nameField.getText().toString();

		final EditText emailField = (EditText) findViewById( R.id.comment_form_email );
		String email = emailField.getText().toString();

		final EditText commentField = (EditText) findViewById( R.id.comment_form_comment );
		String commentBody = commentField.getText().toString();
		
		Comment comment = new Comment();
		comment.setName( name );
		comment.setEmail( email );
		comment.setComment( commentBody );
		
		String selectedEvent = Prefs.getSelectedEvent( _settings );

		EventsController.addBlockComment( this, selectedEvent, SharedDataManager.getCurrentDay().getId(), SharedDataManager.getCurrentSchedule().getId(), SharedDataManager.getCurrentBlock().getId(), comment );
		
		Toast toast = Toast.makeText( v.getContext(), "New Comment posted.", Toast.LENGTH_LONG );
		toast.setGravity( Gravity.CENTER, 0, 0 );
		toast.show();
	}

}
