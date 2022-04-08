package View;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	private JMenu ouvrireComp;
	private JMenu consulterCompte;
	private JMenu option;
	
	public MenuBar() {
		this.BuildMenu();
	}	
	
	public void BuildMenu() {
		this.MenuOuvrireCompte();
		this.MenuConsulterCompte();
		this.MenuOption();
	}
	
	public void MenuOuvrireCompte() {
		ouvrireComp = new JMenu("Ouvrir un compte");
		
		this.add(ouvrireComp);
	}
	
	public void MenuConsulterCompte() {
		JMenuItem consulter = new JMenuItem("Consulter");
		JMenuItem deposer = new JMenuItem("Déposer");
		JMenuItem retirer = new JMenuItem("Retirer");
		
		consulterCompte = new JMenu("Mes comptes");
		
		consulterCompte.add(consulter);
		consulterCompte.add(deposer);
		consulterCompte.add(retirer);
		
		this.add(consulterCompte);
	}
	
	public void MenuOption() {
		JMenuItem aPorpos = new JMenuItem("A propos");
		JMenuItem deconexion = new JMenuItem("Déconexion");
		
		option = new JMenu("Options");
		
		option.add(aPorpos);
		option.add(deconexion);
		
		this.add(option);
	}
}
