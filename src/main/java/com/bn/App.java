package com.bn;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new CustomerController().getView()); // Reference a controller (follows the MVC protocol)
        stage.setScene(scene);
        stage.show();
    }
}