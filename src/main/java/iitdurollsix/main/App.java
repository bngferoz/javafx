package iitdurollsix.main;

import java.io.IOException;
import java.time.LocalDateTime;

import iitdurollsix.components.LoginForm;
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
		} catch(Exception e) {
			e.printStackTrace();
			throw new RollSixCustomException(e.getLocalizedMessage(), LocalDateTime.now());
		}
		
	}
	
	
	public static void lunchFX() {
		launch();
	}
	
	
	 
}
