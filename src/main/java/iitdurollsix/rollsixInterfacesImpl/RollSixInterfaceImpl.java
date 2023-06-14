package iitdurollsix.rollsixInterfacesImpl;

import java.io.IOException;

import iitdurollsix.component.NumericField;
import iitdurollsix.constant.StaticVariables;
import iitdurollsix.rollsixInterfaces.RollSixInterface;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class RollSixInterfaceImpl implements RollSixInterface{
	
	private final Stage primaryStage;
    private final BorderPane root;
    public static String STYLESHEET_CASPIAN = "roll6.css";

	public RollSixInterfaceImpl(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.root  = new BorderPane();
		root.setPadding(new Insets(10, 20, 10, 20));
	}
	@Override
    public void initializeUserInterface() throws IOException{
		drawScene(root);
    	Scene scene = new Scene(root, StaticVariables.WINDOW_WIDTH, StaticVariables.WINDOW_HEIGHT);
    	scene.getStylesheets().add(RollSixInterfaceImpl.class.getResource("rollsix.css").toExternalForm());
    	primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
		primaryStage.setTitle("roll06");
    	primaryStage.show();
		
	}
	void drawScene(BorderPane root) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15,12,15,12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color:white");
		
		Button tmpbtn = new Button("Temperature"); 
		tmpbtn.setPrefSize(200, 20);
		tmpbtn.setStyle("-fx-background-color:green");
		
		Button clsbtn = new Button("Close App");
		tmpbtn.setPrefSize(100, 20);
		tmpbtn.setStyle("-fx-background-color:blue");
		
		hbox.getChildren().addAll(tmpbtn, clsbtn);
		tmpbtn.setOnAction(e->{
			drawTemperatureCalculator(root);
		});
		clsbtn.setOnAction(e->{
			primaryStage.close();
		});
		
		root.setTop(hbox);
		

		
	}
    void drawTemperatureCalculator(BorderPane root) {
    	
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
		

		
		
		Button resetBtn = new Button("Reset"); 
		resetBtn.setOnAction(e->{
			txtCelsius.setText("");
			txtFrnht.setText("");
		});
		
		tempGrid.add(celcius, 0, 5); 
		tempGrid.add(txtCelsius, 1, 5);
		tempGrid.add(msg, 2, 5);
		tempGrid.add(frnht, 0, 6);       
		tempGrid.add(txtFrnht, 1, 6); 
		tempGrid.add(resetBtn, 0, 7); 
		root.setCenter(tempGrid);
		
		

    }
	@Override
    public void showDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();

        if (dialog.getResult() == ButtonType.OK) {
        	
        }
    }

    @Override
    public void showError(String message) {
        Alert dialog = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        dialog.showAndWait();
    }
    
    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getText().equals("0")
                    || event.getText().equals("1")
                    || event.getText().equals("2")
                    || event.getText().equals("3")
                    || event.getText().equals("4")
                    || event.getText().equals("5")
                    || event.getText().equals("6")
                    || event.getText().equals("7")
                    || event.getText().equals("8")
                    || event.getText().equals("9")
            ) {
                int value = Integer.parseInt(event.getText());
                //handleInput(value, event.getSource());
            } else {
                ((TextField)event.getSource()).setText("");
            }
        }

        event.consume();
    }
    
    @Override
    public void closeApp() {
		primaryStage.close();
	}

}
