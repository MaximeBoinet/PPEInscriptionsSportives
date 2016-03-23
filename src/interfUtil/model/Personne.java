package interfUtil.model;

import java.util.Set;

import inscriptions.Competition;
import inscriptions.Equipe;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {
	private final ObjectProperty<Set<Competition>> competitions;
	private final StringProperty nom;
	private final StringProperty prenom, mail;
	private final ObjectProperty<Set<Equipe>> equipes;
	private final IntegerProperty nbrCompetition, nbrEquipes;

	public Personne(Set<Competition> competitions, String nom, String prenom, String mail
			, Set<Equipe> equipes) {
		this.competitions = new SimpleObjectProperty<Set<Competition>>(competitions);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.mail = new SimpleStringProperty(mail);
		this.equipes = new SimpleObjectProperty<Set<Equipe>>(equipes);
		this.nbrCompetition = new SimpleIntegerProperty(this.getCompetitions().size());
		this.nbrEquipes = new SimpleIntegerProperty(this.getEquipe().size());
	}

	public Set<Competition> getCompetitions() {
		return this.competitions.get();
	}

	public void setCompetition(Set<Competition> competitions) {
		this.competitions.set(competitions);
	}
	public ObjectProperty<Set<Competition>> competitionsProperty() {
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

	public String getPreom() {
		return this.prenom.get();
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	public StringProperty prenomProperty() {
		return this.prenom;
	}

	public String getMail() {
		return this.mail.get();
	}

	public void setMail(String mail) {
		this.mail.set(mail);
	}
	public StringProperty mailProperty() {
		return this.mail;
	}

	public Set<Equipe> getEquipe() {
		return this.equipes.get();
	}

	public void setEquipe(Set<Equipe> equipes) {
		this.equipes.set(equipes);
	}

	public ObjectProperty<Set<Equipe>> equipesProperty() {
		return this.equipes;
	}

	public int getNbrEquipes() {
		return this.nbrEquipes.get();
	}

	public void setNbrEquipes() {
		this.nbrEquipes.set(this.getEquipe().size());
	}

	public IntegerProperty nbrEquipesProperty() {
		return nbrEquipes;
	}

	public int getNbrCompetitions() {
		return this.nbrEquipes.get();
	}

	public void setNbrCompetitions() {
		this.nbrCompetition.set(this.getCompetitions().size());
	}

	public IntegerProperty nbrCompetitionProperty() {
		return nbrCompetition;
	}
}
