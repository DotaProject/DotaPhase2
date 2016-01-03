package common;

public class Barraks extends GameComponents {

    {
        isAlive = true;

        health = 5000;
    }

    public Path path;

    //constructor
    public Barraks(int teamID, Cell[][] cell, Path path) {

        this.path = path;
        this.teamID = teamID;

        for (int i = 0; i < cell.length; i++) {
            for (int j = 0; j < cell[0].length; j++) {
                cells.add(cell[i][j]);
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
