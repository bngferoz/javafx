package iitdurollsix.components;
import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {
	
	private Header header;
	private Footer footer;
	private AppController appController;
	private AfterLoginMenu afterLoginMenu;
	
	public Dashboard(String userName) {
		this.appController = new AppController();
		this.footer = new Footer();
		this.header = new Header();
		this.afterLoginMenu = new AfterLoginMenu(userName);
	}
	
	public BorderPane drawDashboard() {
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10, 20, 10, 20));
		
    	
    	GridPane gridPane = new GridPane();
		
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(25,25,25,25));
				
    	root.setTop(afterLoginMenu.getMenu());
		//root.setTop(header.drawHeader(userName));
		root.setCenter(gridPane);
		root.setBottom(footer.drawFooter());
		return root;
		
	}
	VBox getDashBoardMenu() {
		return new VBox();
	}

}
