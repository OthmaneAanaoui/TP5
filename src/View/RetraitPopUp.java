package View;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RetraitPopUp  {
	JComboBox choixCompte;
	JTextField txtMontantDepot;
	JTextField txtSolde;
	Object[] popUpTab;
	JPanel container;
	
	public RetraitPopUp() {
		
		JLabel lblChoixCompte = new JLabel("Choix du compte :");
		JLabel lblMontantDepot = new JLabel("Montant du retrait :");
		JLabel lblSolde = new JLabel("Solde aprés opération :");
	
		choixCompte = new JComboBox();	
		txtMontantDepot = new JTextField();
		txtSolde = new JTextField();
			
		popUpTab = new Object[] {lblChoixCompte, choixCompte, lblMontantDepot, txtMontantDepot, lblSolde, txtSolde};
	}
	
	public void showPopUp() {		
		int choix = JOptionPane.showConfirmDialog(container, popUpTab, "Retrait", JOptionPane.OK_CANCEL_OPTION, JOptionPane.CLOSED_OPTION);
		
		if (choix == JOptionPane.OK_OPTION) {
			System.out.println("it's okay");
		}
	}
}
