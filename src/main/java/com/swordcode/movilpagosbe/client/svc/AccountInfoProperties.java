package com.swordcode.movilpagosbe.client.svc;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.Date;

/**
 *
 * @author euclidesflores
 */
public interface AccountInfoProperties extends PropertyAccess<Account>{
	@Editor.Path("accountID")
	ModelKeyProvider<Account> key();

	@Editor.Path("accountID")
	LabelProvider<Account> nameLabel();

	ValueProvider<Account, Number> serviceID();
	ValueProvider<Account, String> accountID();
	ValueProvider<Account, Double> amount();
	ValueProvider<Account, Boolean> enabled();
	ValueProvider<Account, String> userID();
	ValueProvider<Account, String> oid();
	ValueProvider<Account, String> customer();
	ValueProvider<Account, Date> dueDate();
}
