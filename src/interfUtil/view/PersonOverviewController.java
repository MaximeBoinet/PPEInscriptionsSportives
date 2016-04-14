package interfUtil.view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

import java.time.LocalDate;
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
    private Button creerCompet = new Button();
    @FXML
    private Button changerDate = new Button("Changer");
    @FXML
    private DatePicker dtePickerSet = new DatePicker();
    @FXML
    private Button supprimCompet = new Button();
    @FXML
    private Button mailCompet = new Button();

    //Champs pour les equipes
    @FXML
    private TableView<Competition> competitionDisponible;
    @FXML
    private TableColumn<Competition, String> nomCD;
    @FXML
    private TableView<Competition> competitionParticipe;
    @FXML
    private TableColumn<Competition, String> nomCP;
    @FXML
    private TableView<Candidat> personneDisponible;
    @FXML
    private TableColumn<Candidat, String> nomPD;
    @FXML
    private TableView<Candidat> personneMembre;
    @FXML
    private TableColumn<Candidat, String> nomPM;
    @FXML
    private Button inscrireCompet = new Button();
    @FXML
    private Button desinscrireCompet = new Button();
    @FXML
    private Button inscrirePersonne = new Button();
    @FXML
    private Button desinscrirePersonne = new Button();
    @FXML
    private Button appliquerNomB = new Button();
    @FXML
    private TextField nomEquipe = new TextField();
    @FXML
    private TextField nomEquipeNew = new TextField();
    @FXML
    private Button creerEquipe = new Button();
    @FXML
    private Button supprimerEquipe = new Button();
    @FXML
    private Button mailEquipe = new Button();

    //Champs pour personnes
    @FXML
    private TableView<Competition> competDispoPersonne;
    @FXML
    private TableColumn<Competition, String> nomCDP;
    @FXML
    private TableView<Competition> competParticipePersonne;
    @FXML
    private TableColumn<Competition, String> nomCPP;
    @FXML
    private TableView<Candidat> equipeDispoPersonne;
    @FXML
    private TableColumn<Candidat, String> nomEDP;
    @FXML
    private TableView<Candidat> equipeInscritePersonne;
    @FXML
    private TableColumn<Candidat, String> nomEIP;
    @FXML
    private Button inscrireCompetP = new Button();
    @FXML
    private Button desinscrireCompetP = new Button();
    @FXML
    private Button inscrireEquipeP = new Button();
    @FXML
    private Button desinscrireEquipeP = new Button();
    @FXML
    private Button creerPersonne = new Button();
    @FXML
    private Button supprimerPersonne = new Button();
    @FXML
    private Button appliquerNom = new Button();
    @FXML
    private Button appliquerPrenom = new Button();
    @FXML
    private Button appliquerMail = new Button();
    @FXML
    private TextField nomUpdate = new TextField();
    @FXML
    private TextField prenomUpdate = new TextField();
    @FXML
    private TextField mailUpdate = new TextField();
    @FXML
    private TextField nomNew = new TextField();
    @FXML
    private TextField prenomNew = new TextField();
    @FXML
    private TextField mailNew = new TextField();
    @FXML
    private Button mailPersonne = new Button();

    private MainApp mainApp;

    Competition currentCompet = null;
	ObservableList<Candidat> lesEquipeAInscrire = FXCollections.observableArrayList();
	ObservableList<Candidat> lesEquipeInscrite = FXCollections.observableArrayList();
    Equipe currentEquipe = null;
    ObservableList<Competition> competDispos = FXCollections.observableArrayList();
	ObservableList<Competition> competParticipes = FXCollections.observableArrayList();
	ObservableList<Candidat> personneDispos = FXCollections.observableArrayList();
	ObservableList<Candidat> personneMembres = FXCollections.observableArrayList();
	Personne currentPersonne = null;
	ObservableList<Competition> competDisposPersonne = FXCollections.observableArrayList();
	ObservableList<Competition> competParticipesPersonne = FXCollections.observableArrayList();
	ObservableList<Candidat> equipeDisposPersonne = FXCollections.observableArrayList();
	ObservableList<Candidat> equipeInscritesPersonne = FXCollections.observableArrayList();


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
        	supprimCompet.setDisable(false);
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
        	dtePickerSet.setValue(currentCompet.getDateCloture());
        	changerDate.setDisable(false);
        	mailCompet.setDisable(false);

        } else {
        	currentCompet = null;
        	dtePickerSet.setValue(null);
        	supprimCompet.setDisable(true);
        	changerDate.setDisable(true);
        	mailCompet.setDisable(true);
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
    	if (candidat != null) {
			desinscrire.setDisable(false);
    	} else {
			desinscrire.setDisable(true);
    	}
    }

    @FXML
    private void handleInscrireCompetCandidat() {
    	Candidat candi = equipeAInscrire.getItems().get(equipeAInscrire.getSelectionModel().getSelectedIndex());
    	if (currentCompet.estEnEquipe()) {
    		this.currentCompet.add((Equipe)(candi));
    		System.out.println("Equipe add");
		} else {
			this.currentCompet.add((Personne)(candi));
			System.out.println("Personne add");
		}
        equipeAInscrire.getItems().remove(candi);
        equipeInscrite.getItems().add(candi);
        competitions.refresh();
    }

    @FXML
    private void handleDesinscrireCompetCandidat() {
    	Candidat candi = equipeInscrite.getItems().get(equipeInscrite.getSelectionModel().getSelectedIndex());
        currentCompet.remove(candi);
    	equipeInscrite.getItems().remove(candi);
        equipeAInscrire.getItems().add(candi);
        competitions.refresh();
    }

    @FXML
    private void verifChampComplet() {
    	if (nomcompet.getText().length() != 0 && dtePicker.getValue() != null) {
    		creerCompet.setDisable(false);
    	} else {
    		creerCompet.setDisable(true);
    	}
    }

    @FXML
    private void handleCreerCompetition() {
    	if (dtePicker.getValue().isEqual(LocalDate.now()) || dtePicker.getValue().isBefore(LocalDate.now())) {
    		createAlerte("Date Incorrect", "Selectionnez une autre date", "La date doit être ultérieur à la date actuel");
		} else {
	    	Competition compet = Inscriptions.getInscriptions().createCompetition(nomcompet.getText(), dtePicker.getValue(), enEquipeCB.isSelected(), -1);
	    	mainApp.getCompetitions().add(compet);
		}
    }

    @FXML
    private void handleSuppression() {
    	Competition tempsCompet = currentCompet;
    	mainApp.getCompetitions().remove(currentCompet);
    	tempsCompet.delete();
    }

    @FXML
    private void handleChangementDate() {
    	if (currentCompet.getDateCloture().isBefore(dtePickerSet.getValue())) {
    		currentCompet.setDateCloture(dtePickerSet.getValue());
    		competitions.refresh();
		}
    	else {
    		createAlerte("Date Incorrect", "Selectionnez une autre date", "La date doit être ultérieur à celle actuellement défini pour la compétition");
    	}
    }

    @FXML
    private void handleDatePicked() {
    	if (dtePickerSet.getValue() != null && currentCompet != null) {
    		changerDate.setDisable(false);
    	}
    }
    
    @FXML
    private void handleMailCompet() {
    	mainApp.showPersonEditDialog(currentCompet);
    }

    private void showEquipeDetails(Equipe equipe) {
    	if (equipe != null) {
    		supprimerEquipe.setDisable(false);
        	currentEquipe = equipe;
        	competDispos.clear();
        	competParticipes.clear();
        	personneDispos.clear();
        	personneMembres.clear();

        	for (Competition compet : Inscriptions.getInscriptions().getCompetitions()) {
        		if (compet.estEnEquipe()) {
        			if (equipe.getCompetitions().contains(compet)) {
    					competParticipes.add(compet);
    				} else {
    					competDispos.add(compet);
    				}
				}
			}

        	for (Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
        		if (candidat instanceof Personne) {
					if (equipe.getMembres().contains(candidat)) {
						personneMembres.add(candidat);
					} else {
						personneDispos.add(candidat);
					}
        		}
			}

        	competitionDisponible.setItems(competDispos);
        	competitionParticipe.setItems(competParticipes);
        	personneDisponible.setItems(personneDispos);
        	personneMembre.setItems(personneMembres);

        	nomCD.setCellValueFactory(cellData ->new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        	nomCP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        	nomPD.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        	nomPM.setCellValueFactory(cellData ->new ReadOnlyStringWrapper(cellData.getValue().getNom()));

        	nomEquipeNew.setText(currentEquipe.getNom());
        	appliquerNomB.setDisable(false);
        	mailEquipe.setDisable(false);
        } else {
        	currentEquipe = null;
        	supprimerEquipe.setDisable(true);
        	appliquerNomB.setDisable(true);
        	mailEquipe.setDisable(true);
        }
    }

    private void setCurrentCompetDispo(Competition competition) {
    	if (competition != null) {
			inscrireCompet.setDisable(false);
		} else {
			inscrireCompet.setDisable(true);
		}
    }

    private void setCurrentCompetPerticipe(Competition competition) {
    	if (competition != null) {
			desinscrireCompet.setDisable(false);
		} else {
			desinscrireCompet.setDisable(true);
		}
    }

    private void setCurrentPersonneDispo(Candidat candidat) {
    	if (candidat != null) {
			inscrirePersonne.setDisable(false);
		} else {
			inscrirePersonne.setDisable(true);
		}
    }

    private void setCurrentPersonneMembre(Candidat candidat) {
    	if (candidat != null) {
			desinscrirePersonne.setDisable(false);
		} else {
			desinscrirePersonne.setDisable(true);
		}
    }

    @FXML
    private void handleInscrireCompetEquipe() {
    	Competition compet = competitionDisponible.getItems().get(competitionDisponible.getSelectionModel().getSelectedIndex());
    	compet.add(currentEquipe);
    	competitionDisponible.getItems().remove(compet);
    	competitionParticipe.getItems().add(compet);
    	equipes.refresh();
    }

    @FXML
    private void handleDesinscrireCompetEquipe() {
    	Competition compet = competitionParticipe.getItems().get(competitionParticipe.getSelectionModel().getSelectedIndex());
    	compet.remove(currentEquipe);
    	competitionDisponible.getItems().add(compet);
    	competitionParticipe.getItems().remove(compet);
    	equipes.refresh();
    }

    @FXML
    private void handleInscrireMembreEquipe() {
    	Candidat candi = personneDisponible.getItems().get(personneDisponible.getSelectionModel().getSelectedIndex());
    	currentEquipe.add((Personne)candi);
    	personneDisponible.getItems().remove(candi);
    	personneMembre.getItems().add(candi);
    	equipes.refresh();
    }

    @FXML
    private void handleDesinscrireMembreEquipe() {
    	Candidat candi = personneMembre.getItems().get(personneMembre.getSelectionModel().getSelectedIndex());
    	currentEquipe.remove((Personne)candi);
    	personneDisponible.getItems().add(candi);
    	personneMembre.getItems().remove(candi);
    	equipes.refresh();
    }

    @FXML
    private void handleCreateEquipe() {
    	Equipe equipe = Inscriptions.getInscriptions().createEquipe(nomEquipe.getText(), -1);
    	mainApp.getEquipes().add(equipe);
    }

    @FXML
    private void toggleChangerEquipe() {
    	if (nomEquipeNew.getText().length() != 0 && currentEquipe != null) {
			appliquerNomB.setDisable(false);
		} else {
			appliquerNomB.setDisable(true);
		}
    }

    @FXML
    private void toggleCreerEquipe() {
    	if (nomEquipe.getText().length() == 0) {
			creerEquipe.setDisable(true);
		} else {
			creerEquipe.setDisable(false);
		}
    }

    @FXML
    private void handleChangerNomEquipe() {
    	currentEquipe.setNom(nomEquipeNew.getText());
    	equipes.refresh();
    }

    @FXML
    private void handleSupprimerEquipe() {
    	Equipe tempsEquipe = currentEquipe;
    	mainApp.getEquipes().remove(currentEquipe);
    	tempsEquipe.delete();
    }

    @FXML
    private void handleMailEquipe() {
    	mainApp.showPersonEditDialog(currentEquipe);
    }
    
    private void showPersonneDetails(Personne personne) {
    	if (personne != null) {
    		supprimerPersonne.setDisable(false);
        	currentPersonne = personne;
        	competDisposPersonne.clear();
        	competParticipesPersonne.clear();
        	equipeDisposPersonne.clear();
        	equipeInscritesPersonne.clear();

        	for (Competition compet : Inscriptions.getInscriptions().getCompetitions()) {
        		if (!compet.estEnEquipe()) {
        			if (personne.getCompetitions().contains(compet)) {
            			competParticipesPersonne.add(compet);
    				} else {
    					competDisposPersonne.add(compet);
    				}
				}

			}

        	for (Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
				if (personne.getEquipes().contains(candidat)) {
					equipeInscritesPersonne.add(candidat);
				} else if (candidat instanceof Equipe) {
					equipeDisposPersonne.add(candidat);
				}
			}

        	competDispoPersonne.setItems(competDisposPersonne);
        	competParticipePersonne.setItems(competParticipesPersonne);
        	equipeDispoPersonne.setItems(equipeDisposPersonne);
        	equipeInscritePersonne.setItems(equipeInscritesPersonne);

        	nomCDP.setCellValueFactory(cellData ->new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        	nomCPP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        	nomEDP.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        	nomEIP.setCellValueFactory(cellData ->new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        	nomUpdate.setText(currentPersonne.getNom());
        	prenomUpdate.setText(currentPersonne.getPrenom());
        	mailUpdate.setText(currentPersonne.getMail());
        	appliquerNom.setDisable(false);
        	appliquerPrenom.setDisable(false);
        	appliquerMail.setDisable(false);
        	mailPersonne.setDisable(false);

        } else {
        	currentEquipe = null;
        	nomUpdate.setText(null);
        	prenomUpdate.setText(null);
        	mailUpdate.setText(null);
        	appliquerNom.setDisable(true);
        	appliquerPrenom.setDisable(true);
        	appliquerMail.setDisable(true);
        	supprimerPersonne.setDisable(true);
        	mailPersonne.setDisable(true);
        }
    }

    private void setCurrentCompetDispoPersonne(Competition compet) {
    	if (compet != null) {
			inscrireCompetP.setDisable(false);
		} else {
			inscrireCompetP.setDisable(true);
		}
    }

    private void setCurrentCompetParticipePersonne(Competition compet) {
    	if (compet != null) {
			desinscrireCompetP.setDisable(false);
		} else {
			desinscrireCompetP.setDisable(true);
		}
    }

    private void setCurrentEquipeDispoPersonne(Candidat candidat) {
    	if (candidat != null) {
			inscrireEquipeP.setDisable(false);
		} else {
			inscrireEquipeP.setDisable(true);
		}
    }

    private void setCurrentEquipeParticipePersonne(Candidat candidat) {
    	if (candidat != null) {
			desinscrireEquipeP.setDisable(false);
		} else {
			desinscrireEquipeP.setDisable(true);
		}
    }

    @FXML
    private void toggleCreerPersonne() {
    	if (prenomNew.getText().length() != 0 && nomNew.getText().length() != 0 && mailNew.getText().length() != 0) {
			creerPersonne.setDisable(false);
		} else {
			creerPersonne.setDisable(true);
		}
    }

    @FXML
    private void handleCreerPersonne() {
    	Personne pers = Inscriptions.getInscriptions().createPersonne(nomNew.getText(), prenomNew.getText(), mailNew.getText(), -1);
    	mainApp.getPersonnes().add(pers);
    }

    @FXML
    private void toggleNewNom() {
    	if (nomUpdate.getText().length() != 0 && currentPersonne != null) {
			appliquerNom.setDisable(false);
		} else {
			appliquerNom.setDisable(true);
		}
    }

    @FXML
    private void toggleNewMail() {
    	if (mailUpdate.getText().length() != 0 && currentPersonne != null) {
			appliquerMail.setDisable(false);
		} else {
			appliquerMail.setDisable(true);
		}
    }

    @FXML
    private void toggleNewPrenom() {
    	if (prenomUpdate.getText().length() != 0 && currentPersonne != null) {
			appliquerPrenom.setDisable(false);
		} else {
			appliquerPrenom.setDisable(true);
		}
    }

    @FXML
    private void handleChangerNomP() {
    	currentPersonne.setNom(nomUpdate.getText());
    	personnes.refresh();
    }

    @FXML
    private void handleChangerPrenomP() {
    	currentPersonne.setPrenom(prenomUpdate.getText());
    	personnes.refresh();
    }

    @FXML
    private void handleChangerMailP() {
    	currentPersonne.setMail(mailUpdate.getText());
    	personnes.refresh();
    }

    @FXML
    private void handleInscrireEquipeP() {
    	Candidat candi = equipeDispoPersonne.getItems().get(equipeDispoPersonne.getSelectionModel().getSelectedIndex());
    	((Equipe)candi).add(currentPersonne);
    	equipeDispoPersonne.getItems().remove(candi);
        equipeInscritePersonne.getItems().add(candi);
        personnes.refresh();
    }

    @FXML
    private void handleDesinscrireEquipeP() {
    	Candidat candi = equipeInscritePersonne.getItems().get(equipeInscritePersonne.getSelectionModel().getSelectedIndex());
    	((Equipe)candi).remove(currentPersonne);
    	equipeDispoPersonne.getItems().add(candi);
        equipeInscritePersonne.getItems().remove(candi);
        personnes.refresh();
    }

    @FXML
    private void handleInscrireCompetP() {
    	Competition compet = competDispoPersonne.getItems().get(competDispoPersonne.getSelectionModel().getSelectedIndex());
    	compet.add(currentPersonne);
    	competDispoPersonne.getItems().remove(compet);
        competParticipePersonne.getItems().add(compet);
        personnes.refresh();
    }

    @FXML
    private void handleDesinscrireCompetP() {
    	Competition compet = competParticipePersonne.getItems().get(competParticipePersonne.getSelectionModel().getSelectedIndex());
    	compet.remove(currentPersonne);
    	competDispoPersonne.getItems().add(compet);
    	competParticipePersonne.getItems().remove(compet);
    	personnes.refresh();
    }

    @FXML
    private void handleSupprimerPersonne() {
    	Personne tempsPersonne = currentPersonne;
    	mainApp.getPersonnes().remove(currentPersonne);
    	tempsPersonne.delete();
    }
    
    @FXML
    private void handleMailPersonne() {
    	mainApp.showPersonEditDialog(currentPersonne);
    }

    public void setMainApp(MainApp mainApp) {
    	System.out.println(4);
    	this.mainApp = mainApp;

    	supprimCompet.setDisable(true);
    	supprimerEquipe.setDisable(true);
    	supprimerPersonne.setDisable(true);
    	changerDate.setDisable(true);
    	creerCompet.setDisable(true);
    	creerEquipe.setDisable(true);
    	appliquerMail.setDisable(true);
    	appliquerNom.setDisable(true);
    	appliquerPrenom.setDisable(true);
    	creerPersonne.setDisable(true);
    	appliquerNomB.setDisable(true);
    	System.out.println(5);
    	competitions.setItems(mainApp.getCompetitions());
    	equipes.setItems(mainApp.getEquipes());
    	personnes.setItems(mainApp.getPersonnes());
    	System.out.println(6);
    	showCompetDetails(null);
    	setCurrentCandidatCompetADesinscrire(null);
    	setCurrentCandidatCompetAInscrire(null);
    	showEquipeDetails(null);
    	setCurrentCompetDispo(null);
    	setCurrentCompetPerticipe(null);
    	setCurrentPersonneDispo(null);
    	setCurrentPersonneMembre(null);
    	showPersonneDetails(null);
    	setCurrentCompetDispoPersonne(null);
    	setCurrentCompetParticipePersonne(null);
    	setCurrentEquipeDispoPersonne(null);
    	setCurrentEquipeParticipePersonne(null);
    	System.out.println(7);
    	competitions.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCompetDetails(newValue));
    	equipeAInscrire.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentCandidatCompetAInscrire(newValue));
    	equipeInscrite.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentCandidatCompetADesinscrire(newValue));
    	equipes.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showEquipeDetails(newValue));
    	competitionDisponible.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentCompetDispo(newValue));
    	competitionParticipe.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentCompetPerticipe(newValue));
    	personneDisponible.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentPersonneDispo(newValue));
    	personneMembre.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentPersonneMembre(newValue));
    	personnes.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showPersonneDetails(newValue));
    	competDispoPersonne.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentCompetDispoPersonne(newValue));
    	competParticipePersonne.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentCompetParticipePersonne(newValue));
    	equipeDispoPersonne.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentEquipeDispoPersonne(newValue));
    	equipeInscritePersonne.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> setCurrentEquipeParticipePersonne(newValue));

    }

    private void createAlerte(String title, String header, String content) {
    	Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
