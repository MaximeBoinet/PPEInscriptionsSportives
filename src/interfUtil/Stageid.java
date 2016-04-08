package interfUtil;

import javafx.stage.Stage;

public class Stageid extends Stage{

	public MainApp mainApp = null;
	
	public Stageid(MainApp mainApp) {
		super();
		this.mainApp = mainApp;
	}

	@Override
	public void close() {
		System.out.println("closed");
		if (!this.mainApp.isLoged()) {
			try {
				this.mainApp.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.close();
	}
}
