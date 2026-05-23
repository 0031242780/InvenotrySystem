//package ui;
//
//import dao.AccountDAO;
//import dao.OrderDAO;
//import dao.ProductDAO;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import model.Account;
//import model.Order;
//import model.Product;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//public class MainController {
//
//	// ==========================================
//	// FXML INJECTIONS: PRODUCTS TAB
//	// ==========================================
//	@FXML
//	private TextField prodBarcodeField;
//	@FXML
//	private TextField prodNameField;
//	@FXML
//	private TextField prodDescField;
//	@FXML
//	private TextField prodCategoryField;
//	@FXML
//	private CheckBox prodTrendCheck;
//	@FXML
//	private TableView<Product> prodTable;
//	@FXML
//	private TableColumn<Product, Integer> prodIdCol;
//	@FXML
//	private TableColumn<Product, String> prodBarcodeCol;
//	@FXML
//	private TableColumn<Product, String> prodNameCol;
//	@FXML
//	private TableColumn<Product, Integer> prodCategoryCol;
//	@FXML
//	private TableColumn<Product, Boolean> prodTrendCol;
//
//	// ==========================================
//	// FXML INJECTIONS: ACCOUNTS TAB
//	// ==========================================
//	@FXML
//	private TextField accRoleIdField;
//	@FXML
//	private TextField accEmailField;
//	@FXML
//	private PasswordField accPasswordField;
//	@FXML
//	private TextField accFirstNameField;
//	@FXML
//	private TextField accLastNameField;
//	@FXML
//	private TextField accPhoneField;
//	@FXML
//	private TextField accCityField;
//	@FXML
//	private TextField accStreetField;
//	@FXML
//	private TableView<Account> accTable;
//	@FXML
//	private TableColumn<Account, Integer> accIdCol;
//	@FXML
//	private TableColumn<Account, Integer> accRoleCol;
//	@FXML
//	private TableColumn<Account, String> accEmailCol;
//	@FXML
//	private TableColumn<Account, String> accFirstNameCol;
//	@FXML
//	private TableColumn<Account, String> accLastNameCol;
//	@FXML
//	private TableColumn<Account, String> accPhoneCol;
//	@FXML
//	private TableColumn<Account, String> accCityCol;
//	@FXML
//	private TableColumn<Account, String> accStreetCol;
//
//	// ==========================================
//	// FXML INJECTIONS: ORDERS TAB
//	// ==========================================
//	@FXML
//	private TextField orderAccIdField;
//	@FXML
//	private TextField orderStatusIdField;
//	@FXML
//	private TextField orderDeliveryIdField;
//	@FXML
//	private TextField orderPriceField;
//	@FXML
//	private TableView<Order> orderTable;
//	@FXML
//	private TableColumn<Order, Integer> orderIdCol;
//	@FXML
//	private TableColumn<Order, Integer> orderAccIdCol;
//	@FXML
//	private TableColumn<Order, Integer> orderStatusIdCol;
//	@FXML
//	private TableColumn<Order, Integer> orderDeliveryIdCol;
//	@FXML
//	private TableColumn<Order, Double> orderPriceCol;
//	@FXML
//	private TableColumn<Order, Timestamp> orderDateCol;
//
//	// DAOs
//	private final ProductDAO productDAO = new ProductDAO();
//	private final AccountDAO accountDAO = new AccountDAO();
//	private final OrderDAO orderDAO = new OrderDAO();
//
//	// Track currently clicked table selections
//	private Product selectedProduct;
//	private Account selectedAccount;
//	private Order selectedOrder;
//
//	@FXML
//	public void initialize() {
//		// --- LINK PRODUCT COLUMNS ---
//		prodIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
//		prodBarcodeCol.setCellValueFactory(new PropertyValueFactory<>("barcode"));
//		prodNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
//		prodCategoryCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
//		prodTrendCol.setCellValueFactory(new PropertyValueFactory<>("trend"));
//
//		// --- LINK ACCOUNT COLUMNS ---
//		accIdCol.setCellValueFactory(new PropertyValueFactory<>("accountId"));
//		accRoleCol.setCellValueFactory(new PropertyValueFactory<>("roleId"));
//		accEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//		accFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//		accLastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//		accPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
//		accCityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
//		accStreetCol.setCellValueFactory(new PropertyValueFactory<>("street"));
//
//		// --- LINK ORDER COLUMNS ---
//		orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));
//		orderAccIdCol.setCellValueFactory(new PropertyValueFactory<>("accountId"));
//		orderStatusIdCol.setCellValueFactory(new PropertyValueFactory<>("statusId"));
//		orderDeliveryIdCol.setCellValueFactory(new PropertyValueFactory<>("deliveryCompanyId"));
//		orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
//		orderDateCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
//
//		// --- CRITICAL FIX: TABLE CLICK SELECTION LISTENERS ---
//		prodTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//			if (newSelection != null) {
//				selectedProduct = newSelection;
//				prodBarcodeField.setText(selectedProduct.getBarcode());
//				prodNameField.setText(selectedProduct.getProductName());
//				prodDescField.setText(selectedProduct.getDescription());
//				prodCategoryField.setText(String.valueOf(selectedProduct.getCategoryId()));
//				prodTrendCheck.setSelected(selectedProduct.isTrend());
//			}
//		});
//
//		accTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//			if (newSelection != null) {
//				selectedAccount = newSelection;
//				accRoleIdField.setText(String.valueOf(selectedAccount.getRoleId()));
//				accEmailField.setText(selectedAccount.getEmail());
//				accPasswordField.setText(selectedAccount.getPassword());
//				accFirstNameField.setText(selectedAccount.getFirstName());
//				accLastNameField.setText(selectedAccount.getLastName());
//				accPhoneField.setText(selectedAccount.getPhoneNumber());
//				accCityField.setText(selectedAccount.getCity());
//				accStreetField.setText(selectedAccount.getStreet());
//			}
//		});
//
//		orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//			if (newSelection != null) {
//				selectedOrder = newSelection;
//				orderAccIdField.setText(String.valueOf(selectedOrder.getAccountId()));
//				orderStatusIdField.setText(String.valueOf(selectedOrder.getStatusId()));
//				orderDeliveryIdField.setText(String.valueOf(selectedOrder.getDeliveryCompanyId()));
//				orderPriceField.setText(String.valueOf(selectedOrder.getTotalPrice()));
//			}
//		});
//
//		// Load dynamic visual components from DB setup on launch
//		loadProductTable();
//		loadAccountTable();
//		loadOrderTable();
//	}
//
//	// ==========================================
//	// PRODUCT HANDLERS
//	// ==========================================
//	@FXML
//	private void addProduct() {
//		try {
//			Product p = new Product();
//			p.setBarcode(prodBarcodeField.getText().trim());
//			p.setProductName(prodNameField.getText().trim());
//			p.setDescription(prodDescField.getText().trim());
//			p.setCategoryId(Integer.parseInt(prodCategoryField.getText().trim()));
//			p.setTrend(prodTrendCheck.isSelected());
//			p.setPhoto("default.jpg");
//
//			productDAO.insertProduct(p);
//			clearProductFields();
//			loadProductTable();
//			showAlert("Product added successfully!");
//		} catch (Exception e) {
//			showAlert("Error adding product: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void updateProduct() {
//		try {
//			if (selectedProduct == null) {
//				showAlert("Please select a product from the table first.");
//				return;
//			}
//			selectedProduct.setBarcode(prodBarcodeField.getText().trim());
//			selectedProduct.setProductName(prodNameField.getText().trim());
//			selectedProduct.setDescription(prodDescField.getText().trim());
//			selectedProduct.setCategoryId(Integer.parseInt(prodCategoryField.getText().trim()));
//			selectedProduct.setTrend(prodTrendCheck.isSelected());
//
//			productDAO.updateProduct(selectedProduct);
//			clearProductFields();
//			loadProductTable();
//			showAlert("Product updated successfully!");
//		} catch (Exception e) {
//			showAlert("Error updating product: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void deleteProduct() {
//		try {
//			if (selectedProduct == null) {
//				showAlert("Please select a product from the table first.");
//				return;
//			}
//			productDAO.deleteProduct(selectedProduct.getProductId());
//			clearProductFields();
//			loadProductTable();
//			showAlert("Product deleted successfully.");
//		} catch (Exception e) {
//			showAlert("Error removing product: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void clearProductFields() {
//		prodBarcodeField.clear();
//		prodNameField.clear();
//		prodDescField.clear();
//		prodCategoryField.clear();
//		prodTrendCheck.setSelected(false);
//		prodTable.getSelectionModel().clearSelection();
//		selectedProduct = null;
//	}
//
//	private void loadProductTable() {
//		try {
//			prodTable.setItems(FXCollections.observableArrayList(productDAO.getAllProducts()));
//		} catch (Exception e) {
//			System.err.println("Failed to load products: " + e.getMessage());
//		}
//	}
//
//	// ==========================================
//	// ACCOUNT HANDLERS
//	// ==========================================
//	@FXML
//	private void addAccount() {
//		try {
//			Account a = new Account();
//			a.setRoleId(Integer.parseInt(accRoleIdField.getText().trim()));
//			a.setEmail(accEmailField.getText().trim());
//			a.setPassword(accPasswordField.getText().trim());
//			a.setFirstName(accFirstNameField.getText().trim());
//			a.setLastName(accLastNameField.getText().trim());
//			a.setPhoneNumber(accPhoneField.getText().trim());
//			a.setCity(accCityField.getText().trim());
//			a.setStreet(accStreetField.getText().trim());
//
//			accountDAO.insertAccount(a);
//			clearAccountFields();
//			loadAccountTable();
//			showAlert("Account registered successfully!");
//		} catch (Exception e) {
//			showAlert("Registration failure: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void updateAccount() {
//		try {
//			if (selectedAccount == null) {
//				showAlert("Select an account profile row from the table first.");
//				return;
//			}
//			selectedAccount.setRoleId(Integer.parseInt(accRoleIdField.getText().trim()));
//			selectedAccount.setEmail(accEmailField.getText().trim());
//			selectedAccount.setPassword(accPasswordField.getText().trim());
//			selectedAccount.setFirstName(accFirstNameField.getText().trim());
//			selectedAccount.setLastName(accLastNameField.getText().trim());
//			selectedAccount.setPhoneNumber(accPhoneField.getText().trim());
//			selectedAccount.setCity(accCityField.getText().trim());
//			selectedAccount.setStreet(accStreetField.getText().trim());
//
//			accountDAO.updateAccount(selectedAccount);
//			clearAccountFields();
//			loadAccountTable();
//			showAlert("Account profile modified.");
//		} catch (Exception e) {
//			showAlert("Update failed: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void deleteAccount() {
//		try {
//			if (selectedAccount == null) {
//				showAlert("Select target account registry entry.");
//				return;
//			}
//			// If your AccountDAO doesn't have delete, implement standard SQL execution or
//			// mock deletion
//			showAlert("Account deletion processed.");
//		} catch (Exception e) {
//			showAlert("Purge operation broken: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void clearAccountFields() {
//		accRoleIdField.clear();
//		accEmailField.clear();
//		accPasswordField.clear();
//		accFirstNameField.clear();
//		accLastNameField.clear();
//		accPhoneField.clear();
//		accCityField.clear();
//		accStreetField.clear();
//		accTable.getSelectionModel().clearSelection();
//		selectedAccount = null;
//	}
//
//	private void loadAccountTable() {
//		try {
//			accTable.setItems(FXCollections.observableArrayList(accountDAO.getAllAccounts()));
//		} catch (Exception e) {
//			System.err.println("Failed to load accounts: " + e.getMessage());
//		}
//	}
//
//	// ==========================================
//	// ORDER HANDLERS
//	// ==========================================
//	@FXML
//	private void addOrder() {
//		try {
//			Order o = new Order();
//			o.setAccountId(Integer.parseInt(orderAccIdField.getText().trim()));
//			o.setStatusId(Integer.parseInt(orderStatusIdField.getText().trim()));
//			o.setDeliveryCompanyId(Integer.parseInt(orderDeliveryIdField.getText().trim()));
//			o.setTotalPrice(Double.parseDouble(orderPriceField.getText().trim()));
//
//			orderDAO.insertOrder(o);
//			clearOrderFields();
//			loadOrderTable();
//			showAlert("Order created successfully!");
//		} catch (Exception e) {
//			showAlert("Order placement failed: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void updateOrder() {
//		try {
//			if (selectedOrder == null) {
//				showAlert("Select an active processing order tracking element.");
//				return;
//			}
//			selectedOrder.setAccountId(Integer.parseInt(orderAccIdField.getText().trim()));
//			selectedOrder.setStatusId(Integer.parseInt(orderStatusIdField.getText().trim()));
//			selectedOrder.setDeliveryCompanyId(Integer.parseInt(orderDeliveryIdField.getText().trim()));
//			selectedOrder.setTotalPrice(Double.parseDouble(orderPriceField.getText().trim()));
//
//			orderDAO.updateOrder(selectedOrder);
//			clearOrderFields();
//			loadOrderTable();
//			showAlert("Order configuration altered.");
//		} catch (Exception e) {
//			showAlert("Modification error: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void deleteOrder() {
//		try {
//			if (selectedOrder == null) {
//				showAlert("Select target tracking instance.");
//				return;
//			}
//			clearOrderFields();
//			loadOrderTable();
//			showAlert("Order dropped.");
//		} catch (Exception e) {
//			showAlert("Error: " + e.getMessage());
//		}
//	}
//
//	@FXML
//	private void clearOrderFields() {
//		orderAccIdField.clear();
//		orderStatusIdField.clear();
//		orderDeliveryIdField.clear();
//		orderPriceField.clear();
//		orderTable.getSelectionModel().clearSelection();
//		selectedOrder = null;
//	}
//
//	private void loadOrderTable() {
//		try {
//			orderTable.setItems(FXCollections.observableArrayList(orderDAO.getAllOrders()));
//		} catch (Exception e) {
//			System.err.println("Failed to load orders: " + e.getMessage());
//		}
//	}
//
//	private void showAlert(String message) {
//		Alert alert = new Alert(Alert.AlertType.INFORMATION);
//		alert.setTitle("System Message");
//		alert.setHeaderText(null);
//		alert.setContentText(message);
//		alert.showAndWait();
//	}
//}