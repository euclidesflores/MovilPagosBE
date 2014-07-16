package com.swordcode.movilpagosbe.client.svc;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author euclidesflores
 */
public class Account implements Serializable {
	private Number			_serviceID;
	private String			_accountID;
	private Number			_amount;
	private boolean			_enabled;
	private String			_userID;
	private String			_oid;
	private String			_customer;
	private Date			_dueDate;

	private Account() {
	}

	public Account(Number serviceID, String accountID, Number amount,
					boolean enabled, String userID, String oid, String customer, Date dueDate) {
		_serviceID = serviceID;
		_accountID = accountID;
		_amount = amount;
		_enabled = enabled;
		_userID = userID;
		_oid = oid;
		_customer = customer;
		_dueDate = dueDate;
	}

	public Number getServiceID() {
		return _serviceID;
	}

	public String getAccountID() {
		return _accountID;
	}

	public Double getAmount() {
		return _amount.doubleValue();
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public String getUserID() {
		return _userID;
	}

	public String getOid() {
		return _oid;
	}

	public String getCustomer() {
		return _customer;
	}

	public Date getDueDate() {
		return _dueDate;
	}

}
