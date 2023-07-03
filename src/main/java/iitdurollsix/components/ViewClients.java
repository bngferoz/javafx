package iitdurollsix.components;

import java.sql.SQLException;
import java.util.List;

import dto.UserDto;
import entity.User;
import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
import iitdurollsix.rollsixInterfaces.DbConnectionInterface;
import iitdurollsix.rollsixInterfacesImpl.DbConnectionImpl;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewClients {
	private Footer footer;
	private AppController appController;
	private AfterLoginMenu afterLoginMenu;
	private String userName;
	private DbConnectionInterface db;
	
	
	public ViewClients(String userName) {
		this.appController = new AppController();
		this.footer = new Footer();
		this.afterLoginMenu = new AfterLoginMenu(userName);
		this.userName = userName;
		this.db = new DbConnectionImpl();
	}
	
	public BorderPane drawClientList() throws RollSixCustomException, SQLException {
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10, 20, 10, 20));
		
    	
    	GridPane gridPane = new GridPane();
		
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(25,25,25,25));
    	
    	//go back button start
    	Button backToLogin = new Button("Go Back!");

		HBox buttons = new HBox(backToLogin);
		buttons.setPadding(new Insets(15,12,15,12));
		buttons.setSpacing(10);

		backToLogin.setOnAction(e->{
			try {
				appController.switchToDashboard(e, userName);
			} catch (RollSixCustomException | SQLException e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			}
		});
		
		
		
		TableView tableView = new TableView();

	    TableColumn<User, String> columnEmail = 
	    new TableColumn<>("Email");
	    
	    columnEmail.setCellValueFactory(
	        new PropertyValueFactory<>("email"));


		
		TableColumn<User, String> columnFirstName = 
	    new TableColumn<>("First Name");
	    
		columnFirstName.setCellValueFactory(
	        new PropertyValueFactory<>("first_name"));


	    TableColumn<User, String> columnLastName = 
	    new TableColumn<>("Last Name");
	    
	    columnLastName.setCellValueFactory(
	        new PropertyValueFactory<>("last_name"));
	    
	    TableColumn<User, String> columnAddress = 
	    	    new TableColumn<>("Address");
	    	    
	    columnAddress.setCellValueFactory(
	    	        new PropertyValueFactory<>("address"));
	    
	    
        TableColumn<UserDto, String> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        actionColumn.setCellFactory(column -> new ButtonTableCell<>("clientList",userName));

	    
	    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    tableView.getColumns().addAll(columnEmail, columnFirstName, columnLastName, columnAddress, actionColumn);



        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                UserDto person = (UserDto) tableView.getSelectionModel().getSelectedItem();
                if (person != null) {
//                	Alert alert = new Alert(AlertType.INFORMATION, "Hello", ButtonType.OK);
//					alert.show();
                }
            }
        });
	    
	    List<UserDto> clients = db.getUserByRole("client");
	    if(clients!=null && !clients.isEmpty()) {
	    	for (UserDto userDto : clients) {
				tableView.getItems().add(userDto);
			}
	    }

        VBox vbox = new VBox(tableView);

        gridPane.add(buttons, 0, 0, 3, 1);
		
    	gridPane.add(vbox, 0, 1, 50, 15);		
    	
    	root.setTop(afterLoginMenu.getMenu());
		root.setCenter(gridPane);
		root.setBottom(footer.drawFooter());
		
		
		return root;
		
	}

}
