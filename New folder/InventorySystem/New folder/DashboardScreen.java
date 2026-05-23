package general;

import Invontory.CategoryInterface;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardScreen {

	private Scene scene;

	public DashboardScreen(Stage stage) {

		Label title = new Label("Database Inventory Management");
		title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		// Initialize your exact Category Interface Component
		CategoryInterface categoryInterface = new CategoryInterface();

		BorderPane layout = new BorderPane();

		VBox top = new VBox(title);
		top.setPadding(new Insets(15));

		layout.setTop(top);
		
		// Maintains your left/right structural split properties safely
		layout.setLeft(categoryInterface.getLeft());
		layout.setRight(categoryInterface.getRight());

		TabPane tabs = new TabPane();

		Tab homeTab = new Tab("Categories", layout);
		homeTab.setClosable(false);

		tabs.getTabs().add(homeTab);

		scene = new Scene(tabs, 1100, 650);

		stage.setScene(scene);
		stage.setTitle("Inventory System");
		stage.show();
	}

	public Scene getScene() {
		return scene;
	}
}