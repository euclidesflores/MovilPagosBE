/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swordcode.movilpagosbe.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author euclidesflores
 */
@RemoteServiceRelativePath("beloginservice")
public interface BELoginService extends RemoteService {

	public String myMethod(String s);
}
