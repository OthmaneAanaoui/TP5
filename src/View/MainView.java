package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame{
	private ConsulterCompte consulterCompte;
	private OuvrirCompte OuvrirCompte;
	private DepotPopUp depotPopUp;
	private RetraitPopUp retraitPopUp;
	private APropos aPropos;
	
	public MainView() {
		this.depotPopUp = new DepotPopUp();
		this.retraitPopUp = new RetraitPopUp();
		
		this.setTitle("Giuchet Auto");
		
		this.consulterCompte = new ConsulterCompte();
		this.OuvrirCompte = new OuvrirCompte();
		this.aPropos = new APropos();
		
		this.add(aPropos);
		this.add(consulterCompte);
		this.add(OuvrirCompte);
		
		this.setJMenuBar(new MenuBar());
		
		this.setContentPane(aPropos);
        this.revalidate();
		
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
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

	public ConsulterCompte getConsulterCompte() {
		return consulterCompte;
	}

	public void setConsulterCompte(ConsulterCompte consulterCompte) {
		this.consulterCompte = consulterCompte;
	}

	public OuvrirCompte getOuvrirCompte() {
		return OuvrirCompte;
	}

	public void setOuvrirCompte(OuvrirCompte ouvrirCompte) {
		OuvrirCompte = ouvrirCompte;
	}
	
	public APropos getaPropos() {
		return aPropos;
	}

	public void setaPropos(APropos aPropos) {
		this.aPropos = aPropos;
	}
}
