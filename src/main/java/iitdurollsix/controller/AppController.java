package iitdurollsix.controller;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import iitdurollsix.components.AddProduct;
import iitdurollsix.components.ChangePassword;
import iitdurollsix.components.CreateAdmin;
import iitdurollsix.components.Dashboard;
import iitdurollsix.components.EditOtherAdmin;
import iitdurollsix.components.LoginForm;
import iitdurollsix.components.RegistrationForm;
import iitdurollsix.components.ViewAdmins;
import iitdurollsix.components.ViewAllProducts;
import iitdurollsix.components.ViewClients;
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
	
	public void switchToDashboard(ActionEvent e, String userName) throws RollSixCustomException, SQLException {
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

	public void swithToCreateNewAdmin(ActionEvent e, String userName) throws RollSixCustomException, SQLException {
		CreateAdmin createAdmin = new CreateAdmin(userName);
		root = createAdmin.drawAdminRegistrationForm();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Admin Registration");
		stage.show();
		
	}

	public void swithToViewAdmin(ActionEvent e, String userName) throws RollSixCustomException, SQLException {
		ViewAdmins viewAdmin = new ViewAdmins(userName);
		root = viewAdmin.drawAdminList();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Admin List");
		stage.show();
		
	}
	public void swithToViewAdmin(javafx.scene.input.MouseEvent e, String userName) throws RollSixCustomException, SQLException {
		ViewAdmins viewAdmin = new ViewAdmins(userName);
		root = viewAdmin.drawAdminList();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Admin List");
		stage.show();
		
	}

	public void editOtherAdmin(ActionEvent e, String currentUser, String userToBeEdited) throws Exception {
		EditOtherAdmin editOtherAdmin = new EditOtherAdmin(currentUser, userToBeEdited);
		root = editOtherAdmin.drawEditProfile();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Edit Other Admin");
		stage.show();
		
	}

	public void addProduct(ActionEvent e, String userName) throws RollSixCustomException, SQLException {
		AddProduct addProduct = new AddProduct(userName);
		root = addProduct.drawProductForm();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Add Product");
		stage.show();
		
	}

	public void showProductList(ActionEvent e, String currentUser) throws RollSixCustomException, SQLException {
		ViewAllProducts viewAllProducts = new ViewAllProducts(currentUser);
		root = viewAllProducts.drawProductList();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Product List");
		stage.show();	
	}

	public void showClientList(ActionEvent e, String currentUser) throws RollSixCustomException, SQLException {
		ViewClients viewClients = new ViewClients(currentUser);
		root = viewClients.drawClientList();
		try {
			stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		}catch (Exception e1) {
			stage = (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();
	    }
		this.prepareScene(root);
		stage.setScene(scene);
		stage.setTitle("Client List");
		stage.show();
		
	}
}
