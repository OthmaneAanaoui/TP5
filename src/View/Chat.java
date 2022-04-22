package View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.ConnexionDataBase;
import Controller.ControllerButton;
import Model.Account;
import Model.Transaction;
import Model.User;

public class Chat extends JPanel{
	private GridBagConstraints gbc;	
	private ControllerButton controllerButton;
	private ConnexionDataBase connexionDataBase;
	
	private JComboBox cb_userToSpeak;
	private JTextField txt_msg;
	
	private DefaultTableModel dataTable;
	
	public Chat() {
		this.connexionDataBase = new ConnexionDataBase();
		this.controllerButton = new ControllerButton();
		
		this.gbc = new GridBagConstraints();
		
		this.setLayout(new BorderLayout());
		
		JPanel panelNord = new JPanel();
		JPanel panelSud = new JPanel();
		
		JLabel lbl_choixUser = new JLabel("A qui voulez vous parler ?");
		JLabel lbl_msg = new JLabel("Votre message :");
		
		JButton btn_refresh = new JButton();
		btn_refresh.setText("Rafraichir");
		btn_refresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JButton btn_send = new JButton();
		btn_send.setText("Envoyer");
		btn_send.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JTable jTable = new JTable();
		JScrollPane jScrollPane = new JScrollPane(jTable);
		dataTable = new DefaultTableModel();
		
		dataTable.addColumn("Utilisateur");
		dataTable.addColumn("Date");
		dataTable.addColumn("Message");
		
		jTable.setModel(dataTable);
		
		jTable.disable();
				
		this.txt_msg = new JTextField();
		this.cb_userToSpeak = new JComboBox();
		
		updateTable(dataTable);
		
		panelNord.setLayout(new GridBagLayout());
		panelSud.setLayout(new GridBagLayout());
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// Panel nord
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.insets = new Insets(10,35,5,35);
		panelNord.add(lbl_choixUser, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelNord.add(cb_userToSpeak, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.gridx = 2;
		panelNord.add(btn_refresh, gbc);

		gbc.gridwidth = 3;
		gbc.gridy = 2;
		gbc.gridx = 0;
		panelNord.add(jTable, gbc);
		
		
		// panel sud
		gbc.gridwidth = 1;
		gbc.gridy = 0;
		gbc.gridx = 0;
		panelSud.add(lbl_msg, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelSud.add(txt_msg, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		gbc.gridx = 2;
		panelSud.add(btn_send, gbc);
		
		this.add(panelNord, BorderLayout.NORTH);
		this.add(panelSud, BorderLayout.SOUTH);
	}
	
	public void updateCBX() {
		for (User user : connexionDataBase.getUsers()) {
			cb_userToSpeak.addItem(user.getFirstName()+"_"+user.getId());
		}
	}
	
	public void updateTable(DefaultTableModel dataTable) {
		dataTable.setRowCount(0);
		for (int i = 0; i < 3; i++) {
			Vector row = new Vector();
			
			row.add("user " + i);
			row.add("date " + i);
			row.add("msg " + i);
			
			dataTable.addRow(row);
		}
		
	}
}
