package graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application {

<<<<<<< Updated upstream
    public static void main(String[] args) {
        launch(args);
    }

=======
>>>>>>> Stashed changes
    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("view/MenuWindow.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("DOTA ALLSTARS");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

<<<<<<< Updated upstream
=======

    public static void main(String[] args) {
        launch(args);
    }

>>>>>>> Stashed changes
}
