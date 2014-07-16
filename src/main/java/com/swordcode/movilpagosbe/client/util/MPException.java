package com.swordcode.movilpagosbe.client.util;

import java.io.Serializable;

/**
 *
 * @author euclidesflores
 */
public class MPException extends Exception implements Serializable
{
	public MPException() {
	}

	public MPException(String message) {
		super(message);
	}

	public MPException(String message, Throwable cause) {
		super(message, cause);
	}
}
