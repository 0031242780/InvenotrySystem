//package ui;
//
//import dao.ProductDAO;
//import dao.OrderDAO;
//import dao.AccountDAO;
//import model.Product;
//import model.Order;
//import model.Account;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//public class EmployeeDashboardUI extends Application {
//
//	private final ProductDAO productDAO = new ProductDAO();
//	private final OrderDAO orderDAO = new OrderDAO();
//	private final AccountDAO accountDAO = new AccountDAO();
//
//	private VBox workspace;
//
//	@Override
//	public void start(Stage primaryStage) {
//		BorderPane root = new BorderPane();
//
//		// --- SIDEBAR UI ---
//		VBox sidebar = new VBox(10);
//		sidebar.setPadding(new Insets(15));
//		sidebar.setPrefWidth(220);
//		sidebar.setStyle("-fx-background-color: #2c3e50;");
//
//		Label panelTitle = new Label("Admin Panel");
//		panelTitle.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
//		panelTitle.setPadding(new Insets(0, 0, 15, 0));
//
//		Button btnProducts = new Button("Manage Products");
//		Button btnOrders = new Button("Orders & Tracking");
//		Button btnAccounts = new Button("Accounts Management");
//
//		String btnStyle = "-fx-background-color: #34495e; -fx-text-fill: white; -fx-alignment: CENTER_LEFT; -fx-font-size: 13px; -fx-cursor: hand;";
//		for (Button btn : new Button[] { btnProducts, btnOrders, btnAccounts }) {
//			btn.setStyle(btnStyle);
//			btn.setMaxWidth(Double.MAX_VALUE);
//			btn.setPadding(new Insets(10));
//		}
//
//		sidebar.getChildren().addAll(panelTitle, btnProducts, btnOrders, btnAccounts);
//		root.setLeft(sidebar);
//
//		// --- CENTRAL MONITOR ---
//		workspace = new VBox(15);
//		workspace.setPadding(new Insets(20));
//		root.setCenter(workspace);
//
//		// Wiring view links inside Java dynamically without FXML files
//		btnProducts.setOnAction(e -> showProductsView());
//		btnOrders.setOnAction(e -> showOrdersView());
//		btnAccounts.setOnAction(e -> showAccountsView());
//
//		// Default layout view on boot
//		showProductsView();
//
//		Scene scene = new Scene(root, 1050, 650);
//		primaryStage.setTitle("Mega PC Market - Employee Dashboard");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}
//
//	private void showProductsView() {
//		workspace.getChildren().clear();
//
//		Label sectionHeader = new Label("Product Inventory Control");
//		sectionHeader.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
//
//		TableView<Product> productTable = new TableView<>();
//		productTable.setPrefHeight(380);
//
//		TableColumn<Product, Integer> idCol = new TableColumn<>("Product ID");
//		idCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
//
//		TableColumn<Product, String> nameCol = new TableColumn<>("Product Name");
//		nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
//
//		TableColumn<Product, String> barcodeCol = new TableColumn<>("Barcode");
//		barcodeCol.setCellValueFactory(new PropertyValueFactory<>("barcode"));
//
//		productTable.getColumns().addAll(idCol, nameCol, barcodeCol);
//
//		try {
//			productTable.setItems(FXCollections.observableArrayList(productDAO.getAllProducts()));
//		} catch (Exception e) {
//			System.err.println("Error pulling products: " + e.getMessage());
//		}
//
//		HBox actionToolbar = new HBox(15);
//		actionToolbar.setAlignment(Pos.CENTER_LEFT);
//		Button btnDelete = new Button("Remove Product");
//		btnDelete.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
//
//		btnDelete.setOnAction(e -> {
//			Product selected = productTable.getSelectionModel().getSelectedItem();
//			if (selected != null) {
//				try {
//					productDAO.deleteProduct(selected.getProductId());
//					showProductsView(); // Refresh the dynamic container view layout
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//		});
//
//		actionToolbar.getChildren().add(btnDelete);
//		workspace.getChildren().addAll(sectionHeader, productTable, actionToolbar);
//	}
//
//	private void showOrdersView() {
//		workspace.getChildren().clear();
//		Label sectionHeader = new Label("Orders Tracking");
//		sectionHeader.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
//
//		TableView<Order> orderTable = new TableView<>();
//		orderTable.setPrefHeight(380);
//
//		TableColumn<Order, Integer> idCol = new TableColumn<>("Order ID");
//		idCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));
//		TableColumn<Order, Double> priceCol = new TableColumn<>("Total Price");
//		priceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
//
//		orderTable.getColumns().addAll(idCol, priceCol);
//
//		try {
//			orderTable.setItems(FXCollections.observableArrayList(orderDAO.getAllOrders()));
//		} catch (Exception e) {
//			System.err.println("Error pulling orders: " + e.getMessage());
//		}
//
//		workspace.getChildren().addAll(sectionHeader, orderTable);
//	}
//
//	private void showAccountsView() {
//		workspace.getChildren().clear();
//		Label sectionHeader = new Label("Accounts Registry");
//		sectionHeader.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
//
//		TableView<Account> accountTable = new TableView<>();
//		accountTable.setPrefHeight(380);
//
//		TableColumn<Account, Integer> idCol = new TableColumn<>("Account ID");
//		idCol.setCellValueFactory(new PropertyValueFactory<>("accountId"));
//		TableColumn<Account, String> emailCol = new TableColumn<>("Email");
//		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//
//		accountTable.getColumns().addAll(idCol, emailCol);
//
//		try {
//			accountTable.setItems(FXCollections.observableArrayList(accountDAO.getAllAccounts()));
//		} catch (Exception e) {
//			System.err.println("Error pulling accounts: " + e.getMessage());
//		}
//
//		workspace.getChildren().addAll(sectionHeader, accountTable);
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//}