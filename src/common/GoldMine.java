package common;

public class GoldMine extends GameComponents {

    private boolean hasOwner = false;
    private int teamID;



    //constructor
    public GoldMine(Cell cell, Map map){
        this.isAlive = true;
        this.cells.add(cell);
        cells.get(0).goldMines.add(this);
        map.getGameBoard()[cell.getRow()][cell.getColumn()].goldMines.add(this);
    }


    //getter and setter
    public boolean isHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }
}