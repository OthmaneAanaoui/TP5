package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.ControllerButton;

public class Login extends JFrame{
	private JPanel container;
	private JPanel panelEnd;
	private JPanel mainPanel;
	private ControllerButton controllerButton;
	JTextField txt_mail;
	JPasswordField txt_mdp;

	private GridBagConstraints gbc;
	
	public Login() {
		this.controllerButton = new ControllerButton();
		gbc = new GridBagConstraints();
		
		this.setTitle("Connexion");
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		this.setMainPanel();
		
		this.btnSignIn();
		
		this.add(container);
		
		this.setSize(600, 300);
	}
	
	public void btnSignIn() {
		panelEnd = new JPanel();
		panelEnd.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton signIn = new JButton();
		signIn.setText("Créer sont compte");
		signIn.setBorderPainted(false);
		signIn.setBorder(null);
		signIn.setContentAreaFilled(false);
		signIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signIn.setForeground(Color.blue);
		
		signIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controllerButton.switchToSignIn();
			}
		});
		
		JButton logIn = new JButton();
		logIn.setText("Connexion");
		logIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		logIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controllerButton.connexion(txt_mail.getText(),txt_mdp.getText());
			}
		});
		
		panelEnd.add(signIn);
		panelEnd.add(logIn);
		
		container.add(panelEnd, BorderLayout.SOUTH);
	}
	
	public void setMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
				
		JLabel lbl_prenom = new JLabel("Prénom");
		JLabel lbl_nom = new JLabel("Nom");
		JLabel lbl_mail = new JLabel("Mail");
		JLabel lbl_mdp = new JLabel("Mot de passe");
		
		JTextField txt_prenom = new JTextField();
		JTextField txt_nom = new JTextField();
		txt_mail = new JTextField();
		txt_mdp = new JPasswordField();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10,35,5,35);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		mainPanel.add(lbl_mail, gbc);

		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(txt_mail, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 2;
		mainPanel.add(lbl_mdp, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		mainPanel.add(txt_mdp, gbc);
		
		container.add(mainPanel);
	}
}
