package iitdurollsix.components;


import java.sql.SQLException;

import org.apache.commons.validator.routines.EmailValidator;

import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
import iitdurollsix.rollsixInterfaces.DbConnectionInterface;
import iitdurollsix.rollsixInterfacesImpl.DbConnectionImpl;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class LoginForm {
	
	private AppController appController;
	private DbConnectionInterface db;
	private Footer footer;
	
	public LoginForm() {
		this.appController = new AppController();
		this.db = new DbConnectionImpl();
		this.footer = new Footer();
	}
	

	public BorderPane drawLogin() {
		
		BorderPane loginBorderPane = new BorderPane();
		loginBorderPane.setPadding(new Insets(10, 20, 10, 20));
		
		
		//below line is responsible for omitting the top bar (containing the cross, minimize and enlarge icon)
    	//loginStage.initStyle(StageStyle.TRANSPARENT);
        
    	
    	GridPane loginGrid = new GridPane();
		
		loginGrid.setHgap(10);
		loginGrid.setVgap(10);
		loginGrid.setPadding(new Insets(25,25,25,25));
		
		Label userName = new Label("User Name:");
		Label password = new Label("Password:");
		Label userNameMsg = new Label("Please Enter User Name!");
		Label passwordMsg = new Label("Plese Enter Password!");
		
		TextField txtUserName = new TextField();
		PasswordField txtPassword = new PasswordField();
		
		
		Button resetBtn = new Button("Reset"); 

		Button loginBtn = new Button("Login"); 
		
		Button clsbtn = new Button("Close Window!");
		
		Button register = new Button("New Client Registration!");
		
		Button forgotPassword = new Button("Forgot Password?");
		
		HBox loginButtons = new HBox(resetBtn, loginBtn);
		loginButtons.setPadding(new Insets(15,12,15,12));
		loginButtons.setSpacing(10);
		
		HBox otherButtons = new HBox(clsbtn, register, forgotPassword);
		otherButtons.setPadding(new Insets(15,12,15,12));
		otherButtons.setSpacing(10);
		
		
		forgotPassword.setOnAction(e->{
			try {
				appController.switchToForgotPassword(e);
			} catch (Exception e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			}
		});
		
		clsbtn.setOnAction(e->{
			Stage stage = (Stage) clsbtn.getScene().getWindow();
			 	stage.close();
		});
		
		register.setOnAction(e->{
			try {
				appController.switchToRegistration(e);
			} catch (RollSixCustomException e1) {
				System.out.println(e1.getLocalizedMessage());
				e1.printStackTrace();
				
			}
		});
		
		
		resetBtn.setOnAction(e->{
			txtUserName.setText("");
			txtPassword.setText("");
		});
		
		loginBtn.setOnAction(e->{
			
			if(txtUserName.getText().equals("")) {
					userNameMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
					userNameMsg.setText("Username can not be empty!");
			}
			else if(txtUserName.getText().length()>100) {
				userNameMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				userNameMsg.setText("Maximum 100 characters are allowed!");
			}
			else if(!EmailValidator.getInstance().isValid(txtUserName.getText())) {
				userNameMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				userNameMsg.setText("Please enter a valid email address!");
			}
			else if(txtPassword.getText().equals("")) {
				passwordMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				passwordMsg.setText("Password can not be empty!");
			}
			else if(txtPassword.getText().length()>100) {
				passwordMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				passwordMsg.setText("Maximum 100 characters are allowed!");
			}
			else {
				try {
					String userNameFromDb = loginCheck(txtUserName.getText(),txtPassword.getText(),loginBorderPane);
					if(!userName.equals("")) {
						appController.switchToDashboard(e, userNameFromDb);
					}
					else {

						Alert alert = new Alert(AlertType.ERROR, "Please enter valid Username and Password!", ButtonType.OK);
						alert.show();
					}
				}catch (Exception e1) {

					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
				}

			}
			});	
		
		txtPassword.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                loginBtn.fire();
            }
        });
		loginGrid.add(otherButtons, 0, 4, 3, 1);
		loginGrid.add(userName, 0, 5); 
		loginGrid.add(txtUserName, 1, 5);
		loginGrid.add(userNameMsg, 2, 5);
		loginGrid.add(password, 0, 6);       
		loginGrid.add(txtPassword, 1, 6); 
		loginGrid.add(passwordMsg, 2, 6);
		loginGrid.add(loginButtons, 0, 7, 3, 1);
		
		loginBorderPane.setCenter(loginGrid);
		loginBorderPane.setBottom(footer.drawFooter());
		return loginBorderPane;
		
	}



	private String loginCheck(String userName, String password, BorderPane root) throws RollSixCustomException, SQLException {
		return db.validateUserNamePassword(userName, password);
	}

	
}
