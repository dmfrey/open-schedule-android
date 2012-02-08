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
import org.openschedule.api.Event;
import org.openschedule.api.Speaker;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Daniel Frey
 *
 */
public class SpeakerActivity extends AbstractOpenScheduleActivity {

	private static final String TAG = SpeakerActivity.class.getSimpleName();
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
	    Log.d( TAG, "onCreate : enter" );

	    super.onCreate( savedInstanceState );
		
		setContentView( R.layout.speaker );

		final ListView listView = (ListView) findViewById( R.id.speaker_details_menu );
		
		String[] menu_items = getResources().getStringArray( R.array.speaker_options_array );
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.menu_list_item, menu_items );
		listView.setAdapter( arrayAdapter );
		
		listView.setOnItemClickListener( new OnItemClickListener() {
		    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
		    	
		    	try {
		    		Intent intent = new Intent();

		    		Speaker speaker = getApplicationContext().getSelectedSpeaker();
		    		switch( position ) {
		    		case 0:
		    			Log.d( TAG, "display biography" );

		    			if( null != speaker.getBio() && !"".equals( speaker.getBio() ) ) {
		    				intent.setClass( view.getContext(), SpeakerBiographyActivity.class );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The speaker has not provided a biography.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}

		    			break;
		    		case 1:
		    			Log.d( TAG, "display web site in browser" );

		    			if( null != speaker.getWebSite() && !"".equals( speaker.getWebSite() ) ) {
		    				Uri uri = Uri.parse( speaker.getWebSite() );
		    				intent = new Intent( Intent.ACTION_VIEW, uri );

		    				Log.v( TAG, "starting display web site intent" );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The speaker has not provided a web site.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}

		    			break;
		    		case 2:
		    			Log.d( TAG, "send email" );

		    			if( null != speaker.getEmail() && !"".equals( speaker.getEmail() ) ) {
		    				Event event = getApplicationContext().getSelectedEvent();

		    				intent = new Intent( Intent.ACTION_SEND );
		    				intent.putExtra( Intent.EXTRA_EMAIL, new String[] { speaker.getEmail() } );
		    				intent.putExtra( Intent.EXTRA_SUBJECT, event.getName() );
		    				intent.setType( "plain/text" );

		    				Log.v( TAG, "starting send email intent" );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The speaker has not provided an email.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}

		    			break;
		    		default:
		    			Log.d( TAG, "default option" );
		    			break;
		    		}

		    		startActivity( intent );
		    	} catch( ActivityNotFoundException e ) {
		    		Log.w( TAG, "onItemClick : no activity specified" );
		    	}
		    }
		});

		Log.d( TAG, "onCreate : exit" );
	}

	@Override
	protected void onStart() {
	    Log.d( TAG, "onStart : enter" );

	    super.onStart();
		
		refreshSpeaker();

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	protected void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();
		
		refreshSpeaker();

		Log.d( TAG, "onResume : exit" );
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

	//***************************************
    // Private methods
    //***************************************
	private void refreshSpeaker() {
		Log.d( TAG, "Refreshing Session : enter" );

		Speaker speaker = getApplicationContext().getSelectedSpeaker();
		if( null == speaker ) {
			Log.d( TAG, "Refreshing Speaker : exit, no speaker" );

			return;
		}
		
		final TextView speakerNameTextView = (TextView) findViewById( R.id.speaker_name_text_view );
		final TextView speakerEmailTextView = (TextView) findViewById( R.id.speaker_email_text_view );
		final TextView speakerWebSiteTextView = (TextView) findViewById( R.id.speaker_web_site_text_view );

		speakerNameTextView.setText( speaker.getName() );
		
		if( null != speaker.getEmail() && !"".equals( speaker.getEmail() ) ) {
			speakerEmailTextView.setText( speaker.getEmail() );
		}
		
		if( null != speaker.getWebSite() && !"".equals( speaker.getWebSite() ) ) {
			speakerWebSiteTextView.setText( speaker.getWebSite() );
		}
		
		Log.d( TAG, "Refreshing Session : exit" );
	}
	
}
