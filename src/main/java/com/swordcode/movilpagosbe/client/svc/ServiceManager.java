/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swordcode.movilpagosbe.client.svc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.swordcode.movilpagosbe.client.svc.auth.AuthService;
import com.swordcode.movilpagosbe.client.svc.auth.AuthServiceAsync;
import com.swordcode.movilpagosbe.client.xhr.SimpleURL;
import java.util.List;

/**
 *
 * @author euclidesflores
 */
public class ServiceManager {
	private static ServiceManager                           __svcManager = new ServiceManager();
	private AuthServiceAsync								_authService;
	private SimpleURL										_baseURL;
	private static ParseUser								_parseUser;
	private static List<Account>							_accounts;


	private ServiceManager() {
		setServiceBaseURL(GWT.getHostPageBaseURL());
	}

	public SimpleURL getServiceBaseURL() {
		return _baseURL;
	}

	public static void setParseUser(ParseUser parseUser) {
		_parseUser = parseUser;
	}

	public static ParseUser getParseUser() {
		return _parseUser;
	}

	public final void setServiceBaseURL(String baseURL) {
		_baseURL = new SimpleURL(baseURL);
	}

	private String getServiceURL(String serviceName) {
		return getServiceBaseURL().addPath(serviceName).toString();
	}

	public AuthServiceAsync getAuthService() {
		if (_authService == null) {
			_authService = (AuthServiceAsync)GWT.create(AuthService.class);
			((ServiceDefTarget)_authService).setServiceEntryPoint(getServiceURL("auth"));
		}
		return _authService;
	}

	public final static ServiceManager getServiceManager() {
		return __svcManager;
	}

	public static List<Account> getAccounts() {
		return _accounts;
	}

	public static void setAccounts(List<Account> accounts) {
		_accounts = accounts;
	}
}
