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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ViewProfile {

	private Footer footer;
	private AppController appController;
	private DbConnectionInterface db;
	private User user;
	private AfterLoginMenu afterLoginMenu;
	private String userName;
	
	
	
	public ViewProfile(String userName) {
		this.user = new User();
		this.footer = new Footer();
		this.db = new DbConnectionImpl();
		this.appController = new AppController();
		this.userName = userName;
		this.afterLoginMenu = new AfterLoginMenu(userName);
	}

	public BorderPane drawViewProfile() throws Exception {
		
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
		
		Label firstName = new Label("Firstname:");
		Label lastName = new Label("Lastname:");
		Label address = new Label("Address:");
		

		Label msgFirstName = new Label("");
		Label msgLastName = new Label("");
		Label msgAddress = new Label("");
		
		Label txtFirstName = new Label(user.getFirst_name());
		txtFirstName.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
		
		Label txtLastName = new Label(user.getLast_name());
		txtLastName.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
		
		Label txtAddress = new Label(user.getAddress());
		txtAddress.setStyle("-fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold;");
		
		
		Button editProfile = new Button("Edit Profile");
		Button dashBack = new Button("Go Back to Dashboard!");
		
		HBox editButtons = new HBox(editProfile, dashBack);
		editButtons.setPadding(new Insets(15,12,15,12));
		editButtons.setSpacing(10);
		
		
		editProfile.setOnAction(e->{
			try {
				appController.editProfile(e, userName);
			} catch (Exception e1) {
				Alert errorAlert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				errorAlert.show();
				e1.printStackTrace();
			}
		});

		dashBack.setOnAction(e->{
			try {
				appController.switchToDashboard(e, userName);
			} catch (Exception e1) {
				Alert errorAlert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				errorAlert.show();
				e1.printStackTrace();
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
