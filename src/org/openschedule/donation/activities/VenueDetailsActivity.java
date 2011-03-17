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
package org.openschedule.donation.activities;

import org.openschedule.donation.R;
import org.openschedule.donation.controllers.NavigationManager;
import org.openschedule.donation.domain.Event;
import org.openschedule.donation.domain.Venue;
import org.openschedule.donation.util.SharedDataManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
 * @author dmfrey
 *
 */
public class VenueDetailsActivity extends Activity {

	private static final String TAG = "VenueDetailsActivity";
	
	//***************************************
	// Activity methods
	//***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );

		super.onCreate( savedInstanceState );
		
		setContentView( R.layout.venue_details );
		
		final ListView listView = (ListView) findViewById( R.id.venue_details_menu );
		
		String[] menu_items = getResources().getStringArray( R.array.venue_options_array );
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.menu_list_item, menu_items );
		listView.setAdapter( arrayAdapter );
		
		listView.setOnItemClickListener( new OnItemClickListener() {
		    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
		    	
				Venue venue = SharedDataManager.getCurrentVenue();
				switch( position ) {
		    		case 0:
		    			Log.d( TAG, "display web site in browser" );

		    			if( null != venue.getWebSite() && !"".equals( venue.getWebSite() ) ) {
		    				Uri uri = Uri.parse( venue.getWebSite() );
		    				Intent intent = new Intent( Intent.ACTION_VIEW, uri );

		    				Log.v( TAG, "starting display web site intent" );
		    				NavigationManager.startActivity( view.getContext(), intent );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The venue does not have a web site defined.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}
		    			
		    			break;
			      	case 1:
			      		Log.d( TAG, "initiate phone call" );

		    			if( null != venue.getPhone() && !"".equals( venue.getPhone() ) ) {
		    				String phone = venue.getPhone();
		    				phone = phone.replace( "(", "" );
		    				phone = phone.replace( ")", "" );
		    				phone = phone.replace( "-", "" );
		    				phone = phone.replace( ".", "" );
		    				phone = phone.replace( " ", "" );
		    				
		    				Uri uri = Uri.parse( "tel:" + phone );
		    				Intent intent = new Intent( Intent.ACTION_DIAL, uri );

		    				Log.v( TAG, "starting intiate phone call intent" );
		    				NavigationManager.startActivity( view.getContext(), intent );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The venue does not have phone number specified.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}

		    			break;
			      	case 2:
			      		Log.d( TAG, "send email" );

		    			if( null != venue.getEmail() && !"".equals( venue.getEmail() ) ) {
		    				Event event = SharedDataManager.getCurrentEvent();
		    				
		    				Intent intent = new Intent( Intent.ACTION_SEND );
		    				intent.putExtra( Intent.EXTRA_EMAIL, new String[] { venue.getEmail() } );
		    				intent.putExtra( Intent.EXTRA_SUBJECT, event.getName() );
		    				intent.setType( "plain/text" );
		    				
		    				Log.v( TAG, "starting send email intent" );
		    				NavigationManager.startActivity( view.getContext(), intent );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The venue does not have an email address defined.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}

		    			break;
			      	case 3:
			      		Log.d( TAG, "navigate to venue" );

			      		if( Build.VERSION.SDK_INT > 4 ) {
			      			if( ( null != venue.getAddressOne() && !"".equals( venue.getAddressOne() ) ) ||
			      					( null != venue.getCity() && !"".equals( venue.getCity() ) ) ||
			      					( null != venue.getState() && !"".equals( venue.getState() ) ) ||
			      					( null != venue.getZip() && !"".equals( venue.getZip() ) )
			      			) {
			      				String address = venue.getAddressOne() + "+" + venue.getCity() + "+" + venue.getState() + "+" + venue.getZip();

			      				Uri uri = Uri.parse( "geo:0,0?q=" + address );
			      				Intent intent = new Intent( Intent.ACTION_VIEW, uri );

			      				Log.v( TAG, "starting navigate intent" );
			      				NavigationManager.startActivity( view.getContext(), intent );
			      			} else {
			      				Toast toast = Toast.makeText( view.getContext(), "The address for this venue has not been specified.", Toast.LENGTH_LONG );
			      				toast.setGravity( Gravity.CENTER, 0, 0 );
			      				toast.show();
			      			}
			      		} else {
		      				Toast toast = Toast.makeText( view.getContext(), "Navigation is only available on Android 2.1 and above.", Toast.LENGTH_LONG );
		      				toast.setGravity( Gravity.CENTER, 0, 0 );
		      				toast.show();
			      		}
			      		
			      		break;
			      	default:
			      		Log.d( TAG, "default option" );
			      		break;
		    	}
		    }
		});

		Log.d( TAG, "onCreate : exit" );
	}
	
	@Override
	public void onStart() {
		Log.d( TAG, "onStart : enter" );

		super.onStart();

		refreshVenueDetails();

		Log.d( TAG, "onStart : exit" );
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
	    	NavigationManager.startActivity( this, AboutActivity.class );

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
	private void refreshVenueDetails() {
		Log.d( TAG, "Refreshing Venue Details : enter" );

		final TextView venueName = (TextView) findViewById( R.id.venue_name );
		final TextView venueAddressOne = (TextView) findViewById( R.id.venue_address_one );
		final TextView venueAddressTwo = (TextView) findViewById( R.id.venue_address_two );
		final TextView venueCityStateZip = (TextView) findViewById( R.id.venue_city_state_zip );
		final TextView venuePhone = (TextView) findViewById( R.id.venue_phone );
		final TextView venueEmail = (TextView) findViewById( R.id.venue_email );
		final TextView venueWebSite = (TextView) findViewById( R.id.venue_web_site );
		
		Venue venue = SharedDataManager.getCurrentVenue();
		
		if( null == venue ) {
			Log.d( TAG, "Refreshing Venue Details : exit, no venue" );
			return;
		}
		
		venueName.setText( venue.getName() );

		if( null != venue.getAddressOne() && !"".equals( venue.getAddressOne() ) ) {
			venueAddressOne.setText( venue.getAddressOne() );
			venueAddressOne.setVisibility( TextView.VISIBLE );
		}
		
		if( null != venue.getAddressTwo() && !"".equals( venue.getAddressTwo() ) ) {
			venueAddressTwo.setText( venue.getAddressTwo() );
			venueAddressTwo.setVisibility( TextView.VISIBLE );
		}

		if( ( null != venue.getCity() && !"".equals( venue.getCity() ) ) ||
				( null != venue.getState() && !"".equals( venue.getState() ) ) ||
				( null != venue.getZip() && !"".equals( venue.getZip() ) )
		) {
			boolean comma = false;
			String cityStateZip = "";
			if( null != venue.getCity() && !"".equals( venue.getCity() ) ) {
				cityStateZip += venue.getCity();
				
				comma = true;
			}
			
			if( null != venue.getState() && !"".equals( venue.getState() ) ) {
				if( comma ) {
					cityStateZip += ", ";
				}
				
				cityStateZip += venue.getState();
				
				comma = true;
			}

			if( null != venue.getZip() && !"".equals( venue.getZip() ) ) {
				if( comma ) {
					cityStateZip += ", ";
				}
				
				cityStateZip += venue.getZip();
			}

			venueCityStateZip.setText( cityStateZip );
			venueCityStateZip.setVisibility( TextView.VISIBLE );
		}
	
		if( null != venue.getPhone() && !"".equals( venue.getPhone() ) ) {
			venuePhone.setText( venue.getPhone() );
			venuePhone.setVisibility( TextView.VISIBLE );
		}

		if( null != venue.getEmail() && !"".equals( venue.getEmail() ) ) {
			venueEmail.setText( venue.getEmail() );
			venueEmail.setVisibility( TextView.VISIBLE );
		}

		if( null != venue.getWebSite() && !"".equals( venue.getWebSite() ) ) {
			venueWebSite.setText( venue.getWebSite() );
			venueWebSite.setVisibility( TextView.VISIBLE );
		}

		Log.d( TAG, "Refreshing Venue Details : exit" );
	}

}

