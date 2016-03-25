package bd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.mysql.jdbc.PreparedStatement;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;


public class Connect {
	
	private String host = "jdbc:mysql://localhost:3306/ppe inscription sportif";
	private String username = "root";
	private String password = "";
	private Connection connec = null;
	private Statement statement = null;
	java.sql.PreparedStatement prepare = null;
	ResultSet resultat = null;
	String query;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	 
     //Créé connexion à la BD
  
	public Connect() {
		try {
			connec = DriverManager.getConnection(host, username, password);
			System.out.println("Works");
			statement =  connec.createStatement();
		} 
		catch (Exception e) {
			System.out.println("problème de connexion");
		}
	}
	

	 //Initialise l'inscription avec BD

	public void getBaseD(Inscriptions inscription) throws SQLException {
		getPersonnes(inscription);
		getEquipes(inscription);
		getCompet(inscription);
		getPersonnesEquipes(inscription);
		getParticipCompet(inscription);	
	}
	
	
	 // Affiche les personnes dans leurs équipes 
	
	private void getPersonnesEquipes(Inscriptions inscription) {
		try {
			resultat = statement.executeQuery("call SelectPersoEquipe() ");
			
			while(resultat.next()){
				for (Candidat candi : inscription.getCandidats()){
					if(candi.getNom().equals(resultat.getString("nompersonne"))&& candi instanceof Personne){
						for (Candidat equipe : inscription.getCandidats()){
							 
							if(equipe.getNom().equals(resultat.getString("candidat_idcandidat")) && equipe instanceof Equipe)
							 ((Equipe) equipe).add((Personne) candi);	
					/*if(candi instanceof Personne && candi.getNom().equals(resultat.getString("prenompersonne"))){
						for (Candidat equipe : inscription.getCandidats()){
							if(equipe instanceof Equipe ) {
								query = "call RecupNomCandidat(?)";
								prepare = connec.prepareStatement(query);
								prepare.setString(1, resultat.getString("equipe_candidat_idcandidat"));
								ResultSet resultat2 = prepare.executeQuery();
								resultat2.next();
								if (equipe.getNom().equals(resultat2.getString("nomcandidat"))) {
									((Equipe) equipe).add((Personne) candi);
								}
							}*/			
						}
					}
				}
			}
		} 
		
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	 //Affiche candidats de type personnes 
	
	private void getPersonnes(Inscriptions inscription) throws SQLException{
		resultat = statement.executeQuery("call SelectCandiPerso()");
		while (resultat.next()) {	
			inscription.createPersonne(resultat.getString("nomcandidat"), resultat.getString("nompersonne"), resultat.getString("mailpersonne"));
		}
	}
	
	 //Affiche candidats de type équipes

	private void getEquipes(Inscriptions inscription) throws SQLException{
		resultat = statement.executeQuery("call SelectCandiEquipe()");
		while (resultat.next()) {
			inscription.createEquipe(resultat.getString("nomcandidat"));	
		}
	}
	
	
	// Affiche toutes les compétitions
	
	private void getCompet(Inscriptions inscription) throws SQLException{
		resultat = statement.executeQuery("call SelectCompet()");
		while (resultat.next()) {
			LocalDate date = LocalDate.parse(resultat.getString("datecloture"), formatter);
			inscription.createCompetition(resultat.getString("nomcompet"), date, resultat.getBoolean("enequipe"));
		}
	}
	

	// Affiche toutes les compétitions ainsi que les participants des disciplines respectifs, 

	private void getParticipCompet(Inscriptions inscription) {
		try {
			Statement statement = connec.createStatement();
			ResultSet resultat = statement.executeQuery("call SelectPartiCompet()");
			while(resultat.next()){
				for (Competition compet : inscription.getCompetitions()) {
					if (compet.getNom().equals(resultat.getString("nomcompet"))){
						if (compet.estEnEquipe()){
							for (Candidat candi : inscription.getCandidats()) {
								if (candi.getNom().equals(resultat.getString("nomcandidat")) && candi instanceof Equipe)
									compet.add((Equipe) candi);	
							}	
						}
						
						// Si la compétition solo alors on va choisir candidats via leur E-mail
						
						else{
							Statement statementa = connec.createStatement();
							ResultSet resultata = statementa.executeQuery("call SelectCandiPerso()");
							
							while(resultata.next()){
								for (Candidat candi : inscription.getCandidats()) {
									if ( candi instanceof Personne && ((Personne) candi).getMail().equals(resultata.getString("mailpersonne")))
										compet.add((Personne)candi);
								}
							}
						}
					}	
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	
	 // Ajoute une équipe
	
	public void ajouterEquipe(String nomcandidat){
		try {
			query = "call AjoutEquipe(?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1, nomcandidat);
			prepare.executeQuery();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Ajoute une personne

	public void ajouterPersonne(String nomequipe, String nompersonne, String mailpersonne) {
		try {
			query = "call AjoutPersonne(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1, mailpersonne);
			prepare.setString(1, nompersonne);
			prepare.setString(2, mailpersonne);
			prepare.executeQuery();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Ajoute une compétiton 

	public void ajouterCompetition(String nomcompet, LocalDate dateCloturec, boolean enEquipe) {
		try { 
			query = "call AjoutCompet(?,?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1, nomcompet);
			prepare.setDate(2,Date.valueOf(dateCloturec));
			prepare.setBoolean(3,enEquipe);
			prepare.executeQuery();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
		//Supprime une compétition 
	 
	public void EnleverCompet(String nomc) {
		try {
			query = "call SupprimeCompet('"+nomc+"')";
			statement.executeQuery(query);
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		//Supprime une personne

	public void EnleverPersonne(String mailp) {
		
		query = "call SupprimePersonne('"+mailp+"')";
		try {
			statement.executeQuery(query);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

		// Supprime une équipe 

	public void EnleverEquipe(String nome) {
		
		query = "call SupprimeEquipe('"+nome+"')";
		try {
			statement.executeQuery(query);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	// Inscrit un candidat à une compétitions 

	public void InscritCompetCandi(Candidat candidat, Competition competition) {
		int idcandidat = 0,idcompetition = 0;
		try { 
					query = "call AjoutCandiCompet(?,?)";
					prepare = connec.prepareStatement(query);
					prepare.setInt(1 ,idcandidat);
					prepare.setInt(2, idcompetition);
					prepare.executeQuery();
	/*		if(candidat instanceof Personne){
				resultat = statement.executeQuery("call AjoutPersonneCompet('"+((Personne) candidat).getMail()+"','"+competition.getNom()+"')");
			}
			else{
				resultat = statement.executeQuery("call AjoutEquipeCompet('"+candidat.getNom()+"','"+competition.getNom()+"')");
			}
			while(resultat.next()){
				idcandidat = resultat.getInt("Numcandidat");
				idcompetition = resultat.getInt("Numcompetition");
				
			Statement statementa = connec.createStatement();
			statementa.executeQuery("call AjoutCandiCompet("+idcandidat+","+idcompetition+")");*/
		} 
		
					
		catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	
	// Ajoute un candidat dans une equipe

	public void AjoutCandiDansEquipe(String nomp, String mailp) {
		try { 
			query = "call AjoutPersonneEquipe(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1,nomp);
			prepare.setString(2, mailp);
			prepare.executeQuery();
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	
	/* Retire une personne d'une équipe */
	public void EnlevePersonneEquipe(String mailp, String nomp)
	{
		int persone_candidat_idcandidat = 0,equipe_candidat_idcandidat= 0;
		try 
		{

			query = "call SupprimePersonneEquipe(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setInt(1,equipe_candidat_idcandidat);
			prepare.setInt(2,persone_candidat_idcandidat);
		
			prepare.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/* Retire un candidat (équipe ou personne) d'une compétitions*/
	public void retirerCandidatCompetition(Candidat candidat, Competition competition) {
		int idcandidat = 0,idcompetition = 0;
		try 
		{ 
				query = "call SupprimeCandidatCompet(?,?)";
				prepare = connec.prepareStatement(query);
				prepare.setInt(1, idcandidat );
				prepare.setInt(2,idcompetition);
			prepare.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Modification nom d'une competition

	public void ModifieNomCompet(Competition compet, String nomc) {
		try { 
			query = "call ModifCompetition(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1,compet.getNom());
			prepare.setString(2, nomc);
			prepare.executeQuery();
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	// Modification du mail d'une personne

	public void ModifMailCandidat(Candidat candidat, String mailp) {
		try { 
			if (candidat instanceof Personne){
				query = "call ModifPersonne(?,?)";
				prepare = connec.prepareStatement(query);
				prepare.setString(1,((Personne)candidat).getMail());
				prepare.setString(2, mailp);
			}
			prepare.executeQuery();	
		} 
		catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	
}




	
	
