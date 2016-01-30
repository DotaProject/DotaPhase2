package graphic.controller;


import graphic.Map1;
import graphic.Map2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private String team = "empty";

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button map1;

    private ObservableList<String> list = FXCollections.observableArrayList("Sentinel", "Scourge");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(list);
    }

    public void selectedTeam(ActionEvent event) {
        team = comboBox.getValue();
    }

    public String getTeam() {
        return team;
    }

    public void map1Clicked(ActionEvent e) throws Exception {

        if (!team.equals("empty")) {
            Stage previousStage = (Stage) map1.getScene().getWindow();
            Map1 map1 = new Map1();
            Stage primaryStage = new Stage();
            primaryStage.setScene(map1.getScene());
            primaryStage.setTitle("DOTA ALLSTARS");
            previousStage.close();
            primaryStage.show();

        }

    }

    public void map2Clicked(ActionEvent e) throws Exception {
        if (!team.equals("empty")) {
            Map2.launch();
        }

    }
}