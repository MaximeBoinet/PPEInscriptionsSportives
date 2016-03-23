package interfUtil.model;

import inscriptions.Personne;

import java.util.Set;
import java.util.SortedSet;

import inscriptions.Competition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Equipe {
	private final ObjectProperty<Set<Competition>> competitions;
	private final StringProperty nom;
	private final ObjectProperty<SortedSet<Personne>> membres;
	private final IntegerProperty nbrCompetition;
	private final IntegerProperty nbrMembres;

	public Equipe(String nom, Set<Competition> competitions, SortedSet<Personne> membres) {
		this.nom = new SimpleStringProperty(nom);
		this.competitions = new SimpleObjectProperty<Set<Competition>>(competitions);
		this.membres = new SimpleObjectProperty<SortedSet<Personne>>(membres);
		this.nbrCompetition = new SimpleIntegerProperty(this.getCompetitions().size());
		this.nbrMembres = new SimpleIntegerProperty(this.getMembres().size());
	}

	public Set<Competition> getCompetitions() {
		return this.competitions.get();
	}

	public void setCompetitions(Set<Competition> competitions) {
		this.competitions.set(competitions);
	}

	public ObjectProperty<Set<Competition>> competitionProperty() {
		return this.competitions;
	}

	public String getNom() {
		return this.nom.get();
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public StringProperty nomProperty() {
		return this.nom;
	}
	public SortedSet<Personne> getMembres() {
		return this.membres.get();
	}

	public void setMembres(SortedSet<Personne> membre) {
		this.membres.set(membre);
	}

	public ObjectProperty<SortedSet<Personne>> membresProperty() {
		return this.membres;
	}

	public int getNbrCompetition() {
		return this.nbrCompetition.get();
	}

	public void setNbrCompetition() {
		this.nbrCompetition.set(this.getCompetitions().size());
	}

	public IntegerProperty nbrCompetitionProperty() {
		return this.nbrCompetition;
	}

	public int getNbrMembres() {
		return this.nbrMembres.get();
	}

	public void setNbrMembres() {
		this.nbrMembres.set(this.getMembres().size());
	}
	public IntegerProperty nbrMembresProperty() {
		return this.nbrMembres;
	}
}
