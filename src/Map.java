import common.Cell;

import java.util.ArrayList;

public class Map {

    private int row;
    private int column;
    private ArrayList<ArrayList<Cell>>path1;
    private ArrayList<ArrayList<Cell>>path2;
    private ArrayList<ArrayList<Cell>>path3;
    private Cell[][] ancient1;//sakhteman asli sentinel
    private Cell[][] ancient2;//salhteman asli scourge
    private ArrayList<Cell[][]> barraks1;//3ta sakhteman niruye sentinel
    private ArrayList<Cell[][]> barraks2;//3ta sakhteman niruye scourge
    private ArrayList<Cell> goldMines;//manabe tala
    private int numberOfGoldMines;

    public Map(int row, int column, ArrayList<ArrayList<Cell>> path1, ArrayList<ArrayList<Cell>> path2, ArrayList<ArrayList<Cell>> path3,
               Cell[][] ancient1, Cell[][] ancient2, ArrayList<Cell[][]> barraks1, ArrayList<Cell[][]> barraks2,
               ArrayList<Cell> goldMines) {
        this.row = row;
        this.column = column;
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
        this.ancient1 = ancient1;
        this.ancient2 = ancient2;
        this.barraks1 = barraks1;
        this.barraks2 = barraks2;
        this.goldMines = goldMines;

        numberOfGoldMines = goldMines.size();
    }

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

    public ArrayList<ArrayList<Cell>> getPath1() {
        return path1;
    }

    public void setPath1(ArrayList<ArrayList<Cell>> path1) {
        this.path1 = path1;
    }

    public ArrayList<ArrayList<Cell>> getPath2() {
        return path2;
    }

    public void setPath2(ArrayList<ArrayList<Cell>> path2) {
        this.path2 = path2;
    }

    public ArrayList<ArrayList<Cell>> getPath3() {
        return path3;
    }

    public void setPath3(ArrayList<ArrayList<Cell>> path3) {
        this.path3 = path3;
    }

    public Cell[][] getAncient1() {
        return ancient1;
    }

    public void setAncient1(Cell[][] ancient1) {
        this.ancient1 = ancient1;
    }

    public Cell[][] getAncient2() {
        return ancient2;
    }

    public void setAncient2(Cell[][] ancient2) {
        this.ancient2 = ancient2;
    }

    public ArrayList<Cell[][]> getBarraks1() {
        return barraks1;
    }

    public void setBarraks1(ArrayList<Cell[][]> barraks1) {
        this.barraks1 = barraks1;
    }

    public ArrayList<Cell[][]> getBarraks2() {
        return barraks2;
    }

    public void setBarraks2(ArrayList<Cell[][]> barraks2) {
        this.barraks2 = barraks2;
    }

    public ArrayList<Cell> getGoldMines() {
        return goldMines;
    }

    public void setGoldMines(ArrayList<Cell> goldMines) {
        this.goldMines = goldMines;
    }

    public int getNumberOfGoldMines() {
        return numberOfGoldMines;
    }

    public void setNumberOfGoldMines(int numberOfGoldMines) {
        this.numberOfGoldMines = numberOfGoldMines;
    }
}
