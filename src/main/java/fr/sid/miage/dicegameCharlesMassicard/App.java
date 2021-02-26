package fr.sid.miage.dicegameCharlesMassicard;

import java.io.IOException;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**	
 * @author Anne-Laure CHARLES	
 * @author Louis MASSICARD (user name : louis)	
 * @version 	
 * @since %G% - %U% (%I%)	
 *	
 */
public class App  extends Application {
	
	/**	
	 * Method start : allow to initiate 	
	 *  - the FXML first view	
	 *  - the title of the applicaiton's window 	
	 *  - the icon of the applicaiton's window 	
	 *  - the CSS	
	 * Allow also to param the applicaiton's window (size, etc.).	
	 */
	public void start(Stage primaryStage) {
		Parent root =  null;
		try {
			//If files in resources
			root = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainView.fxml"));
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
		scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
		//not allow to modify the window 
		primaryStage.setResizable(false);
		//To change the visibility to false
		primaryStage.hide();
		//for the responsive window's
		primaryStage.sizeToScene();
		scene.setRoot((Parent) root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		DiceGame diceGame = DiceGame.getInstance();
		
	}
	
	/**	
	 * Main function.	
	 * 	
	 * @param args Arguments passed to application's Jar (here, not used).	
	 */
	public static void main(String[] args) {
		//Démarrer le menu
		launch(args);
	}

}