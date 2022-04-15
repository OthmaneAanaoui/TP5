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

		signin = new Signin();
		login = new Login();
		//mainView.setVisible(true);
		login.setVisible(true);
		//signin.setVisible(true);
	}
}