package vipcom;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent; 


public class App extends Application {
	public static String STYLESHEET_CASPIAN = "vipcom.css";
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Text text1 = new Text("Celsius");
			Text text2 = new Text("Fahrenheit");
			Text text3 = new Text("Please Enter Celsius value!");
			
			TextField celsicusInput = new TextField();
			
			
			
			TextField fOutput = new TextField();
			Button button1 = new Button("Reset"); 
			
			
			EventHandler<ActionEvent> button2event = null;
			
			EventHandler<ActionEvent> button1event = null;
				
		    
			Button button2 = new Button("Close");
			
			button2event = new EventHandler<ActionEvent>() { 
		         @Override 
		         public void handle(ActionEvent event) { 
		        	 Stage stage = (Stage) button2.getScene().getWindow();
		        	 stage.close();
		         }           
		      };
		      
		      button1event = new EventHandler<ActionEvent>() { 
			         @Override 
			         public void handle(ActionEvent event) { 
			        	 celsicusInput.setText("");
			        	 fOutput.setText("");
			         }           
			      };
		      
			
			 EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() { 
		         @Override 
		         public void handle(KeyEvent event) { 
		        	String celsiusString = celsicusInput.getText();
		            try {
		            	//double cDouble = Double.parseDouble(celsiusString);
		            	text3.setText(celsiusString);
						
		            }
		            catch (Exception e) {
		            	text3.setText("Please Enter Numeric Value!");
			            
					}
		            
		         }           
		      };
		      
		      button2.setOnAction(button2event);

		      button1.setOnAction(button1event);
				
			
		      celsicusInput.addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField); 
			
			GridPane root = new GridPane();
			root.setMinSize(400, 200);
			root.setPadding(new Insets(10, 10, 10, 10)); 
			root.setVgap(5); 
			root.setHgap(5);
			root.setAlignment(Pos.CENTER);
			root.add(text1, 0, 0); 
			root.add(celsicusInput, 1, 0);
			root.add(text3, 2, 0);
			root.add(text2, 0, 1);       
			root.add(fOutput, 1, 1); 
			root.add(button1, 0, 2); 
			root.add(button2, 1, 2);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(App.class.getResource(STYLESHEET_CASPIAN).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("vipcom");
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void lunchFX() {
		launch();
	}
	
	 
}
