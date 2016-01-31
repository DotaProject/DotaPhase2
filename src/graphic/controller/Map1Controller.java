package graphic.controller;

import graphic.Map1;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Map1Controller implements Initializable{

    private Map1 map1;
    private Button[][] buttons;
    private EventHandler<KeyEvent> keyListener;

    @FXML private Button startButton;
    @FXML private Button pauseButton;
    @FXML private TextField chatBox;
    @FXML private TextField time;
    @FXML private TextField coins;
    @FXML private Button infantryAttacker;
    @FXML private Button tankAttacker;
    @FXML private Button stoneTower;
    @FXML private Button fireTower;
    @FXML private Button towerRangeUpgrade;
    @FXML private Button towerPowerUpgrade;
    @FXML private Button attackerRangeUpgrade;
    @FXML private Button attackerPowerUpgrade;

    private ImageView infantryAttackerImage;
    private ImageView tankAttackerImage;
    private ImageView stoneTowerImage;
    private ImageView fireTowerImage;
    private ImageView tinyImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.map1 = new Map1();
            this.infantryAttackerImage = new ImageView(getClass().getResource("images/Sentinel/AttackForceInfantry.png").toString());
            this.tankAttackerImage = new ImageView(getClass().getResource("images/Sentinel/AttackForceTank.png").toString());
            this.stoneTowerImage= new ImageView(getClass().getResource("images/stone.jpg").toString());
            this.fireTowerImage = new ImageView(getClass().getResource("images/fire.jpg").toString());
            this.tinyImage = new ImageView(getClass().getResource("images/Tiny.png").toString());
            this.buttons = map1.getButtons();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO ye hero neshun dade beshe avale bazi tu sakhtemun asli

        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().startTimer();
            }
        });
        pauseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().pauseTimer();
            }
        });

        infantryAttacker.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().createAttacker();
                //TODO jash malum she

            }
        });

        tankAttacker.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().createAttacker();
                //TODO jash
            }
        });

        fireTower.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().createTower();
                //TODO
            }
        });

        stoneTower.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().createTower();
                //TODO
            }
        });

        towerPowerUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().purchaseTowerPowerup();
                //TODO
            }
        });

        towerRangeUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().purchaseTowerPowerup();
                //TODO
            }
        });

        attackerPowerUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().purchaseAttackersPowerup();
                //TODO
            }
        });

        attackerRangeUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map1.getJudge().purchaseAttackersPowerup();
                //TODO
            }
        });

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                final int a = i;
                final int b = j;
                buttons[i][j].setOnAction(event -> buttonsHandle(event,a,b));
            }
        }

        this.keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN ||
                        event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                    try {
                        map1.getJudge().heroMove();
                        moveHeroOnScreen();
                        //TODO
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    event.consume();
                }
            }
        };

        setCoins();
        setTime();
    }

    public void buttonsHandle(ActionEvent e , int i , int j){
        //TODO
       // if (map1.getJudge().getEngine().map) //age game board khali bud ke hichi vagarna hamle kone)
        logic.common.Cell target = new logic.common.Cell();
        target.setRow(i);
        target.setColumn(j);
        map1.getJudge().heroAttack(,);
    }

    public void setCoins(){
        //in bara team sentinel e
        coins.setText( this.map1.getJudge().getMoney(0) + "" );
    }

    public void setTime(){
       time.setText(this.map1.getJudge().getTime() + "");
    }

    public void moveAttackerOnScreen(){
        //TODO ke ba attackesham hast mese event esh
    }

    public void moveHeroOnScreen(){

    }

    public void changeMapOnRange(){
        //TODO
    }

}
