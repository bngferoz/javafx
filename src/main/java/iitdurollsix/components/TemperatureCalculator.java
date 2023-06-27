package iitdurollsix.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TemperatureCalculator {

	public void drawTemperatureCalculator(BorderPane root) {
    	
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
