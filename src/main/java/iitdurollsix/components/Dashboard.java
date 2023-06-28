package iitdurollsix.components;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Dashboard {
	
	private Footer footer;
	
	public Dashboard() {
		this.footer = new Footer();
	}
	
	public BorderPane drawDashboard() {
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10, 20, 10, 20));
		
    	
    	GridPane gridPane = new GridPane();
		
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(25,25,25,25));
				
    	Button clsbtn = new Button("Close App");
		
		
		HBox buttons = new HBox(clsbtn);
		buttons.setPadding(new Insets(15,12,15,12));
		buttons.setSpacing(10);
		
		
		clsbtn.setOnAction(e->{
			Stage stage = (Stage) clsbtn.getScene().getWindow();
			 	stage.close();
		});
		
		gridPane.add(buttons, 0, 7, 3, 1);
		
		root.setCenter(gridPane);
		root.setBottom(footer.drawFooter());
		return root;
		
	}

}
