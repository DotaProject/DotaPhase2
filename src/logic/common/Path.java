package logic.common;

import java.util.ArrayList;

public class Path implements Components {

    private Lane Lane1;
    private Lane Lane2;
    private Lane Lane3;
    private Lane Lane4;
    private Lane Lane5;
    private Lane[] lanes = new Lane[5];
    private ArrayList<Cell> cells = new ArrayList<>();


    //constructor
    public Path(ArrayList<ArrayList<Cell>> path, Map map) {

        Lane1 = new Lane(path.get(0), map);
        Lane2 = new Lane(path.get(1), map);
        Lane3 = new Lane(path.get(2), map);
        Lane4 = new Lane(path.get(3), map);
        Lane5 = new Lane(path.get(4), map);
        lanes[0] = Lane1;
        lanes[1] = Lane2;
        lanes[2] = Lane3;
        lanes[3] = Lane4;
        lanes[4] = Lane5;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < path.get(i).size(); j++) {
                cells.add(path.get(i).get(j));

            }
        }
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

        if (other.cells.size() != this.cells.size()) {
            return false;
        }
        for (int i = 0; i < this.cells.size(); i++) {
            if (this.cells.get(i).getColumn() != other.cells.get(i).getColumn() ||
                    this.cells.get(i).getRow() != other.cells.get(i).getRow()) {
                return false;
            }
        }
        return true;
    }

    //getters and setters
    public Lane getLane5() {
        return Lane5;
    }

    public void setLane5(Lane lane5) {
        Lane5 = lane5;
    }

    public Lane getLane4() {
        return Lane4;
    }

    public void setLane4(Lane lane4) {
        Lane4 = lane4;
    }

    public Lane getLane3() {
        return Lane3;
    }

    public void setLane3(Lane lane3) {
        Lane3 = lane3;
    }

    public Lane getLane2() {
        return Lane2;
    }

    public void setLane2(Lane lane2) {
        Lane2 = lane2;
    }

    public Lane getLane1() {
        return Lane1;
    }

    public void setLane1(Lane lane1) {
        Lane1 = lane1;
    }



    public ArrayList<Cell> getCells() {
        return cells;
    }

    public Lane[] getLanes() {
        return lanes;
    }
}