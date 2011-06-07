package GUI;

import java.util.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class GUI {
	
    private JFrame frame;            // top level container of the gui
    private JLabel messageLabel;     // for status messages
    private JPanel toolPanel;		 // for buttons
    private JTextField textPanel;	 // for text
    
    private JButton credit;			 // show amount of money
    private JButton transfer;		 // transfer money
    
    public GUI(String s) {

        frame = new JFrame(s);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
        toolPanel = new JPanel();
        messageLabel = new JLabel("Display for messages");
        textPanel = new JTextField();
        
        credit = new JButton("Kontostand");
        transfer = new JButton("Überweisen");
        
        credit.setToolTipText("Zeige Kontostand");
        transfer.setToolTipText("Geld an Konto überweisen");
        
        toolPanel.setLayout(new GridLayout(0, 1));
        toolPanel.add(credit);
        toolPanel.add(transfer);

        // BorderLayout is default for Frame content
        frame.add(BorderLayout.SOUTH, this.messageLabel);
        frame.add(BorderLayout.WEST, toolPanel);
        
        frame.add(BorderLayout.CENTER, textPanel);

        frame.setSize(500, 100);
        frame.setVisible(true);
    }

    public static void main(String[] args){
    	new GUI("test");
    }


}
