package iitdurollsix.components;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Footer {

	public HBox drawFooter() {

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15,12,15,12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color:white");
		Label welcome = new Label("© Roll 06, EMIT 2nd Batch (Advanced OOP Lab Project, IIT, DU!)");
		welcome.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");
		
		URL duLogo = getClass().getClassLoader().getResource("assets/images/du.png");
		Image image = new Image(duLogo.toString());
		ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setFitWidth(100);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        iv.setFitHeight(30);
		hbox.getChildren().addAll(iv,welcome);
		hbox.setPrefHeight(20);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
}
