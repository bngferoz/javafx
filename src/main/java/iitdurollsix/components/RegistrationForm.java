package iitdurollsix.components;

import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class RegistrationForm {
	
	private Footer footer;
	private AppController appController;
	
	public RegistrationForm() {
		this.footer = new Footer();
		this.appController = new AppController();
	}

	public BorderPane drawRegistrationForm() throws RollSixCustomException {
		
		BorderPane registrationBorderPane = new BorderPane();
		registrationBorderPane.setPadding(new Insets(10, 20, 10, 20));
		
		
		GridPane registrationGrid = new GridPane();
		
		registrationGrid.setHgap(10);
		registrationGrid.setVgap(10);
		registrationGrid.setPadding(new Insets(25,25,25,25));
		
		Label rUserName = new Label("User Name:");
		Label rPassword = new Label("Password:");
		
		TextField rTxtUserName = new TextField();
		PasswordField rTxtPassword = new PasswordField();
		
		
				
		Button rRegister = new Button("Register");

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
		registrationGrid.add(rButtons, 0, 7, 3, 1);
		
		registrationBorderPane.setCenter(registrationGrid);
		registrationBorderPane.setBottom(footer.drawFooter());
		
		return registrationBorderPane;
	}


	
}
