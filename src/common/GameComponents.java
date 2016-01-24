package common;

import java.util.ArrayList;

public class GameComponents implements Components {

    protected ArrayList<Cell> cells = new ArrayList<>();
    protected int health = 0;
    protected int teamID;
    protected boolean isAlive;


    //getters and setters
    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
