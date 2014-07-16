
package com.swordcode.movilpagosbe.server.svc;

import com.swordcode.movilpagosbe.client.svc.auth.AuthService;
import com.swordcode.movilpagosbe.client.svc.auth.UserProfile;
import com.swordcode.movilpagosbe.client.util.MPException;
import com.swordcode.movilpagosbe.server.parse.Parse;
import com.swordcode.movilpagosbe.server.parse.ParseException;
import com.swordcode.movilpagosbe.server.parse.ParseObject;
import com.swordcode.movilpagosbe.server.parse.ParseQuery;
import com.swordcode.movilpagosbe.server.parse.ParseUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author euclidesflores
 */
public class AuthServiceImpl extends MPServiceServlet implements AuthService
{
	private final static String PARSE_APPLICATION_ID =
									"Parse ID";
	private final static String PARSE_CLIENT_KEY =
									"Parse client key";

	public UserProfile signIn(String username, String password) throws MPException {
//		Parse.initialize(PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
//		ParseUser user = new ParseUser();
//		try {
//			user = user.find(username, password);
//		} catch (ParseException ex) {
//			System.out.println("ex " + ex.getMessage());
//		}
//		ParseQuery query = new ParseQuery("Events");
////		query.whereEqualTo("username", username);
////		query.whereEqualTo("password", password);
//		try {
//			List<ParseObject> list = query.find();
//			System.out.println("I have a list " + list.size());
//		} catch (ParseException ex) {
//			System.out.println("Error message " + ex.getMessage());
//		}
		return null;

	}

	public void signOut() {
	}

}
