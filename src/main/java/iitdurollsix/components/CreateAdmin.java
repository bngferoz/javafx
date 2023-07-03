package iitdurollsix.components;

import java.sql.SQLException;
import java.time.LocalDateTime;

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

public class CreateAdmin {
	private Footer footer;
	private AppController appController;
	private DbConnectionInterface db;
	private String userName;
	private AfterLoginMenu afterLoginMenu;
	
	public CreateAdmin(String userName) {
		this.footer = new Footer();
		this.db = new DbConnectionImpl();
		this.appController = new AppController();
		this.userName = userName;
		this.afterLoginMenu = new AfterLoginMenu(userName);
	}

	public BorderPane drawAdminRegistrationForm() throws RollSixCustomException, SQLException {
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10, 20, 10, 20));
		
		
		GridPane registrationGrid = new GridPane();
		
		registrationGrid.setHgap(10);
		registrationGrid.setVgap(10);
		registrationGrid.setPadding(new Insets(25,25,25,25));
		
		Label rUserName = new Label("Email:");
		Label rPassword = new Label("Password:");
		Label reType = new Label("Re-Type Password:");
		Label firstName = new Label("Firstname:");
		Label lastName = new Label("Lastname:");
		Label address = new Label("Address:");
		

		Label msgUserName = new Label("");
		Label msgPassword = new Label("");
		Label msgFirstName = new Label("");
		Label msgLastName = new Label("");
		Label msgAddress = new Label("");
		Label msgReType = new Label("");
		
		TextField rTxtUserName = new TextField();
		PasswordField rTxtPassword = new PasswordField();
		PasswordField txtReType = new PasswordField();
		TextField txtFirstName = new TextField();
		TextField txtLastName = new TextField();
		TextField txtAddress = new TextField();
		
		
				
		Button rRegister = new Button("Register New Admin!");
		rRegister.setOnAction(e->{
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
			else if(rTxtPassword.getText().equals("")) {
				msgPassword.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgPassword.setText("Password can not be empty!");
			}
			else if(rTxtPassword.getText().length()>100) {
				msgPassword.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgPassword.setText("Maximum 100 characters are allowed!");
			}
			else if(txtReType.getText().equals("")) {
				msgReType.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgReType.setText("Re-Type Password can not be empty!");
			}
			else if(txtReType.getText().length()>100) {
				msgReType.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgReType.setText("Maximum 100 characters are allowed!");
			}
			else if(!txtReType.getText().equals(rTxtPassword.getText())) {
				msgReType.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgReType.setText("Re-type password does not match!");
			}
			else if(txtFirstName.getText().equals("")) {
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
					if(registerUser(rTxtUserName.getText(), rTxtPassword.getText(), txtFirstName.getText(), txtLastName.getText(), txtAddress.getText(), borderPane)) {
						Alert alert = new Alert(AlertType.INFORMATION, "Admin Registration is Successful!", ButtonType.OK);
						alert.show();
						appController.switchToDashboard(e, userName);
					}
				} catch (RollSixCustomException | SQLException e1) {
					System.out.println(e1.getLocalizedMessage());
					e1.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
				}
			}
		});
		

		Button backToLogin = new Button("Go Back!");
		backToLogin.setOnAction(e->{
			try {
				appController.switchToDashboard(e, userName);
			} catch (RollSixCustomException | SQLException e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			}
		});
		
		HBox rButtons = new HBox(rRegister, backToLogin);
		rButtons.setPadding(new Insets(15,12,15,12));
		rButtons.setSpacing(10);
		
		
		
		
		registrationGrid.add(rUserName, 0, 5); 
		registrationGrid.add(rTxtUserName, 1, 5);
		registrationGrid.add(rPassword, 0, 6);       
		registrationGrid.add(rTxtPassword, 1, 6);
		registrationGrid.add(reType, 0, 7);       
		registrationGrid.add(txtReType, 1, 7);
		registrationGrid.add(firstName, 0, 8); 
		registrationGrid.add(txtFirstName, 1, 8);
		registrationGrid.add(lastName, 0, 9);       
		registrationGrid.add(txtLastName, 1, 9);
		registrationGrid.add(address, 0, 10); 
		registrationGrid.add(txtAddress, 1, 10);
		
		registrationGrid.add(msgUserName, 2, 5);
		registrationGrid.add(msgPassword, 2, 6);
		registrationGrid.add(msgReType, 2, 7);
		registrationGrid.add(msgFirstName, 2, 8);
		registrationGrid.add(msgLastName, 2, 9);
		registrationGrid.add(msgAddress, 2, 10);
		
		
		registrationGrid.add(rButtons, 0, 11, 3, 1);
		borderPane.setTop(afterLoginMenu.getMenu());
		borderPane.setCenter(registrationGrid);
		borderPane.setBottom(footer.drawFooter());
		
		return borderPane;
	}

	private boolean registerUser(String email, String password, String firstName, String lastName, String address, BorderPane root) throws RollSixCustomException {
		try {
			return db.registerUser(email, password, firstName, lastName, address, "admin");
		} catch (RollSixCustomException | SQLException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			throw new RollSixCustomException(e.getLocalizedMessage(), LocalDateTime.now());
		}
		
	}
}
