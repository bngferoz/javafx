package iitdurollsix.components;
import dto.UserDto;
import entity.Product;
import iitdurollsix.controller.AppController;
import iitdurollsix.rollsixInterfaces.DbConnectionInterface;
import iitdurollsix.rollsixInterfacesImpl.DbConnectionImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;

public class ButtonTableCell<S, T> extends TableCell<S, T> {
    private final Button button;
    AppController appController;
    String currentUser;
    private DbConnectionInterface db;
	

    public ButtonTableCell(String currentUser) {
    	this.db = new DbConnectionImpl();
    	this.currentUser = currentUser;
    	this.appController = new AppController();
        button = new Button("Edit Profile");
        button.setOnAction(event -> {
            S rowData = getTableView().getItems().get(getIndex());
            T cellData = getTableColumn().getCellData(rowData);
            if (rowData instanceof UserDto) {
            	UserDto person = (UserDto) rowData;
                try {
					appController.editOtherAdmin(event, currentUser ,person.getEmail());
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
					alert.show();
					e.printStackTrace();
				}
            }
            else {
            	Alert alert = new Alert(AlertType.ERROR, "User Data is not available", ButtonType.OK);
				alert.show();
            }
        });
        setGraphic(button);
        setText(null);
    }
    public ButtonTableCell(String viewClients, String currentUser) {
    	this.db = new DbConnectionImpl();
    	this.currentUser = currentUser;
    	this.appController = new AppController();
        button = new Button("Delete Client");
        button.setOnAction(event -> {
            S rowData = getTableView().getItems().get(getIndex());
            T cellData = getTableColumn().getCellData(rowData);
            if (rowData instanceof UserDto) {
            	UserDto person = (UserDto) rowData;
                try {
					if(db.deleteUserByEmail(person.getEmail())){

						Alert alert = new Alert(AlertType.INFORMATION, "Client deleted successfully!", ButtonType.OK);
						alert.show();
						appController.showClientList(event, currentUser);
					}
					else {
						Alert alert = new Alert(AlertType.ERROR, "Unable to delete!", ButtonType.OK);
						alert.show();
					}
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
					alert.show();
					e.printStackTrace();
				}
            }
            else {
            	Alert alert = new Alert(AlertType.ERROR, "User Data is not available!", ButtonType.OK);
				alert.show();
            }
        });
        setGraphic(button);
        setText(null);
    }
    public ButtonTableCell(boolean isProduct,String currentUser) {
    	this.db = new DbConnectionImpl();
    	this.currentUser = currentUser;
    	this.appController = new AppController();
        button = new Button("Delete Product");
        button.setOnAction(event -> {
            S rowData = getTableView().getItems().get(getIndex());
            T cellData = getTableColumn().getCellData(rowData);
            if (rowData instanceof Product) {
            	Product product = (Product) rowData;
                try {
					db.deleteProductByName(product.getName());
					Alert alert = new Alert(AlertType.INFORMATION, "Product deleted successfully!", ButtonType.OK);
					alert.show();
					appController.showProductList(event, currentUser);
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR, e.getLocalizedMessage(), ButtonType.OK);
					alert.show();
					e.printStackTrace();
				}
            }
            else {
            	Alert alert = new Alert(AlertType.ERROR, "Product Data is not available", ButtonType.OK);
				alert.show();
            }
        });
        setGraphic(button);
        setText(null);
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(button);
        }
    }
}
