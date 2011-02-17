/**
 * 
 */
package org.openschedule.activities.twitter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

import org.openschedule.domain.Block;
import org.openschedule.domain.Event;
import org.openschedule.util.SharedDataManager;
import org.openschedule.util.TwitterPrefs;
import org.springframework.social.twitter.TwitterOperations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author dmfrey
 *
 */
public class TwitterActivity extends Activity {
	
	private static final String TAG = "OAuthActivity";
	private SharedPreferences _settings;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		Log.d( TAG, "onCreate : enter" );
		
		super.onCreate( savedInstanceState );
		
		if( null == getIntent().getData() ) {
			Log.v( TAG, "onCreate : getIntent().getData() is null" );

			try {
				_settings = getSharedPreferences( TwitterPrefs.PREFS, Context.MODE_PRIVATE );
				
				if( TwitterPrefs.isLoggedIn( _settings ) ) {
					Log.d( TAG, "onCreate : already logged into twitter" );
					
					postTweet();
					
					Log.d( TAG, "onCreate : exit, already logged into twitter and tweet posted" );
					finish();
				} else {
				
					Log.v( TAG, "onCreate : TwitterPrefs.getConsumerKey=" + TwitterPrefs.getConsumerKey() );
					Log.v( TAG, "onCreate : TwitterPrefs.getConsumerSecret=" + TwitterPrefs.getConsumerSecret() );

					Log.v( TAG, "onCreate : TwitterPrefs.getRequestTokenUrl=" + TwitterPrefs.getRequestTokenUrl() );
					Log.v( TAG, "onCreate : TwitterPrefs.getAccessTokenUrl=" + TwitterPrefs.getAccessTokenUrl() );
					Log.v( TAG, "onCreate : TwitterPrefs.getAuthorizationUrl=" + TwitterPrefs.getAuthorizationUrl() );

					// signpost
					OAuthConsumer oauthConsumer = new CommonsHttpOAuthConsumer( TwitterPrefs.getConsumerKey(), TwitterPrefs.getConsumerSecret() );
					OAuthProvider oauthProvider = new CommonsHttpOAuthProvider( TwitterPrefs.getRequestTokenUrl(), TwitterPrefs.getAccessTokenUrl(), TwitterPrefs.getAuthorizationUrl() );
					oauthProvider.setOAuth10a( true );

					String authUrl = oauthProvider.retrieveRequestToken( oauthConsumer, TwitterPrefs.CALLBACK_URI_STRING  );
					String requestTokenValue = oauthConsumer.getToken();
					String requestTokenSecret = oauthConsumer.getTokenSecret();
					Uri uri = Uri.parse( authUrl );

					Log.v( TAG, "onCreate : requestTokenValue=" + requestTokenValue );
					Log.v( TAG, "onCreate : requestTokenSecret=" + requestTokenSecret );
					TwitterPrefs.saveRequestInformation( _settings, requestTokenValue, requestTokenSecret );

					Intent intent = new Intent( Intent.ACTION_VIEW, uri );
					startActivity( intent );
					finish();
				}
			} catch( Exception e ) {
				Log.e( "ErrorHandler", e.getMessage(), e );

				Writer result = new StringWriter();
				e.printStackTrace( new PrintWriter( result ) );
			}
		}

		Log.d( TAG, "onCreate : exit" );
	}
	
	@Override
	protected void onResume() {
		Log.d( TAG, "onResume : enter" );

		super.onResume();
		
		Uri uri = getIntent().getData();
		if( uri == null || !TwitterPrefs.getCallbackUri().getScheme().equals( uri.getScheme() ) ) {
			Log.d( TAG, "onResume : exit, uri == null or callback URI does not equal" );
			
			return;
		}
		
		Log.v( TAG, "onResume : uri=" + uri.toString() );

		_settings = getSharedPreferences( TwitterPrefs.PREFS, Context.MODE_PRIVATE );
		String[] tokenAndSecret = TwitterPrefs.getRequestTokenAndSecret( _settings );
		String requestTokenValue = tokenAndSecret[ 0 ];
		String requestTokenSecret = tokenAndSecret[ 1 ];

		try {
			String verifierValue = uri.getQueryParameter( OAuth.OAUTH_VERIFIER );

			// signpost
			OAuthConsumer oauthConsumer = new CommonsHttpOAuthConsumer( TwitterPrefs.getConsumerKey(), TwitterPrefs.getConsumerSecret() );
			oauthConsumer.setTokenWithSecret( requestTokenValue, requestTokenSecret );
			OAuthProvider oauthProvider = new CommonsHttpOAuthProvider( TwitterPrefs.getRequestTokenUrl(), TwitterPrefs.getAccessTokenUrl(), TwitterPrefs.getAuthorizationUrl() );
			oauthProvider.setOAuth10a( true );
			oauthProvider.retrieveAccessToken( oauthConsumer, verifierValue );
			
			String accessTokenValue = oauthConsumer.getToken();
			Log.v( TAG, "onResume : accessTokenValue=" + accessTokenValue );

			String accessTokenSecret = oauthConsumer.getTokenSecret();
			Log.v( TAG, "onResume : accessTokenSecret=" + accessTokenSecret );

			TwitterPrefs.saveAuthInformation( _settings, accessTokenValue, accessTokenSecret );
			
			// Clear the request stuff, now that we have the real thing
			TwitterPrefs.resetRequestInformation( _settings );
		}
		catch( Exception e ) {
			Log.e( TAG, e.getMessage(), e );
			Writer result = new StringWriter();
			e.printStackTrace( new PrintWriter( result ) );
		}
		finally {
			postTweet();
			finish();
		}

		Log.d( TAG, "onResume : exit" );
	}

	private void postTweet() {
		Log.v( TAG, "postTweet : enter" );
		
		TwitterOperations twitter = TwitterPrefs.getTwitterOperations( _settings );
		
		String greeting = "I am attending: ";
		
		Event event = SharedDataManager.getCurrentEvent();
		String hashTag = " #" + event.getShortName();
		
		Block block = SharedDataManager.getCurrentBlock();
		String session = block.getSession().getName();
		
		if( ( greeting.length() + session.length() + hashTag.length() ) > 140 ) {
			int totalChars = greeting.length() + session.length() + hashTag.length();
			int stripChars = greeting.length() + hashTag.length();
			
			while( totalChars - stripChars > 140 ) {
				stripChars++;
			}
			
			session = session.substring( 0, stripChars - 1 );
		}
		
		StringBuilder status = new StringBuilder();
		status.append( greeting ).append( session ).append( hashTag );
		Log.d( TAG, "postTweet=" + status.toString() + ", length=" + status.toString().length() );
		
		twitter.updateStatus( status.toString() );
		Toast toast = Toast.makeText( this, "Posted to Twitter: " + status.toString(), Toast.LENGTH_LONG );
		toast.setGravity( Gravity.CENTER, 0, 0 );
		toast.show();

		Log.v( TAG, "postTweet : exit" );
	}

}
