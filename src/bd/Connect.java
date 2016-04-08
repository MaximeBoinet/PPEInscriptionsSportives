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

	private String host = "jdbc:mysql://localhost:3306/ppe inscription sportif2";
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
		System.out.println(Inscriptions.getInscriptions().toString());

	}


	 // Affiche les personnes dans leurs équipes

	private void getPersonnesEquipes(Inscriptions inscription) {
		try {
			resultat = statement.executeQuery("call SelectPersoEquipe() ");
			while(resultat.next()){
				for (Candidat candi : inscription.getCandidats()){
					if(candi instanceof Personne && ((Personne)candi).getPrenom().equals(resultat.getString("nompersonne"))){
						for (Candidat equipe : inscription.getCandidats()){
							if(equipe instanceof Equipe && equipe.getNom().equals(resultat.getString("nomcandidat")))
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
			//System.out.println(resultat.getString("nomcompet") + " " + resultat.getBoolean("enequipe"));
		}
	}


	// Affiche toutes les compétitions ainsi que les participants des disciplines respectifs,

	private void getParticipCompet(Inscriptions inscription) {
		try {
			Statement statement = connec.createStatement();
			ResultSet resultat = statement.executeQuery("call SelectPartiCompet()");
			while(resultat.next()){
				for (Competition compet : inscription.getCompetitions()) {
					//System.out.println(resultat.getString("nomcompet") + " " + compet.getNom() + " " + compet.getNom().equals(resultat.getString("nomcompet" )));
					if (compet.getNom().equals(resultat.getString("nomcompet"))){
						for (Candidat candi : inscription.getCandidats()){
							//System.out.println(resultat.getString("nomcandidat") + " " + candi.getNom() + " " + candi.getNom().equals(resultat.getString("nomcandidat")));
							//System.out.println(compet.getNom() + " " + compet.estEnEquipe());
							if (candi.getNom().equals(resultat.getString("nomcandidat"))){
								System.out.println(candi.getNom() + " " + compet.getNom());
								if (compet.estEnEquipe()) {
									compet.add((Equipe) candi);
								}
								else {
									compet.add((Personne) candi);
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

	public void ajouterPersonne(String nomcandidat, String nompersonne, String mailpersonne) {
		try {
			query = "call AjoutPersonne(?,?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1, nomcandidat);
			prepare.setString(2, nompersonne);
			prepare.setString(3, mailpersonne);
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
		try {
					query = "call AjoutCandiCompet(?,?)";
					prepare = connec.prepareStatement(query);
					prepare.setString(1 ,candidat.getNom());
					prepare.setString(2, competition.getNom());
					prepare.executeQuery();

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
			prepare.setString(1, mailp);
			prepare.setString(2, nomp);
			prepare.executeQuery();
			System.out.println(nomp + " " + mailp);
			System.out.println("ok");
		}
		catch (SQLException e) {
			System.out.println("Echec ajout");
		}
	}

	/* Retire une personne d'une équipe */
	public void EnlevePersonneEquipe(Personne personne, Equipe equipe)
	{
		try
		{
			query = "call SupprimePersonneEquipe(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setString(1, personne.getNom());
			prepare.setString(2,equipe.getNom());

			prepare.executeQuery();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/* Retire un candidat (équipe ou personne) d'une compétitions*/
	public void retirerCandidatCompetition(Candidat candidat, Competition competition) {
		try
		{
				query = "call SupprimeCandidatCompet(?,?)";
				prepare = connec.prepareStatement(query);
				prepare.setString(1, candidat.getNom() );
				prepare.setString(2,competition.getNom());
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
			prepare.setString(2,compet.getNom());
			prepare.setString(1, nomc);
			prepare.executeQuery();
		}
		catch (SQLException e) {
			//e.printStackTrace();
		}
	}

	public void ModifieDateCompet(LocalDate dateclot, LocalDate dateclo, String nomco) {
		try {
			query = "call ModifDateCompetition(?,?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setDate(1,Date.valueOf(dateclot));
			prepare.setDate(2, Date.valueOf(dateclo));
			prepare.setString(3, nomco);
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
				prepare.setString(2,((Personne)candidat).getMail());
				prepare.setString(1, mailp);
			}
			prepare.executeQuery();
		}
		catch (SQLException e) {
			//e.printStackTrace();
		}
	}

	// Modification le nom d'une personne

		public void ModifNomPersonne(Candidat candidat, String nomp) {
			try {
				if (candidat instanceof Personne){
					query = "call ModifNomPersonne(?,?)";
					prepare = connec.prepareStatement(query);
					prepare.setString(1,((Personne)candidat).getNom());
					prepare.setString(2, nomp);
				}
				prepare.executeQuery();
			}
			catch (SQLException e) {
				//e.printStackTrace();
			}
		}

		// Modification le prenom d'une personne

				public void ModifPrenomPersonne(Candidat candidat, String prenomp) {
					try {
						if (candidat instanceof Personne){
							query = "call ModifPrenomPersonne(?,?)";
							prepare = connec.prepareStatement(query);
							prepare.setString(1, prenomp);
							prepare.setString(2,((Personne)candidat).getPrenom());
						}
						prepare.executeQuery();
					}
					catch (SQLException e) {
						//e.printStackTrace();
					}
				}
				
				public boolean isUser(String pseudo, String password) {
					query = "call RecupUser(?,?)";
					try {
						prepare = connec.prepareStatement(query);
						prepare.setString(1, pseudo);
						prepare.setString(2, password);
						resultat = prepare.executeQuery();
						return resultat.next();
					} catch (SQLException e) {
						e.printStackTrace();
						return false;
					}
				}
}






