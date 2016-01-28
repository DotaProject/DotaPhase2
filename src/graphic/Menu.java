package graphic;

import graphic.controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {

    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        menuWindow();

    }

    public void menuWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Menu.class.getResource("view/MenuWindowController.fxml"));
            AnchorPane pane = loader.load();


            MenuController menuController = loader.getController();

            Scene scene = new Scene(pane);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
