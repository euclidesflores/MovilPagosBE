/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swordcode.movilpagosbe.server.parse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

/**
 *
 * @author euclidesflores
 */
public class ParseUser {
	private String					_fullName;
	private String					_userName;
	private String					_sessionToken;
	private String					_objectID;
	private String					_email;

	private ParseUser() {
	}

	public ParseUser(String fullName, String userName, String sessionToken, String objectID, String email) {
		_fullName = fullName;
		_userName = userName;
		_sessionToken = sessionToken;
		_objectID = objectID;
		_email = email;
	}

	public ParseUser find(String username, String password) throws ParseException {
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
//			URI uri = new URI(String.format("https://api.parse.com/1/login?username=\"%s\"&password=\"%s\"", URLEncoder.encode(username, "UTF8" ), URLEncoder.encode(password, "UTF8" )));
			HttpGet httpget = new HttpGet(
				"https://api.parse.com/1/login?username=" +
					URLEncoder.encode("\"" + username + "\"", "UTF-8") + "&password=" +
					URLEncoder.encode("\"" + password + "\"", "UTF-8")
			);
			httpget.addHeader("X-Parse-Application-Id", Parse.getApplicationId());
			httpget.addHeader("X-Parse-REST-API-Key", Parse.getRestAPIKey());
//			System.out.println("** Isabella " + uri.getQuery());
			HttpResponse httpResponse = httpclient.execute(httpget);
			ParseResponse parseResponse = new ParseResponse(httpResponse);
			if (parseResponse.isFailed())
			{
				System.out.println("response " + parseResponse.getException().getMessage());
				throw parseResponse.getException();
			}

			JSONObject obj = parseResponse.getJsonObject();

			if (obj == null)
			{
				System.out.println("response  obj is null");
				throw parseResponse.getException();
			}
			return new ParseUser();
		} catch (ClientProtocolException e)
		{
			throw ParseResponse.getConnectionFailedException(e);
		}
		catch (IOException e)
		{
			throw ParseResponse.getConnectionFailedException(e);
		}
	}

	public String getFullName() {
		return _fullName;
	}

	public String getUserName() {
		return _userName;
	}

	public String getSessionToken() {
		return _sessionToken;
	}

	public String getObjectID() {
		return _objectID;
	}

	public String getEmail() {
		return _email;
	}
}
