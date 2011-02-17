/**
 * 
 */
package org.openschedule.activities;

import org.openschedule.R;
import org.openschedule.domain.Speaker;
import org.openschedule.util.SharedDataManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author dmfrey
 *
 */
public class SpeakerBiographyActivity extends Activity {

	private static final String TAG = "SpeakerBiographyActivity";
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
	    Log.d( TAG, "onCreate : enter" );

	    super.onCreate( savedInstanceState );
		
		setContentView( R.layout.speaker_biography );

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

	//***************************************
    // Private methods
    //***************************************
	private void refreshSpeaker() {
		Log.d( TAG, "Refreshing Speaker : enter" );

		Speaker speaker = SharedDataManager.getCurrentSpeaker();
		if( null == speaker ) {
			Log.d( TAG, "Refreshing Speaker : exit, no speaker" );

			return;
		}
		
		final TextView speakerBiographyTextView = (TextView) findViewById( R.id.speaker_biography_textview );

		speakerBiographyTextView.setText( speaker.getBio() );
		
		Log.d( TAG, "Refreshing Speaker : exit" );
	}
	
}
