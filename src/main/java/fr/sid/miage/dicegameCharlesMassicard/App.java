package fr.sid.miage.dicegameCharlesMassicard;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App  extends Application {
	
	public void start(Stage primaryStage) {
		Parent root =  null;
		try {
			//If files in resources
			root = FXMLLoader.load(getClass().getClassLoader().getResource("view/RollForm.fxml"));
			//If in packages in src/main/java
			//root = FXMLLoader.load(getClass().getResource("views/Main.fxml"));
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			Platform.exit();
            System.exit(0);
		}
		Scene scene = new Scene(root);
		primaryStage.setTitle("Projet - DiceGame ");
		primaryStage.getIcons().add(new Image("images/dice-game.png"));
		//scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
		//not allow to modify the window 
		//primaryStage.setResizable(false);
		//To change the visibility to false
		primaryStage.hide();
		//for the responsive window's
		primaryStage.sizeToScene();
		scene.setRoot((Parent) root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		//Démarrer le menu
		launch(args);
	}

}