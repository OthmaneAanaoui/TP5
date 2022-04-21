package View;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ConnexionDataBase;
import Controller.ControllerButton;
import Model.Account;
import Model.User;

public class DepotPopUp {
	private ControllerButton controllerButton;
	private ConnexionDataBase connexionDataBase;
	
	JComboBox choixCompte;
	JTextField txtMontantDepot;
	JTextField txtSolde;
	Object[] popUpTab;
	JPanel container;
	
	public DepotPopUp() {
		this.controllerButton = new ControllerButton();
		this.connexionDataBase = new ConnexionDataBase();
		
		JLabel lblChoixCompte = new JLabel("Choix du compte :");
		JLabel lblMontantDepot = new JLabel("Montant du dépot :");
		JLabel lblSolde = new JLabel("Solde aprés opération :");
	
		choixCompte = new JComboBox();	
		txtMontantDepot = new JTextField();
		txtSolde = new JTextField();
		
		
		
		popUpTab = new Object[] {lblChoixCompte, choixCompte, lblMontantDepot, txtMontantDepot, lblSolde, txtSolde};
	}
	
	public void showPopUp() {
		int choix = JOptionPane.showConfirmDialog(container, popUpTab, "Dépot", JOptionPane.OK_CANCEL_OPTION, JOptionPane.CLOSED_OPTION);
		
		if (choix == JOptionPane.OK_OPTION) {
			if(!txtMontantDepot.getText().isEmpty()) {
				
				try {
					String choixCpt = (String) choixCompte.getSelectedItem();
					int id = Integer.parseInt(choixCpt.split("_")[0]) ;
					float motant = Float.parseFloat(txtMontantDepot.getText());
					controllerButton.addMoney(id,motant);
					controllerButton.switchPanel("consulterCompte");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				
				
			}
		}
	}
	
	public void updateCBbox(){
		choixCompte.removeAllItems();
		for (Account account : connexionDataBase.getAccountFromUser()) {
			choixCompte.addItem(account.getId() +"_"+account.getType()+"| Motant : "+account.getSold());
		}
	}
}
