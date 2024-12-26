package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Load both forms
        Pane serverForm = FXMLLoader.load(this.getClass().getResource("/view/server_form.fxml"));
        Pane clientForm = FXMLLoader.load(this.getClass().getResource("/view/client_form.fxml"));

        // Create a GridPane layout for side-by-side arrangement
        GridPane gridPane = new GridPane();
        gridPane.add(serverForm, 0, 0); // Add server form to column 0
        gridPane.add(clientForm, 1, 0); // Add client form to column 1

        // Set the spacing between forms
        gridPane.setHgap(20); // Horizontal gap between forms

        // Create the main scene with the GridPane layout
        Scene scene = new Scene(gridPane);

        // Configure the primary stage
        stage.setScene(scene);
        stage.setTitle("Server and Client Forms");
        stage.centerOnScreen();
        stage.show();
    }
}
