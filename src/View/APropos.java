package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.ConnexionDataBase;
import Controller.ControllerButton;

public class APropos extends JPanel{
	private GridBagConstraints gbc;	
	private ControllerButton controllerButton;
	private ConnexionDataBase connexionDataBase;
	
	JTextField txt_version;
	JTextField txt_date;
	JTextArea txt_new;
	
	public APropos() {
		this.connexionDataBase = new ConnexionDataBase();
		this.controllerButton = new ControllerButton();
		ArrayList<Object> list = connexionDataBase.getVersion();
		this.gbc = new GridBagConstraints();
		
		this.setLayout(new GridBagLayout());
	
		JLabel lbl_version = new JLabel("Version : ");
		JLabel lbl_date = new JLabel("Date : ");
		JLabel lbl_news = new JLabel("news : ");
		
		txt_version = new JTextField();
		txt_version.setEditable(false);
		txt_version.setText(list.get(0).toString());
		
		txt_date = new JTextField();
		txt_date.setEditable(false);
		txt_date.setText(list.get(1).toString());
		
		txt_new = new JTextArea();
		txt_new.setEditable(false);
		txt_new.setText(list.get(2).toString().replace("{", "").replace("}", "").replace("\"", ""));
		txt_new.setSize(60, 30);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10,35,5,35);
		this.add(lbl_version, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(txt_version, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(lbl_date, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(txt_date, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(lbl_news, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(txt_new, gbc);
	}

}
