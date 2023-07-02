package iitdurollsix.components;

import java.sql.SQLException;
import java.time.LocalDateTime;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ChangePassword {
	private Footer footer;
	private AppController appController;
	private DbConnectionInterface db;
	private User user;
	private AfterLoginMenu afterLoginMenu;
	private String userName;
	
	
	public ChangePassword(String userName) {
		this.user = new User();
		this.footer = new Footer();
		this.db = new DbConnectionImpl();
		this.appController = new AppController();
		this.userName = userName;
		this.afterLoginMenu = new AfterLoginMenu(userName);
	}

	public BorderPane drawChangePassword() throws Exception {
		
		user = db.getUserByUsername(userName);
		if(user==null) {
			throw new RollSixCustomException("User not found in the database!", LocalDateTime.now());
		}
		
		BorderPane registrationBorderPane = new BorderPane();
		registrationBorderPane.setPadding(new Insets(10, 20, 10, 20));
		
		
		GridPane registrationGrid = new GridPane();
		
		registrationGrid.setHgap(10);
		registrationGrid.setVgap(10);
		registrationGrid.setPadding(new Insets(25,25,25,25));
		

		Label existingPassLabel = new Label("Existing Password:");
		Label newPassLabel = new Label("New Password:");
		Label reTypeLabel = new Label("Re-Type Password:");
		

		Label msgExistingPass = new Label("");
		Label msgNewPass = new Label("");
		Label msgReType = new Label("");
		

		PasswordField txtExistingPass = new PasswordField();
		PasswordField txtNewPass = new PasswordField();
		PasswordField txtReType = new PasswordField();
		
		Button changePassword = new Button("Change Password!");
		Button dashBack = new Button("Go Back to Dashboard!");
		
		
		HBox editButtons = new HBox(changePassword, dashBack);
		editButtons.setPadding(new Insets(15,12,15,12));
		editButtons.setSpacing(10);
		
		dashBack.setOnAction(e->{
			try {
				appController.switchToDashboard(e, userName);
			} catch (Exception e1) {
				Alert errorAlert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				errorAlert.show();
				e1.printStackTrace();
			}
		});
		
		changePassword.setOnAction(e->{
			if(txtExistingPass.getText().equals("")) {
				msgExistingPass.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgExistingPass.setText("Existing Password can not be empty!");
			}
			else if(txtExistingPass.getText().length()>100) {
				msgExistingPass.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgExistingPass.setText("Maximum 100 characters are allowed!");
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
					if(db.changePassword(userName, txtNewPass.getText(), txtExistingPass.getText())) {
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
		

		
		registrationGrid.add(existingPassLabel, 0, 7); 
		registrationGrid.add(txtExistingPass, 1, 7);
		registrationGrid.add(msgExistingPass, 2, 7);
		registrationGrid.add(newPassLabel, 0, 8);       
		registrationGrid.add(txtNewPass, 1, 8);
		registrationGrid.add(msgNewPass, 2, 8);
		registrationGrid.add(reTypeLabel, 0, 9); 
		registrationGrid.add(txtReType, 1, 9);
		registrationGrid.add(msgReType, 2, 9);
		registrationGrid.add(editButtons, 0, 10, 3, 1);
		
		
		registrationBorderPane.setTop(afterLoginMenu.getMenu());
		registrationBorderPane.setCenter(registrationGrid);
		registrationBorderPane.setBottom(footer.drawFooter());
		
		return registrationBorderPane;
	}

}
