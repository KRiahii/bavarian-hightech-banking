package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.swing.JTextArea;
import bavaria.hightech.banking.Interface.BankCustomerView;

public class ButtonCreditListener implements ActionListener {

	private int kNumberFrom;
	private BankCustomerView bV;
	private JTextArea kontostand;
	private GUI gui;
	private String mimeTyp;

	public ButtonCreditListener(BankCustomerView bV, GUI gui, String mimeTyp) {
		this.bV = bV;
		this.gui = gui;
		this.mimeTyp = mimeTyp;
		kontostand = gui.get_textPanel_kontostand();
		gui.get_credit().addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		
		try {
			kNumberFrom = Integer.parseInt(gui.get_textPanel_kNumberFrom()
					.getText());
		} catch (NumberFormatException e) {
		}
		//kontostand.setText(bA.accountsCurrent(kNumberFrom, 1));

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try {
			bV.getStatement(kNumberFrom, 1, bout, this.mimeTyp);
			kontostand.setText(bout.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}