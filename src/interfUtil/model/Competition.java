package interfUtil.model;

import java.time.LocalDate;
import java.util.Set;

import inscriptions.Candidat;
import inscriptions.Inscriptions;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Competition {

	private final ObjectProperty<Inscriptions> inscriptions;
	private final StringProperty nom;
	private final ObjectProperty<Set<Candidat>> candidats;
	private final ObjectProperty<LocalDate> dateCloture;
	private final BooleanProperty enEquipe;
	private final IntegerProperty nbrCandidat;

	public Competition() {
		this(null, null, null, null, false);
	}

	public Competition(Inscriptions inscription, String nom, Set<Candidat> candidats, LocalDate dateCloture, boolean enEquipe)
	{
		this.inscriptions = new SimpleObjectProperty<Inscriptions>(inscription);
		this.nom = new SimpleStringProperty(nom);
		this.candidats = new SimpleObjectProperty<Set<Candidat>>(candidats);
		this.dateCloture = new SimpleObjectProperty<LocalDate>(dateCloture);
		this.enEquipe = new SimpleBooleanProperty(enEquipe);
		this.nbrCandidat = new SimpleIntegerProperty(this.getCandidats().size());
	}

	public Inscriptions getInscriptions() {
		return this.inscriptions.get();
	}

	public void setInscriptions(Inscriptions inscription) {
		this.inscriptions.set(inscription);
	}

	public ObjectProperty<Inscriptions> inscriptionProperty() {
		return this.inscriptions;
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
	public Set<Candidat> getCandidats() {
		return this.candidats.get();
	}

	public void setCandidats(Set<Candidat> candidats) {
		this.candidats.set(candidats);
	}

	public ObjectProperty<Set<Candidat>> candidatsProperty() {
		return this.candidats;
	}
	public boolean isEnEquipe() {
		return this.enEquipe.get();
	}

	public void setEnEquipe(boolean enEquipe) {
		this.enEquipe.set(enEquipe);
	}

	public BooleanProperty enEquipeProperty() {
		return this.enEquipe;
	}

	public LocalDate getDateCloture() {
		return this.dateCloture.get();
	}

	public void setDateCloture(LocalDate dateCloture) {
		this.dateCloture.set(dateCloture);
	}

	public ObjectProperty<LocalDate> dateClotureProperty(){
		return this.dateCloture;
	}

	public int getNbrCandidat() {
		return nbrCandidat.get();
	}

	public void setNbrCandidat() {
		this.nbrCandidat.set(this.getCandidats().size());
	}

	public IntegerProperty nbrCandidatProperty() {
		return this.nbrCandidat;
	}
}
