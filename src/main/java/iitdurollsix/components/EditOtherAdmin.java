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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EditOtherAdmin {
	private Footer footer;
	private AppController appController;
	private DbConnectionInterface db;
	private User user;
	private String currentUser;
	private String userToBeEdited;
	private AfterLoginMenu afterLoginMenu;
	
	
	public EditOtherAdmin(String currentUser, String userToBeEdited) {
		this.user = new User();
		this.footer = new Footer();
		this.db = new DbConnectionImpl();
		this.appController = new AppController();
		this.currentUser = currentUser;
		this.userToBeEdited = userToBeEdited;
		this.afterLoginMenu = new AfterLoginMenu(currentUser);
	}

	public BorderPane drawEditProfile() throws Exception {
		
		user = db.getUserByUsername(userToBeEdited);
		if(user==null) {
			throw new RollSixCustomException("User not found in the database!", LocalDateTime.now());
		}
		
		BorderPane registrationBorderPane = new BorderPane();
		registrationBorderPane.setPadding(new Insets(10, 20, 10, 20));
		
		
		GridPane registrationGrid = new GridPane();
		
		registrationGrid.setHgap(10);
		registrationGrid.setVgap(10);
		registrationGrid.setPadding(new Insets(25,25,25,25));
		
		Label firstName = new Label("Firstname:");
		Label lastName = new Label("Lastname:");
		Label address = new Label("Address:");
		

		Label msgFirstName = new Label("");
		Label msgLastName = new Label("");
		Label msgAddress = new Label("");
		
		TextField txtFirstName = new TextField(user.getFirst_name());
		TextField txtLastName = new TextField(user.getLast_name());
		TextField txtAddress = new TextField(user.getAddress());
		
		Button editProfile = new Button("Edit Profile");
		Button dashBack = new Button("Go Back!");
		
		
		HBox editButtons = new HBox(editProfile, dashBack);
		editButtons.setPadding(new Insets(15,12,15,12));
		editButtons.setSpacing(10);
		
		dashBack.setOnAction(e->{
			try {
				appController.swithToViewAdmin(e, currentUser);
			} catch (Exception e1) {
				Alert errorAlert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				errorAlert.show();
				e1.printStackTrace();
			}
		});
		
		
		editProfile.setOnAction(e->{
			if(txtFirstName.getText().equals("")) {
				msgFirstName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgFirstName.setText("Firstname can not be empty!");
			}
			else if(txtFirstName.getText().length()>100) {
				msgFirstName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgFirstName.setText("Maximum 100 characters are allowed!");
			}
			else if(txtLastName.getText().equals("")) {
				msgLastName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgLastName.setText("Lastname can not be empty!");
			}
			else if(txtLastName.getText().length()>100) {
				msgLastName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgLastName.setText("Maximum 100 characters are allowed!");
			}
			else if(txtAddress.getText().equals("")) {
				msgAddress.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgAddress.setText("Address can not be empty!");
			}
			else if(txtAddress.getText().length()>100) {
				msgAddress.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgAddress.setText("Maximum 100 characters are allowed!");
			}
			else {
				try {
					if(db.editProfile(userToBeEdited, txtFirstName.getText(), txtLastName.getText(), txtAddress.getText())) {
						Alert alert = new Alert(AlertType.INFORMATION, "Profile update Successful!", ButtonType.OK);
						alert.show();
						try {
							appController.swithToViewAdmin(e, currentUser);
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
		

		
		registrationGrid.add(firstName, 0, 7); 
		registrationGrid.add(txtFirstName, 1, 7);
		registrationGrid.add(lastName, 0, 8);       
		registrationGrid.add(txtLastName, 1, 8);
		registrationGrid.add(address, 0, 9); 
		registrationGrid.add(txtAddress, 1, 9);
		registrationGrid.add(editButtons, 0, 10, 3, 1);
		
		
		registrationBorderPane.setTop(afterLoginMenu.getMenu());
		registrationBorderPane.setCenter(registrationGrid);
		registrationBorderPane.setBottom(footer.drawFooter());
		
		return registrationBorderPane;
	}

}
