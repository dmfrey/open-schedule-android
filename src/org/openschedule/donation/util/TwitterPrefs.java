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
package org.openschedule.donation.util;

import org.springframework.social.twitter.TwitterTemplate;

import android.content.SharedPreferences;
import android.net.Uri;

/**
 * @author dmfrey
 *
 */
public class TwitterPrefs {

	public static final String TWITTER_SERVER_BASE_URL = "https://api.twitter.com";

	public static final String PREFS = "TwitterPreferences";

	public static final String REQUEST_TOKEN = "request_token";
	public static final String REQUEST_SECRET = "request_secret";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String ACCESS_TOKEN_SECRET = "access_token_secret";
	private static final String API_KEY = "MZ3VfGgAZpOgZeZru7zlQ";
	private static final String API_SECRET = "ALtxkb5XXQqW2LpNhqVrCNEmrkmsQQcJeLeO4wqxg";

	public static final String CALLBACK_URI_STRING = "x-org-openschedule://twitter-response";

	public static void saveRequestInformation( final SharedPreferences settings, final String token, final String secret ) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putString( REQUEST_TOKEN, token );
		editor.putString( REQUEST_SECRET, secret );
		editor.commit();
	}

	public static String getRequestTokenUrl() {
		return getUrlBase() + "/oauth/request_token";
	}

	public static String getAccessTokenUrl() {
		return getUrlBase() + "/oauth/access_token";
	}

	public static String getAuthorizationUrl() {
		return getUrlBase() + "/oauth/authorize";
	}
	
	public static String[] getRequestTokenAndSecret( final SharedPreferences settings ) {
		 String token = settings.getString( TwitterPrefs.REQUEST_TOKEN, null );
		 String secret = settings.getString( TwitterPrefs.REQUEST_SECRET, null );
		 return new String[] { token, secret };
	}

	public static void saveAuthInformation( final SharedPreferences settings, final String token, final String secret ) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putString( ACCESS_TOKEN, token );
		editor.putString( ACCESS_TOKEN_SECRET, secret );
		editor.commit();
	}
	
	public static String[] getAccessTokenAndSecret( final SharedPreferences settings ) {
		String token = null;
		String tokenSecret = null;
		if( settings.contains( ACCESS_TOKEN ) && settings.contains( ACCESS_TOKEN_SECRET ) ) {
			token = settings.getString( ACCESS_TOKEN, null );
			tokenSecret = settings.getString( ACCESS_TOKEN_SECRET, null );
		}
		return new String[] { token, tokenSecret };
	}
	
	public static void resetRequestInformation( final SharedPreferences settings ) {
		SharedPreferences.Editor editor = settings.edit();
		editor.remove( REQUEST_TOKEN );
		editor.remove( REQUEST_SECRET );
		editor.commit();
	}
	
	public static void disconnect( final SharedPreferences settings ) {
		SharedPreferences.Editor editor = settings.edit();
		editor.remove( ACCESS_TOKEN );
		editor.remove( ACCESS_TOKEN_SECRET );
		editor.commit();	
	}
	
	public static boolean isLoggedIn( final SharedPreferences settings ) {
		String[] tokenAndSecret = getAccessTokenAndSecret( settings );
		return tokenAndSecret[ 0 ] != null && tokenAndSecret[ 1 ] != null;
	}
	
	public static Uri getAuthorizationUrl( String requestToken ) {
		return Uri.parse( getAuthorizationUrl() + "?oauth_token=" + requestToken);
	}
	
	public static String getConsumerKey() {
		return API_KEY;
	}
	
	public static String getConsumerSecret() {
		return API_SECRET;
	}

	public static String getUrlBase() {
		return TWITTER_SERVER_BASE_URL;
	}

	public static Uri getCallbackUri() {
		return Uri.parse( CALLBACK_URI_STRING );
	}

	public static TwitterTemplate getTwitterTemplate( final SharedPreferences settings ) {
		String[] tokenAndSecret = TwitterPrefs.getAccessTokenAndSecret( settings );
		return new TwitterTemplate( API_KEY, API_SECRET, tokenAndSecret[ 0 ], tokenAndSecret[ 1 ] );
	}

}
