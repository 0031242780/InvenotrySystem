//package general;
//
//import dao.AccountDAO;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import model.Account;
//
//public class LoginScreen {
//
//	private TextField emailField;
//	private PasswordField passwordField;
//	private Button loginButton;
//	private GridPane gp;
//	private Scene scene;
//	private VBox root;
//
//	public LoginScreen(Stage stage) {
//
//		emailField = new TextField();
//		passwordField = new PasswordField();
//		loginButton = new Button("Login");
//
//		emailField.setPromptText("Email");
//		passwordField.setPromptText("Password");
//
//		gp = new GridPane();
//		gp.setVgap(10);
//		gp.setHgap(10);
//		gp.setPadding(new Insets(20));
//		gp.setAlignment(Pos.CENTER);
//
//		gp.add(new Label("Email"), 0, 0);
//		gp.add(emailField, 1, 0);
//
//		gp.add(new Label("Password"), 0, 1);
//		gp.add(passwordField, 1, 1);
//
//		root = new VBox(15, gp, loginButton);
//		root.setAlignment(Pos.CENTER);
//		root.setPadding(new Insets(30));
//
//		loginButton.setOnAction(e -> {
//
//			try {
//
//				AccountDAO dao = new AccountDAO();
//				Account a = dao.login(emailField.getText(), passwordField.getText());
//
//				if (a != null) {
//					stage.setScene(new general.DashboardScreen(stage).getScene());
//				} else {
//					showAlert("Login Failed", "Wrong email or password", Alert.AlertType.ERROR);
//				}
//
//			} catch (Exception ex) {
//				showAlert("Error", ex.getMessage(), Alert.AlertType.ERROR);
//			}
//		});
//
//		scene = new Scene(root, 400, 250);
//		stage.setScene(scene);
//		stage.show();
//	}
//
//	private void showAlert(String title, String message, Alert.AlertType type) {
//
//		Alert alert = new Alert(type);
//		alert.setTitle(title);
//		alert.setHeaderText(null);
//		alert.setContentText(message);
//		alert.showAndWait();
//	}
//
//	public Scene getScene() {
//		return scene;
//	}
//}