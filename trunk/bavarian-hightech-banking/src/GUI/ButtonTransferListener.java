package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import bavaria.hightech.banking.Interface.BankCustomerView;
import bavaria.hightech.banking.Money.Currency;

public class ButtonTransferListener implements ActionListener {

	private int kNumberFrom;
	private int kNumberTo;
	private long amount;
	private Currency currencyFrom;
	private Currency currencyTo;
	private BankCustomerView bV;
	private GUI gui;
	private Map<String, Currency> currencyTable;

	public ButtonTransferListener(BankCustomerView bV, GUI gui) {
		this.bV = bV;
		this.gui = gui;
		gui.get_transfer().addActionListener(this);
		currencyTable = gui.get_currencyTable();
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			kNumberFrom = Integer.parseInt(gui.get_textPanel_kNumberFrom()
					.getText());
			kNumberTo = Integer.parseInt(gui.get_textPanel_kNumberTo()
					.getText());
			amount = Long.parseLong(gui.get_textPanel_amount().getText());
		} catch (NumberFormatException e) {
		}

		currencyFrom = currencyTable.get(gui.get_comboBoxFrom()
				.getSelectedItem());
		currencyTo = currencyTable.get(gui.get_comboBoxTo().getSelectedItem());

		bV.transferMoney(amount, kNumberFrom, kNumberTo, currencyFrom,
				currencyTo);
	}
}