/**
 * 
 */
package org.openschedule.activities;

import org.openschedule.R;
import org.openschedule.controllers.EventsController;
import org.openschedule.domain.Comment;
import org.openschedule.util.Prefs;

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
public class EventCommentFormActivity extends Activity {

	private static final String TAG = "EventCommentFormActivity";

	private SharedPreferences _settings;

	public void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );
		
	    super.onCreate( savedInstanceState );
	    setContentView( R.layout.comment_form );

		_settings = getSharedPreferences( Prefs.PREFS, Context.MODE_PRIVATE );

	    TextView title = (TextView) findViewById( R.id.comment_form_label_title );
	    title.setText( getString( R.string.event_comment_form_label_text_title ) );

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

		EventsController.addEventComment( this, selectedEvent, comment );
		
		Toast toast = Toast.makeText( v.getContext(), "New Comment posted.", Toast.LENGTH_LONG );
		toast.setGravity( Gravity.CENTER, 0, 0 );
		toast.show();
	}

}
