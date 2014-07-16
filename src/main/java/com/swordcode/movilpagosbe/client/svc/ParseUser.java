package com.swordcode.movilpagosbe.client.svc;

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
