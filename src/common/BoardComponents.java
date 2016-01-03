package common;

import java.util.ArrayList;

public class BoardComponents {

    protected ArrayList<Cell> cells = new ArrayList<>();

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }
}
