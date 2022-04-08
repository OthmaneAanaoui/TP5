package Controller;

import Model.User;

public class ControllerButton {
	
	private ConnexionDataBase connexionDataBase;
	private controller controller;
	public ControllerButton() {
		this.connexionDataBase = new ConnexionDataBase();
		this.controller = new controller();
	}

	public void connexion(String email,String mdp) {
		User user = null;
		try {
			user = connexionDataBase.getUserByEmailAndPassword(email, mdp);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(user != null) {
			controller.mainView.setVisible(true);
		}
		
	}
	
	public void inscription() {
		
	}
	
	public void quitter() {
		System.exit(0);
	}
	
}
