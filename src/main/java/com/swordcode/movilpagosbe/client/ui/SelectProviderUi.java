package com.swordcode.movilpagosbe.client.ui;

import com.sencha.gxt.widget.core.client.Window;

/**
 *
 * @author euclidesflores
 */
public class SelectProviderUi extends Window {

	public SelectProviderUi() {
		setPixelSize(710, 490);
		setModal(false);
		setClosable(false);
		setActive(true);
		setHeaderVisible(true);
		setHeadingHtml("New window");
		show();
	}
}
