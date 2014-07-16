package com.swordcode.movilpagosbe.client.svc.auth;

import com.google.gwt.user.client.rpc.RemoteService;
import com.swordcode.movilpagosbe.client.util.MPException;

/**
 *
 * @author euclidesflores
 */
public interface AuthService extends RemoteService {
	public UserProfile signIn(String username, String password) throws MPException;
	public void signOut();
}
