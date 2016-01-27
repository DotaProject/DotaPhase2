package graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Map1 extends Application {

    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        map1Window();

    }

    public void map1Window(){

        try {
            FXMLLoader loader = new FXMLLoader(Menu.class.getResource("/view/Map1WindowController.fxml"));
            AnchorPane pane = loader.load();


            Map1Controller menuController = loader.getController();

            Scene scene = new Scene(pane, Color.BEIGE);

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
