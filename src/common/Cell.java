package common;

import java.util.ArrayList;

public class Cell {

    private int row;
    private int column;

    //getter and setter
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        final Cell other = (Cell) obj;
        if(other.getRow() != this.getRow() || other.getColumn() != this.getColumn()){
            return false;
        }
        return true;
    }
}
