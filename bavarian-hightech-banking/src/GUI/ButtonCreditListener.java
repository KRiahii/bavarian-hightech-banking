package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import bavaria.hightech.banking.Interface.BankAdmin;

class ButtonCreditListener implements ActionListener {

	private int kNumberFrom;
	private BankAdmin bA;
	private JTextArea kontostand;
	private GUI gui;

	public ButtonCreditListener(BankAdmin bA, GUI gui) {
		this.bA = bA;
		this.gui = gui;
		kontostand = gui.get_textPanel_kontostand();
		gui.get_credit().addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			kNumberFrom = Integer.parseInt(gui.get_textPanel_kNumberFrom()
					.getText());
		} catch (NumberFormatException e) {
		}
		kontostand.setText(bA.accountsCurrent(kNumberFrom, 1));
	}
}