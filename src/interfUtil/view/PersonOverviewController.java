package interfUtil.view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import interfUtil.model.Equipe;
import interfUtil.model.Personne;
import interfUtil.MainApp;
import interfUtil.model.Competition;

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


    private MainApp mainApp;

    public PersonOverviewController() {

    }

    @FXML
    private void initialize() {
        nomc.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        datecloture.setCellValueFactory(cellData -> cellData.getValue().dateClotureProperty().asString());
        nbrCandidat.setCellValueFactory(cellData -> cellData.getValue().nbrCandidatProperty().asString());
        enEquipe.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().isEnEquipe() ? "Oui" : "Non"));
        nome.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        nbrMembres.setCellValueFactory(cellData -> cellData.getValue().nbrMembresProperty().asString());
        nbrCompetition.setCellValueFactory(cellData -> cellData.getValue().nbrCompetitionProperty().asString());
        prenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        nomp.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        mail.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
        nbrEquipe.setCellValueFactory(cellData -> cellData.getValue().nbrEquipesProperty().asString());
        nbrCompetitionp.setCellValueFactory(cellData -> cellData.getValue().nbrCompetitionProperty().asString());
    }

    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;

    	competitions.setItems(mainApp.getCompetitions());
    	equipes.setItems(mainApp.getEquipes());
    	personnes.setItems(mainApp.getPersonnes());
    }
}
