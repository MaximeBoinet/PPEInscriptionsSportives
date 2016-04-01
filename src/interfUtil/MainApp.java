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
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Personne;
import interfUtil.view.PersonOverviewController;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Competition> competitions = FXCollections.observableArrayList(Inscriptions.getInscriptions().getCompetitions());
    private ObservableList<Equipe> equipes = FXCollections.observableArrayList();
    private ObservableList<Personne> personnes = FXCollections.observableArrayList();

    public MainApp() {
        for (Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
			if (candidat instanceof Equipe)
				equipes.add(((Equipe)candidat));
			else
				personnes.add(((Personne)candidat));
		}
        Inscriptions.setMainApp(this);
    }

    public void resetList() {
    	competitions.clear();
        equipes.clear();
        personnes.clear();
        for (Competition competition : Inscriptions.getInscriptions().getCompetitions()) {
        	competitions.add(competition);
		}
        for (Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
			if (candidat instanceof Equipe)
				equipes.add(((Equipe)candidat));
			else
				personnes.add(((Personne)candidat));
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

    public Stage getPrimaryStage() {
    	return this.primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
