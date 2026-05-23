package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {

	public void openProducts() throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("ProductView.fxml"));

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.setTitle("Products");
		stage.show();
	}

	public void openAccounts() throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("AccountView.fxml"));

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.setTitle("Accounts");
		stage.show();
	}

	public void openOrders() throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("OrderView.fxml"));

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.setTitle("Orders");
		stage.show();
	}

	public void openInventory() throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("InventoryView.fxml"));

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.setTitle("Inventory");
		stage.show();
	}
}