package iitdurollsix.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Header {

	public HBox drawHeader(String userName) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15,12,15,12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color:white");
		Label welcome = new Label("Welcome "+userName+"!");
		welcome.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");
		hbox.getChildren().addAll(welcome);
		hbox.setPrefHeight(20);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
}
