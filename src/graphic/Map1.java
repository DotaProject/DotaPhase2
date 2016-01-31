package graphic;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import logic.judge.Judge;
import logic.judge.MapReader;

import java.io.IOException;

public class Map1 {

    private MapReader map;
    private Judge judge;
    private Button[][] buttons;
    private Scene scene;


    public Map1() throws IOException {

        Pane root = FXMLLoader.load(getClass().getResource("view/StackPaneSentinel.fxml"));
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(1));
        gridPane.setLayoutY(0);
        gridPane.setLayoutX(270);

        this.map = new MapReader();
        map.init1();
        this.judge = new Judge();
        judge.loadMap(map.getColumns(), map.getRows(), map.getPath1(), map.getPath2(), map.getPath3(),
                map.getAncient1(), map.getAncient2(), map.getBarracks1(), map.getBarracks2(), map.getGoldMines());

        this.buttons = new Button[map.getRows()][map.getColumns()];

        for (int i = 0; i < map.getColumns(); i++) {
            gridPane.getRowConstraints().add(new RowConstraints(15.5));
            for (int j = 0; j < map.getColumns(); j++) {
                if (j == 0) {
                    gridPane.getColumnConstraints().add(new ColumnConstraints(15.5));
                }
                buttons[i][j] = new Button("");
                buttons[i][j].setFocusTraversable(false);
                buttons[i][j].setMinHeight(5);
                buttons[i][j].setMinWidth(5);
                buttons[i][j].setMaxHeight(Integer.MAX_VALUE);
                buttons[i][j].setMaxWidth(Integer.MAX_VALUE);
                gridPane.add(buttons[i][j], i, j);
            }
        }
        setPicture();
        root.getChildren().add(gridPane);
        scene = new Scene(root);
    }

    private void setPicture() {
        logic.common.Cell[][] board = judge.getEngine().map.getGameBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].ancientScourge.size() != 0) {
                    String image = getClass().getResource("images/Scourge/Ancient.jpg").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].ancientSentinel.size() != 0) {
                    String image = getClass().getResource("images/Sentinel/Ancient.jpg").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;

                }
                if (board[i][j].barraksScourge.size() != 0) {
                    String image = getClass().getResource("images/Scourge/Barracks.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].barraksSentinel.size() != 0) {
                    String image = getClass().getResource("images/Sentinel/Barracks.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].tiny.size() != 0) {
                    String image = getClass().getResource("images/Sentinel/Tiny.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].venomancer.size() != 0) {
                    String image = getClass().getResource("images/Scourge/Venomancer.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].attackerScourgeInfantry.size() != 0) {
                    String image = getClass().getResource("images/Scourge/AttackForceInfantry.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].attackerScourgeTank.size() != 0) {
                    String image = getClass().getResource("images/Scourge/AttackForceTank.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].attackerSentinelInfantry.size() != 0) {
                    String image = getClass().getResource("images/Sentinel/AttackForceInfantry.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].attackerSentinelTank.size() != 0) {
                    String image = getClass().getResource("images/Sentinel/AttackForceTank.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].towerBlack.size() != 0) {
                    String image = getClass().getResource("images/black.jpg").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].towerFire.size() != 0) {
                    String image = getClass().getResource("images/fire.jpg").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].towerPoison.size() != 0) {
                    String image = getClass().getResource("images/poison.jpg").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;

                }
                if (board[i][j].towerStone.size() != 0) {
                    String image = getClass().getResource("images/stone.jpg").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;

                }
                if (board[i][j].goldMines.size() != 0) {
                    buttons[i][j].setStyle("-fx-base: yellow;");
                    String image = getClass().getResource("images/money.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                    continue;
                }
                if (board[i][j].lanes.size() != 0) {
                    String image = getClass().getResource("images/lane.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");

                } else {
                    String image = getClass().getResource("images/grass.png").toExternalForm();
                    buttons[i][j].setStyle("-fx-background-image: url('" + image + "'); " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: stretch;" + "-fx-background-size: 16 16");
                }
            }
        }
    }

    public Scene getScene() {
        return scene;
    }


    public Button[][] getButtons() {
        return buttons;
    }

    public MapReader getMap() {
        return map;
    }

    public Judge getJudge() {
        return judge;
    }
}
