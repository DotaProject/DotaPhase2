package logic.common;

import java.util.ArrayList;

public class Cell {

    public ArrayList<Ancient> ancientSentinel = new ArrayList<>();
    public ArrayList<Ancient> ancientScourge = new ArrayList<>();
    public ArrayList<Barraks> barraksSentinel = new ArrayList<>();
    public ArrayList<Barraks> barraksScourge = new ArrayList<>();
    public ArrayList<GoldMine> goldMines = new ArrayList<>();
    public ArrayList<Lane> lanes = new ArrayList<>();
    public ArrayList<Hero> tiny = new ArrayList<>();
    public ArrayList<Hero> venomancer = new ArrayList<>();
    public ArrayList<AttackForces> attackerSentinelTank = new ArrayList<>();
    public ArrayList<AttackForces> attackerScourgeTank = new ArrayList<>();
    public ArrayList<AttackForces> attackerSentinelInfantry = new ArrayList<>();
    public ArrayList<AttackForces> attackerScourgeInfantry = new ArrayList<>();
    public ArrayList<Tower> towerFire = new ArrayList<>();
    public ArrayList<Tower> towerStone = new ArrayList<>();
    public ArrayList<Tower> towerBlack = new ArrayList<>();
    public ArrayList<Tower> towerPoison = new ArrayList<>();
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

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Cell other = (Cell) obj;
        if(other.getRow() != this.getRow() || other.getColumn() != this.getColumn()){
            return false;
        }
        return true;
    }
}