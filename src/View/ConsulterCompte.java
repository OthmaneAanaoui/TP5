package View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ControllerButton;

public class ConsulterCompte extends JPanel{
	private GridBagConstraints gbc;	
	private ControllerButton controllerButton;
	
	public ConsulterCompte() {
		gbc = new GridBagConstraints();
		
		this.setLayout(new GridBagLayout());
		
	}
}
