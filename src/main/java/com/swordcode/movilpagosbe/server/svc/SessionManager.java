package com.swordcode.movilpagosbe.server.svc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author euclidesflores
 */
public class SessionManager {
	private ArrayList<UserSession>		_sessions = new ArrayList<UserSession>();
	private static SessionManager		__sessionManager = new SessionManager();

	private SessionManager() {
	}

	public List<UserSession> getSessions() {
		return Collections.unmodifiableList(_sessions);
	}

	public void sessionCreated(UserSession s) {
		_sessions.add(s);
	}

	public void sessionDestroyed(UserSession s) {
		_sessions.remove(s);
	}

	public static SessionManager getSessionManager() {
		return __sessionManager;
	}
}
