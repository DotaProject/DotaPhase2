package common;


import common.exception.DotaExceptionBase;

import java.util.ArrayList;

public class Tower extends Forces {

    private int birthTime;
    private int infantryAttackPower;
    private int tankAttackPower;
    private int towerType;

    private int price;
    private int rangeUpgradeTime = 0;
    private int index;
    private int row;
    private int column;
    private double value;
    private Path path;
    private Lane lane;

    private int falg = 0;
    //0 morde
    //1 zende o hamle

    //constructor
    public Tower(int teamID, int towerType, Path path, Lane lane, int index, int rowNumber, int colNumber, int time, Map map) {

        this.birthTime = time;
        this.range = 7;
        this.teamID = teamID;
        this.towerType = towerType;
        this.path = path;
        this.lane = lane;
        this.index = index;
        this.row = rowNumber;
        this.column = colNumber;
        this.isAlive = true;

        if (teamID == 0) {
            if (towerType == 0) {
                tankAttackPower = 50;
                infantryAttackPower = 400;
                reloadTime = 2000;
                health = 5000;
                price = 300;
                value = 0.8 * 300;
                map.getGameBoard()[rowNumber][colNumber].towerFire.add(this);
            }
            if (towerType == 1) {
                tankAttackPower = 200;
                infantryAttackPower = 40;
                reloadTime = 800;
                health = 4000;
                price = 500;
                value = 0.8 * 500;
                map.getGameBoard()[rowNumber][colNumber].towerStone.add(this);
            }
        }
        if (teamID == 1) {
            if (towerType == 2) {
                tankAttackPower = 100;
                infantryAttackPower = 20;
                reloadTime = 500;
                health = 4000;
                price = 500;
                value = 0.8 * 500;
                map.getGameBoard()[rowNumber][colNumber].towerBlack.add(this);
            }
            if (towerType == 3) {
                tankAttackPower = 20;
                infantryAttackPower = 100;
                reloadTime = 500;
                health = 5000;
                price = 300;
                value = 0.8 * 300;
                map.getGameBoard()[rowNumber][colNumber].towerPoison.add(this);
            }
        }


    }

    public void upgradePower(Map map) throws DotaExceptionBase {
        {
            if (teamID == 0) {
                int temp = map.getAncient1()[0].getTreasury();
                temp -= (value * 15) / 100;
                if (temp < 0) {
                    throw new DotaExceptionBase();
                } else {
                    map.getAncient1()[0].setTreasury(temp);
                }
            }
            if (teamID == 1) {
                int temp = map.getAncient2()[0].getTreasury();
                temp -= (value * 15) / 100;
                if (temp < 0) {
                    throw new DotaExceptionBase();
                } else {
                    map.getAncient2()[0].setTreasury(temp);
                }
            }
            value += (int) (15 * value / 100);
            tankAttackPower += tankAttackPower / 10;
            infantryAttackPower += infantryAttackPower / 10;
        }
    }

    public void upgradeRange(Map map) throws DotaExceptionBase {
        if (rangeUpgradeTime < 3) {
            if (teamID == 0) {
                int temp = map.getAncient1()[0].getTreasury();
                temp -= value / 10;
                if (temp < 0) {
                    throw new DotaExceptionBase();
                } else {
                    map.getAncient1()[0].setTreasury(temp);
                }
            } else if (teamID == 1) {
                int temp = map.getAncient2()[0].getTreasury();
                temp -= value / 10;
                if (temp < 0) {
                    throw new DotaExceptionBase();
                } else {
                    map.getAncient2()[0].setTreasury(temp);
                }
            }
            rangeUpgradeTime++;
            range++;
            value += (int) (20 * value / 100);
        } else {
            throw new DotaExceptionBase();
        }
    }


    public ArrayList<Cell> getInRange(Map map) throws DotaExceptionBase {

        ArrayList<Cell> towerRange = new ArrayList<>();
        for (int i = -range; i < range + 1; i++) {
            if (i + row > -1 && i + row < map.getRow()) {
                for (int j = -range; j < range + 1; j++) {
                    if (j + column > -1 && j + column < map.getColumn()) {
                        boolean flag = false;
                        for (int k = 0; k < path.getCells().size(); k++) {
                            if (path.getCells().get(k).equals(map.getGameBoard()[row + i][column + j])) {
                                flag = true;
                            }
                        }
                        if (flag == true) {
                            if (teamID == 0) {

                                if (map.getGameBoard()[row + i][column + j].attackerScourgeInfantry.size() != 0) {
                                    towerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].attackerScourgeTank.size() != 0) {
                                    towerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].venomancer.size() != 0) {
                                    towerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                            }

                            if (teamID == 1) {
                                if (map.getGameBoard()[row + i][column + j].attackerSentinelInfantry.size() != 0) {
                                    towerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].attackerSentinelTank.size() != 0) {
                                    towerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].tiny.size() != 0) {
                                    towerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                            }
                        }
                    }
                }
            }
        }
        return towerRange;
    }

    private ArrayList<Cell> findMinValue(ArrayList<Cell> targets) {
        ArrayList<Cell> minHealthList = new ArrayList<>();
        int minHealth = 10 ^ 5;
        if (teamID == 0) {
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).attackerScourgeInfantry.size(); j++) {
                    if (targets.get(i).attackerScourgeInfantry.get(j).health < minHealth) {
                        minHealth = targets.get(i).attackerScourgeInfantry.get(j).health;
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).attackerScourgeTank.size(); j++) {
                    if (targets.get(i).attackerScourgeTank.get(j).health < minHealth) {
                        minHealth = targets.get(i).attackerScourgeTank.get(j).health;
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).venomancer.size(); j++) {
                    if (targets.get(i).venomancer.get(j).health < minHealth) {
                        minHealth = targets.get(i).venomancer.get(j).health;
                    }
                }
            }
            minHealthList.clear();
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).attackerScourgeInfantry.size(); j++) {
                    if (targets.get(i).attackerScourgeInfantry.get(j).health == minHealth) {
                        minHealthList.add(targets.get(i));
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).attackerScourgeTank.size(); j++) {
                    if (targets.get(i).attackerScourgeTank.get(j).health == minHealth) {
                        minHealthList.add(targets.get(i));
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).venomancer.size(); j++) {
                    if (targets.get(i).venomancer.get(j).health == minHealth) {
                        minHealthList.add(targets.get(i));
                    }
                }
            }
        }
        if (teamID == 1) {
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).attackerSentinelInfantry.size(); j++) {
                    if (targets.get(i).attackerSentinelInfantry.get(j).health < minHealth) {
                        minHealth = targets.get(i).attackerSentinelInfantry.get(j).health;
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).attackerSentinelTank.size(); j++) {
                    if (targets.get(i).attackerSentinelTank.get(j).health < minHealth) {
                        minHealth = targets.get(i).attackerSentinelTank.get(j).health;
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).tiny.size(); j++) {
                    if (targets.get(i).tiny.get(j).health < minHealth) {
                        minHealth = targets.get(i).tiny.get(j).health;
                    }
                }
            }
            minHealthList.clear();
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).attackerSentinelInfantry.size(); j++) {
                    if (targets.get(i).attackerSentinelInfantry.get(j).health == minHealth) {
                        minHealthList.add(targets.get(i));
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).attackerSentinelTank.size(); j++) {
                    if (targets.get(i).attackerSentinelTank.get(j).health == minHealth) {
                        minHealthList.add(targets.get(i));
                    }
                }
            }
            for (int i = 0; i < targets.size(); i++) {
                for (int j = 0; j < targets.get(i).tiny.size(); j++) {
                    if (targets.get(i).tiny.get(j).health == minHealth) {
                        minHealthList.add(targets.get(i));
                    }
                }
            }
        }
        return minHealthList;
    }

    private ArrayList<Cell> findMinDistance(ArrayList<Cell> tempTargets1) {

        ArrayList<Cell> minDistanceList = new ArrayList<>();
        int minDistance = 10;
        if(teamID == 0) {
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).attackerScourgeInfantry.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).attackerScourgeInfantry.get(j).row - row),
                            Math.abs(tempTargets1.get(i).attackerScourgeInfantry.get(j).column - column)) < minDistance) {
                        minDistance = Math.max(Math.abs(tempTargets1.get(i).attackerScourgeInfantry.get(j).row - row),
                                Math.abs(tempTargets1.get(i).attackerScourgeInfantry.get(j).column - column));
                    }
                }
            }
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).attackerScourgeTank.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).attackerScourgeTank.get(j).row - row),
                            Math.abs(tempTargets1.get(i).attackerScourgeTank.get(j).column - column)) < minDistance) {
                        minDistance = Math.max(Math.abs(tempTargets1.get(i).attackerScourgeTank.get(j).row - row),
                                Math.abs(tempTargets1.get(i).attackerScourgeTank.get(j).column - column));
                    }
                }
            }
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).venomancer.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).venomancer.get(j).row - row),
                            Math.abs(tempTargets1.get(i).venomancer.get(j).column - column)) < minDistance) {
                        minDistance = Math.max(Math.abs(tempTargets1.get(i).venomancer.get(j).row - row),
                                Math.abs(tempTargets1.get(i).venomancer.get(j).column - column));
                    }
                }
            }
            minDistanceList.clear();
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).attackerScourgeInfantry.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).attackerScourgeInfantry.get(j).row - row),
                            Math.abs(tempTargets1.get(i).attackerScourgeInfantry.get(j).column - column)) == minDistance) {
                        minDistanceList.add(tempTargets1.get(i));
                    }
                }
            }
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).attackerScourgeTank.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).attackerScourgeTank.get(0).row - row),
                            Math.abs(tempTargets1.get(i).attackerScourgeTank.get(j).column - column)) == minDistance) {
                        minDistanceList.add(tempTargets1.get(i));
                    }
                }
            }
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).venomancer.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).venomancer.get(j).row - row),
                            Math.abs(tempTargets1.get(i).venomancer.get(j).column - column)) == minDistance) {
                        minDistanceList.add(tempTargets1.get(i));
                    }
                }
            }
        }
        if(teamID == 1) {
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).attackerSentinelInfantry.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).attackerSentinelInfantry.get(j).row - row),
                            Math.abs(tempTargets1.get(i).attackerSentinelInfantry.get(j).column - column)) < minDistance) {
                        minDistance = Math.max(Math.abs(tempTargets1.get(i).attackerSentinelInfantry.get(j).row - row),
                                Math.abs(tempTargets1.get(i).attackerSentinelInfantry.get(j).column - column));
                    }
                }
            }
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).attackerSentinelTank.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).attackerSentinelTank.get(j).row - row),
                            Math.abs(tempTargets1.get(i).attackerSentinelTank.get(j).column - column)) < minDistance) {
                        minDistance = Math.max(Math.abs(tempTargets1.get(i).attackerSentinelTank.get(j).row - row),
                                Math.abs(tempTargets1.get(i).attackerSentinelTank.get(j).column - column));
                    }
                }
            }
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).tiny.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).tiny.get(j).row - row),
                            Math.abs(tempTargets1.get(i).tiny.get(j).column - column)) < minDistance) {
                        minDistance = Math.max(Math.abs(tempTargets1.get(i).tiny.get(j).row - row),
                                Math.abs(tempTargets1.get(i).tiny.get(j).column - column));
                    }
                }
            }
            minDistanceList.clear();
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).attackerSentinelInfantry.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).attackerSentinelInfantry.get(j).row - row),
                            Math.abs(tempTargets1.get(i).attackerSentinelInfantry.get(j).column - column)) == minDistance) {
                        minDistanceList.add(tempTargets1.get(i));
                    }
                }
            }
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).attackerSentinelTank.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).attackerSentinelTank.get(0).row - row),
                            Math.abs(tempTargets1.get(i).attackerSentinelTank.get(j).column - column)) == minDistance) {
                        minDistanceList.add(tempTargets1.get(i));
                    }
                }
            }
            for (int i = 0; i < tempTargets1.size(); i++) {
                for (int j = 0; j < tempTargets1.get(i).tiny.size(); j++) {
                    if (Math.max(Math.abs(tempTargets1.get(i).tiny.get(j).row - row),
                            Math.abs(tempTargets1.get(i).tiny.get(j).column - column)) == minDistance) {
                        minDistanceList.add(tempTargets1.get(i));
                    }
                }
            }
        }
        return minDistanceList;
    }

    private ArrayList<Cell> findMostValue(ArrayList<Cell> tempTargets2){
        ArrayList<Cell> mostValuList = new ArrayList<>();

        int mostValue = 10^5;
        int tempValue;
        if(teamID == 0) {
            for (int i = 0; i < tempTargets2.size(); i++) {
                tempValue = 0;
                for (int j = 0; j < tempTargets2.get(i).attackerScourgeInfantry.size(); j++) {
                    tempValue += tempTargets2.get(i).attackerScourgeInfantry.get(j).getValue();
                }
                if (tempValue > mostValue) {
                    mostValue = tempValue;
                }
            }


            for (int i = 0; i < tempTargets2.size(); i++) {
                tempValue = 0;
                for (int j = 0; j < tempTargets2.get(i).attackerScourgeTank.size(); j++) {
                    tempValue += tempTargets2.get(i).attackerScourgeTank.get(j).getValue();
                }
                if (tempValue > mostValue) {
                    mostValue = tempValue;
                }
            }

            for (int i = 0; i < tempTargets2.size(); i++) {
                tempValue = 0;
                for (int j = 0; j < tempTargets2.get(i).attackerScourgeInfantry.size(); j++) {
                    tempValue += tempTargets2.get(i).attackerScourgeInfantry.get(j).getValue();
                }
                if (tempValue == mostValue) {
                    mostValuList.add(tempTargets2.get(i));
                }
            }

            for (int i = 0; i < tempTargets2.size(); i++) {
                tempValue = 0;
                for (int j = 0; j < tempTargets2.get(i).attackerScourgeTank.size(); j++) {
                    tempValue += tempTargets2.get(i).attackerScourgeTank.get(j).getValue();
                }
                if (tempValue == mostValue) {
                    mostValuList.add(tempTargets2.get(i));
                }
            }
        }
        if(teamID == 1) {
            for (int i = 0; i < tempTargets2.size(); i++) {
                tempValue = 0;
                for (int j = 0; j < tempTargets2.get(i).attackerSentinelInfantry.size(); j++) {
                    tempValue += tempTargets2.get(i).attackerSentinelInfantry.get(j).getValue();
                }
                if (tempValue > mostValue) {
                    mostValue = tempValue;
                }
            }


            for (int i = 0; i < tempTargets2.size(); i++) {
                tempValue = 0;
                for (int j = 0; j < tempTargets2.get(i).attackerSentinelTank.size(); j++) {
                    tempValue += tempTargets2.get(i).attackerSentinelTank.get(j).getValue();
                }
                if (tempValue > mostValue) {
                    mostValue = tempValue;
                }
            }

            for (int i = 0; i < tempTargets2.size(); i++) {
                tempValue = 0;
                for (int j = 0; j < tempTargets2.get(i).attackerSentinelInfantry.size(); j++) {
                    tempValue += tempTargets2.get(i).attackerSentinelInfantry.get(j).getValue();
                }
                if (tempValue == mostValue) {
                    mostValuList.add(tempTargets2.get(i));
                }
            }

            for (int i = 0; i < tempTargets2.size(); i++) {
                tempValue = 0;
                for (int j = 0; j < tempTargets2.get(i).attackerSentinelTank.size(); j++) {
                    tempValue += tempTargets2.get(i).attackerSentinelTank.get(j).getValue();
                }
                if (tempValue == mostValue) {
                    mostValuList.add(tempTargets2.get(i));
                }
            }
        }
        return mostValuList;
    }


    public Cell findTarget(Map map) throws DotaExceptionBase {

        ArrayList<Cell> targets = getInRange(map);

        ArrayList<Cell> tempTargets1 ;
        ArrayList<Cell> tempTargets2 ;
        ArrayList<Cell> tempTargets3 ;

        if (targets.size() == 1) {
            return targets.get(0);
        }
        if (targets.size() > 1) {

            tempTargets1 = findMinValue(targets);

            if (tempTargets1.size() == 1) {
                return tempTargets1.get(0);
            }
            if (tempTargets1.size() > 1) {

                tempTargets2 = findMinDistance(tempTargets1);

                if (tempTargets2.size() == 1) {
                    return tempTargets2.get(0);
                }

                if (tempTargets2.size() > 1) {

                    tempTargets3 = findMostValue(tempTargets2);

                    return tempTargets3.get(0);
                }
            }

        }

        return null;
    }

    //getters and setters
    public void attack (Map map) throws DotaExceptionBase {

        Cell target = findTarget(map);

        if (Math.abs(target.getColumn() - column) < range && Math.abs(target.getRow() - row) < range) {
            if (teamID == 0) {
                if (target.attackerScourgeInfantry.size() != 0) {
                    for (int i = 0; i < target.attackerScourgeInfantry.size(); i++) {
                        target.attackerScourgeInfantry.get(i).health -= infantryAttackPower;
                        if (target.attackerScourgeInfantry.get(i).health <= 0) {
                            target.attackerScourgeInfantry.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.attackerScourgeInfantry.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerScourgeInfantry.remove(i);
                        }
                    }
                }
                if (target.attackerScourgeTank.size() != 0) {
                    for (int i = 0; i < target.attackerScourgeTank.size(); i++) {
                        target.attackerScourgeTank.get(i).health -= tankAttackPower;
                        if (target.attackerScourgeTank.get(i).health <= 0) {
                            target.attackerScourgeTank.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.attackerScourgeTank.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerScourgeTank.remove(i);
                        }
                    }
                }
                if (target.venomancer.size() != 0) {
                    for (int i = 0; i < target.venomancer.size(); i++) {
                        target.venomancer.get(i).health -= infantryAttackPower;
                        if (target.venomancer.get(i).health <= 0) {
                            target.venomancer.get(i).isAlive = false;
                            map.getGameBoard()[target.getRow()][target.getColumn()].venomancer.remove(i);
                        }
                    }
                }
            }
            if (teamID == 1) {
                if (target.attackerSentinelInfantry.size() != 0) {
                    for (int i = 0; i < target.attackerSentinelInfantry.size(); i++) {
                        target.attackerSentinelInfantry.get(i).health -= infantryAttackPower;
                        if (target.attackerSentinelInfantry.get(i).health <= 0) {
                            target.attackerSentinelInfantry.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.attackerSentinelInfantry.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerSentinelInfantry.remove(i);
                        }
                    }
                }
                if (target.attackerSentinelTank.size() != 0) {
                    for (int i = 0; i < target.attackerSentinelTank.size(); i++) {
                        target.attackerSentinelTank.get(i).health -= tankAttackPower;
                        if (target.attackerSentinelTank.get(i).health <= 0) {
                            target.attackerSentinelTank.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.attackerSentinelTank.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerSentinelTank.remove(i);
                        }
                    }
                }
                if (target.tiny.size() != 0) {
                    for (int i = 0; i < target.tiny.size(); i++) {
                        target.tiny.get(i).health -= tankAttackPower;
                        if (target.tiny.get(i).health <= 0) {
                            target.tiny.get(i).isAlive = false;
                            map.getGameBoard()[target.getRow()][target.getColumn()].tiny.remove(i);
                        }
                    }
                }

            }
        }
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public int getInfantryAttackPower() {
        return infantryAttackPower;
    }

    public void setInfantryAttackPower(int infantryAttackPower) {
        this.infantryAttackPower = infantryAttackPower;
    }

    public int getTankAttackPower() {
        return tankAttackPower;
    }

    public void setTankAttackPower(int tankAttackPower) {
        this.tankAttackPower = tankAttackPower;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(int birthTime) {
        this.birthTime = birthTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRangeUpgradeTime() {
        return rangeUpgradeTime;
    }

    public void setRangeUpgradeTime(int rangeUpgradeTime) {
        this.rangeUpgradeTime = rangeUpgradeTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

}