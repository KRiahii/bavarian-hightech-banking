package GUI;

import javax.swing.*;

import bavaria.hightech.banking.Money.Currency;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class GUI {

	private JFrame frame;
	private JPanel toolPanel;
	private JPanel kontoPanel;
	private JPanel containerPanel;
	private JPanel comboPanel;
	private JTextArea textPanel_kontostand;
	private JTextField textPanel_kNumberFrom;
	private JTextField textPanel_kNumberTo;
	private JTextField textPanel_amount;
	private JScrollPane scroll_pane;
	private JComboBox comboBoxFrom;
	private JComboBox comboBoxTo;
	private Map<String, Currency> currencyTable;

	private JButton credit;
	private JButton transfer;

	public GUI(String s, Locale currentLocale) {

		frame = new JFrame(s);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ResourceBundle bank = ResourceBundle.getBundle("i18n/BankBundle",
				currentLocale);

		toolPanel = new JPanel();
		kontoPanel = new JPanel();
		containerPanel = new JPanel();
		comboPanel = new JPanel();
		textPanel_kontostand = new JTextArea();
		textPanel_kNumberFrom = new JTextField();
		textPanel_kNumberTo = new JTextField();
		textPanel_amount = new JTextField();
		scroll_pane = new JScrollPane(this.textPanel_kontostand);

		currencyTable = new HashMap<String, Currency>();
		currencyTable.put("Britischespfund", Currency.BRITISCHESPFUND);
		currencyTable.put("Euro", Currency.EURO);
		currencyTable.put("Japanischeryen", Currency.JAPANISCHERYEN);
		currencyTable.put("Schweizerfranken", Currency.SCHWEIZERFRANKEN);
		currencyTable.put("USDollar", Currency.USDOLLAR);

		comboBoxFrom = new JComboBox();
		comboBoxFrom.setToolTipText("kNumberFrom");
		comboBoxTo = new JComboBox();
		comboBoxTo.setToolTipText("kNumberTo");

		for (String currencyString : currencyTable.keySet()) {
			comboBoxFrom.addItem(currencyString);
		}

		for (String currencyString : currencyTable.keySet()) {
			comboBoxTo.addItem(currencyString);
		}

		credit = new JButton(bank.getString("creditAccount"));
		transfer = new JButton(bank.getString("bankTransfer"));

		credit.setToolTipText(bank.getString("showCredit"));
		transfer.setToolTipText(bank.getString("transferMoneyTo"));
		textPanel_kNumberFrom.setToolTipText("kNumberFrom");
		textPanel_kNumberTo.setToolTipText("kNumberTo");
		textPanel_amount.setToolTipText("amount");

		toolPanel.setLayout(new GridLayout(1, 2));
		toolPanel.add(credit);
		toolPanel.add(transfer);

		kontoPanel.setLayout(new GridLayout(1, 2));
		kontoPanel.add(textPanel_kNumberFrom);
		kontoPanel.add(textPanel_kNumberTo);

		comboPanel.setLayout(new GridLayout(1, 2));
		comboPanel.add(comboBoxFrom);
		comboPanel.add(comboBoxTo);

		containerPanel.setLayout(new GridLayout(3, 1));
		containerPanel.add(kontoPanel);
		containerPanel.add(comboPanel);
		containerPanel.add(textPanel_amount);

		frame.add(BorderLayout.SOUTH, toolPanel);
		frame.add(BorderLayout.NORTH, containerPanel);
		frame.add(BorderLayout.CENTER, scroll_pane);

		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	JTextArea get_textPanel_kontostand() {
		return textPanel_kontostand;
	}

	JTextField get_textPanel_kNumberFrom() {
		return textPanel_kNumberFrom;
	}

	JTextField get_textPanel_kNumberTo() {
		return textPanel_kNumberTo;
	}

	JTextField get_textPanel_amount() {
		return textPanel_amount;

	}

	JButton get_credit() {
		return credit;
	}

	JButton get_transfer() {
		return transfer;
	}

	JComboBox get_comboBoxFrom() {
		return comboBoxFrom;
	}

	JComboBox get_comboBoxTo() {
		return comboBoxTo;
	}

	Map<String, Currency> get_currencyTable() {
		return currencyTable;
	}
}