package com.swordcode.movilpagosbe.client.svc;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 *
 * @author euclidesflores
 */
public interface ServiceInfoProperties extends PropertyAccess<ServiceInfo> {
	@Path("serviceID")
	ModelKeyProvider<ServiceInfo> key();

	@Path("description")
	LabelProvider<ServiceInfo> nameLabel();

	ValueProvider<ServiceInfo, String> serviceName();
	ValueProvider<ServiceInfo, Number> serviceType();
	ValueProvider<ServiceInfo, Number> processingType();
	ValueProvider<ServiceInfo, String> description();
	ValueProvider<ServiceInfo, Boolean> enabled();
	ValueProvider<ServiceInfo, Number> serviceID();
}
