package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
		
		JButton btn = new JButton();

		JFileChooser fileChooser = new JFileChooser("resources");

		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				int rep = fileChooser.showOpenDialog(null);
				
				if (rep == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					
					System.out.println(file.getName());
					
				}
			}
		});
		
		
		this.add(btn);
		
		this.setSize(600, 600);
		this.setVisible(true);
	}
}
