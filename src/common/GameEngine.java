package common;

import java.util.ArrayList;

public class GameEngine {
    public Map map;
    private int row;
    private int column;
    private ArrayList<ArrayList<Cell>> path1;
    private ArrayList<ArrayList<Cell>> path2;
    private ArrayList<ArrayList<Cell>> path3;
    private Cell[][] ancient1;
    private Cell[][] ancient2;
    private ArrayList<Cell[][]> barraks1;
    private ArrayList<Cell[][]> barraks2;
    private ArrayList<Cell> goldMines;

    public void makeMap (int row, int column, ArrayList<ArrayList<Cell>> path1, ArrayList<ArrayList<Cell>> path2,
                         ArrayList<ArrayList<Cell>> path3, Cell[][] ancient1, Cell[][] ancient2, ArrayList<Cell[][]> barraks1,
                         ArrayList<Cell[][]> barraks2, ArrayList<Cell> goldMines){
        map = new Map(row, column, path1, path2, path3, ancient1, ancient2, barraks1, barraks2, goldMines);
    }

    public Tower createTower(int teamID, int towerType, Path path,Lane lane,int index, int row, int column, int time) {
        Tower tower = new Tower(teamID,towerType,path,lane,index,row,column,time,map);
        if (towerType == 0)
            map.getGameBoard()[row][column].towerFire.add(tower);

        if (towerType == 1)
            map.getGameBoard()[row][column].towerStone.add(tower);

        if (towerType == 2)
            map.getGameBoard()[row][column].towerBlack.add(tower);

        if (towerType == 3)
            map.getGameBoard()[row][column].towerPoison.add(tower);

        return tower;
    }

    public AttackForces createAttacker(int teamID, int attackerType,Path path,Lane lane, int row, int column, int time) {
        AttackForces attackForce = new AttackForces(teamID, attackerType,path,lane, row, column,time,map);
        if (attackerType == 9 && teamID == 0) {
            map.getGameBoard()[row][column].attackerSentinelTank.add(attackForce);
        }
        if (attackerType == 9 && teamID == 1) {
            map.getGameBoard()[row][column].attackerScourgeTank.add(attackForce);
        }
        if (attackerType == 8 && teamID == 0) {
            map.getGameBoard()[row][column].attackerSentinelInfantry.add(attackForce);
        }
        if (attackerType == 8 && teamID == 1) {
            map.getGameBoard()[row][column].attackerScourgeInfantry.add(attackForce);
        }
        return attackForce;
    }
}
