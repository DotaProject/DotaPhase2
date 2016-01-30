package logic.common;

public class Barraks extends GameComponents {

    public Path path;

    {
        isAlive = true;
        health = 5000;
    }

    //constructor
    public Barraks(int teamID, Cell[][] cell, Path path, Map map) {

        this.path = path;
        this.teamID = teamID;

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cells.add(cell[i][j]);
                if(teamID == 0){
                    cells.get(i).barraksSentinel.add(this);
                    map.getGameBoard()[cell[i][j].getRow()][cell[i][j].getColumn()].barraksSentinel.add(this);
                }
                if(teamID == 1){
                    cells.get(i).barraksScourge.add(this);
                    map.getGameBoard()[cell[i][j].getRow()][cell[i][j].getColumn()].barraksScourge.add(this);
                }
            }
        }

    }

    //getters and setters
    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}