package inscriptions;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Candidat à un événement sportif, soit une personne physique, soit une équipe.
 *
 */

public abstract class Candidat implements Comparable<Candidat>, Serializable
{
	private static final long serialVersionUID = -6035399822298694746L;
	private Inscriptions inscriptions;
	private String nom;
	private Set<Competition> competitions;
	private int identifiant;

	Candidat(Inscriptions inscriptions, String nom, int identifiant)
	{
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.identifiant = identifiant;
		competitions = new TreeSet<>();
	}

	/**
	 * Retourne l'identifiant du candidat
	 * @return 
	 */
	
	public int getId() {
		return identifiant;
	}
	
	/**
	 * Retourne le nom du candidat.
	 * @return
	 */

	public String getNom()
	{
		return nom;
	}

	/**
	 * Modifie le nom du candidat.
	 * @param nom
	 */

	public void setNom(String nom)
	{
		Inscriptions.getConnection().ModifNomPersonne(this, nom);
		this.nom = nom;
	}

	/**
	 * Retourne toutes les compétitions auxquelles ce candidat est inscrit.s
	 * @return
	 */

	public Set<Competition> getCompetitions()
	{
		return Collections.unmodifiableSet(competitions);
	}

	boolean add(Competition competition)
	{
		return competitions.add(competition);
	}

	boolean remove(Competition competition)
	{
		return competitions.remove(competition);
	}

	/**
	 * Supprime un candidat de l'application.
	 */

	public void delete()
	{
		for (Competition c : competitions)
			c.remove(this);
		inscriptions.remove(this);
		if (this instanceof Equipe) {
			Inscriptions.getConnection().EnleverEquipe(this.getId());
		} else if (this instanceof Personne) {
			Inscriptions.getConnection().EnleverPersonne(((Personne)this).getId());
		}
	}

	@Override
	public int compareTo(Candidat o)
	{
		return getNom().compareTo(o.getNom());
	}

	@Override
	public String toString()
	{
		return "\n" + identifiant + " " + getNom() + " -> inscrit à " + getCompetitions();
	}
}
