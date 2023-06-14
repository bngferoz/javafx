package iitdurollsix.rollsixInterfacesImpl;

import java.io.IOException;

import iitdurollsix.constant.StaticVariables;
import iitdurollsix.controller.AppController;
import iitdurollsix.rollsixInterfaces.RollSixInterface;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class RollSixInterfaceImpl implements RollSixInterface{
	
	private final Stage primaryStage;
    private final BorderPane root;
    public static String STYLESHEET_CASPIAN = "roll6.css";
    private AppController appController = new AppController();

	public RollSixInterfaceImpl(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.root  = new BorderPane();
		root.setPadding(new Insets(10, 20, 10, 20));
	}
	@Override
    public void initializeUserInterface() throws IOException{
		appController.drawScene(root);
    	Scene scene = new Scene(root, StaticVariables.WINDOW_WIDTH, StaticVariables.WINDOW_HEIGHT);
    	scene.getStylesheets().add(RollSixInterfaceImpl.class.getResource("rollsix.css").toExternalForm());
    	primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
		primaryStage.setTitle("roll06");
    	primaryStage.show();
		
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
		primaryStage.close();
	}

}
