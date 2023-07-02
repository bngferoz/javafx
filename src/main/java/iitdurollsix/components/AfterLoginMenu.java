package iitdurollsix.components;

import iitdurollsix.controller.AppController;
import iitdurollsix.exception.RollSixCustomException;
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
	
	public  AfterLoginMenu(String userName) {
		this.menuBar = new MenuBar();
		this.hBox = new HBox(menuBar);
		this.appController = new AppController();
		this.userName = userName;
	}
	
	public HBox getMenu() {
		
		Menu menu = new Menu("Profile");
		
		MenuItem clsbtn = new MenuItem("Close Window");
		MenuItem editProfile = new MenuItem("Edit Profile");
		MenuItem viewProfile = new MenuItem("View Profile");
		MenuItem changePassword = new MenuItem("Change Password");
		MenuItem logout = new MenuItem("Logout");
		
		menu.getItems().addAll(viewProfile,editProfile, changePassword,logout, clsbtn);
		
		this.menuBar.getMenus().addAll(menu);
		
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
