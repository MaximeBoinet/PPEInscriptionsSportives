package inscriptions;


import java.io.Serializable;
import java.util.Collections;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

/**
 * Représente une compétition, c'est-à-dire un ensemble de candidats
 * inscrits à un événement, les inscriptions sont closes à la date dateCloture.
 *
 */

public class Competition implements Comparable<Competition>, Serializable
{
	private static final long serialVersionUID = -2882150118573759729L;
	private Inscriptions inscriptions;
	private String nom;
	private Set<Candidat> candidats;
	private LocalDate dateCloture;
	private boolean enEquipe = false;
	private int identifiant;

	Competition(Inscriptions inscriptions, String nom, LocalDate dateCloture, boolean enEquipe, int identifiant)
	{
		this.enEquipe = enEquipe;
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.dateCloture = dateCloture;
		this.identifiant = identifiant;
		candidats = new TreeSet<>();
		
	}

	/**
	 * Retourne l'identifiant de la competition
	 * @return 
	 */
	
	public int getId() {
		return identifiant;
	}
	
	/**
	 * Retourne le nom de la compétition.
	 * @return
	 */

	public String getNom()
	{
		return nom;
	}

	/**
	 * Retourne vrai si les inscriptions sont encore ouvertes,
	 * faux si les inscriptions sont closes.
	 * @return
	 */

	public boolean inscriptionsOuvertes()
	{
		// TODO retourner vrai si la date système est antérieur à la date de clôture.

		return dateCloture == null || LocalDate.now().isBefore(dateCloture);
	}

	/**
	 * Retourne la date de cloture des inscriptions.
	 * @return
	 */

	public LocalDate getDateCloture()
	{
		return dateCloture == null ? null : dateCloture;
	}

	/**
	 * Est vrai si et seulement si les inscriptions sont réservées aux équipes.
	 * @return
	 */

	public boolean estEnEquipe()
	{
		return enEquipe;
	}

	/**
	 * Modifie la date de cloture des inscriptions. Il est possible de la reculer
	 * mais pas de l'avancer.
	 * @param dateCloture
	 */

	public void setDateCloture(LocalDate dateCloture)
	{
		// TODO vérifier que l'on avance pas la date.
		if (this.dateCloture == null || dateCloture.isAfter(getDateCloture())) {
			Inscriptions.getConnection().ModifieDateCompet(dateCloture, this);
			this.dateCloture = dateCloture;
		}

	}

	/**
	 * Retourne l'ensemble des candidats inscrits.
	 * @return
	 */

	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}

	/**
	 * Inscrit un candidat de type Personne à la compétition. Provoque une
	 * exception si la compétition est réservée aux équipes.
	 * @param personne
	 * @return
	 */

	public boolean add(Personne personne)
	{
		// TODO vérifier que la date de clôture n'est pas passée
		if (dateCloture == null || LocalDate.now().isBefore(dateCloture))
		{
			if (enEquipe)
				throw new RuntimeException();
			personne.add(this);
			System.out.println("okp");
			Inscriptions.getConnection().InscritCompetCandi(personne, this);

			return candidats.add(personne);
		}
		else
			return false;
	}

	/**
	 * Inscrit un candidat de type Equipe à la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes.
	 * @param personne
	 * @return
	 */

	public boolean add(Equipe equipe)
	{
		// TODO vérifier que la date de clôture n'est pas passée
		if (dateCloture == null || LocalDate.now().isBefore(dateCloture))
		{
			if (!enEquipe)
				throw new RuntimeException();
			equipe.add(this);
			System.out.println("oke");
			Inscriptions.getConnection().InscritCompetCandi(equipe, this);
			return candidats.add(equipe);
		}
		else
			return false;
	}

	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */

	public boolean remove(Candidat candidat)
	{
		Inscriptions.getConnection().retirerCandidatCompetition(candidat, this);
		candidat.remove(this);
		return candidats.remove(candidat);
	}

	/**
	 * Supprime la compétition de l'application.
	 */

	public void delete()
	{
		for (Candidat candidat : candidats)
			remove(candidat);
		Inscriptions.getConnection().EnleverCompet(this.getId());
		inscriptions.remove(this);
	}

	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}

	@Override
	public String toString()
	{
		return identifiant + " " + getNom();
	}
}
