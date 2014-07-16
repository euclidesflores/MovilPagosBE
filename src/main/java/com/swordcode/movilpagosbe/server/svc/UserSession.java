package com.swordcode.movilpagosbe.server.svc;

import com.swordcode.movilpagosbe.client.svc.auth.UserProfile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author euclidesflores
 */
public class UserSession implements HttpSessionListener {
	private UserProfile				_profile;
	private int						_currentRequestID;

	private final static String KEY_UserSession     = "session";

	public UserSession(UserProfile profile, HttpServletRequest request) {
		_profile = profile;
		setSession(request);
	}

	public int getCurrentRequestID() {
		return _currentRequestID;
	}

	void setCurrentRequestID(int requestID) {
		_currentRequestID = requestID;
	}

	private void setSession(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		if (httpSession != null) {
			httpSession.setAttribute(KEY_UserSession, this);
			SessionManager.getSessionManager().sessionCreated(this);
		}
	}

	public static UserSession getSession(HttpServletRequest request) {
		if (request != null) {
			HttpSession httpSession = request.getSession();
				if (httpSession != null) return (UserSession)httpSession.getAttribute(KEY_UserSession);
		}
		return null;
	}

	public void sessionCreated(HttpSessionEvent hse) {
	}

	public void sessionDestroyed(HttpSessionEvent hse) {
		SessionManager.getSessionManager().sessionDestroyed(this);
	}

}
