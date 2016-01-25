package common;

import common.exception.DotaExceptionBase;

import java.util.ArrayList;
import java.util.Random;

public class Hero extends MovingForces {

    private int heroType;

    //constructor
    public Hero(int teamID, int heroType, Map map) {
        if (teamID == 1) {
            speed = 250;
            damage = 300;
            reloadTime = 300;
            range = 7;
            health = 4000;
            this.teamID = teamID;
            row = map.getAncient2()[0].getCell()[2][2].getRow();
            column = map.getAncient2()[0].getCell()[2][2].getColumn();
            map.getGameBoard()[row][column].tiny.add(this);
        } else if (teamID == 0) {
            speed = 400;
            damage = 400;
            reloadTime = 400;
            range = 7;
            health = 5000;
            row = map.getAncient1()[0].getCell()[2][2].getRow();
            column = map.getAncient1()[0].getCell()[2][2].getColumn();
            this.teamID = teamID;
            map.getGameBoard()[row][column].venomancer.add(this);
        }
        this.heroType = heroType;
        this.isAlive = true;
    }

    public void heroMove(Cell dest, int direction, Map map) throws DotaExceptionBase {

        //exceptions
        if (dest.getRow() >= map.getRow() || dest.getColumn() >= map.getColumn() || dest.getColumn() < 0 || dest.getRow() < 0) {
            throw new DotaExceptionBase();
        }
        if (direction != 0 && direction != 1 && direction != 2 && direction != 3) {
            throw new DotaExceptionBase();
        }

        //moving
        if (teamID == 0) {
            map.getGameBoard()[row][column].tiny.remove(this);
        }
        if (teamID == 1) {
            map.getGameBoard()[row][column].venomancer.remove(this);
        }

        if (direction == 0) {
            if (row == 0) {
                throw new DotaExceptionBase();
            }
            row--;
        }
        if (direction == 1) {
            if (column == map.getColumn() - 1) {
                throw new DotaExceptionBase();
            }
            column++;
        }
        if (direction == 2) {
            if (row == map.getRow() - 1) {
                throw new DotaExceptionBase();
            }
            row++;
        }
        if (direction == 3) {
            if (column != 0) {
                throw new DotaExceptionBase();
            }
            column--;
        }
        if (teamID == 0) {
            map.getGameBoard()[row][column].tiny.add(this);
        }
        if (teamID == 1) {
            map.getGameBoard()[row][column].venomancer.add(this);
        }

        checkGoldMine(map);
    }

    //call after every move
    private void checkGoldMine(Map map) throws DotaExceptionBase {
        if (map.getGameBoard()[row][column].goldMines.size() == 1) {
            if (map.getGameBoard()[row][column].goldMines.get(0).isHasOwner() == true && this.teamID != map.getGameBoard()[row][column].goldMines.get(0).getTeamID()) {
                map.getGoldMines().remove(this);
                map.getGameBoard()[row][column].goldMines.remove(0);
                if (this.teamID == 0) {
                    int temp = map.getAncient2()[0].getNumberOfGoldMines();
                    temp--;
                    map.getAncient2()[0].setNumberOfGoldMines(temp);
                }
                if (this.teamID == 1) {
                    int temp = map.getAncient1()[0].getNumberOfGoldMines();
                    temp--;
                    map.getAncient1()[0].setNumberOfGoldMines(temp);
                }
                makeRandomGoldMine(map, row, column);
            }
            if (map.getGameBoard()[row][column].goldMines.get(0).isHasOwner() == false) {
                map.getGameBoard()[row][column].goldMines.get(0).setHasOwner(true);
                map.getGameBoard()[row][column].goldMines.get(0).setTeamID(this.teamID);

                if (this.teamID == 0) {
                    int temp = map.getAncient1()[0].getNumberOfGoldMines();
                    temp++;
                    map.getAncient1()[0].setNumberOfGoldMines(temp);
                }
                if (this.teamID == 1) {
                    int temp = map.getAncient2()[0].getNumberOfGoldMines();
                    temp--;
                    map.getAncient2()[0].setNumberOfGoldMines(temp);
                }
            }
        }
        if (map.getGameBoard()[row][column].goldMines.size() > 1) {
            throw new DotaExceptionBase();
        }
    }

    private void makeRandomGoldMine(Map map, int x, int y) throws DotaExceptionBase {
        int flag = 0;
        while (flag == 0) {
            Random rand = new Random();
            int i = rand.nextInt(map.getGameBoard().length);
            int j = rand.nextInt(map.getGameBoard()[0].length);
            if (map.getGameBoard()[i][j].ancientScourge.size() == 0 && map.getGameBoard()[i][j].ancientSentinel.size() == 0 &&
                    map.getGameBoard()[i][j].barraksSentinel.size() == 0 && map.getGameBoard()[i][j].barraksScourge.size() == 0 &&
                    map.getGameBoard()[i][j].lanes.size() == 0
                    && i != x && j != y) {
                GoldMine gm = new GoldMine(map.getGameBoard()[i][j], map);
                map.getGameBoard()[i][j].goldMines.add(gm);
                map.getGoldMines().add(gm);
            }
        }

    }

    public void attack(Cell target, Map map) throws DotaExceptionBase {
        if (target.getColumn() > column - 1 || target.getRow() > row - 1 || target.getColumn() < 0 || target.getRow() < 0) {
            throw new DotaExceptionBase();
        }
        if (Math.abs(target.getColumn() - column) < range && Math.abs(target.getRow() - row) < range) {
            if (teamID == 0) {
                if (target.ancientScourge.size() != 0) {
                    target.ancientScourge.get(0).health -= damage;
                    if (target.ancientScourge.get(0).health <= 0) {
                        target.ancientScourge.get(0).isAlive = false;//TODO
                        //end game
                        map.getGameBoard()[target.getRow()][target.getColumn()].ancientScourge.remove(0);
                    }
                }
                if (target.barraksScourge.size() != 0) {
                    target.barraksScourge.get(0).health -= damage;
                    if (target.barraksScourge.get(0).health <= 0) {
                        target.barraksScourge.get(0).isAlive = false;
                        map.getGameBoard()[target.getRow()][target.getColumn()].barraksScourge.remove(0);
                    }
                }
                if (target.attackerScourgeInfantry.size() != 0) {
                    for (int i = 0; i < target.attackerScourgeInfantry.size(); i++) {
                        target.attackerScourgeInfantry.get(i).health -= damage;
                        if (target.attackerScourgeInfantry.get(i).health <= 0) {
                            target.attackerScourgeInfantry.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.attackerScourgeInfantry.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerScourgeInfantry.remove(i);
                        }
                    }
                }
                if (target.attackerScourgeTank.size() != 0) {
                    for (int i = 0; i < target.attackerScourgeTank.size(); i++) {
                        target.attackerScourgeTank.get(i).health -= damage;
                        if (target.attackerScourgeTank.get(i).health <= 0) {
                            target.attackerScourgeTank.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.attackerScourgeTank.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerScourgeTank.remove(i);
                        }
                    }
                }
                if (target.venomancer.size() != 0) {
                    target.venomancer.get(0).health -= damage;
                    if (target.venomancer.get(0).health <= 0) {
                        target.venomancer.get(0).isAlive = false;
                        map.getGameBoard()[target.getRow()][target.getColumn()].venomancer.remove(0);
                    }
                }
                if (target.towerBlack.size() != 0) {
                    for (int i = 0; i < target.towerBlack.size(); i++) {
                        target.towerBlack.get(i).health -= damage;
                        if (target.towerBlack.get(i).health <= 0) {
                            target.towerBlack.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.towerBlack.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].towerBlack.remove(i);
                        }
                    }
                }
                if (target.towerPoison.size() != 0) {
                    for (int i = 0; i < target.towerPoison.size(); i++) {
                        target.towerPoison.get(i).health -= damage;
                        if (target.towerPoison.get(i).health <= 0) {
                            target.towerPoison.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.towerPoison.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].towerPoison.remove(i);
                        }
                    }
                }
            }
            if (teamID == 1) {
                if (target.ancientSentinel.size() != 0) {
                    target.ancientSentinel.get(0).health -= damage;
                    if (target.ancientSentinel.get(0).health <= 0) {
                        target.ancientSentinel.get(0).isAlive = false;//TODO
                        //end game
                        map.getGameBoard()[target.getRow()][target.getColumn()].ancientSentinel.remove(0);
                    }
                }
                if (target.barraksSentinel.size() != 0) {
                    target.barraksSentinel.get(0).health -= damage;
                    if (target.barraksSentinel.get(0).health <= 0) {
                        target.barraksSentinel.get(0).isAlive = false;
                        map.getGameBoard()[target.getRow()][target.getColumn()].barraksSentinel.remove(0);
                    }
                }
                if (target.attackerSentinelInfantry.size() != 0) {
                    for (int i = 0; i < target.attackerSentinelInfantry.size(); i++) {
                        target.attackerSentinelInfantry.get(i).health -= damage;
                        if (target.attackerSentinelInfantry.get(i).health <= 0) {
                            target.attackerSentinelInfantry.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.attackerSentinelInfantry.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerSentinelInfantry.remove(i);
                        }
                    }
                }
                if (target.attackerSentinelTank.size() != 0) {
                    for (int i = 0; i < target.attackerSentinelTank.size(); i++) {
                        target.attackerSentinelTank.get(i).health -= damage;
                        if (target.attackerSentinelTank.get(i).health <= 0) {
                            target.attackerSentinelTank.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.attackerSentinelTank.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerSentinelTank.remove(i);
                        }
                    }
                }
                if (target.tiny.size() != 0) {
                    target.tiny.get(0).health -= damage;
                    if (target.tiny.get(0).health <= 0) {
                        target.tiny.get(0).isAlive = false;
                        map.getGameBoard()[target.getRow()][target.getColumn()].tiny.remove(0);
                    }
                }
                if (target.towerStone.size() != 0) {
                    for (int i = 0; i < target.towerStone.size(); i++) {
                        target.towerStone.get(i).health -= damage;
                        if (target.towerStone.get(i).health <= 0) {
                            target.towerStone.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.towerStone.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].towerStone.remove(i);
                        }
                    }
                }
                if (target.towerFire.size() != 0) {
                    for (int i = 0; i < target.towerFire.size(); i++) {
                        target.towerFire.get(i).health -= damage;
                        if (target.towerFire.get(i).health <= 0) {
                            target.towerFire.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() + (int) target.towerFire.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].towerFire.remove(i);
                        }
                    }
                }
            } else {
                throw new DotaExceptionBase();
            }
        }
    }

    public ArrayList<Cell> getInRange(Map map) throws DotaExceptionBase {
        ArrayList<Cell> heroRange = new ArrayList<>();
        for (int i = -range; i < range + 1; i++) {
            if (i + row > -1 && i + row < map.getGameBoard().length) {
                for (int j = -range; j < range + 1; j++) {
                    if (j + column > -1 && j + column < map.getGameBoard()[i].length) {
                        if (teamID == 0) {
                            //tower
                            if (map.getGameBoard()[row + i][column + j].towerBlack.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            if (map.getGameBoard()[row + i][column + j].towerPoison.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            //forces
                            if (map.getGameBoard()[row + i][column + j].attackerScourgeInfantry.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            if (map.getGameBoard()[row + i][column + j].attackerScourgeTank.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            if (map.getGameBoard()[row + i][column + j].venomancer.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            //ancient
                            if (map.getGameBoard()[row + i][column + j].ancientScourge.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            //barracks
                            if (map.getGameBoard()[row + i][column + j].barraksScourge.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                        }
                        if (teamID == 1) {
                            //tower
                            if (map.getGameBoard()[row + i][column + j].towerStone.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            if (map.getGameBoard()[row + i][column + j].towerFire.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            //forces
                            if (map.getGameBoard()[row + i][column + j].attackerSentinelInfantry.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            if (map.getGameBoard()[row + i][column + j].attackerSentinelTank.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            if (map.getGameBoard()[row + i][column + j].tiny.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            //ancient
                            if (map.getGameBoard()[row + i][column + j].ancientSentinel.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                            //barracks
                            if (map.getGameBoard()[row + i][column + j].barraksSentinel.size() != 0) {
                                heroRange.add(map.getGameBoard()[row + i][column + j]);
                            }
                        }
                        if (map.getGameBoard()[row + i][column + j].goldMines.size() != 0) {
                            heroRange.add(map.getGameBoard()[row + i][column + j]);
                        }
                    }

                }
            }
        }
        return heroRange;
    }


}