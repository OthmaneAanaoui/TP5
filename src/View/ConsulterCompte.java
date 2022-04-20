package View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.Operation;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import Controller.ConnexionDataBase;
import Controller.ControllerButton;
import Model.Account;
import Model.Transaction;

public class ConsulterCompte extends JPanel{
	private GridBagConstraints gbc;	
	private ControllerButton controllerButton;
	private ConnexionDataBase connexionDataBase;
	
	private JTextField txt_montantTotal;
	private JTextField txt_montantCompte1;
	private JTextField txt_montantCompte2;
	
	JFormattedTextField txt_decouvertCpt1;
	JFormattedTextField txt_decouvertCpt2;
	public ConsulterCompte() {
		this.connexionDataBase = new ConnexionDataBase();
		this.controllerButton = new ControllerButton();
		
		this.gbc = new GridBagConstraints();
		
		this.setLayout(new GridBagLayout());
	
		JLabel lbl_montantTotal = new JLabel("Solde total : ");
		JLabel lbl_montantCompte1 = new JLabel("Solde du premier compte : ");
		JLabel lbl_montantCompte2 = new JLabel("Solde du second compte : ");
		JLabel lbl_decouvertCpt1 = new JLabel("Découvert autorisé : ");
		JLabel lbl_decouvertCpt2 = new JLabel("Découvert autorisé : ");
		JLabel lbl_listOpe = new JLabel("Liste des opérations : ");
		
		txt_montantTotal = new JTextField();
		txt_montantTotal.setEditable(false);
		
		txt_montantCompte1 = new JTextField();
		txt_montantCompte1.setEditable(false);
		
		txt_montantCompte2 = new JTextField();
		txt_montantCompte2.setEditable(false);
		
		JTable jTable = new JTable();
		JScrollPane jScrollPane = new JScrollPane(jTable);
		DefaultTableModel dataTable = new DefaultTableModel();
		
		dataTable.addColumn("Date");
		dataTable.addColumn("Type");
		dataTable.addColumn("Compte");
		dataTable.addColumn("Montant");
		
		jTable.setModel(dataTable);

		txt_decouvertCpt1 = new JFormattedTextField();
		txt_decouvertCpt2 = new JFormattedTextField();
		
		JButton btn_decouvertCpt1 = new JButton();
		btn_decouvertCpt1.setText("Valider");
		btn_decouvertCpt1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_decouvertCpt1.setVisible(false);
		btn_decouvertCpt1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String plafond = txt_decouvertCpt1.getText();
				// plafond = plafond.replace("-", "").replace("€", "").trim();
				
				System.out.println(plafond);
			}
		});
		
		JButton btn_decouvertCpt2 = new JButton();
		btn_decouvertCpt2.setText("Valider");
		btn_decouvertCpt2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_decouvertCpt2.setVisible(false);
		btn_decouvertCpt2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String plafond = txt_decouvertCpt2.getText();
				// plafond = plafond.replace("-", "").replace("€", "").trim();
				
				System.out.println(plafond);
			}
		});
		
		txt_decouvertCpt1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			
			public void warn() {
				if (!txt_decouvertCpt1.getText().equals(""))
					btn_decouvertCpt1.setVisible(true);
				else 
					btn_decouvertCpt1.setVisible(false);
			}

		});
		
		txt_decouvertCpt2.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			
			public void warn() {
				if (!txt_decouvertCpt2.getText().equals(""))
					btn_decouvertCpt2.setVisible(true);
				else 
					btn_decouvertCpt2.setVisible(false);
			}
		});
			
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.insets = new Insets(10,35,5,35);
		this.add(lbl_montantTotal, gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		this.add(txt_montantTotal, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		this.add(lbl_montantCompte1, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		this.add(txt_montantCompte1, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 1;
		this.add(lbl_decouvertCpt1, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 1;
		this.add(txt_decouvertCpt1, gbc);
				
		gbc.gridy = 3;
		gbc.gridx = 2;
		this.add(btn_decouvertCpt1, gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 0;
		this.add(lbl_montantCompte2, gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 0;
		this.add(txt_montantCompte2, gbc);
	
		gbc.gridy = 4;
		gbc.gridx = 1;
		this.add(lbl_decouvertCpt2, gbc);
		
		gbc.gridy = 5;
		gbc.gridx = 1;
		this.add(txt_decouvertCpt2, gbc);
			
		gbc.gridy = 5;
		gbc.gridx = 2;
		this.add(btn_decouvertCpt2, gbc);
		
		gbc.gridy = 6;
		gbc.gridx = 0;
		this.add(lbl_listOpe, gbc);

		updateTable(dataTable);
		
		gbc.gridy = 7;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		this.add(jTable, gbc);
	}
	
	public void updateValues() {
		txt_montantTotal.setText(connexionDataBase.getSoldTotal());
		ArrayList<Account> accounts = connexionDataBase.getAccountFromUser();
		System.out.println(accounts.get(0).sold);
		String sold1 = accounts.get(0).sold+"";
		txt_montantCompte1.setText(sold1);
		if(accounts.size() > 1) {
			String sold2 = accounts.get(1).sold+"";
			txt_montantCompte2.setText(sold2);
		}
		
	}
	
	protected NumberFormatter createFormatter(int minValue, int maxValue) {
		 NumberFormat longFormat = NumberFormat.getIntegerInstance();

		 NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		 
		 numberFormatter.setValueClass(Long.class);
		 numberFormatter.setMinimum(minValue);
		 
		 if (maxValue != -1)
			 numberFormatter.setMaximum(maxValue);
		 
	    return numberFormatter;
	}

	public void updateTable(DefaultTableModel dataTable) {
		Account account = new Account();
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		transactions.add(new Transaction(0, "Dépot", account, 500,  new Date()));
		transactions.add(new Transaction(0, "Retrait", account, 110,  new Date()));
		transactions.add(new Transaction(0, "Retrait", account, 20,  new Date()));
		transactions.add(new Transaction(0, "Dépot", account, 10,  new Date()));
		
		dataTable.setRowCount(0);
		
		for (Transaction transaction : transactions) {
			Vector row = new Vector();
			
			row.add(transaction.getDate());
			row.add(transaction.getName());
			row.add(transaction.getAccount());
			row.add(transaction.getMontant());
			
			dataTable.addRow(row);
		}
		
	}
}
