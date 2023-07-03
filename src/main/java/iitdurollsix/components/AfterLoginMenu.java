package iitdurollsix.components;

import java.sql.SQLException;

import entity.User;
import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
import iitdurollsix.rollsixInterfaces.DbConnectionInterface;
import iitdurollsix.rollsixInterfacesImpl.DbConnectionImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

public class AfterLoginMenu {

	private MenuBar menuBar;
	private HBox hBox;
	private AppController appController;
	private String userName;
	private User user;
	private DbConnectionInterface db;
	
	
	public  AfterLoginMenu(String userName){
		this.menuBar = new MenuBar();
		this.hBox = new HBox(menuBar);
		this.appController = new AppController();
		this.userName = userName;
		this.db = new DbConnectionImpl();
		
	}
	
	public HBox getMenu() throws RollSixCustomException, SQLException {
		

		
		Menu menu = new Menu("Profile");
		
		MenuItem clsbtn = new MenuItem("Close App");
		MenuItem editProfile = new MenuItem("Edit Profile");
		MenuItem viewProfile = new MenuItem("View Profile");
		MenuItem changePassword = new MenuItem("Change Password");
		MenuItem logout = new MenuItem("Logout");
		menu.getItems().addAll(viewProfile,editProfile, changePassword,logout, clsbtn);
		this.menuBar.getMenus().add(menu);
		
		this.user = db.getUserByUsername(userName);
		if(user!=null && user.getRole().equals("admin")) {
			Menu adminMenu = new Menu("Admin Panel");
			
			MenuItem newAdmin = new MenuItem("Create New Admin");
			MenuItem viewAdmins = new MenuItem("View Admins");
			MenuItem viewClients = new MenuItem("View Clients");
			MenuItem addProduct = new MenuItem("Add Product");
			MenuItem viewProduct = new MenuItem("View All Products");
			
			adminMenu.getItems().addAll(newAdmin,viewAdmins,viewClients,addProduct, viewProduct);

			this.menuBar.getMenus().add(adminMenu);
			
			//action on clicking view clients menu
			viewClients.setOnAction(e->{
				try {
					appController.showClientList(e, userName);
				} catch (Exception e1) {
					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
					e1.printStackTrace();
				}
			});
			
			//action on clicking add product menu
			addProduct.setOnAction(e->{
				try {
					appController.addProduct(e, userName);
				} catch (Exception e1) {
					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
					e1.printStackTrace();
				}
			});
			
			//action on clicking view product menu
			viewProduct.setOnAction(e->{
				try {
					appController.showProductList(e, userName);
				} catch (Exception e1) {
					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
					e1.printStackTrace();
				}
			});
			
			
			newAdmin.setOnAction(e->{
				try {
					appController.swithToCreateNewAdmin(e, userName);
				} catch (Exception e1) {
					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
					e1.printStackTrace();
				}
			});
			
			viewAdmins.setOnAction(e->{
				try {
					appController.swithToViewAdmin(e, userName);
				} catch (Exception e1) {
					Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
					alert.show();
					e1.printStackTrace();
				}
			});
		}
		if(user!=null && user.getRole().equals("client")) {
			Menu clientMenu = new Menu("Client Panel");
			
			MenuItem newOrder = new MenuItem("New Order");
			MenuItem viewOrder = new MenuItem("View Orders");
			
			clientMenu.getItems().addAll(newOrder, viewOrder);

			this.menuBar.getMenus().add(clientMenu);
		}
		
		
		viewProfile.setOnAction(e->{
			try {
				appController.viewProfile(e, userName);
			} catch (Exception e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			}
		});
		
		clsbtn.setOnAction(e->{
			appController.closeWindow(e);
		});
		
		logout.setOnAction(e->{
			appController.switchToLogin(e);
		});

		changePassword.setOnAction(e->{
			try {
				appController.changePassword(e, userName);
			} catch (Exception e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			}
		});
		
		editProfile.setOnAction(e->{
			try {
				appController.editProfile(e, userName);
			} catch (RollSixCustomException e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			} catch (Exception e1) {
				Alert alert = new Alert(AlertType.ERROR, e1.getLocalizedMessage(), ButtonType.OK);
				alert.show();
				e1.printStackTrace();
			}
		});
		
		return hBox;
	}
}
