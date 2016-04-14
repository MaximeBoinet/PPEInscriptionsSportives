package inscriptions;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant
 * s'inscrire à une compétition.
 *
 */

public class Equipe extends Candidat
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();

	Equipe(Inscriptions inscriptions, String nom, int identifiant)
	{
		super(inscriptions, nom, identifiant);
	}

	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
	 */

	public SortedSet<Personne> getMembres()
	{
		return Collections.unmodifiableSortedSet(membres);
	}

	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 */

	public boolean add(Personne membre)
	{
		System.out.println(!Inscriptions.getConstruction());
		if (!Inscriptions.getConstruction())
			Inscriptions.getConnection().AjoutCandiDansEquipe(this, membre);

		membre.add(this);
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'équipe.
	 * @param membre
	 * @return
	 */

	public boolean remove(Personne membre)
	{
		membre.remove(this);
		if (!Inscriptions.getConstruction())
			Inscriptions.getConnection().EnlevePersonneEquipe(membre, this);
		return membres.remove(membre);
	}

	@Override
	public void delete()
	{
		super.delete();
	}

	@Override
	public String toString()
	{
		return "Equipe " + super.toString();
	}
}
