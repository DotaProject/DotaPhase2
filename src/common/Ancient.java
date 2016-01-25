package common;

public class Ancient extends GameComponents {


    private int treasury = 5000;
    private int numberOfGoldMines = 0;
    private Cell[][] cell;

    {
        health = 10000;
        isAlive = true;
    }



    //constructor
    public Ancient(int teamID, Cell[][] cell, Map map) {
        this.teamID = teamID;
        this.cell = cell;

        for (int i = 0; i <5 ; i++) {
            for (int j = 0; j < 5; j++) {
                cells.add(cell[i][j]);
                if(teamID == 0){
                    cells.get(i).ancientSentinel.add(this);
                    map.getGameBoard()[cell[i][j].getRow()][cell[i][j].getColumn()].ancientSentinel.add(this);
                }
                if(teamID == 1){
                    cells.get(i).ancientScourge.add(this);
                    map.getGameBoard()[cell[i][j].getRow()][cell[i][j].getColumn()].ancientScourge.add(this);
                }
            }
        }
    }

    //We should call this function per second
    public void addTimeMoney(){
        this.treasury += 10;
    }

    //We should call this function per second
    public void addGoldMineMoney(){
        this.treasury += numberOfGoldMines*100;
    }

    //getters and setters
    public int getNumberOfGoldMines() {
        return numberOfGoldMines;
    }

    public void setNumberOfGoldMines(int numberOfGoldMines) {
        this.numberOfGoldMines = numberOfGoldMines;
    }

    public int getTreasury() {
        return treasury;
    }

    public void setTreasury(int treasury) {
        this.treasury = treasury;
    }

    public Cell[][] getCell() {
        return cell;
    }

    public void setCell(Cell[][] cell) {
        this.cell = cell;
    }
}