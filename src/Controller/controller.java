package Controller;

import View.Login;
import View.MainView;
import View.Signin;

public class controller {
	static MainView mainView;
	static Signin signin;
	static Login login;
	public static void main(String[] args) {
		mainView = new MainView();
		//mainView.setVisible(true);
		
		//signin = new Signin();
		//signin.setVisible(true);
		
		login = new Login();
		login.setVisible(true);
	}
}