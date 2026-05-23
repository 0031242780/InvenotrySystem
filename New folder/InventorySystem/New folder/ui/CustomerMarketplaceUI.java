package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CustomerMarketplaceUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));

		// --- TOP NAVIGATION BAR ---
		HBox topNavbar = new HBox(15);
		topNavbar.setPadding(new Insets(10));
		topNavbar.setAlignment(Pos.CENTER_LEFT);
		topNavbar.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 5;");

		Label logoLabel = new Label("MEGA PC Market");
		logoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

		TextField searchField = new TextField();
		searchField.setPromptText("Search products or barcodes...");
		searchField.setPrefWidth(250);

		ComboBox<String> categoryDropdown = new ComboBox<>();
		categoryDropdown.setPromptText("All Categories");
		// Populated dynamically from Categories table (category_name)

		Button viewCartButton = new Button("View Cart (0)");
		viewCartButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");

		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		topNavbar.getChildren().addAll(logoLabel, searchField, categoryDropdown, spacer, viewCartButton);
		root.setTop(topNavbar);

		// --- MAIN PRODUCT DISPLAY GRID ---
		GridPane productGrid = new GridPane();
		productGrid.setHgap(20);
		productGrid.setVgap(20);
		productGrid.setPadding(new Insets(20, 0, 0, 0));

		// Example of a single dynamically generated Product Card
		VBox productCard = new VBox(10);
		productCard.setPadding(new Insets(15));
		productCard.setStyle("-fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-background-color: #ffffff;");
		productCard.setPrefWidth(200);
		productCard.setAlignment(Pos.CENTER);

		// Placeholder for Product Photo
		StackPane photoPlaceholder = new StackPane();
		photoPlaceholder.setPrefSize(150, 120);
		photoPlaceholder.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 3;");
		Label photoLabel = new Label("[ Product Image ]");
		photoPlaceholder.getChildren().add(photoLabel);

		Label prodNameLabel = new Label("Intel Core i7-13700K");
		prodNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

		Label priceLabel = new Label("$380.00");
		priceLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 14px; -fx-font-weight: bold;");

		Button addToCartButton = new Button("Add to Cart");
		addToCartButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

		productCard.getChildren().addAll(photoPlaceholder, prodNameLabel, priceLabel, addToCartButton);

		// Add card to grid column 0, row 0
		productGrid.add(productCard, 0, 0);

		ScrollPane scrollPane = new ScrollPane(productGrid);
		scrollPane.setFitToWidth(true);
		scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
		root.setCenter(scrollPane);

		Scene scene = new Scene(root, 900, 600);
		primaryStage.setTitle("Mega PC Market - Customer Storefront");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}