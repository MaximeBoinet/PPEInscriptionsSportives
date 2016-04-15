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
import javafx.stage.Modality;
import javafx.stage.Stage;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Personne;
import interfUtil.view.IdentificationControler;
import interfUtil.view.MailSenderController;
import interfUtil.view.PersonOverviewController;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private boolean identifie = false;
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
        System.out.println(1);
        initRootLayout();
        System.out.println(2);
        showPersonOverview();
        System.out.println(3);
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
			showIdentification();
			if (!isLoged()) {
	        	this.getPrimaryStage().close();
			}
			PersonOverviewController controller = loader.getController();
			System.out.println(controller);
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void showIdentification() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Identification.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stageid dialogStage = new Stageid(this);
            dialogStage.setTitle("S'identifier");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
			IdentificationControler controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			dialogStage.showAndWait();
		} catch (IOException e) {
			// TODO: handle exception
		}
    }

    public boolean showPersonEditDialog(Object unObj) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MailSender.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Envoyer un Mail");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            MailSenderController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setObj(unObj);

            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Stage getPrimaryStage() {
    	return this.primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public boolean getId() {
    	return this.identifie;
    }
    
    public void setId(Boolean bool) {
    	this.identifie = bool;
    }
    
    public boolean isLoged() {
    	return getId();
    }
}
