package interfUtil.view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import interfUtil.MainApp;
import inscriptions.Candidat;
import java.time.format.DateTimeFormatter;

import inscriptions.Competition;

public class PersonOverviewController {
    @FXML
    private TableView<Competition> competitions;
    @FXML
    private TableColumn<Competition, String> nomc;
    @FXML
    private TableColumn<Competition, String> datecloture;
    @FXML
    private TableColumn<Competition, String> nbrCandidat;
    @FXML
    private TableColumn<Competition, String> enEquipe;


    @FXML
    private TableView<Equipe> equipes;
    @FXML
    private TableColumn<Equipe, String> nome;
    @FXML
    private TableColumn<Equipe, String> nbrMembres;
    @FXML
    private TableColumn<Equipe, String> nbrCompetition;

    @FXML
    private TableView<Personne> personnes;
    @FXML
    private TableColumn<Personne, String> prenom;
    @FXML
    private TableColumn<Personne, String> nomp;
    @FXML
    private TableColumn<Personne, String> mail;
    @FXML
    private TableColumn<Personne, String> nbrEquipe;
    @FXML
    private TableColumn<Personne, String> nbrCompetitionp;

    //Champs pour les competitons
    @FXML
    private TableView<Candidat> equipeAInscrire;
    @FXML
    private TableColumn<Candidat, String> nomEAI;
    @FXML
    private TableView<Candidat> equipeInscrite;
    @FXML
    private TableColumn<Candidat, String> nomEI;
    @FXML
    private Button desinscrire = new Button("Desinscrire");
    @FXML
    private Button inscrire = new Button("Inscrire");
    @FXML
    private TextField nomcompet = new TextField();
    @FXML
    private CheckBox enEquipeCB = new CheckBox();
    @FXML
    private DatePicker dtePicker = new DatePicker();
    @FXML
    private Button changerDate = new Button("Changer");
    @FXML
    private DatePicker dtePickerSet = new DatePicker();

    //Champs pour les equipes
    private MainApp mainApp;

    Competition currentCompet = null;
	ObservableList<Candidat> lesEquipeAInscrire = FXCollections.observableArrayList();
	ObservableList<Candidat> lesEquipeInscrite = FXCollections.observableArrayList();

	public PersonOverviewController() {

    }

    @FXML
    private void initialize() {
    	initializeCompetition();
    	initializeEquipe();
    	initializePersonne();
    }
    
    public void initializeCompetition() {
    	nomc.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        datecloture.setCellValueFactory(cellData -> new ReadOnlyStringWrapper((cellData.getValue().getDateCloture()).format(DateTimeFormatter.ISO_LOCAL_DATE)));
        nbrCandidat.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getCandidats().size())));
        enEquipe.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().estEnEquipe() ? "Oui" : "Non"));
    }

    public void initializeEquipe () {
    	nome.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        nbrMembres.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getMembres().size())));
        nbrCompetition.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getCompetitions().size())));
    }
    
    public void initializePersonne() {
    	prenom.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPrenom()));
        nomp.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        mail.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMail()));
        nbrEquipe.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getEquipes().size())));
        nbrCompetitionp.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getCompetitions().size())));
    }
    
    private void showCompetDetails(Competition compet) {
        if (compet != null) {
        	currentCompet = compet;
        	lesEquipeInscrite.clear();
        	lesEquipeAInscrire.clear();

        	for (Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
        		if (!compet.getCandidats().contains(candidat)) {
        			if (compet.estEnEquipe() && candidat instanceof Equipe) {
    					lesEquipeAInscrire.add(candidat);
    				}
            		else if (!compet.estEnEquipe() && candidat instanceof Personne) {
            			lesEquipeAInscrire.add(candidat);
    				}
				}

			}

        	for (Candidat candidat : compet.getCandidats()) {
				lesEquipeInscrite.add(candidat);
			}

        	equipeAInscrire.setItems(lesEquipeAInscrire);
        	equipeInscrite.setItems(lesEquipeInscrite);
        	nomEAI.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        	nomEI.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));

        } else {
        	currentCompet = null;
        }
    }

    private void setCurrentCandidatCompetAInscrire(Candidat candidat) {
    	if (candidat != null) {
			inscrire.setDisable(false);
		} else {
			inscrire.setDisable(true);
		}
    }

    private void setCurrentCandidatCompetADesinscrire(Candidat candidat) {
    	if (candidat != null)
			desinscrire.setDisable(false);
		else
			desinscrire.setDisable(true);
    }

    private void dateSelected() {

    }

    @FXML
    private void handleInscrireCompetCandidat() {
    	Candidat candidat = equipeAInscrire.getItems().get(equipeAInscrire.getSelectionModel().getSelectedIndex());
    	if (currentCompet.estEnEquipe()) {
    		this.currentCompet.add((Equipe)(candidat));
		} else {
			this.currentCompet.add((Personne)(candidat));
		}
        equipeAInscrire.getItems().remove(candidat);
        equipeInscrite.getItems().add(candidat);
    }

    @FXML
    private void handleDesinscrireCompetCandidat() {
    	Candidat candidat = equipeInscrite.getItems().get(equipeInscrite.getSelectionModel().getSelectedIndex());
        currentCompet.remove(candidat);
    	equipeInscrite.getItems().remove(candidat);
        equipeAInscrire.getItems().add(candidat);
    }

    @FXML
    private void handleCreerCompetition() {
    	Competition compet = Inscriptions.getInscriptions().createCompetition(nomcompet.getText(), dtePicker.getValue(), enEquipeCB.isSelected());
    	mainApp.getCompetitions().add(compet);
    }

    @FXML
    private void handleSuppression() {
    	Competition uneCompet = competitions.getItems().get(competitions.getSelectionModel().getSelectedIndex());
    	mainApp.getCompetitions().remove(uneCompet);
    	uneCompet.delete();
    }

    @FXML
    private void handleChangementDate() {
    	currentCompet.setDateCloture(dtePickerSet.getValue());
    }

    @FXML
    private void handleDatePicked() {
    	if (dtePickerSet.getValue() != null) {
    		changerDate.setDisable(false);
    	}
    }
    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    	changerDate.setDisable(true);
    	competitions.setItems(mainApp.getCompetitions());
    	equipes.setItems(mainApp.getEquipes());
    	personnes.setItems(mainApp.getPersonnes());
    	showCompetDetails(null);
    	setCurrentCandidatCompetADesinscrire(null);
    	setCurrentCandidatCompetAInscrire(null);
    	competitions.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCompetDetails(newValue));
    	equipeAInscrire.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentCandidatCompetAInscrire(newValue));
    	equipeInscrite.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentCandidatCompetADesinscrire(newValue));
    }
}
