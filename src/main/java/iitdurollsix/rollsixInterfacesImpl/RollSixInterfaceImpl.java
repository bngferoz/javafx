package iitdurollsix.rollsixInterfacesImpl;

import java.io.IOException;

import iitdurollsix.components.LoginForm;
import iitdurollsix.constant.StaticVariables;
import iitdurollsix.rollsixInterfaces.RollSixInterface;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class RollSixInterfaceImpl implements RollSixInterface{
	
	private Stage stage;
	private Scene scene;
	private BorderPane root;
	
	private LoginForm loginForm;
	
	
	public RollSixInterfaceImpl(Stage stage) {
		this.loginForm = new LoginForm();
		this.stage = stage;
	}
	@Override
    public void initializeUserInterface() throws IOException{
		
		root = loginForm.drawLogin();
		scene = new Scene(root, StaticVariables.WINDOW_WIDTH, StaticVariables.WINDOW_HEIGHT);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("assets/css/rollsix.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
    @Override
    public void showDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();

        if (dialog.getResult() == ButtonType.OK) {
        	
        }
    }

    @Override
    public void showError(String message) {
        Alert dialog = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        dialog.showAndWait();
    }
    
    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getText().equals("0")
                    || event.getText().equals("1")
                    || event.getText().equals("2")
                    || event.getText().equals("3")
                    || event.getText().equals("4")
                    || event.getText().equals("5")
                    || event.getText().equals("6")
                    || event.getText().equals("7")
                    || event.getText().equals("8")
                    || event.getText().equals("9")
            ) {
                int value = Integer.parseInt(event.getText());
                //handleInput(value, event.getSource());
            } else {
                ((TextField)event.getSource()).setText("");
            }
        }

        event.consume();
    }
    
    @Override
    public void closeApp() {
		stage.close();
	}

}
