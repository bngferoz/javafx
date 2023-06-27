package iitdurollsix.rollsixInterfaces;

import java.io.IOException;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public interface RollSixInterface {
	void initializeUserInterface(Stage stage) throws IOException;
	void showDialog(String message);
	void showError(String message);
	void handle(KeyEvent event);
	void closeApp();
}
