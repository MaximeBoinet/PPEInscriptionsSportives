package interfUtil;

import java.util.SortedSet;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import inscriptions.Competition;
import inscriptions.Inscriptions;

@SuppressWarnings("serial")
public class Fenpincipal extends JFrame{

	private JTabbedPane onglet;
	private JPanel panCompet = new JPanel();
	private JPanel panEquipe = new JPanel();
	private JPanel panPersonne = new JPanel(); 
	
	public Fenpincipal(){
		this.setLocationRelativeTo(null);
		this.setTitle("Inscription Sportive M2L");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 300);
		
		panCompet.add(new JScrollPane(getJTableCompet()));
		
		onglet = new JTabbedPane();
		onglet.setBounds(0, 0, 300, 200);
		onglet.add("Compétitions", panCompet);
		onglet.add("Equipes", new JScrollPane(panEquipe));
		onglet.add("Personnes", new JScrollPane(panPersonne));
		this.getContentPane().add(onglet);
		this.setVisible(true);
		pack();
	}
	
	public JTable getJTableCompet()
	{
		SortedSet<Competition> competitions = Inscriptions.getInscriptions().getCompetitions();
		Object[][] donnees = new Object[competitions.size()][6];
		int cpt = 0;
		for (Competition competition : competitions) {
			donnees[cpt][0] = competition.getNom();
			donnees[cpt][1] = competition.getDateCloture();
			donnees[cpt][2] = competition.estEnEquipe() ? "Oui" : "Non";
			donnees[cpt][3] = competition.getCandidats().size();
			donnees[cpt][4] = "Les Ccandi";
			donnees[cpt][5] = "Supprimer";
			cpt++;
		}
		
		String[] entetes = {"Nom", "Date de Cloture", "En Equipe", "Nbr Candidat", "Les Candidat", "Supprimer"};
		JTable tab = new JTable(new ZModel(donnees, entetes));
		tab.setRowHeight(20);
		tab.getColumn("Les Candidat").setCellEditor(new ComboEditor(new JComboBox()));
		tab.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());
		tab.getColumn("Supprimer").setCellEditor(new ButtonEditor(new JCheckBox()));
		tab.setDefaultRenderer(JComponent.class,new TableComponent());
		return tab;
	}
}
