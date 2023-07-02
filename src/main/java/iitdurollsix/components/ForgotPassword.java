package iitdurollsix.components;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.commons.validator.routines.EmailValidator;

import entity.User;
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

public class ForgotPassword {

	private Footer footer;
	private AppController appController;
	private DbConnectionInterface db;
	
	
	public ForgotPassword() {
		this.footer = new Footer();
		this.db = new DbConnectionImpl();
		this.appController = new AppController();
	}

	public BorderPane drawForgotPassword() throws Exception {
		
		
		
		BorderPane registrationBorderPane = new BorderPane();
		registrationBorderPane.setPadding(new Insets(10, 20, 10, 20));
		
		
		GridPane registrationGrid = new GridPane();
		
		registrationGrid.setHgap(10);
		registrationGrid.setVgap(10);
		registrationGrid.setPadding(new Insets(25,25,25,25));
		
		Label rUserName = new Label("Email:");
		Label msgUserName = new Label("");
		TextField rTxtUserName = new TextField();
		
		
		Label newPassLabel = new Label("New Password:");
		Label reTypeLabel = new Label("Re-Type Password:");
		

		Label msgNewPass = new Label("");
		Label msgReType = new Label("");
		

		PasswordField txtNewPass = new PasswordField();
		PasswordField txtReType = new PasswordField();
		
		Button changePassword = new Button("Change Password!");
		Button dashBack = new Button("Go Back!");
		
		
		HBox editButtons = new HBox(changePassword, dashBack);
		editButtons.setPadding(new Insets(15,12,15,12));
		editButtons.setSpacing(10);
		
		dashBack.setOnAction(e->{
			try {
				appController.switchToLogin(e);
			} catch (Exception e1) {
				Alert errorAlert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				errorAlert.show();
				e1.printStackTrace();
			}
		});
		
		changePassword.setOnAction(e->{
			if(rTxtUserName.getText().equals("")) {
				msgUserName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgUserName.setText("Email address can not be empty!");
			}
			else if(rTxtUserName.getText().length()>100) {
				msgUserName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgUserName.setText("Maximum 100 characters are allowed!");
			}
			else if(!EmailValidator.getInstance().isValid(rTxtUserName.getText())) {
				msgUserName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgUserName.setText("Please enter a valid email address!");
			}
			else if(txtNewPass.getText().equals("")) {
				msgNewPass.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgNewPass.setText("New Password can not be empty!");
			}
			else if(txtNewPass.getText().length()>100) {
				msgNewPass.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgNewPass.setText("Maximum 100 characters are allowed!");
			}
			else if(txtReType.getText().equals("")) {
				msgReType.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgReType.setText("Re-Type Password can not be empty!");
			}
			else if(txtReType.getText().length()>100) {
				msgReType.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgReType.setText("Maximum 100 characters are allowed!");
			}
			else if(!txtReType.getText().equals(txtNewPass.getText())) {
				msgReType.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgReType.setText("Re-Type password does not match!");
			}
			else {
				try {
					if(db.forgotPassword(rTxtUserName.getText(), txtNewPass.getText())) {
						Alert alert = new Alert(AlertType.INFORMATION, "Password Changed Successfully!", ButtonType.OK);
						alert.show();
						try {
							appController.switchToLogin(e);
						} catch (Exception e1) {
							Alert errorAlert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
							errorAlert.show();
							e1.printStackTrace();
						}
					}
				} catch (RollSixCustomException | SQLException e1) {
					Alert errorAlert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					errorAlert.show();
					e1.printStackTrace();
				}
			}
		});
		

		
		registrationGrid.add(rUserName, 0, 7); 
		registrationGrid.add(rTxtUserName, 1, 7);
		registrationGrid.add(msgUserName, 2, 7);
		registrationGrid.add(newPassLabel, 0, 8);       
		registrationGrid.add(txtNewPass, 1, 8);
		registrationGrid.add(msgNewPass, 2, 8);
		registrationGrid.add(reTypeLabel, 0, 9); 
		registrationGrid.add(txtReType, 1, 9);
		registrationGrid.add(msgReType, 2, 9);
		registrationGrid.add(editButtons, 0, 10, 3, 1);
		
		
		registrationBorderPane.setCenter(registrationGrid);
		registrationBorderPane.setBottom(footer.drawFooter());
		
		return registrationBorderPane;
	}
}
