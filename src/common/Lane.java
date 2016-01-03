package common;

import java.util.ArrayList;

public class Lane extends BoardComponents {

    //constructor
    public Lane(ArrayList<Cell> cell) {
        for (int i = 0; i < cell.size(); i++) {
            cells.add(cell.get(i));
        }
    }

}
