package common;

import java.util.ArrayList;

public class Lane implements Components {


    private ArrayList<Cell> cells = new ArrayList<>();

    //constructor
    public Lane(ArrayList<Cell> cell) {

        for (int i = 0; i < cell.size(); i++) {
            cells.add(cell.get(i));
        }

    }
}
