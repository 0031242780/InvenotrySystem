package Invontory;

import dao.CategoryDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Category;

public class CategoryInterface {

	private TableView<Category> table;

	// ================= PRODUCT (HIDDEN) =================
	// private TableView<Product> productTable;
	// private ObservableList<Product> allProducts;
	// private ProductDAO productDAO = new ProductDAO();
	// ===================================================

	private TableColumn<Category, Integer> idCol;
	private TableColumn<Category, String> nameCol, descCol;

	private Label lbItems, lbName, lbDesc;

	private TextField tfName, tfDesc;

	private Button addBtn, updateBtn, deleteBtn, clearBtn, refreshBtn;

	private VBox right;

	private GridPane gp;

	private ObservableList<Category> categories;

	private CategoryDAO categoryDAO = new CategoryDAO();

	private BorderPane root;

	public CategoryInterface() {

		table = new TableView<>();

		idCol = new TableColumn<>("Category ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("categoryId"));

		nameCol = new TableColumn<>("Category Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

		descCol = new TableColumn<>("Description");
		descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

		table.getColumns().addAll(idCol, nameCol, descCol);

		categories = getData();
		table.setItems(categories);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		VBox left = new VBox(10, table);
		left.setPadding(new Insets(10));
		VBox.setVgrow(table, Priority.ALWAYS);

		// RIGHT (FORM)
		lbItems = new Label("Count items: 0");

		lbName = new Label("Category Name");
		lbDesc = new Label("Description");

		tfName = new TextField();
		tfDesc = new TextField();

		gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		gp.add(lbName, 0, 0);
		gp.add(tfName, 1, 0);

		gp.add(lbDesc, 0, 1);
		gp.add(tfDesc, 1, 1);

		addBtn = new Button("Add");
		updateBtn = new Button("Update");
		deleteBtn = new Button("Delete");
		clearBtn = new Button("Clear");
		refreshBtn = new Button("Refresh");

		HBox btnBox = new HBox(10, addBtn, updateBtn, deleteBtn, clearBtn, refreshBtn);
		btnBox.setAlignment(Pos.CENTER);

		right = new VBox(15, lbItems, gp, btnBox);
		right.setPadding(new Insets(15));
		right.setAlignment(Pos.TOP_CENTER);

		// BORDER PANE
		root = new BorderPane();
		root.setCenter(left);
		root.setRight(right);

		setActions();

		table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
			if (selected != null) {
				tfName.setText(selected.getCategoryName());
				tfDesc.setText(selected.getDescription());
			}
		});
	}

	private ObservableList<Category> getData() {
		ObservableList<Category> list = FXCollections.observableArrayList();
		try {
			list.addAll(categoryDAO.getAll());
		} catch (Exception e) {
			showErrorAlert("Database Error", e.getMessage());
		}
		return list;
	}

	private void showErrorAlert(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void setActions() {

		addBtn.setOnAction(e -> {
			try {
				if (tfName.getText().isEmpty() || tfDesc.getText().isEmpty())
					return;

				Category c = new Category();
				c.setCategoryName(tfName.getText());
				c.setDescription(tfDesc.getText());

				categoryDAO.insert(c);

				categories.setAll(getData());
				table.refresh();

				clearFields();

			} catch (Exception ex) {
				showErrorAlert("Error", ex.getMessage());
			}
		});

		updateBtn.setOnAction(e -> {
			Category selected = table.getSelectionModel().getSelectedItem();
			if (selected == null)
				return;

			try {
				selected.setCategoryName(tfName.getText());
				selected.setDescription(tfDesc.getText());

				categoryDAO.update(selected);
				table.refresh();

			} catch (Exception ex) {
				showErrorAlert("Error", ex.getMessage());
			}
		});

		deleteBtn.setOnAction(e -> {
			Category selected = table.getSelectionModel().getSelectedItem();
			if (selected == null)
				return;

			try {
				categoryDAO.delete(selected.getCategoryId());
				categories.remove(selected);
				table.refresh();
				clearFields();

			} catch (Exception ex) {
				showErrorAlert("Error", ex.getMessage());
			}
		});

		refreshBtn.setOnAction(e -> {
			categories.setAll(getData());
			table.refresh();
		});

		clearBtn.setOnAction(e -> clearFields());
	}

	private void clearFields() {
		tfName.clear();
		tfDesc.clear();
		table.getSelectionModel().clearSelection();
	}

	public BorderPane getView() {
		return root;
	}
}