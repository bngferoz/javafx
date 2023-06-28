package iitdurollsix.components;

import java.sql.SQLException;
import java.time.LocalDateTime;

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

public class RegistrationForm {
	
	private Footer footer;
	private AppController appController;
	private DbConnectionInterface db;
	
	
	public RegistrationForm() {
		this.footer = new Footer();
		this.db = new DbConnectionImpl();
		this.appController = new AppController();
	}

	public BorderPane drawRegistrationForm() throws RollSixCustomException {
		
		BorderPane registrationBorderPane = new BorderPane();
		registrationBorderPane.setPadding(new Insets(10, 20, 10, 20));
		
		
		GridPane registrationGrid = new GridPane();
		
		registrationGrid.setHgap(10);
		registrationGrid.setVgap(10);
		registrationGrid.setPadding(new Insets(25,25,25,25));
		
		Label rUserName = new Label("Email:");
		Label rPassword = new Label("Password:");
		Label firstName = new Label("Firstname:");
		Label lastName = new Label("Lastname:");
		Label address = new Label("Address:");
		

		Label msgUserName = new Label("");
		Label msgPassword = new Label("");
		Label msgFirstName = new Label("");
		Label msgLastName = new Label("");
		Label msgAddress = new Label("");
		
		TextField rTxtUserName = new TextField();
		PasswordField rTxtPassword = new PasswordField();
		TextField txtFirstName = new TextField();
		PasswordField txtLastName = new PasswordField();
		TextField txtAddress = new TextField();
		
		
				
		Button rRegister = new Button("Register");
		rRegister.setOnAction(e->{
			if(rTxtUserName.getText().equals("")) {
				msgUserName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgUserName.setText("Email address can not be empty!");
			}
			else if(rTxtPassword.getText().equals("")) {
				msgPassword.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgPassword.setText("Password can not be empty!");
			}
			else if(txtFirstName.getText().equals("")) {
				msgFirstName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgFirstName.setText("Firstname can not be empty!");
			}
			else if(txtLastName.getText().equals("")) {
				msgLastName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgLastName.setText("Lastname can not be empty!");
			}
			else if(txtAddress.getText().equals("")) {
				msgAddress.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgAddress.setText("Address can not be empty!");
			}
			else {
				try {
					if(registerUser(rTxtUserName.getText(), rTxtPassword.getText(), txtFirstName.getText(), txtLastName.getText(), txtAddress.getText(), registrationBorderPane)) {
						Alert alert = new Alert(AlertType.INFORMATION, "User Registration is Successful!", ButtonType.OK);
						alert.show();
						appController.switchToLogin(e);
					}
				} catch (RollSixCustomException e1) {
					System.out.println(e1.getLocalizedMessage());
					e1.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
				}
			}
		});
		

		Button backToLogin = new Button("Already registerd? Login Here!");
		backToLogin.setOnAction(e->{
			appController.switchToLogin(e);
		});
		
		HBox rButtons = new HBox(rRegister, backToLogin);
		rButtons.setPadding(new Insets(15,12,15,12));
		rButtons.setSpacing(10);
		
		
		
		
		registrationGrid.add(rUserName, 0, 5); 
		registrationGrid.add(rTxtUserName, 1, 5);
		registrationGrid.add(rPassword, 0, 6);       
		registrationGrid.add(rTxtPassword, 1, 6);
		registrationGrid.add(firstName, 0, 7); 
		registrationGrid.add(txtFirstName, 1, 7);
		registrationGrid.add(lastName, 0, 8);       
		registrationGrid.add(txtLastName, 1, 8);
		registrationGrid.add(address, 0, 9); 
		registrationGrid.add(txtAddress, 1, 9);
		
		registrationGrid.add(rButtons, 0, 10, 3, 1);
		
		registrationBorderPane.setCenter(registrationGrid);
		registrationBorderPane.setBottom(footer.drawFooter());
		
		return registrationBorderPane;
	}

	private boolean registerUser(String email, String password, String firstName, String lastName, String address, BorderPane root) throws RollSixCustomException {
		try {
			return db.registerUser(email, password, firstName, lastName, address);
		} catch (RollSixCustomException | SQLException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			throw new RollSixCustomException(e.getLocalizedMessage(), LocalDateTime.now());
		}
		
	}
	
}
