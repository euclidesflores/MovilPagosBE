package com.swordcode.movilpagosbe.client.ui;

import com.sencha.gxt.widget.core.client.form.DoubleField;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.sencha.gxt.cell.core.client.PropertyDisplayCell;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.fx.client.FxElement;

import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent.ParseErrorHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.swordcode.movilpagosbe.client.svc.Account;
import com.swordcode.movilpagosbe.client.svc.AccountInfoProperties;
import com.swordcode.movilpagosbe.client.svc.ServiceInfo;
import com.swordcode.movilpagosbe.client.svc.ServiceInfoProperties;
import com.swordcode.movilpagosbe.client.svc.ServiceManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author euclidesflores
 */

public class MPViewPort extends Viewport {
	private SimpleContainer						_cmp;
	private ListStore<ServiceInfo>				_store1;
	private Grid<ServiceInfo>					_grid1;
	private ContentPanel						_west;
	private ContentPanel						_center;
	private ListStore<Account>					_store2;
	private Grid<Account>						_grid2;
	private Window								_w;
	private Window								_w2;
	private	VerticalLayoutContainer				_vp;
	private	FramedPanel							_panel;
	private	TextField							_customer;
	private TextField							_accountNo;
	private TextField							_dueDate;
	private TextField							_amountDue;
	private DoubleField							_amountToPay;
	private TextButton							_checkout;
	private TextButton							_cancel;
	private ContentPanel						_htmlPanel;

	private static final ServiceInfoProperties	_props1 = GWT.create(ServiceInfoProperties.class);
	private static final AccountInfoProperties	_props2 = GWT.create(AccountInfoProperties.class);
	private static final NumberFormat			_fmt = NumberFormat.getFormat("$#00.00");

	public MPViewPort() {
		 if (_cmp == null) {
			 _cmp = new SimpleContainer();
			 _cmp.setBorders(true);
			final BorderLayoutContainer con = new BorderLayoutContainer();
			_cmp.add(con, new MarginData(21, 0, 0, 0));
			_cmp.add(con);
			con.setBorders(true);
			_cmp.setBorders(true);
			add(_cmp);
			_west = new ContentPanel();
			_west.setBorders(false);
			_west.setHeadingText("Servicios");
			_center = new ContentPanel();
			_center.setBorders(false);
			_center.setHeadingText("Cuentas vinculadas");
			BorderLayoutData westData = new BorderLayoutData(320);
			westData.setCollapsible(true);
			westData.setSplit(true);
			westData.setCollapseMini(true);
			westData.setMargins(new Margins(0, 8, 0, 5));
			MarginData centerData = new MarginData();
			con.setWestWidget(_west, westData);
			con.setCenterWidget(_center, centerData);
			createServicePanel();
		 }
	}

	private void createServicePanel() {
		ColumnConfig<ServiceInfo, String> serviceCol =
				new ColumnConfig<ServiceInfo, String>(_props1.description(), 50, SafeHtmlUtils.fromTrustedString("<b>Servicios</b>"));
		serviceCol.setAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		List<ColumnConfig<ServiceInfo, ?>> l1 = new ArrayList<ColumnConfig<ServiceInfo, ?>>();
		l1.add(serviceCol);
		ColumnModel<ServiceInfo> cm = new ColumnModel<ServiceInfo>(l1);
		_store1 = new ListStore<ServiceInfo>(_props1.key());
		List<ServiceInfo> _serviceList = new ArrayList<ServiceInfo>();
		ServiceInfo si = new ServiceInfo("GasNatural", 0, 0, "GasNatural", true, 0);
		_serviceList.add(si);
		si = new ServiceInfo("I.D.A.A.N.", 0, 0, "I.D.A.A.N.", true, 1);
		_serviceList.add(si);
		si = new ServiceInfo("Comprar boletos", 0, 0, "Comprar boletos", true, 2);
		_serviceList.add(si);
		_store1.addAll(_serviceList);
		_grid1 = new Grid<ServiceInfo>(_store1, cm);
		_grid1.setHideHeaders(true);
		_grid1.setColumnReordering(true);
		_grid1.getView().setAutoExpandColumn(serviceCol);
		_grid1.getView().setStripeRows(true);
		_grid1.getView().setEmptyText("Servicios no disponibles");
		_grid1.setBorders(false);
		_west.add(_grid1);

		// Define grid for account
		ColumnConfig<Account, String> accountCol =
				new ColumnConfig<Account, String>(_props2.accountID(), 150, SafeHtmlUtils.fromTrustedString("<b>Nro. de cuenta</b>"));
		accountCol.setAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		ColumnConfig<Account, String> customerCol =
				new ColumnConfig<Account, String>(_props2.customer(), 210, SafeHtmlUtils.fromTrustedString("<b>Nombre</b>"));
		ColumnConfig<Account, Date> dueDateCol =
				new ColumnConfig<Account, Date>(_props2.dueDate(), 200, SafeHtmlUtils.fromTrustedString("<b>Vencimiento</b>"));
		dueDateCol.setCell(new DateCell(DateTimeFormat.getFormat("dd-MMM-yyyy h:mm a")));
		dueDateCol.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		ColumnConfig<Account, Double> amountCol =
				new ColumnConfig<Account, Double>(_props2.amount(), 240, SafeHtmlUtils.fromTrustedString("<b>Saldo</b>"));
		amountCol.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		amountCol.setCell(new PropertyDisplayCell<Double>(new DoublePropertyEditor(_fmt)));

		SafeStyles btnPaddingStyle = SafeStylesUtils.fromTrustedString("padding: 1px 3px 0;");
		ColumnConfig<Account, String> btnCol = new ColumnConfig<Account, String>(_props2.accountID(), 100, "");
		btnCol.setColumnTextClassName(CommonStyles.get().inlineBlock());
		btnCol.setColumnTextStyle(btnPaddingStyle);
		TextButtonCell button = new TextButtonCell();
		button.setText("Pagar");
		button.addSelectHandler(new SelectHandler() {
			public void onSelect(SelectEvent event) {
				Context c = event.getContext();
				int row = c.getIndex();
				Account ax = _store2.get(row);
				ServiceInfo six = _grid1.getSelectionModel().getSelectedItem();
				if (six == null) six = _store1.get(0);
				openPaymentUi(ax, six);
			}
		});
		btnCol.setCell(button);

		List<ColumnConfig<Account, ?>> l2 = new ArrayList<ColumnConfig<Account, ?>>();
		l2.add(accountCol);
		l2.add(customerCol);
		l2.add(dueDateCol);
		l2.add(amountCol);
		l2.add(btnCol);
		ColumnModel<Account> cm2 = new ColumnModel<Account>(l2);
		_store2 = new ListStore<Account>(_props2.key());
		List<Account> accounts = new ArrayList<Account>();
		for (int i=0; i < ServiceManager.getAccounts().size(); i++) {
			Account account = ServiceManager.getAccounts().get(i);
			// FIX-ME
			if (account.getServiceID().intValue() == 0) {
				accounts.add(account);
			}
		}
		_store2.addAll(accounts);
		_grid2 = new Grid<Account>(_store2, cm2);
		_grid2.getView().setStripeRows(true);
		_grid2.getView().setAutoFill(true);
		_grid2.getView().setEmptyText("No tiene cuentas registradas");
		_center.add(_grid2);
		_grid1.getSelectionModel().addSelectionHandler(
				new SelectionHandler<ServiceInfo>() {
			public void onSelection(SelectionEvent<ServiceInfo> event) {
				List<Account> la = new ArrayList<Account>();
				ServiceInfo si = event.getSelectedItem();
				for (int i=0; i < ServiceManager.getAccounts().size(); i++) {
					Account account = ServiceManager.getAccounts().get(i);
					// FIX-ME
					if (account.getServiceID().intValue() == si.getServiceID().intValue()) {
						la.add(account);
					}
				}
				_store2.clear();
				_store2.addAll(la);
			}
		});
	}

	private void openPaymentUi(final Account a, final ServiceInfo si) {
		if (_w == null) {
			_w = new Window();
			_w.setPixelSize(560, 400);
			_w.setModal(true);
			_w.setResizable(false);
			_vp = new VerticalLayoutContainer();
			_panel = new FramedPanel();
			_panel.setBodyStyle("background: none; padding: 15px");
			_panel.setBodyBorder(false);
			_panel.setBorders(false);
			_panel.setHeaderVisible(false);
			_panel.add(_vp);
			_customer = new TextField();
			_customer.setReadOnly(true);
			_vp.add(new FieldLabel(_customer, "Cliente"), new VerticalLayoutData(1, -1));
			_accountNo = new TextField();
			_accountNo.setReadOnly(true);
			_vp.add(new FieldLabel(_accountNo, "NIC"), new VerticalLayoutData(1, -1));
			_dueDate = new TextField();
			_dueDate.setReadOnly(true);
			_vp.add(new FieldLabel(_dueDate, "Vencimiento"), new VerticalLayoutData(1, -1));
			_amountDue = new TextField();
			_amountDue.setReadOnly(true);
			_vp.add(new FieldLabel(_amountDue, "Saldo"), new VerticalLayoutData(1, -1));
			_amountToPay = new DoubleField();
			_amountToPay.setFormat(NumberFormat.getFormat("#00.00"));
			_amountToPay.setSelectOnFocus(true);
			_amountToPay.setAllowNegative(false);
			_amountToPay.setAllowDecimals(true);
			_amountToPay.setEmptyText("Cantidad a pagar");
			_amountToPay.addParseErrorHandler(new ParseErrorHandler() {
				public void onParseError(ParseErrorEvent event) {
				}
			});
			_vp.add(new FieldLabel(_amountToPay, "Cantidad a pagar"), new VerticalLayoutData(1, -1));
			_checkout = new TextButton("Pagar ahora");
			_cancel = new TextButton("Cancelar");
			_checkout.addSelectHandler(new SelectHandler() {
				public void onSelect(SelectEvent event) {
					_w2 = new Window();
					ServiceInfo six = _grid1.getSelectionModel().getSelectedItem();
					if (six == null) six = _store1.get(0);
					_w2.setHeadingHtml("Pagando " + six.getDescription());
					final ContentPanel htmlPanel = new ContentPanel();
					htmlPanel.mask("Cargando interface de pago");
					Frame frame = new Frame("http://www.sencha.com/products/gxt");
					frame.addLoadHandler(new LoadHandler() {
						public void onLoad(LoadEvent event) {
							htmlPanel.unmask();
						}
					});
					htmlPanel.setHeaderVisible(false);
					htmlPanel.setBodyBorder(false);
					htmlPanel.setBodyBorder(false);
					htmlPanel.add(frame);
					_w2.add(htmlPanel);
					_w2.setPixelSize(610, 500);
					_w2.setModal(true);
					_w2.setResizable(false);
					_w2.show();
				}
			});
			_cancel.addSelectHandler(new SelectHandler() {
				public void onSelect(SelectEvent event) {
					_w.hide();
				}
			});
			_panel.addButton(_checkout);
			_panel.addButton(_cancel);
			_w.add(_panel);
		}

		String serviceName = si.getServiceName();
		_w.setHeadingText("Pagar " + serviceName);
		_customer.setText(a.getCustomer());
		_accountNo.setText(a.getAccountID());
		_dueDate.setText(DateTimeFormat.getFormat("dd-MMM-yyyy h:mm a").format(a.getDueDate()));
		_amountDue.setText(_fmt.format(a.getAmount()));
		_amountToPay.setValue(a.getAmount());
		_w.show();
		_w.setFocusWidget(_amountToPay);
	}
}