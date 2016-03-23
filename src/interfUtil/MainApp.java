package interfUtil;

import java.io.IOException;

import inscriptions.Inscriptions;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import interfUtil.model.Competition;
import interfUtil.model.Equipe;
import interfUtil.model.Personne;
import interfUtil.view.PersonOverviewController;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Competition> competitions = FXCollections.observableArrayList();
    private ObservableList<Equipe> equipes = FXCollections.observableArrayList();
    private ObservableList<Personne> personnes = FXCollections.observableArrayList();

    public MainApp() {
        for (inscriptions.Competition competition : Inscriptions.getInscriptions().getCompetitions()) {
            competitions.add(new Competition(Inscriptions.getInscriptions(), competition.getNom(), competition.getCandidats()
                    ,competition.getDateCloture(), competition.estEnEquipe()));
        }

        for (inscriptions.Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
			if (candidat instanceof inscriptions.Equipe) {
				equipes.add(new Equipe(candidat.getNom(),candidat.getCompetitions(), ((inscriptions.Equipe)candidat).getMembres()));
			}else {
				personnes.add(new Personne(candidat.getCompetitions(), candidat.getNom(),
						((inscriptions.Personne)candidat).getPrenom(), ((inscriptions.Personne)candidat).getMail()
						, ((inscriptions.Personne)candidat).getEquipes()));
			}
		}
    }

    public ObservableList<Competition> getCompetitions() {
        return competitions;
    }

    public ObservableList<Equipe> getEquipes() {
    	return equipes;
    }

    public ObservableList<Personne> getPersonnes() {
    	return personnes;
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inscription M2L");

        initRootLayout();

        showPersonOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showPersonOverview() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverView = (AnchorPane) loader.load();
			rootLayout.setCenter(personOverView);
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			// TODO: handle exception
		}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
