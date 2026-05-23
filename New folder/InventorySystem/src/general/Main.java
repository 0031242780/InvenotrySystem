package general;

import Invontory.CategoryInterface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {

		CategoryInterface ui = new CategoryInterface();

		Scene scene = new Scene(ui.getView(), 1100, 650);

		stage.setTitle("Category Management System");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}