package com.swordcode.movilpagosbe.client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.XElement;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Function;
import com.google.gwt.user.client.Event;
import static com.google.gwt.query.client.GQuery.$;
import com.google.gwt.user.client.ui.RootPanel;
import com.swordcode.movilpagosbe.client.svc.Account;
import com.swordcode.movilpagosbe.client.svc.ParseUser;
import com.swordcode.movilpagosbe.client.svc.ServiceManager;
import com.swordcode.movilpagosbe.client.ui.MPViewPort;
import com.swordcode.movilpagosbe.client.util.JsonUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Main entry point.
 *
 * @author euclidesflores
 */
public class Main implements IsWidget, EntryPoint {

	private boolean					_valid;
	private String					_userNameStr;
	private String					_passwordStr;
	private ParseUser				_parseUser;
	private List<Account>			_accounts = new ArrayList<Account>();

	/**
	 * Creates a new instance of Main
	 */
	public Main() {
	}

	/**
	 * The entry point method, called automatically by loading a module that
	 * declares an implementing class as an entry-point
	 */
	public void onModuleLoad() {
		final XElement panel = (XElement) DOM.getElementById("ldrpanel");
		final XElement userName = (XElement) DOM.getElementById("login_username");
		final XElement loginUi = (XElement) DOM.getElementById("login");
		userName.focus();

		$("#login").submit(new Function() {
			@Override
			public boolean f(Event e) {
				_valid = true;
				final String errorMsg = "Este campo es requerido";
				final String errorCn = "error";

				$("." + errorCn).remove();
				$(".required").each(new Function() {
					@Override
					public void f() {
						GQuery parent = $(this).parent();
						String id = $(this).attr("id");
						if (id.equals("login_username")) _userNameStr = $(this).val();
						if (id.equals("login_password")) _passwordStr = $(this).val();
						if ($(this).val().equals("") || $(this).val().isEmpty()) {
							String msg = $(this).attr("title");
							msg = msg.isEmpty() ? errorMsg : msg;
							$("<span class=" + errorCn + ">" + msg
									+ "</span>").appendTo(parent).fadeIn(10).click(new Function() {
								@Override
								public void f() {
									$(this).remove();
								}
							});
							_valid = false;
						}
					}
				});

				if (_valid) {
					String url = "https://api.parse.com/1/login?username=" + _userNameStr + "&password=" + _passwordStr;
					RequestBuilder b = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
					b.setHeader("X-Parse-Application-Id", "Parse_Application_Id");
					b.setHeader("X-Parse-REST-API-Key", "Parse_Api_Key");
					b.setCallback(new RequestCallback() {
						public void onResponseReceived(Request request, Response response) {
							if (response.getStatusCode() == Response.SC_OK) {
								$("#ldrpanel").removeClass("hidden");
								$("#login").addClass("hidden");
								JSONValue value = JSONParser.parseStrict(response.getText());
								JSONObject object = value.isObject();
								String un = object.get("username").isString().stringValue();
								String fn = object.get("additional").isString().stringValue();
								String em = object.get("email").isString().stringValue();
								String id = object.get("objectId").isString().stringValue();
								String tk = object.get("sessionToken").isString().stringValue();
								_parseUser = new ParseUser(fn, un, tk, id, em);
								ServiceManager.setParseUser(_parseUser);
								findAccounts();
							} else {
								open();
							}
						}

						public void onError(Request request, Throwable exception) {
							Window.alert("An error occured while requesting the home page"
									+ " from the server. " + exception.getMessage());
						}
					});
					try {
						b.send();
					} catch (RequestException ex1) {
						Window.alert("An error occured while requesting the home page"
								+ " from the server. " + ex1.getMessage());
					}
				}
				return false;
			}
		});

	}

	private void findAccounts() {
		String whereStr = JsonUtils.getURLConstraints(true, "userID", _parseUser.getObjectID());
		String url = "https://api.parse.com/1/classes/account" + whereStr;
		RequestBuilder b = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		b.setHeader("X-Parse-Application-Id", "Parse_Application_Id");
		b.setHeader("X-Parse-REST-API-Key", "Parse_Api_Key");
		b.setCallback(new RequestCallback() {
			public void onResponseReceived(Request request, Response response) {
				$("#ldrpanel").addClass("hidden");
				if (response.getStatusCode() == Response.SC_OK) {
					JSONValue value = JSONParser.parseStrict(response.getText());
					JSONObject obj = value.isObject();
					JSONArray array = obj.get("results").isArray();
					if (array != null) {
						for (int i=0; i < array.size(); i++) {
							JSONObject o = array.get(i).isObject();
							Number serviceID = o.get("ServiceID").isNumber().doubleValue();
							String accountID = o.get("accountID").isString().stringValue();
							Number amount = o.get("amount_due").isNumber().doubleValue();
							boolean enabled = o.get("enabled").isBoolean().booleanValue();
							String uid = o.get("userID").isString().stringValue();
							String oid = o.get("objectId").isString().stringValue();
							String customer = o.get("customer") == null ? "" : o.get("customer").isString().stringValue();
							Date dueDate;
							if (o.get("due_date") == null) {
								String objDate =  o.get("createdAt").isString().stringValue();
								dueDate= JsonUtils.getJsonDate(objDate);
							} else {
								JSONObject objDate = o.get("due_date").isObject();
								JSONValue newValue = objDate.get("iso");
								dueDate = JsonUtils.getJsonDate(newValue.isString().stringValue());
							}

							if (enabled && serviceID.intValue() != 2) {
								Account account = new Account(serviceID, accountID, amount, enabled,
									uid, oid, customer, dueDate
								);
								_accounts.add(account);
							}
						}
						ServiceManager.setAccounts(_accounts);
						MPViewPort vp = new MPViewPort();
						RootPanel.get().add(vp);
					}
				}
			}

			public void onError(Request request, Throwable exception) {
			}
		});

		try {
			b.send();
		} catch (RequestException ex1) {
			Window.alert("An error occured while requesting the home page"
				+ " from the server. " + ex1.getMessage());
		}
	}

	private void sendAlert() {
	}

	public Widget asWidget() {
		return null;
	}

	final native void open() /*-{
		$wnd.jQuery("#dialog").dialog("open");
    }-*/;
}
