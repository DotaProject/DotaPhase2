package common;

public class Barraks extends GameComponents {

    private Path path;

    {
        health = 5000;
    }

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

    //getter and setter
    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
