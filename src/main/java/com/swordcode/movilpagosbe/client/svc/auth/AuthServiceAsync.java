package com.swordcode.movilpagosbe.client.svc.auth;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author euclidesflores
 */

public interface AuthServiceAsync {
	public void signIn(String username, String password, AsyncCallback<UserProfile> callback);
	public void signOut(AsyncCallback callback);
}
