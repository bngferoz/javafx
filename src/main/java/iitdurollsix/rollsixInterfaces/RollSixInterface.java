package iitdurollsix.rollsixInterfaces;

import java.io.IOException;
import javafx.scene.input.KeyEvent;


public interface RollSixInterface {
	void initializeUserInterface() throws IOException;
	void showDialog(String message);
	void showError(String message);
	void handle(KeyEvent event);
	void closeApp();
}
