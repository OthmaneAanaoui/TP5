package Controller;

import Model.User;

public class ControllerButton {
	User user = null;
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
		}
		
	}
	
	public void inscription(String email, String password, String firstName, String lastName) {
		boolean isOk = connexionDataBase.createUser(email, password, firstName, lastName);
		if(isOk) {
			switchToLogIn();
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
	
}
