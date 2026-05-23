package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFx extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // FIXED: Using an absolute path check to ensure your changes are picked up
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Main.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1100, 680);
        stage.setTitle("Mega PC Market - Dashboard View");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}