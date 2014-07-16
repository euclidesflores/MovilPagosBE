/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swordcode.movilpagosbe.client.svc.auth;

import java.io.Serializable;

/**
 *
 * @author euclidesflores
 */
public class UserProfile implements Serializable {
		private	String						_userID;
		private String						_signInName;
		private String						_fullName;
		private String						_email;

	private UserProfile() {
	}

	public UserProfile(String userID, String signInName, String fullName, String email) {
		_userID = userID;
		_signInName = signInName;
		_fullName = fullName;
		_email = email;
	}

	public String getUserID() {
		return _userID;
	}

	public String getSignInName() {
		return _signInName;
	}

	public String getFullName() {
		return _fullName;
	}

	public String getEmail() {
		return _email;
	}

	private static final long serialVersionUID = 1;
}
