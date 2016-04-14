package inscriptions;


import interfUtil.MainApp;
import mail.mailmessage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;








import bd.Connect;
import menu.MenuPrincipal;
/**
 * Point d'entrée dans l'application, un seul objet de type Inscription
 * permet de gérer les compétitions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats à des compétition.
 */

public class Inscriptions implements Serializable
{
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
	private static Connect connection;
	private static boolean construction = true;
	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();
	private static MainApp mainApp;

	private Inscriptions()
	{
	}

	/**
	 * Retourne les compétitions.
	 * @return
	 */

	public SortedSet<Competition> getCompetitions()
	{
		return Collections.unmodifiableSortedSet(competitions);
	}

	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * @return
	 */

	public SortedSet<Candidat> getCandidats()
	{
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Créée une compétition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */

	public Competition createCompetition(String nom,
			LocalDate dateCloture, boolean enEquipe, int identifiant)
	{
		if (!construction)
			identifiant = getConnection().ajouterCompetition(nom, dateCloture, enEquipe);

		Competition competition = new Competition(this, nom, dateCloture, enEquipe, identifiant);
		competitions.add(competition);
		return competition;
	}

	public static void setMainApp(MainApp mainApps)
	{
		mainApp = mainApps;
	}

	public static MainApp getMainApp() {
		return mainApp;
	}
	/**
	 * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.

	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */

	public Personne createPersonne(String nom, String prenom, String mail, int identifiant)
	{
		if (!construction) 
			identifiant = getConnection().ajouterPersonne(nom, prenom, mail);
		
		Personne personne = new Personne(this, nom, prenom, mail, identifiant);
		if (!construction) {
			try {
				new mailmessage(personne.getMail(), "Inscription", "Vous venez d'être inscrit à l'application m2l" ).envoyer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		candidats.add(personne);
		return personne;
	}

	/**
	 * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */

	public Equipe createEquipe(String nom, int identifiant)
	{
		if (!construction)
			identifiant = getConnection().ajouterEquipe(nom);

		Equipe equipe = new Equipe(this, nom, identifiant);
		candidats.add(equipe);
		return equipe;
	}

	void remove(Competition competition)
	{
		getConnection().EnleverCompet(competition.getId());
		competitions.remove(competition);
	}

	void remove(Candidat candidat)
	{
		if (candidat instanceof Personne)
			getConnection().EnleverPersonne(((Personne) candidat).getId());
		else
			getConnection().EnleverEquipe(candidat.getId());
		candidats.remove(candidat);
	}

	/**
	 * Retourne l'unique instance de cette classe.
	 * Crée cet objet s'il n'existe déjà.
	 * @return l'unique objet de type {@link Inscriptions}.
	 */

	public static Inscriptions getInscriptions()
	{

		if (inscriptions == null)
		{
			setConnection(new Connect());
			inscriptions = new Inscriptions();
			try {
				getConnection().getBaseD(inscriptions);
				construction = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//inscriptions = readObject();

			//if (inscriptions == null)
			//	inscriptions = new Inscriptions();
		}
		return inscriptions;
	}

	public static boolean getConstruction()
	{
		return construction;
	}
	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */

	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la dernière sauvegarde.
	 * Ne modifie pas les compétitions et candidats déjà existants.
	 */

	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}

	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;

		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				}
				catch (IOException e){}
		}
	}

	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement
	 * lors d'une exécution ultérieure du programme.
	 * @throws IOException
	 */

	public void sauvegarder() throws IOException
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);

			System.out.println("Sauvegardé");
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (oos != null)
					oos.close();
			}
			catch (IOException e){}
		}
	}

	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString()
			+ "\nCompetitions  " + getCompetitions().toString();
	}

	public static void main(String[] args)
	{
		MainApp.main(args);
	}

	public static Connect getConnection() {
		return connection;
	}

	public static void setConnection(Connect connection) {
		Inscriptions.connection = connection;
	}
}
