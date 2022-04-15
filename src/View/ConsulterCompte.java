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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerButton;

public class ConsulterCompte extends JPanel{
	private GridBagConstraints gbc;	
	private ControllerButton controllerButton;
	
	
	public ConsulterCompte() {
		this.controllerButton = new ControllerButton();
		
		this.gbc = new GridBagConstraints();
		
		this.setLayout(new GridBagLayout());
	
		JLabel lbl_montantTotal = new JLabel("Solde total : ");
		JLabel lbl_montantCompte1 = new JLabel("Solde du premier compte : ");
		JLabel lbl_montantCompte2 = new JLabel("Solde du second comtpe : ");
		JLabel lbl_plafondNegatif = new JLabel("Plafond négatife : ");
		JLabel lbl_listOpe = new JLabel("Liste des opérations : ");
		
		JTextField txt_montantTotal = new JTextField();
		txt_montantTotal.setEditable(false);
		JTextField txt_montantCompte1 = new JTextField();
		txt_montantCompte1.setEditable(false);
		JTextField txt_montantCompte2 = new JTextField();
		txt_montantCompte2.setEditable(false);
		
		JTable jTable = new JTable();
		JScrollPane jScrollPane = new JScrollPane(jTable);
		DefaultTableModel dataTable = new DefaultTableModel();
		
		dataTable.addColumn("Type");
		dataTable.addColumn("Montant");
		
		jTable.setModel(dataTable);
		
		JButton btn_editPlanfondNegatif = new JButton();
		btn_editPlanfondNegatif.setText("Valder le plafond");
		btn_editPlanfondNegatif.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_editPlanfondNegatif.setVisible(false);
		
		JTextField txt_plafondNegatif = new JTextField();
		txt_plafondNegatif.getDocument().addDocumentListener(new DocumentListener() {
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
				if (!txt_plafondNegatif.getText().equals(""))
					btn_editPlanfondNegatif.setVisible(true);
				else 
					btn_editPlanfondNegatif.setVisible(false);
			}

		});
			
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10,35,5,35);
		this.add(lbl_montantTotal, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(txt_montantTotal, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(lbl_montantCompte1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(txt_montantCompte1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(lbl_montantCompte2, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(txt_montantCompte2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(lbl_plafondNegatif, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(txt_plafondNegatif, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(btn_editPlanfondNegatif, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.add(lbl_listOpe, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		this.add(jScrollPane, gbc);
	}
}
