package View;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import Controller.ControllerButton;

public class OuvrirCompte extends JPanel{
	private ControllerButton controllerButton;
	private GridBagConstraints gbc;	
	
	public OuvrirCompte() {
		gbc = new GridBagConstraints();
		this.controllerButton = new ControllerButton();
		
		this.setLayout(new GridBagLayout());
		
		JLabel lbl_typeCompte = new JLabel("Type de compte");
		JLabel lbl_user2 = new JLabel("Second utilisateur :");
		JLabel lbl_depot = new JLabel("1er depot :");
		JLabel lbl_plafondNegatif = new JLabel("Plafon négatif :");
		
		JComboBox cb_typeCompte = new JComboBox(Model.Type.values());
		JTextField txt_user2 = new JTextField();
		
		// ----- add try catch isNumber ---------------------------
		JFormattedTextField txt_depot = new JFormattedTextField(createFormatter("########.## €"));
		JFormattedTextField txt_plafondNegatif = new JFormattedTextField(createFormatter("########.## €"));
		
		JButton btn_nouveauCompte = new JButton();
		btn_nouveauCompte.setText("Ouvrir le compte");
		btn_nouveauCompte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btn_nouveauCompte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				float floor;
				try {
					floor = Float.parseFloat(txt_plafondNegatif.getText()) ;
				} catch (Exception e2) {
					// TODO: handle exception
					floor = 0;
				}
				
				controllerButton.addAccount(cb_typeCompte.getSelectedItem().toString(), 0, floor, txt_user2.getText());
			}
		});
		
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
		this.add(lbl_plafondNegatif, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(txt_plafondNegatif, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(lbl_depot, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(txt_depot, gbc);
		
		
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 6;
		this.add(btn_nouveauCompte, gbc);
	}
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	        
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
}
