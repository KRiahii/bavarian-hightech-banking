package GUI;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.*;
import javax.swing.*;

import Proxy.DebugHandler;
import bavaria.hightech.banking.BankImpl;
import bavaria.hightech.banking.Money;
import bavaria.hightech.banking.Interface.BankAdmin;
import bavaria.hightech.banking.Interface.BankCustomerView;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class GUI {

	private JFrame frame;
	private JPanel toolPanel;
	private JPanel kontoPanel;
	private JTextArea textPanel_kontostand;
	private JTextField textPanel_kNumberFrom;
	private JTextField textPanel_kNumberTo;
	private JTextField textPanel_amount;

	private JButton credit;
	private JButton transfer;

	public GUI(String s) {

		frame = new JFrame(s);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		toolPanel = new JPanel();
		kontoPanel = new JPanel();
		textPanel_kontostand = new JTextArea();
		textPanel_kNumberFrom = new JTextField();
		textPanel_kNumberTo = new JTextField();
		textPanel_amount = new JTextField();

		credit = new JButton("Kontostand");
		transfer = new JButton("Überweisen");

		credit.setToolTipText("Zeige Kontostand");
		transfer.setToolTipText("Geld an Konto überweisen");
		textPanel_kNumberFrom.setToolTipText("kNumberFrom");
		textPanel_kNumberTo.setToolTipText("kNumberTo");
		textPanel_amount.setToolTipText("amount");

		toolPanel.setLayout(new GridLayout(0, 1));
		toolPanel.add(credit);
		toolPanel.add(transfer);

		kontoPanel.setLayout(new GridLayout(1, 0));
		kontoPanel.add(textPanel_kNumberFrom);
		kontoPanel.add(textPanel_kNumberTo);

		frame.add(BorderLayout.WEST, toolPanel);
		frame.add(BorderLayout.NORTH, kontoPanel);
		frame.add(BorderLayout.CENTER, textPanel_kontostand);
		frame.add(BorderLayout.SOUTH, textPanel_amount);

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

	public static void main(String[] args) throws SecurityException,
			IOException {

		BankImpl bank = new BankImpl(new Locale("de", "de"));

		DebugHandler handler = new DebugHandler(bank);

		BankCustomerView bV = (BankCustomerView) Proxy.newProxyInstance(
				BankCustomerView.class.getClassLoader(),
				new Class[] { BankCustomerView.class }, handler);

		BankAdmin bA = (BankAdmin) Proxy.newProxyInstance(
				BankAdmin.class.getClassLoader(),
				new Class[] { BankAdmin.class }, handler);

		bV.createAcc("DepositAccount", "A", 1);
		bV.createAcc("DepositAccount", "B", 0);
		bV.createAcc("GiroAccount", "C", 0);
		bV.createAcc("GiroAccount", "D", 1);
		bV.createAcc("GiroAccount", "E", 2);

		bV.addMoney(12432, 2000, Money.Currency.BRITISCHESPFUND);
		bV.addMoney(3200, 2001, Money.Currency.EURO);
		bV.addMoney(46345, 2002, Money.Currency.JAPANISCHERYEN);
		bV.addMoney(313254, 2003, Money.Currency.USDOLLAR);
		bV.addMoney(4040, 2004, Money.Currency.BRITISCHESPFUND);

		GUI gui = new GUI("test");
		new ButtonTransferListener(bV, gui);
		new ButtonCreditListener(bA, gui);
	}

}