package View;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DepotPopUp {
	JComboBox choixCompte;
	JTextField txtMontantDepot;
	JTextField txtSolde;
	Object[] popUpTab;
	JPanel container;
	
	public DepotPopUp(JPanel container) {
		this.container = container;
		
		JLabel lblChoixCompte = new JLabel("Choix du compte :");
		JLabel lblMontantDepot = new JLabel("Montant du d�pot :");
		JLabel lblSolde = new JLabel("Solde apr�s op�ration :");
	
		choixCompte = new JComboBox();	
		txtMontantDepot = new JTextField();
		txtSolde = new JTextField();
			
		popUpTab = new Object[] {lblChoixCompte, choixCompte, lblMontantDepot, txtMontantDepot, lblSolde, txtSolde};
	}
	
	public void showPopUp() {
		int choix = JOptionPane.showConfirmDialog(container, popUpTab, "D�pot", JOptionPane.OK_CANCEL_OPTION);
		
		if (choix == JOptionPane.OK_OPTION) {
			System.out.println("it's okay");
		}
	}
}
