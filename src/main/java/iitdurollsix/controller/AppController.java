package iitdurollsix.controller;

import iitdurollsix.component.NumericField;
import iitdurollsix.component.dbconnection.DbConnectionImpl;
import iitdurollsix.component.dbconnection.DbConnectionInterface;
import iitdurollsix.exception.RollSixCustomException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


public class AppController {
	
	DbConnectionInterface db = new DbConnectionImpl();
	
	public void drawScene(BorderPane root) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15,12,15,12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color:white");
		
		Button tmpbtn = new Button("Temperature"); 
		Button loginBtn = new Button("Login");
		Button clsbtn = new Button("Close App");
		
		
		
		hbox.getChildren().addAll(tmpbtn, loginBtn, clsbtn);
		tmpbtn.setOnAction(e->{
			drawTemperatureCalculator(root);
		});
		clsbtn.setOnAction(e->{
			Stage stage = (Stage) clsbtn.getScene().getWindow();
       	 	stage.close();
		});
		loginBtn.setOnAction(e->{
			drawLogin(root);
		});
		
		root.setTop(hbox);
		

		
	}
	
private void drawLogin(BorderPane root) {
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
	
	
	resetBtn.setOnAction(e->{
		txtUserName.setText("");
		txtPassword.setText("");
	});
	
	loginBtn.setOnAction(e->{
		try {
			loginCheck(txtUserName.getText(),txtPassword.getText(),root);
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
	loginGrid.add(resetBtn, 0, 7); 
	loginGrid.add(loginBtn, 1, 7);
	root.setCenter(loginGrid);
	
	
	

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
	
	
}

private void drawTemperatureCalculator(BorderPane root) {
    	
    	GridPane tempGrid = new GridPane();
    	//tempGrid.setAlignment(Pos.CENTER);
    	tempGrid.setHgap(10);
    	tempGrid.setVgap(10);
    	tempGrid.setPadding(new Insets(25,25,25,25));
    	
    	Label celcius = new Label("Celsius");
    	Label frnht = new Label("Fahrenheit");
    	Label msg = new Label("Please Enter Celsius value!");
		
		NumericField txtCelsius = new NumericField();
		NumericField txtFrnht = new NumericField();
		
		

		Button resetBtn = new Button("Reset"); 
		
		
		resetBtn.setOnAction(e->{
			txtCelsius.setText("");
			txtFrnht.setText("");
		});
		
		Alert a = new Alert(AlertType.NONE);
		//Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		//alert.showAndWait();
//		if (alert.getResult() == ButtonType.YES) {
//		}
		
		
//		txtCelsius.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//		    @Override
//		    public void handle(KeyEvent event) {
//		    	txtFrnht.setText(event.getText());
//                   
//		    }});
		
		txtCelsius.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Double celDbl;
				Double frhtDbl;
				try{
					if(!newValue.equals("")) {
						celDbl = Double.parseDouble(newValue);
						frhtDbl =(celDbl*1.8)+32;
						txtFrnht.setText(frhtDbl+"");
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR, "Invalid Entry!", ButtonType.OK);
					alert.show();
					
				}
			}
            
		});
		

		
		
		
		tempGrid.add(celcius, 0, 5); 
		tempGrid.add(txtCelsius, 1, 5);
		tempGrid.add(msg, 2, 5);
		tempGrid.add(frnht, 0, 6);       
		tempGrid.add(txtFrnht, 1, 6); 
		tempGrid.add(resetBtn, 0, 7); 
		root.setCenter(tempGrid);
		
		

    }

}
