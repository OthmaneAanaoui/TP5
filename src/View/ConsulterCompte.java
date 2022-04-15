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

public class ConsulterCompte extends JPanel{
	private GridBagConstraints gbc;	
	
	public ConsulterCompte() {
		gbc = new GridBagConstraints();
		
		this.setLayout(new GridBagLayout());
		
		JLabel lbl_typeCompte = new JLabel("Type de compte");
		JLabel lbl_user2 = new JLabel("Second utilisateur :");
		JLabel lbl_depot = new JLabel("1er depot :");

		JComboBox cb_typeCompte = new JComboBox(Model.Type.values());
		JTextField txt_user2 = new JTextField();
		
		// ----- add try catch isNumber ---------------------------
		JTextField txt_depot = new JTextField();
		
		JButton btn_nouveauCompte = new JButton();
		btn_nouveauCompte.setText("Ouvrir le compte");
		btn_nouveauCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10,35,5,35);
		this.add(lbl_typeCompte, gbc);
		
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(cb_typeCompte, gbc);
		
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(lbl_user2, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(txt_user2, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(lbl_depot, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(txt_depot, gbc);
		
		
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(btn_nouveauCompte, gbc);
	}
}
