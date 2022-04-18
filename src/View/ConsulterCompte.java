package View;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

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

public class ConsulterCompte extends JPanel{
	private GridBagConstraints gbc;	
	private ControllerButton controllerButton;
	private ConnexionDataBase connexionDataBase;
	
	private JTextField txt_montantTotal;
	public ConsulterCompte() {
		this.connexionDataBase = new ConnexionDataBase();
		this.controllerButton = new ControllerButton();
		
		this.gbc = new GridBagConstraints();
		
		this.setLayout(new GridBagLayout());
	
		JLabel lbl_montantTotal = new JLabel("Solde total : ");
		JLabel lbl_montantCompte1 = new JLabel("Solde du premier compte : ");
		JLabel lbl_montantCompte2 = new JLabel("Solde du second comtpe : ");
		JLabel lbl_plafondNegatif = new JLabel("Découvert autorisé : ");
		JLabel lbl_listOpe = new JLabel("Liste des opérations : ");
		
		txt_montantTotal = new JTextField();
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

		JFormattedTextField txt_plafondNegatif = new JFormattedTextField(createFormatter(0, 1000));
		
		JButton btn_editPlanfondNegatif = new JButton();
		btn_editPlanfondNegatif.setText("Valder le plafond");
		btn_editPlanfondNegatif.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_editPlanfondNegatif.setVisible(false);
		btn_editPlanfondNegatif.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String plafond = txt_plafondNegatif.getText();
				// plafond = plafond.replace("-", "").replace("€", "").trim();
				
				System.out.println(plafond);
			}
		});
		
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
	
	public void updateValues() {
		txt_montantTotal.setText(connexionDataBase.getSoldTotal());
		
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
}
