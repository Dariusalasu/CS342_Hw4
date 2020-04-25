import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChooseYourOwnHomework extends Application {

	// start:
	// Start scene for game selection
	@Override
	public void start(Stage primaryStage) {
		try {
			// Read fxml file and generate interface
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/SelectGameFXML.fxml"));

			primaryStage.setTitle("Select Your Game");

			Scene scene = new Scene(root, 900, 600);
			scene.getStylesheets().add("/Styles/menu.css");

			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	} // End of start

	public static void main(String[] args) { launch(args); } // End of main

}