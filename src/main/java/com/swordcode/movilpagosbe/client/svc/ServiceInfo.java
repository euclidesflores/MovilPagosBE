/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swordcode.movilpagosbe.client.svc;

import java.io.Serializable;

/**
 *
 * @author euclidesflores
 */
public class ServiceInfo implements Serializable {
	private String					_serviceName;
	private Number					_serviceType;
	private Number					_processingType;
	private String					_description;
	private boolean					_enabled;
	private Number					_serviceID;

	private ServiceInfo() {
	}

	public ServiceInfo(String serviceName, Number serviceType, Number processingType,
					   String description, boolean enabled, Number serviceID) {
		_serviceName = serviceName;
		_serviceType = serviceType;
		_processingType = processingType;
		_description = description;
		_enabled = enabled;
		_serviceID = serviceID;
	}

	/**
	 * @return the _serviceName
	 */
	public String getServiceName() {
		return _serviceName;
	}

	/**
	 * @return the _serviceType
	 */
	public Number getServiceType() {
		return _serviceType;
	}

	/**
	 * @return the _processingType
	 */
	public Number getProcessingType() {
		return _processingType;
	}

	/**
	 * @return the _description
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * @return the _enabled
	 */
	public boolean isEnabled() {
		return _enabled;
	}

	/**
	 * @return the _serviceID
	 */
	public Number getServiceID() {
		return _serviceID;
	}
}
