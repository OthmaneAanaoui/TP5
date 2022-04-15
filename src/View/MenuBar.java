package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Controller.ControllerButton;

public class MenuBar extends JMenuBar{
	private JMenu ouvrireComp;
	private JMenu consulterCompte;
	private JMenu option;
	private ControllerButton controllerButton;
	
	public MenuBar() {
		this.BuildMenu();
		this.controllerButton = new ControllerButton();
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
		
		deposer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controllerButton.showDepot();
			}
		});
		
		retirer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controllerButton.showRetrait();
			}
		});
		
		this.add(consulterCompte);
	}
	
	public void MenuOption() {
		JMenuItem aPorpos = new JMenuItem("A propos");
		JMenuItem deconexion = new JMenuItem("Déconexion");
		JMenuItem quitter = new JMenuItem("Quitter");
		
		option = new JMenu("Options");
		
		option.add(aPorpos);
		option.add(deconexion);
		option.add(quitter);
		
		deconexion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controllerButton.deconnexion();
			}
		});
		
		quitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				controllerButton.quitter();
			}
		});
		
		this.add(option);
	}
}
