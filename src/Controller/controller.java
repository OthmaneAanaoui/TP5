package Controller;

import View.Login;
import View.MainView;
import View.Signin;

public class controller {
	public static void main(String[] args) {
		MainView mainView = new MainView();
		
		Signin signin = new Signin();
		Login login = new Login();
		
		login.setVisible(true);
		signin.setVisible(true);
	}
}