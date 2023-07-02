package iitdurollsix.controller;
import iitdurollsix.components.ChangePassword;
import iitdurollsix.components.Dashboard;
import iitdurollsix.components.LoginForm;
import iitdurollsix.components.RegistrationForm;
import iitdurollsix.components.ViewProfile;
import iitdurollsix.components.EditProfile;
import iitdurollsix.components.ForgotPassword;
import iitdurollsix.constant.StaticVariables;
import iitdurollsix.exception.RollSixCustomException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;



public class AppController {
	
	private Stage stage;
	private Scene scene;
	private BorderPane root;
	
	
	
	public void switchToLogin(ActionEvent e) {
		LoginForm loginForm = new LoginForm();
		root = loginForm.drawLogin();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		
		//below line is responsible for omitting the top bar (containing the cross, minimize and enlarge icon)
    	//stage.initStyle(StageStyle.TRANSPARENT);
		
		
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Login Form");
		stage.show();
	}
	
	public void switchToRegistration(ActionEvent e) throws RollSixCustomException {
		RegistrationForm registrationForm = new RegistrationForm();
		root = registrationForm.drawRegistrationForm();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Registration Form");
		stage.show();
	}
	
	public void switchToDashboard(ActionEvent e, String userName) throws RollSixCustomException {
		Dashboard dashboard = new Dashboard(userName);
		root = dashboard.drawDashboard();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Dashboard");
		stage.show();
	}
	public void editProfile(ActionEvent e, String userName) throws Exception {
		EditProfile editProfile = new EditProfile(userName);
		root = editProfile.drawEditProfile();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Profile");
		stage.show();
	}
	private void prepareScene(BorderPane root) {
		scene = new Scene(root, StaticVariables.WINDOW_WIDTH, StaticVariables.WINDOW_HEIGHT);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("assets/css/rollsix.css").toExternalForm());
    	
	}

	public void closeWindow(ActionEvent e) {
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		stage.close();
		
	}

	public void viewProfile(ActionEvent e, String userName) throws Exception {
		ViewProfile viewProfile = new ViewProfile(userName);
		root = viewProfile.drawViewProfile();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("View Profile");
		stage.show();
		
	}

	public void changePassword(ActionEvent e, String userName) throws Exception {
		ChangePassword changePassword = new ChangePassword(userName);
		root = changePassword.drawChangePassword();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Change Password");
		stage.show();
		
	}

	public void switchToForgotPassword(ActionEvent e) throws Exception {
		ForgotPassword forgotPassword = new ForgotPassword();
		root = forgotPassword.drawForgotPassword();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Forgot Password");
		stage.show();
	}
}
