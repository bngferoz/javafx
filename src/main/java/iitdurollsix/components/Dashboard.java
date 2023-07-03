package iitdurollsix.components;
import java.sql.SQLException;

import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Dashboard {
	
	private Header header;
	private Footer footer;
	private AppController appController;
	private AfterLoginMenu afterLoginMenu;
	private String userName;
	
	public Dashboard(String userName) {
		this.appController = new AppController();
		this.footer = new Footer();
		this.header = new Header();
		this.afterLoginMenu = new AfterLoginMenu(userName);
		this.userName = userName;
	}
	
	public BorderPane drawDashboard() throws RollSixCustomException, SQLException {
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10, 20, 10, 20));
		
    	
    	GridPane gridPane = new GridPane();
		
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(25,25,25,25));
    	gridPane.add(header.drawHeader(userName), 0, 0, 15, 3);		
    	
    	root.setTop(afterLoginMenu.getMenu());
		root.setCenter(gridPane);
		root.setBottom(footer.drawFooter());
		return root;
		
	}

}
