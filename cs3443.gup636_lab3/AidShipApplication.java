package edu.utsa.cs3443.gup636_lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AidShipApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AidShipApplication.class.getResource("/edu/utsa/cs3443/gup636_lab3/layouts/main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1450, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        System.out.println("Hello! I dont know");
    }
}
