package com.swordcode.movilpagosbe.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

/**
 *
 * @author euclidesflores
 */
public class MyExample implements IsWidget {
	private static MyUiBinder _uiBinder = GWT.create(MyUiBinder.class);
	private Widget			_widget;

	public Widget asWidget() {
		if (_widget == null) {
			_widget = _uiBinder.createAndBindUi(this);
		}
		return _widget;
	}

	interface MyUiBinder extends UiBinder<Component, MyExample> {
	}

	@UiField
	BorderLayoutContainer con;

	public MyExample() {
	}

}
