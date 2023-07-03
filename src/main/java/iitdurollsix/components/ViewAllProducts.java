package iitdurollsix.components;

import java.sql.SQLException;
import java.util.List;

import entity.Product;
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

public class ViewAllProducts {
	private Footer footer;
	private AppController appController;
	private AfterLoginMenu afterLoginMenu;
	private String userName;
	private DbConnectionInterface db;
	
	
	public ViewAllProducts(String userName) {
		this.appController = new AppController();
		this.footer = new Footer();
		this.afterLoginMenu = new AfterLoginMenu(userName);
		this.userName = userName;
		this.db = new DbConnectionImpl();
	}
	
	public BorderPane drawProductList() throws RollSixCustomException, SQLException {
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10, 20, 10, 20));
		
    	
    	GridPane gridPane = new GridPane();
		
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	gridPane.setPadding(new Insets(25,25,25,25));
    	
    	Button addProduct = new Button("Add Product!");

    	//go back button start
    	Button backToLogin = new Button("Go Back!");
		
		HBox buttons = new HBox(addProduct, backToLogin);
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
		
		addProduct.setOnAction(e->{
			try {
				appController.addProduct(e, userName);
			} catch (RollSixCustomException | SQLException e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			}
		});
		
		
		TableView tableView = new TableView();

	    TableColumn<Product, String> nameColumn = 
	    new TableColumn<>("Product Name");
	    
	    nameColumn.setCellValueFactory(
	        new PropertyValueFactory<>("name"));


		
		TableColumn<Product, String> priceColumn = 
	    new TableColumn<>("Product Price");
	    
		priceColumn.setCellValueFactory(
	        new PropertyValueFactory<>("price"));


	    TableColumn<Product, String> quantColumn = 
	    new TableColumn<>("Product Quantity");
	    
	    quantColumn.setCellValueFactory(
	        new PropertyValueFactory<>("quantity"));
	    
        TableColumn<Product, String> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        actionColumn.setCellFactory(column -> new ButtonTableCell<>(true,userName));

	    
	    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    tableView.getColumns().addAll(nameColumn, priceColumn, quantColumn, actionColumn);



        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Product product = (Product) tableView.getSelectionModel().getSelectedItem();
                if (product != null) {
//                	Alert alert = new Alert(AlertType.INFORMATION, "Hello", ButtonType.OK);
//					alert.show();
                }
            }
        });
	    
	    List<Product> products = db.getAllProducts();
	    if(products!=null && !products.isEmpty()) {
	    	for (Product product : products) {
				tableView.getItems().add(product);
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
