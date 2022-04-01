package View;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	private JMenu ouvrireComp;
	private JMenu consulterCompte;
	
	
	public MenuBar() {
		this.BuildMenu();
	}	
	
	public void BuildMenu() {
		this.MenuOuvrireCompte();
		this.MenuConsulterCompte();
	}
	
	public void MenuOuvrireCompte() {
		ouvrireComp = new JMenu("Options");
		
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
}
