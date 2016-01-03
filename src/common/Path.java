package common;

import java.util.ArrayList;

public class Path extends BoardComponents {

    private Lane Lane1;
    private Lane Lane2;
    private Lane Lane3;
    private Lane Lane4;
    private Lane Lane5;

    //constructor
    public Path(ArrayList<ArrayList<Cell>> path) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < path.get(i).size(); j++) {
                cells.add(path.get(i).get(j));
            }
        }
        Lane1 = new Lane(path.get(0));
        Lane2 = new Lane(path.get(1));
        Lane3 = new Lane(path.get(2));
        Lane4 = new Lane(path.get(3));
        Lane5 = new Lane(path.get(4));
    }


    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Path other = (Path) obj;

        if (this.cells.size() != other.cells.size()) {
            return false;
        }
        for (int i = 0; i < this.cells.size(); i++) {
            if (this.cells.get(i).getColumn() != other.cells.get(i).getColumn()) {
                return false;
            }
            if (this.cells.get(i).getRow() != other.cells.get(i).getRow()) {
                return false;
            }
        }
        return true;
    }
}
