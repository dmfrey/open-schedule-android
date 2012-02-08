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
package org.openschedule.activities.session;

import org.openschedule.R;
import org.openschedule.activities.AboutActivity;
import org.openschedule.activities.AbstractOpenScheduleActivity;
import org.openschedule.api.Comment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Daniel Frey
 *
 */
public class SessionCommentFormActivity extends AbstractOpenScheduleActivity {

	private static final String TAG = SessionCommentFormActivity.class.getSimpleName();

	public void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );
		
	    super.onCreate( savedInstanceState );
	    setContentView( R.layout.comment_form );

	    TextView title = (TextView) findViewById( R.id.comment_form_label_title );
	    title.setText( getString( R.string.session_comment_form_label_text_title ) );

	    final Button button = (Button) findViewById( R.id.comment_form_submit_button );
        button.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v ) {
                createComment();
                
        		Toast toast = Toast.makeText( v.getContext(), "New Comment posted.", Toast.LENGTH_LONG );
        		toast.setGravity( Gravity.CENTER, 0, 0 );
        		toast.show();

        		finish();
            }
        });
        
	    Log.d( TAG, "onCreate : exit" );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
	    Log.d( TAG, "onCreateOptionsMenu : enter" );

	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate( R.menu.about_menu, menu );

	    Log.d( TAG, "onCreateOptionsMenu : exit" );
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {		
	    Log.d( TAG, "onOptionsItemSelected : enter" );

	    // Handle item selection
	    switch( item.getItemId() ) {
	    case R.id.about_menu:
			Intent intent = new Intent();
			intent.setClass( this, AboutActivity.class );
			startActivity( intent );

	    	Log.d( TAG, "onOptionsItemSelected : exit, about option selected" );
	    	return true;
	    default:
	    	Log.d( TAG, "onOptionsItemSelected : exit, default option selected" );
	        return super.onOptionsItemSelected( item );
	    }
	}

	private void createComment() {
	
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
		
		String selectedEvent = getApplicationContext().getSelectedEventShorName();

		Object[] params = new Object[] { selectedEvent, getApplicationContext().getSelectedDay().getId(), getApplicationContext().getSelectedSchedule().getId(), getApplicationContext().getSelectedBlock().getId(), comment };
		new AddBlockCommentTask().execute( params );
	}

	private class AddBlockCommentTask extends AsyncTask<Object[], Void, Void> {
		
		private Exception exception;
		
		@Override
		protected void onPreExecute() {
			showProgressDialog(); 
		}
		
		@Override
		protected Void doInBackground( Object[]... params ) {
			try {
				getApplicationContext().getOpenScheduleApi().sessionOperations().addBlockComment( (String) params[ 0 ][ 0 ], (Integer) params[ 0 ][ 1 ], (Integer) params[ 0 ][ 2 ], (Integer) params[ 0 ][ 3 ], (Comment) params[ 0 ][ 4 ] );
			} catch( Exception e ) {
				Log.e( TAG, e.getLocalizedMessage(), e );
				exception = e;
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute( Void v ) {
			dismissProgressDialog();
			processException( exception );
		}
	}

}
