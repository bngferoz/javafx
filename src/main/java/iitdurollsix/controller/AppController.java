package iitdurollsix.controller;
import iitdurollsix.components.LoginForm;
import iitdurollsix.components.RegistrationForm;
import iitdurollsix.constant.StaticVariables;
import iitdurollsix.exception.RollSixCustomException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class AppController {
	
	private Stage stage;
	private Scene scene;
	private BorderPane root;
	
	
	private LoginForm loginForm;
	private RegistrationForm registrationForm;
	
	public AppController() {
		this.loginForm = new LoginForm();
		this.registrationForm = new RegistrationForm();
	}
	
	public void switchToLogin(ActionEvent e) {
		root = loginForm.drawLogin();
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Login Form");
		stage.show();
	}
	
	public void switchToRegistration(ActionEvent e) throws RollSixCustomException {
		root = registrationForm.drawRegistrationForm();
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Registration Form");
		stage.show();
	}
	
	private void prepareScene(BorderPane root) {
		scene = new Scene(root, StaticVariables.WINDOW_WIDTH, StaticVariables.WINDOW_HEIGHT);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("assets/css/rollsix.css").toExternalForm());
    	
	}
}
