package ui;

import java.util.List;

import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product;

public class ProductController {

	@FXML
	private TextField barcodeField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField descField;

	@FXML
	private TextField categoryField;

	@FXML
	private CheckBox trendCheck;

	@FXML
	private TableView<Product> table;

	@FXML
	private TableColumn<Product, Integer> idCol;

	@FXML
	private TableColumn<Product, String> nameCol;

	@FXML
	private TableColumn<Product, String> barcodeCol;

	@FXML
	private TableColumn<Product, Integer> categoryCol;

	private ProductDAO dao = new ProductDAO();

	// runs automatically after FXML loads
	@FXML
	public void initialize() {

		// connect table columns with Product getters

		idCol.setCellValueFactory(new PropertyValueFactory<>("productId"));

		nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));

		barcodeCol.setCellValueFactory(new PropertyValueFactory<>("barcode"));

		categoryCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));

		loadProducts();
	}

	@FXML
	public void addProduct() {

		try {

			Product p = new Product();

			p.setBarcode(barcodeField.getText());

			p.setProductName(nameField.getText());

			p.setDescription(descField.getText());

			p.setCategoryId(Integer.parseInt(categoryField.getText()));

			p.setTrend(trendCheck.isSelected());

			p.setPhoto("default.jpg");

			dao.insertProduct(p);

			showMessage("Product inserted successfully!");

			clearFields();

			loadProducts();

		} catch (Exception e) {

			showError(e.getMessage());
		}
	}

	public void loadProducts() {

		try {

			List<Product> products = dao.getAllProducts();

			ObservableList<Product> list = FXCollections.observableArrayList(products);

			table.setItems(list);

		} catch (Exception e) {

			showError(e.getMessage());
		}
	}

	public void clearFields() {

		barcodeField.clear();

		nameField.clear();

		descField.clear();

		categoryField.clear();

		trendCheck.setSelected(false);
	}

	public void showMessage(String msg) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		alert.setContentText(msg);

		alert.showAndWait();
	}

	public void showError(String msg) {

		Alert alert = new Alert(Alert.AlertType.ERROR);

		alert.setContentText(msg);

		alert.showAndWait();
	}
}