package iitdurollsix.components;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.commons.validator.routines.EmailValidator;

import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
import iitdurollsix.rollsixInterfaces.DbConnectionInterface;
import iitdurollsix.rollsixInterfacesImpl.DbConnectionImpl;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AddProduct {
	private Footer footer;
	private AppController appController;
	private DbConnectionInterface db;
	private String userName;
	private AfterLoginMenu afterLoginMenu;
	
	public AddProduct(String userName) {
		this.footer = new Footer();
		this.db = new DbConnectionImpl();
		this.appController = new AppController();
		this.userName = userName;
		this.afterLoginMenu = new AfterLoginMenu(userName);
	}

	public BorderPane drawProductForm() throws RollSixCustomException, SQLException {
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10, 20, 10, 20));
		
		
		GridPane registrationGrid = new GridPane();
		
		registrationGrid.setHgap(10);
		registrationGrid.setVgap(10);
		registrationGrid.setPadding(new Insets(25,25,25,25));
		
		Label pName = new Label("Procuct Name:");
		Label pPrice = new Label("Product Price:");
		Label pQuant = new Label("Product Quantity:");
		

		Label msgName = new Label("");
		Label msgPrice = new Label("");
		Label msgQuant = new Label("");
		
		TextField txtName = new TextField();
		NumericField txtPrice = new NumericField();
		NumericField txtQuant = new NumericField();
		
		
				
		Button rRegister = new Button("Add Product!");
		rRegister.setOnAction(e->{
			if(txtName.getText().equals("")) {
				msgName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgName.setText("Product Name can not be empty!");
			}
			else if(txtName.getText().length()>100) {
				msgName.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgName.setText("Maximum 100 characters are allowed!");
			}
			else if(txtPrice.getText().equals("")) {
				msgPrice.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgPrice.setText("Price can not be empty!");
			}
			else if(txtPrice.getText().length()>10) {
				msgPrice.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgPrice.setText("Maximum 10 characters are allowed!");
			}
			else if(txtQuant.getText().equals("")) {
				msgQuant.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgQuant.setText("Quantity can not be empty!");
			}
			else if(txtQuant.getText().length()>10) {
				msgQuant.setStyle("-fx-text-fill: red; -fx-font-size: 16px; -fx-font-weight: bold;");
				msgQuant.setText("Maximum 10 characters are allowed!");
			}
			else {
				try {
					if(db.addProduct(txtName.getText(), new BigDecimal(txtPrice.getText()), new BigDecimal(txtQuant.getText()))) {
						Alert alert = new Alert(AlertType.INFORMATION, "Product Added Successfully!", ButtonType.OK);
						alert.show();
						appController.showProductList(e, userName);
					}
				} catch (RollSixCustomException | SQLException e1) {
					System.out.println(e1.getLocalizedMessage());
					e1.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
				}
			}
		});
		

		Button backToLogin = new Button("Go Back!");
		backToLogin.setOnAction(e->{
			try {
				appController.switchToDashboard(e, userName);
			} catch (RollSixCustomException | SQLException e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			}
		});
		
		HBox rButtons = new HBox(rRegister, backToLogin);
		rButtons.setPadding(new Insets(15,12,15,12));
		rButtons.setSpacing(10);
		
		
		
		
		registrationGrid.add(pName, 0, 5); 
		registrationGrid.add(txtName, 1, 5);
		registrationGrid.add(pPrice, 0, 6);       
		registrationGrid.add(txtPrice, 1, 6);
		registrationGrid.add(pQuant, 0, 7);       
		registrationGrid.add(txtQuant, 1, 7);
		
		registrationGrid.add(msgName, 2, 5);
		registrationGrid.add(msgPrice, 2, 6);
		registrationGrid.add(msgQuant, 2, 7);
		
		
		registrationGrid.add(rButtons, 0, 8, 3, 1);
		borderPane.setTop(afterLoginMenu.getMenu());
		borderPane.setCenter(registrationGrid);
		borderPane.setBottom(footer.drawFooter());
		
		return borderPane;
	}

	
}
