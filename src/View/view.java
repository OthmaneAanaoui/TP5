package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class view extends JFrame{
	private JPanel container;
	
	public view() {
		this.setTitle("Ma banque");
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		this.setJMenuBar(new MenuBar());
		this.add(container);
		
		this.setSize(600, 600);
		this.setVisible(true);
	}
}
