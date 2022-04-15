package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame{
	private JPanel container;
	private ConsulterCompte consulterCompte;
	private OuvrirCompte OuvrirCompte;
	private DepotPopUp depotPopUp;
	private RetraitPopUp retraitPopUp;
	
	public MainView() {
		depotPopUp = new DepotPopUp(container);
		retraitPopUp = new RetraitPopUp(container);
		
		this.setTitle("Giuchet Auto");
		container = new JPanel();
		container.setLayout(new BorderLayout());
		
		this.OuvrirCompte = new OuvrirCompte();
		
		container.add(OuvrirCompte);
		
		this.setJMenuBar(new MenuBar());
		this.add(container);
		
		this.setSize(600, 600);
	}

	public DepotPopUp getDepotPopUp() {
		return depotPopUp;
	}

	public void setDepotPopUp(DepotPopUp depotPopUp) {
		this.depotPopUp = depotPopUp;
	}

	public RetraitPopUp getRetraitPopUp() {
		return retraitPopUp;
	}

	public void setRetraitPopUp(RetraitPopUp retraitPopUp) {
		this.retraitPopUp = retraitPopUp;
	}
	
	
	
}
