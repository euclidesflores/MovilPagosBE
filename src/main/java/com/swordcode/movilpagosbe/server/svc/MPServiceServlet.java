package com.swordcode.movilpagosbe.server.svc;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.swordcode.movilpagosbe.client.util.MPException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author euclidesflores
 */
public class MPServiceServlet extends RemoteServiceServlet {

	@Override
	protected void onAfterResponseSerialized(String response) {
		super.onAfterResponseSerialized(response);
	}

	public UserSession getSession() throws MPException
	{
		UserSession session = UserSession.getSession(getThreadLocalRequest());
		if (session == null) throw new MPException("Your session has expired");
		return session;
	}

	@Override
	protected SerializationPolicy doGetSerializationPolicy(HttpServletRequest request, String moduleBaseURL, String strongName) {
		if (moduleBaseURL.startsWith("app:/")) {
			StringBuilder url = new StringBuilder();
			url.append(request.isSecure() ? "https://" : "http://");
			url.append(request.getLocalName());
			url.append(':');
			url.append(request.getLocalPort());
			url.append(request.getContextPath());
			url.append(moduleBaseURL.substring(4));
			moduleBaseURL = url.toString();
		}
		return super.doGetSerializationPolicy(request, moduleBaseURL, strongName);
	}
}
