package iitdurollsix.components;


import iitdurollsix.component.dbconnection.DbConnectionImpl;
import iitdurollsix.component.dbconnection.DbConnectionInterface;
import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
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
		Label msg = new Label("Please Enter User Name!");
		
		TextField txtUserName = new TextField();
		PasswordField txtPassword = new PasswordField();
		
		
		Button resetBtn = new Button("Reset"); 

		Button loginBtn = new Button("Login"); 
		
		Button clsbtn = new Button("Close App");
		
		Button register = new Button("Register");
		
		HBox buttons = new HBox(resetBtn, loginBtn, clsbtn, register);
		buttons.setPadding(new Insets(15,12,15,12));
		buttons.setSpacing(10);
		
		
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
			try {
				loginCheck(txtUserName.getText(),txtPassword.getText(),loginBorderPane);
			} catch (RollSixCustomException e1) {

				Alert alert = new Alert(AlertType.ERROR, "Error: "+e1.getMessage()+", time: "+e1.getExceptionTime(), ButtonType.OK);
				alert.show();
			}
		});
		
		loginGrid.add(userName, 0, 5); 
		loginGrid.add(txtUserName, 1, 5);
		loginGrid.add(msg, 2, 5);
		loginGrid.add(password, 0, 6);       
		loginGrid.add(txtPassword, 1, 6); 
		loginGrid.add(buttons, 0, 7, 3, 1);
		
		loginBorderPane.setCenter(loginGrid);
		loginBorderPane.setBottom(footer.drawFooter());
		return loginBorderPane;
		
	}



	private void loginCheck(String userName, String password, BorderPane root) throws RollSixCustomException {
		if(db.validateUserNamePassword(userName, password)) {
			drawDashboard(root);
		}
		else {

			Alert alert = new Alert(AlertType.ERROR, "Invalid Username or Password!", ButtonType.OK);
			alert.show();
		}
		
	}

	private void drawDashboard(BorderPane root) {
		// TODO Auto-generated method stub
		
	}
	
}
