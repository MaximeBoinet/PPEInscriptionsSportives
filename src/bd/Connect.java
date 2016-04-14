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
					if(candi instanceof Personne && ((Personne)candi).getId() == (resultat.getInt("candidat_idcandidat"))){
						for (Candidat equipe : inscription.getCandidats()){
							if(equipe instanceof Equipe && equipe.getId() == (resultat.getInt("equipe_candidat_idcandidat")))
							 ((Equipe) equipe).add((Personne) candi);
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
			inscription.createPersonne(resultat.getString("nomcandidat"), resultat.getString("nompersonne")
					, resultat.getString("mailpersonne"), resultat.getInt("idcandidat"));
		}
	}

	 //Affiche candidats de type équipes

	private void getEquipes(Inscriptions inscription) throws SQLException{
		resultat = statement.executeQuery("call SelectCandiEquipe()");
		while (resultat.next()) {
			inscription.createEquipe(resultat.getString("nomcandidat"), resultat.getInt("idcandidat"));
		}
	}


	// Affiche toutes les compétitions

	private void getCompet(Inscriptions inscription) throws SQLException{
		resultat = statement.executeQuery("call SelectCompet()");
		while (resultat.next()) {
			LocalDate date = LocalDate.parse(resultat.getString("datecloture"), formatter);
			inscription.createCompetition(resultat.getString("nomcompet"), date, resultat.getBoolean("enequipe")
					, resultat.getInt("idcompetition"));
		}
	}


	// Affiche toutes les compétitions ainsi que les participants des disciplines respectifs,

	private void getParticipCompet(Inscriptions inscription) {
		try {
			Statement statement = connec.createStatement();
			ResultSet resultat = statement.executeQuery("call SelectPartiCompet()");
			while(resultat.next()){
				for (Competition compet : inscription.getCompetitions()) {
					if (compet.getId() == (resultat.getInt("idcompetition"))){
						for (Candidat candi : inscription.getCandidats()){
							if (candi.getId() == (resultat.getInt("idcandidat"))){
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

	public int ajouterEquipe(String nomcandidat){
		try {
			query = "call AjoutEquipe(?)";
			prepare = connec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, nomcandidat);
			ResultSet rs = prepare.executeQuery();
			rs.next();
			return rs.getInt("ID");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// Ajoute une personne

	public int ajouterPersonne(String nomcandidat, String nompersonne, String mailpersonne) {
		try {
			query = "call AjoutPersonne(?,?,?)";
			prepare = connec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, nomcandidat);
			prepare.setString(2, nompersonne);
			prepare.setString(3, mailpersonne);
			ResultSet rs = prepare.executeQuery();
			rs.next();
			return rs.getInt("ID");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// Ajoute une compétiton

	public int ajouterCompetition(String nomcompet, LocalDate dateCloturec, boolean enEquipe) {
		try {
			query = "call AjoutCompet(?,?,?)";
			prepare = connec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prepare.setString(1, nomcompet);
			prepare.setDate(2,Date.valueOf(dateCloturec));
			prepare.setBoolean(3,enEquipe);
			ResultSet rs = prepare.executeQuery();
			rs.next();
			return rs.getInt("ID");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}


		//Supprime une compétition

	public void EnleverCompet(int idc) {
		try {
			query = "call SupprimeCompet('"+idc+"')";
			statement.execute(query);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		//Supprime une personne

	public void EnleverPersonne(int idp) {
		query = "call SupprimePersonne('"+idp+"')";
		try {
			statement.execute(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

		// Supprime une équipe

	public void EnleverEquipe(int ide) {
		query = "call SupprimeEquipe('"+ide+"')";
		try {
			statement.execute(query);
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
			prepare.setInt(1 ,candidat.getId());
			prepare.setInt(2, competition.getId());
			prepare.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Ajoute un candidat dans une equipe

	public void AjoutCandiDansEquipe(Equipe equipe, Personne pers) {
		try {
			query = "call AjoutPersonneEquipe(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setInt(1, equipe.getId());
			prepare.setInt(2, pers.getId());
			prepare.execute();
		}
		catch (SQLException e) {
			System.out.println("Echec ajout");
		}
	}

	/* Retire une personne d'une équipe */
	public void EnlevePersonneEquipe(Personne personne, Equipe equipe)
	{
		try {
			query = "call SupprimePersonneEquipe(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setInt(1, personne.getId());
			prepare.setInt(2, equipe.getId());
			prepare.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Retire un candidat (équipe ou personne) d'une compétitions*/
	public void retirerCandidatCompetition(Candidat candidat, Competition competition) {
		try {
			query = "call SupprimeCandidatCompet(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setInt(1, candidat.getId() );
			prepare.setInt(2, competition.getId());
			prepare.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Modification nom d'une competition

	public void ModifieNomCompet(Competition compet, String nomc) {
		try {
			query = "call ModifCompetition(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setInt(1, compet.getId());
			prepare.setString(2, nomc);
			prepare.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Modifie la date de competition
	public void ModifieDateCompet(LocalDate dateclot, Competition compet) {
		try {
			query = "call ModifDateCompetition(?,?)";
			prepare = connec.prepareStatement(query);
			prepare.setDate(1,Date.valueOf(dateclot));
			prepare.setInt(2, compet.getId());
			prepare.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Modification du mail d'une personne

	public void ModifMailCandidat(Candidat candidat, String mailp) {
		try {
			if (candidat instanceof Personne){
				query = "call ModifPersonne(?,?)";
				prepare = connec.prepareStatement(query);
				prepare.setString(1, mailp);
				prepare.setInt(2, candidat.getId());
			}
			prepare.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Modification le nom d'une personne

	public void ModifNomPersonne(Candidat candidat, String nomp) {
		try {
			if (candidat instanceof Personne){
				query = "call ModifNomPersonne(?,?)";
				prepare = connec.prepareStatement(query);
				prepare.setInt(1,((Personne)candidat).getId());
				prepare.setString(2, nomp);
			}
			prepare.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Modification le prenom d'une personne

	public void ModifPrenomPersonne(Candidat candidat, String prenomp) {
		try {
			if (candidat instanceof Personne){
				query = "call ModifPrenomPersonne(?,?)";
				prepare = connec.prepareStatement(query);
				prepare.setString(1, prenomp);
				prepare.setInt(2, candidat.getId());
			}
			prepare.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//Verifie si c'est un utilisateur présent dans la base
	
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






