package common;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    private int row;
    private int column;
    private Path path1;
    private Path path2;
    private Path path3;
    private ArrayList<GoldMine> goldMines;//manabe tala
    private int numberOfGoldMines;

    private Cell[][] gameBoard;
    private HashMap<Cell, ArrayList<Components>> gameBoardComponents = new HashMap<>();

    private Lane[] path1Lanes = new Lane[5];
    private Lane[] path2Lanes = new Lane[5];
    private Lane[] path3Lanes = new Lane[5];

    private Ancient[] ancient1 = new Ancient[1];
    private Ancient[] ancient2 = new Ancient[1];

    private Barraks[] barraks1 = new Barraks[3];
    private Barraks[] barraks2 = new Barraks[3];

    //constructor
    public Map(int row, int column, ArrayList<ArrayList<Cell>> path1, ArrayList<ArrayList<Cell>> path2,
               ArrayList<ArrayList<Cell>> path3, Cell[][] ancient1, Cell[][] ancient2, ArrayList<Cell[][]>
                       barraks1, ArrayList<Cell[][]> barraks2,
               ArrayList<Cell> goldMines) {
        this.row = row;
        this.column = column;

        this.path1 = new Path(path1);
        this.path2 = new Path(path2);
        this.path3 = new Path(path3);

        numberOfGoldMines = goldMines.size();

        gameBoard = new Cell[row][column];
        ArrayList<Components> componets = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gameBoardComponents.put(gameBoard[i][j], componets);
            }
        }

        //newing Lanes
        for (int i = 0; i < 5; i++) {
            path1Lanes[i] = new Lane(path1.get(i));
            path2Lanes[i] = new Lane(path2.get(i));
            path3Lanes[i] = new Lane(path3.get(i));
        }
        initializingLanes(path1Lanes, path1);
        initializingLanes(path2Lanes, path2);
        initializingLanes(path3Lanes, path3);

        //newing Ancients
        this.ancient1[0] = new Ancient(0, ancient1);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gameBoardComponents.get(ancient1[i][j]).add(this.ancient1[0]);
            }
        }
        this.ancient2[0] = new Ancient(1, ancient2);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gameBoardComponents.get(ancient2[i][j]).add(this.ancient2[0]);
            }
        }


        this.barraks1[0] = new Barraks(0, barraks1.get(0),this.path1);
        this.barraks1[1] = new Barraks(0, barraks1.get(1),this.path2);
        this.barraks1[2] = new Barraks(0, barraks1.get(2),this.path3);

        initializingBarraks(this.barraks1[0]);
        initializingBarraks(this.barraks1[1]);
        initializingBarraks(this.barraks1[2]);

        this.barraks2[0] = new Barraks(0, barraks2.get(0),this.path1);
        this.barraks2[1] = new Barraks(0, barraks2.get(1),this.path2);
        this.barraks2[2] = new Barraks(0, barraks2.get(2),this.path3);

        initializingBarraks(this.barraks2[0]);
        initializingBarraks(this.barraks2[1]);
        initializingBarraks(this.barraks2[2]);

        for (int i = 0; i < goldMines.size(); i++) {
            this.goldMines.add(new GoldMine(goldMines.get(i)));
            gameBoardComponents.get(goldMines.get(i)).add(this.goldMines.get(i));
        }
    }


    public void initializingBarraks(Barraks barraks) {
        for (int i = 0; i < barraks.getCells().size(); i++) {
            gameBoardComponents.get(barraks.getCells().get(i)).add(barraks);
        }

    }

    public void initializingLanes(Lane[] pathLanes, ArrayList<ArrayList<Cell>> path) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < path.get(i).size(); j++) {
                gameBoardComponents.get(path.get(i).get(j)).add(pathLanes[i]);
            }
        }

    }

    //getters and setters


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

    public Path getPath1() {
        return path1;
    }

    public void setPath1(Path path1) {
        this.path1 = path1;
    }

    public Path getPath2() {
        return path2;
    }

    public void setPath2(Path path2) {
        this.path2 = path2;
    }

    public Path getPath3() {
        return path3;
    }

    public void setPath3(Path path3) {
        this.path3 = path3;
    }

    public ArrayList<GoldMine> getGoldMines() {
        return goldMines;
    }

    public void setGoldMines(ArrayList<GoldMine> goldMines) {
        this.goldMines = goldMines;
    }

    public int getNumberOfGoldMines() {
        return numberOfGoldMines;
    }

    public void setNumberOfGoldMines(int numberOfGoldMines) {
        this.numberOfGoldMines = numberOfGoldMines;
    }

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Cell[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public HashMap<Cell, ArrayList<Components>> getGameBoardComponents() {
        return gameBoardComponents;
    }

    public void setGameBoardComponents(HashMap<Cell, ArrayList<Components>> gameBoardComponents) {
        this.gameBoardComponents = gameBoardComponents;
    }

    public Lane[] getPath1Lanes() {
        return path1Lanes;
    }

    public void setPath1Lanes(Lane[] path1Lanes) {
        this.path1Lanes = path1Lanes;
    }

    public Lane[] getPath2Lanes() {
        return path2Lanes;
    }

    public void setPath2Lanes(Lane[] path2Lanes) {
        this.path2Lanes = path2Lanes;
    }

    public Lane[] getPath3Lanes() {
        return path3Lanes;
    }

    public void setPath3Lanes(Lane[] path3Lanes) {
        this.path3Lanes = path3Lanes;
    }

    public Ancient[] getAncient1() {
        return ancient1;
    }

    public void setAncient1(Ancient[] ancient1) {
        this.ancient1 = ancient1;
    }

    public Ancient[] getAncient2() {
        return ancient2;
    }

    public void setAncient2(Ancient[] ancient2) {
        this.ancient2 = ancient2;
    }

    public Barraks[] getBarraks1() {
        return barraks1;
    }

    public void setBarraks1(Barraks[] barraks1) {
        this.barraks1 = barraks1;
    }

    public Barraks[] getBarraks2() {
        return barraks2;
    }

    public void setBarraks2(Barraks[] barraks2) {
        this.barraks2 = barraks2;
    }
}
