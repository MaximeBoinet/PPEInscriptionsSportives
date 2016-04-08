package interfUtil.view;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mail.mailmessage;

public class MailSenderController {

	@FXML
	private TextArea textArea = new TextArea();
	@FXML
	private Label label = new Label();
	@FXML
	private Button envoyer = new Button();
	@FXML
	private TextField object = new TextField();
	
	private Stage dialogStage = null;
	private Object monObj = null;
	private Set<String> mesMails = new TreeSet<>();
	
	@FXML
	private void initialize() {
		
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	public void setObj(Object obj) {
		this.monObj = obj;
		int nbrCible = 0;
		System.out.println(monObj instanceof Competition);
		if (monObj instanceof Competition) {
			Set<Candidat> mesCand = ((Competition)monObj).getCandidats();
			
			if (((Competition)monObj).estEnEquipe()) {
				for (Candidat candidat : mesCand) {
					for (Personne perss : ((Equipe)candidat).getMembres()) {
						mesMails.add(perss.getMail());
						nbrCible++;
					}
				}
			} else {
				for (Candidat candidat : mesCand) {
					mesMails.add(((Personne)candidat).getMail());
					nbrCible++;
				}
			}
			label.setText("Envoyer un mail aux " + nbrCible + " participant de la compétition " + ((Competition)obj).getNom());
		} else {
			if (monObj instanceof Personne) {
				mesMails.add(((Personne)monObj).getMail());
				label.setText("Envoyer un mail à " + ((Personne)monObj).getNom() + " " + ((Personne)monObj).getPrenom());
			} else if (monObj instanceof Equipe) {
				Set<Personne> mesCands = ((Equipe)monObj).getMembres();
				for (Personne candidat : mesCands) {
					mesMails.add(candidat.getMail());
					nbrCible++;
				}
				label.setText("Envoyer un mail aux " + nbrCible + " membres de l'equipe " + ((Equipe)obj).getNom());
			}
		}
	}
	
	@FXML
	private void handleEnvoyer() {
		for (String mail : mesMails) {
			new mailmessage(mail, object.getText(), textArea.getText()).envoyer();
		}
		dialogStage.close();
	}
}
