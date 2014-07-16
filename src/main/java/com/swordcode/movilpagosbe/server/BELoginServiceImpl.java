/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swordcode.movilpagosbe.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.swordcode.movilpagosbe.client.services.BELoginService;

/**
 *
 * @author euclidesflores
 */
public class BELoginServiceImpl extends RemoteServiceServlet implements BELoginService {

	public String myMethod(String s) {
		// Do something interesting with 's' here on the server.
		return "Server says: " + s;
	}
}
