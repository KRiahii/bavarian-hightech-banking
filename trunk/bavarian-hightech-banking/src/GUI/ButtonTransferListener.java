package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bavaria.hightech.banking.Money;
import bavaria.hightech.banking.Interface.BankCustomerView;

public class ButtonTransferListener implements ActionListener {

	private int kNumberFrom;
	private int kNumberTo;
	private long amount;
	private BankCustomerView bV;
	private GUI gui;

	public ButtonTransferListener(BankCustomerView bV, GUI gui) {
		this.bV = bV;
		this.gui = gui;
		gui.get_transfer().addActionListener(this);
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
		bV.transferMoney(amount, kNumberFrom, kNumberTo, Money.Currency.EURO,
				Money.Currency.EURO);
	}
}