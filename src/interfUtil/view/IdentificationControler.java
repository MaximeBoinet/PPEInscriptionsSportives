package interfUtil.view;

import bd.Connect;
import inscriptions.Inscriptions;
import interfUtil.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IdentificationControler {

	@FXML
	private TextField pseudo = new TextField();
	@FXML
	private PasswordField mdp = new PasswordField();
	@FXML
	private Button connect = new Button();
	@FXML
	private Label lbl = new Label();
	
	private Stage stage;
	private MainApp mainApp;
	
	public IdentificationControler() {
		
	}
	
	@FXML
	private void initialize() {
		lbl.setText("");
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.stage = dialogStage;
    }
	
	@FXML
	public void handleIdent() {
		if (Inscriptions.getConnection().isUser(pseudo.getText(), mdp.getText())) {
			mainApp.setId(true);
			stage.close();
		} else {
			lbl.setText("Identifiant incorrect");
		}
	}
	
}
