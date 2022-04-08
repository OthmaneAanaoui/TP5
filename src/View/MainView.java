package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame{
	private JPanel container;
	private ConsulterCompte consulterCompte;
	
	public MainView() {
		this.setTitle("Giuchet Auto");
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		this.consulterCompte = new ConsulterCompte();
		
		container.add(consulterCompte);
		
		this.setJMenuBar(new MenuBar());
		this.add(container);
		
		this.setSize(600, 600);
	}
}
