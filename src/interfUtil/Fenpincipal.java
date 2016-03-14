package interfUtil;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Fenpincipal extends JFrame{

	private JTabbedPane onglet;
	private PanneauCompet panCompet = new PanneauCompet();
	private PanneauEquipe panEquipe = new PanneauEquipe();
	private PanneauPersonne panPersonne = new PanneauPersonne(); 
	
	public Fenpincipal(){
		this.setLocationRelativeTo(null);
		this.setTitle("Gérer vos conteneurs");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
		System.out.println("1");
		onglet = new JTabbedPane();
		onglet.add("Compétitions", panCompet);
		onglet.add("Equipes", panEquipe);
		onglet.add("Personnes", panPersonne);
		System.out.println("2");
		
		//On passe ensuite les onglets au content pane
		this.getContentPane().add(onglet);
		this.setVisible(true);
		System.out.println("3");
	}
}
