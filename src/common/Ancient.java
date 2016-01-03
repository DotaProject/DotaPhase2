package common;

public class Ancient extends GameComponents {


    private int treasury = 5000;
    private int  numberOfGoldMines = 0;

    {
        health = 10000;
        isAlive = true;
    }

    //constructor
    public Ancient(int team_ID, Cell[][] cell) {
        this.teamID = team_ID;
        for (int i = 0; i <5 ; i++) {
            for (int j = 0; j < 5; j++) {
               cells.add(cell[i][j]);

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
}
