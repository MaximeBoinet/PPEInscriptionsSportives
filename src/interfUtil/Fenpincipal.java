package interfUtil;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Fenpincipal extends JFrame{

	private JTabbedPane onglet;
	private PanneauCompet panCompet = new PanneauCompet();
	private PanneauEquipe panEquipe = new PanneauEquipe();
	private PanneauPersonne panPersonne = new PanneauPersonne(); 
	
	public Fenpincipal(){
		this.setLocationRelativeTo(null);
		this.setTitle("Inscription Sportive M2L");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 300);
		onglet = new JTabbedPane();
		onglet.setBounds(0, 0, 300, 200);
		onglet.add("Compétitions", panCompet);
		onglet.add("Equipes", panEquipe);
		onglet.add("Personnes", panPersonne);
		this.getContentPane().add(onglet);
		this.setVisible(true);
		pack();
	}
}
