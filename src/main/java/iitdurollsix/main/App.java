package iitdurollsix.main;

import java.io.IOException;
import java.time.LocalDateTime;

import iitdurollsix.exception.RollSixCustomException;
import iitdurollsix.rollsixInterfaces.RollSixInterface;
import iitdurollsix.rollsixInterfacesImpl.RollSixInterfaceImpl;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
	private RollSixInterface rollSixInterfaceImpl;
	
	@Override
	public void start(Stage primaryStage) throws RollSixCustomException {
		rollSixInterfaceImpl = new RollSixInterfaceImpl(primaryStage);
		
		
		try {
			
			rollSixInterfaceImpl.initializeUserInterface();
			
//			button2event = new EventHandler<ActionEvent>() { 
//		         @Override 
//		         public void handle(ActionEvent event) { 
//		        	 Stage stage = (Stage) button2.getScene().getWindow();
//		        	 stage.close();
//		         }           
//		      };
//		      
//		      button1event = new EventHandler<ActionEvent>() { 
//			         @Override 
//			         public void handle(ActionEvent event) { 
//			        	 celsicusInput.setText("");
//			        	 fOutput.setText("");
//			         }           
//			      };
//		      
			
//			 EventHandler<KeyEvent> eventHandlerTextField = new EventHandler<KeyEvent>() { 
//		         @Override 
//		         public void handle(KeyEvent event) { 
//		        	String celsiusString = celsicusInput.getText();
//		            try {
//		            	//double cDouble = Double.parseDouble(celsiusString);
//		            	text3.setText(celsiusString);
//						
//		            }
//		            catch (Exception e) {
//		            	text3.setText("Please Enter Numeric Value!");
//			            
//					}
//		            
//		         }           
//		      };
//		      
//		      button2.setOnAction(button2event);
//
//		      button1.setOnAction(button1event);
//				
//			
//		      celsicusInput.addEventHandler(KeyEvent.KEY_TYPED, eventHandlerTextField); 
//			
//			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RollSixCustomException(e.getLocalizedMessage(), LocalDateTime.now());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RollSixCustomException(e.getLocalizedMessage(), LocalDateTime.now());
		}
	}
	
	
	public static void lunchFX() {
		launch();
	}
	
	
	 
}
