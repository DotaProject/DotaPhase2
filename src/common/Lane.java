package common;

import java.util.ArrayList;

public class Lane implements Components {


    private ArrayList<Cell> cells = new ArrayList<>();



    //constructor
    public Lane(ArrayList<Cell> cell, Map map) {

        for (int i = 0; i < cell.size(); i++) {
            this.cells.add(cell.get(i));
            this.cells.get(i).lanes.add(this);
            map.getGameBoard()[cell.get(i).getRow()][cell.get(i).getColumn()].lanes.add(this);
        }
    }

    //setters and getters
    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }
}
