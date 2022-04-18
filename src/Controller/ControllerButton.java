package Controller;

import javax.swing.JPanel;

import Model.User;

public class ControllerButton {
	static User user;
	private ConnexionDataBase connexionDataBase;
	private controller controller;
	
	public ControllerButton() {
		this.connexionDataBase = new ConnexionDataBase();
		this.controller = new controller();
	}

	public void connexion(String email,String mdp) {
		//User user = null;
		try {
			user = connexionDataBase.getUserByEmailAndPassword(email, mdp);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(user != null) {
			controller.mainView.setVisible(true);
			controller.login.setVisible(false);
			controller.login.getTxt_mail().setText("");
			controller.login.getTxt_mdp().setText("");
		}
		
	}
	
	public void inscription(String email, String password, String firstName, String lastName) {
		boolean isOk = connexionDataBase.createUser(email, password, firstName, lastName);
		if(isOk) {
			controller.mainView.setVisible(false);
			controller.login.setVisible(true);
		}
	}
	
	public void addAccount(String type, float floor, float montantTransaction, String user2) {
		String usersId;
		if(user2 != null) {
			int iduser = Integer.parseInt(user2.split("_")[1]);
			usersId = "{"+user.id+","+iduser+"}";
		}
		else {
			usersId = "{"+user.id+"}";
		}
		
		boolean isOk = connexionDataBase.createAccount(usersId, type, floor);
		if(isOk && montantTransaction > 0) {
			connexionDataBase.createTransaction("depot", user.id, montantTransaction);
		}
	}
	
	public void switchToLogIn() {
		controller.login.setVisible(true);
		controller.signin.setVisible(false);
	}
	
	public void switchToSignIn() {
		controller.login.setVisible(false);
		controller.signin.setVisible(true);
	}
	
	public void quitter() {
		System.exit(0);
	}
	
	public void deconnexion() {
		//System.exit(0);
		user = null;
		controller.mainView.setVisible(false);
		controller.login.setVisible(true);
	}
	
	public void showDepot() {
		controller.mainView.getDepotPopUp().showPopUp();
	}
	
	public void showRetrait() {
		controller.mainView.getRetraitPopUp().showPopUp();
	}
	
	public void switchPanel(String panel) {
		switch (panel) {
		case "ouvrirCompte":
			controller.mainView.getConsulterCompte().setVisible(false);
			controller.mainView.getOuvrirCompte().setVisible(true);
			break;
		case "consulterCompte":
			controller.mainView.getOuvrirCompte().setVisible(false);
			controller.mainView.getConsulterCompte().setVisible(true);
			break;
		case "aPropos":
			controller.mainView.getOuvrirCompte().setVisible(false);
			controller.mainView.getConsulterCompte().setVisible(false);
			
			break;
		default:
			break;
		}
		
	}
	
}
