package common;

import common.exception.DotaExceptionBase;

import java.util.ArrayList;

public class AttackForces extends MovingForces {

    private static int scourge_upgradeHealthTime = 0;
    private static int sentinel_upgradeHealthTime = 0;
    private static int sentinel_upgradeDamageTime = 0;
    private static int scourge_upgradeDamageTime = 0;
    private static int static_health_tank_scourge = 1000;
    private static int static_health_infantry_scourge = 400;
    private static int static_health_tank_sentinel = 1000;
    private static int static_health_infantry_sentinel = 400;
    private static int static_damage_tank_scourge = 100;
    private static int static_damage_infantry_scourge = 20;
    private static int static_damage_tank_sentinel = 100;
    private static int static_damage_infantry_sentinel = 20;
    private static double static_value_tank_scourge = 0.8 * 40;
    private static double static_value_infantry_scourge = 0.8 * 10;
    private static double static_value_tank_sentinel = 0.8 * 40;
    private static double static_value_infantry_sentinel = 0.8 * 10;
    private static ArrayList<Integer> attackerPowerUpsSentinel = new ArrayList<>();
    private static ArrayList<Integer> attackerPowerUpsScourge = new ArrayList<>();
    private int price;
    private int birthTime;
    private int attackerType;
    private Lane lane;
    private Path path;

    public AttackForces(int teamID, int attackerType, Path path, Lane lane, int rowNumber, int colNumber, int time, Map map) {
        this.birthTime = time;
        this.teamID = teamID;
        this.attackerType = attackerType;
        this.path = path;
        this.lane = lane;
        this.row = rowNumber;
        this.column = colNumber;
        this.isAlive = true;


        if (this.attackerType == 9) {//Tank
            speed = 500;
            range = 6;
            price = 40;
            reloadTime = 500;
            if (teamID == 0) {
                health = static_health_tank_sentinel;
                value = static_value_tank_sentinel;
                damage = static_damage_tank_sentinel;
                map.getGameBoard()[rowNumber][colNumber].attackerSentinelTank.add(this);
            }
            if (teamID == 1) {
                health = static_health_tank_scourge;
                value = static_value_tank_scourge;
                damage = static_damage_tank_scourge;
                map.getGameBoard()[rowNumber][colNumber].attackerScourgeTank.add(this);
            }
        }
        if (this.attackerType == 8) {//infantry
            speed = 500;
            range = 4;
            price = 10;
            reloadTime = 200;
            if (teamID == 0) {
                health = static_health_infantry_sentinel;
                value = static_value_infantry_sentinel;
                damage = static_damage_infantry_sentinel;
                map.getGameBoard()[rowNumber][colNumber].attackerSentinelInfantry.add(this);
            }
            if (teamID == 1) {
                health = static_health_infantry_scourge;
                value = static_value_infantry_scourge;
                damage = static_damage_infantry_scourge;
                map.getGameBoard()[rowNumber][colNumber].attackerScourgeInfantry.add(this);
            }
        }
    }

    public static void upgradePower(Map map, int teamID) throws DotaExceptionBase {
        if (teamID == 0) {
            sentinel_upgradeDamageTime++;
            int temp = map.getAncient1()[0].getTreasury();
            temp -= sentinel_upgradeDamageTime * 1000;
            if (temp < 0) {
                sentinel_upgradeDamageTime--;
                throw new DotaExceptionBase();
            } else {
                map.getAncient1()[0].setTreasury(temp);
            }
            static_damage_infantry_sentinel += static_damage_infantry_sentinel / 10;
            static_damage_tank_sentinel += static_damage_tank_sentinel / 10;

            static_value_infantry_sentinel += 0.5;
            static_value_tank_sentinel += 2;

            attackerPowerUpsSentinel.add(2);
        }
        if (teamID == 1) {
            scourge_upgradeDamageTime++;
            int temp = map.getAncient2()[0].getTreasury();
            temp -= scourge_upgradeDamageTime * 1000;
            if (temp < 0) {
                scourge_upgradeDamageTime--;
                throw new DotaExceptionBase();
            } else {
                map.getAncient2()[0].setTreasury(temp);
            }
            static_damage_infantry_scourge += static_damage_infantry_scourge / 10;
            static_damage_tank_scourge += static_damage_tank_scourge / 10;

            static_value_infantry_scourge += 0.5;
            static_value_tank_scourge += 2;

            attackerPowerUpsScourge.add(2);
        }
    }

    public static void upgradeHealth(Map map, int teamID) throws DotaExceptionBase {
        if (teamID == 0) {
            sentinel_upgradeHealthTime++;
            int temp = map.getAncient1()[0].getTreasury();
            temp -= sentinel_upgradeHealthTime * 500;
            if (temp < 0) {
                sentinel_upgradeHealthTime--;
                throw new DotaExceptionBase();
            } else {
                map.getAncient1()[0].setTreasury(temp);
            }
            static_health_infantry_sentinel += 5;
            static_health_tank_sentinel += 5;

            static_value_infantry_sentinel += 0.5;
            static_value_tank_sentinel += 2;

            attackerPowerUpsSentinel.add(3);

        }
        if (teamID == 1) {
            scourge_upgradeHealthTime++;
            int temp = map.getAncient2()[0].getTreasury();
            temp -= scourge_upgradeHealthTime * 500;
            if (temp < 0) {
                scourge_upgradeHealthTime--;
                throw new DotaExceptionBase();
            } else {
                map.getAncient2()[0].setTreasury(temp);
            }
            static_health_infantry_scourge += 5;
            static_health_tank_scourge += 5;

            static_value_infantry_scourge += 0.5;
            static_value_tank_scourge += 2;

            attackerPowerUpsScourge.add(3);
        }

    }

    public static ArrayList<Integer> getAttackerPowerUpsSentinel() {
        return attackerPowerUpsSentinel;
    }

    public static ArrayList<Integer> getAttackerPowerUpsScourge() {
        return attackerPowerUpsScourge;
    }

    public ArrayList<Cell> getInRange(Map map) {
        ArrayList<Cell> attackerRange = new ArrayList<>();
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
                                //tower
                                if (map.getGameBoard()[row + i][column + j].towerBlack.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].towerPoison.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                //forces
                                if (map.getGameBoard()[row + i][column + j].attackerScourgeInfantry.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].attackerScourgeTank.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].venomancer.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                //ancient
                                if (map.getGameBoard()[row + i][column + j].ancientScourge.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }


                            }
                            if (teamID == 1) {
                                //tower
                                if (map.getGameBoard()[row + i][column + j].towerStone.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].towerFire.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                //forces
                                if (map.getGameBoard()[row + i][column + j].attackerSentinelInfantry.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].attackerSentinelTank.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                if (map.getGameBoard()[row + i][column + j].tiny.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                                //ancient
                                if (map.getGameBoard()[row + i][column + j].ancientSentinel.size() != 0) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }


                            }
                        }
                        if (teamID == 0) {
                            //barracks
                            if (map.getGameBoard()[row + i][column + j].barraksScourge.size() != 0) {
                                if (map.getGameBoard()[row + i][column + j].barraksScourge.get(0).path.equals(path)) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                            }
                        }
                        if (teamID == 1) {
                            //barracks
                            if (map.getGameBoard()[row + i][column + j].barraksSentinel.size() != 0) {
                                if (map.getGameBoard()[row + i][column + j].barraksSentinel.get(0).path.equals(path)) {
                                    attackerRange.add(map.getGameBoard()[row + i][column + j]);
                                }
                            }
                        }
                    }

                }
            }
        }
        return attackerRange;
    }

    public Cell minDistanceTower(ArrayList<Cell> attackerRangeTower) {
        int minDistance = 10;
        for (Cell towerCell : attackerRangeTower) {
            if (Math.max(Math.abs(towerCell.getRow() - row), Math.abs(towerCell.getColumn() - column)) < minDistance) {
                minDistance = Math.max(Math.abs(towerCell.getRow() - row), Math.abs(towerCell.getColumn() - column));
            }
        }
        for (Cell towerCell : attackerRangeTower) {
            if (minDistance == Math.max(Math.abs(towerCell.getRow() - row), Math.abs(towerCell.getColumn() - column))) {
                return towerCell;
            }
        }
        return null;
    }

    public ArrayList<Cell> minDistanceForces(ArrayList<Cell> attackerRangeAttacker) {
        ArrayList<Cell> attackerRangeAtackerDistance = new ArrayList<>();

        int minDistance = 10;
        for (Cell attackerCell : attackerRangeAttacker) {
            if (Math.max(Math.abs(attackerCell.getRow() - row), Math.abs(attackerCell.getColumn() - column)) < minDistance) {
                minDistance = Math.max(Math.abs(attackerCell.getRow() - row), Math.abs(attackerCell.getColumn() - column));
            }
        }
        for (Cell attackerCell : attackerRangeAttacker) {
            if (minDistance == Math.max(Math.abs(attackerCell.getRow() - row), Math.abs(attackerCell.getColumn() - column))) {
                attackerRangeAtackerDistance.add(attackerCell);
            }
        }
        return attackerRangeAtackerDistance;
    }

    public Cell getTarget(Map map) {
        ArrayList<Cell> attackerRange = getInRange(map);
        ArrayList<Cell> attackerRangeTower = new ArrayList<>();
        ArrayList<Cell> attackerRangeAttacker = new ArrayList<>();
        ArrayList<Cell> attackerRangeAtackerDistance = new ArrayList<>();
        ArrayList<Cell> attackerRangeAtackerDistanceValue = new ArrayList<>();
        int minDistance = 10;
        int maxValue = 0;

        if (teamID == 0) {
            //tower
            for (int i = 0; i < attackerRange.size(); i++) {
                if (attackerRange.get(i).towerBlack.size() != 0 || attackerRange.get(i).towerPoison.size() != 0) {
                    attackerRangeTower.add(attackerRange.get(i));
                }
            }//one tower
            if (attackerRangeTower.size() == 1) {
                return attackerRangeTower.get(0);
            }//nearest tower
            if (attackerRangeTower.size() > 1) {
                return minDistanceTower(attackerRangeTower);
            }
            //forces
            for (int i = 0; i < attackerRange.size(); i++) {
                if (attackerRange.get(i).attackerScourgeTank.size() != 0 || attackerRange.get(i).attackerScourgeInfantry.size() != 0
                        || attackerRange.get(i).venomancer.size() != 0) {
                    attackerRangeAttacker.add(attackerRange.get(i));
                }
            }//one forces
            if (attackerRangeAttacker.size() == 1) {
                return attackerRangeAttacker.get(0);
            }
            if (attackerRangeAttacker.size() > 1) {
                attackerRangeAtackerDistance = minDistanceForces(attackerRangeAttacker);
            }//nearest
            if (attackerRangeAtackerDistance.size() == 1) {
                return attackerRangeAtackerDistance.get(0);
            }
            //most value
            if (attackerRangeAtackerDistance.size() > 1) {
                for (int i = 0; i < attackerRangeAtackerDistance.size(); i++) {
                    if (attackerRangeAtackerDistance.get(i).attackerScourgeTank.size() != 0 ||
                            attackerRangeAtackerDistance.get(i).attackerScourgeInfantry.size() != 0) {
                        attackerRangeAtackerDistanceValue.add(attackerRangeAtackerDistance.get(i));
                    }
                }
            }
            maxValue = 0;
            ArrayList<Integer> attackerValue = new ArrayList<>();
            for (int i = 0; i < attackerRangeAtackerDistanceValue.size(); i++) {
                attackerValue.add(0);
                for (int k = 0; k < attackerRangeAtackerDistanceValue.get(i).attackerScourgeTank.size(); k++) {
                    attackerValue.set(i, (attackerValue.get(i) + (int) attackerRangeAtackerDistanceValue.get(i).attackerScourgeTank.get(k).value));
                }
                for (int k = 0; k < attackerRangeAtackerDistanceValue.get(i).attackerScourgeInfantry.size(); k++) {
                    attackerValue.set(i, (attackerValue.get(i) + (int) attackerRangeAtackerDistanceValue.get(i).attackerScourgeInfantry.get(k).value));
                }
                if (attackerValue.get(i) > maxValue) {
                    maxValue = attackerValue.get(i);
                }
            }
            for (int i = 0; i < attackerRangeAtackerDistanceValue.size(); i++) {
                if (attackerValue.get(i) == maxValue) {
                    return attackerRangeAtackerDistanceValue.get(i);
                }
            }
            //barracks and ancient
            for (int i = 0; i < attackerRange.size(); i++) {
                if (attackerRange.get(i).barraksScourge.size() != 0) {
                    return attackerRange.get(i);
                } else if (attackerRange.get(i).barraksScourge.size() != 0) {
                    return attackerRange.get(i);
                } else if (attackerRange.get(i).barraksScourge.size() != 0) {
                    return attackerRange.get(i);
                } else if (attackerRange.get(i).ancientScourge.size() != 0) {
                    return attackerRange.get(i);
                }
            }


        }
        if (teamID == 1) {
            //tower
            for (int i = 0; i < attackerRange.size(); i++) {
                if (attackerRange.get(i).towerStone.size() != 0 || attackerRange.get(i).towerFire.size() != 0) {
                    attackerRangeTower.add(attackerRange.get(i));
                }
            }//one tower
            if (attackerRangeTower.size() == 1) {
                return attackerRangeTower.get(0);
            }//nearest tower
            if (attackerRangeTower.size() > 1) {
                return minDistanceTower(attackerRangeTower);
            }
            //forces
            for (int i = 0; i < attackerRange.size(); i++) {
                if (attackerRange.get(i).attackerSentinelTank.size() != 0 || attackerRange.get(i).attackerSentinelInfantry.size() != 0
                        || attackerRange.get(i).tiny.size() != 0) {
                    attackerRangeAttacker.add(attackerRange.get(i));
                }
            }//one forces
            if (attackerRangeAttacker.size() == 1) {
                return attackerRangeAttacker.get(0);
            }
            if (attackerRangeAttacker.size() > 1) {
                attackerRangeAtackerDistance = minDistanceForces(attackerRangeAttacker);
            }//nearest
            if (attackerRangeAtackerDistance.size() == 1) {
                return attackerRangeAtackerDistance.get(0);
            }
            //most value
            if (attackerRangeAtackerDistance.size() > 1) {
                for (int i = 0; i < attackerRangeAtackerDistance.size(); i++) {
                    if (attackerRangeAtackerDistance.get(i).attackerSentinelTank.size() != 0 ||
                            attackerRangeAtackerDistance.get(i).attackerSentinelInfantry.size() != 0) {
                        attackerRangeAtackerDistanceValue.add(attackerRangeAtackerDistance.get(i));
                    }
                }
            }
            maxValue = 0;
            ArrayList<Integer> attackerValue = new ArrayList<>();
            for (int i = 0; i < attackerRangeAtackerDistanceValue.size(); i++) {
                attackerValue.add(0);
                for (int k = 0; k < attackerRangeAtackerDistanceValue.get(i).attackerSentinelTank.size(); k++) {
                    attackerValue.set(i, (attackerValue.get(i) + (int) attackerRangeAtackerDistanceValue.get(i).attackerSentinelTank.get(k).value));
                }
                for (int k = 0; k < attackerRangeAtackerDistanceValue.get(i).attackerSentinelInfantry.size(); k++) {
                    attackerValue.set(i, (attackerValue.get(i) + (int) attackerRangeAtackerDistanceValue.get(i).attackerSentinelInfantry.get(k).value));
                }
                if (attackerValue.get(i) > maxValue) {
                    maxValue = attackerValue.get(i);
                }
            }
            for (int i = 0; i < attackerRangeAtackerDistanceValue.size(); i++) {
                if (attackerValue.get(i) == maxValue) {
                    return attackerRangeAtackerDistanceValue.get(i);
                }
            }
            //barracks and ancient
            for (int i = 0; i < attackerRange.size(); i++) {
                if (attackerRange.get(i).barraksSentinel.size() != 0) {
                    return attackerRange.get(i);
                } else if (attackerRange.get(i).barraksSentinel.size() != 0) {
                    return attackerRange.get(i);
                } else if (attackerRange.get(i).barraksSentinel.size() != 0) {
                    return attackerRange.get(i);
                } else if (attackerRange.get(i).ancientSentinel.size() != 0) {
                    return attackerRange.get(i);
                }
            }


        }


        return null;
    }

    public void attack(Map map) {
        Cell target = getTarget(map);
        if (target != null) {
            if (teamID == 0) {
                if (target.attackerScourgeInfantry != null && target.attackerScourgeInfantry.size() != 0) {
                    for (int i = 0; i < target.attackerScourgeInfantry.size(); i++) {
                        target.attackerScourgeInfantry.get(i).health -= damage;
                        if (target.attackerScourgeInfantry.get(i).health <= 0) {
                            target.attackerScourgeInfantry.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() +
                                    (int) target.attackerScourgeInfantry.get(i).value);
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerScourgeInfantry.remove(i);
                        }
                    }
                }
                if (target.attackerScourgeTank.size() != 0) {
                    for (int i = 0; i < target.attackerScourgeTank.size(); i++) {
                        target.attackerScourgeTank.get(i).health -= damage;
                        if (target.attackerScourgeTank.get(i).health <= 0) {
                            target.attackerScourgeTank.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() +
                                    (int) target.attackerScourgeTank.get(i).value);
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerScourgeTank.remove(i);
                        }
                    }
                }
                if (target.towerBlack.size() != 0) {
                    for (int i = 0; i < target.towerBlack.size(); i++) {
                        target.towerBlack.get(i).health -= damage;
                        if (target.towerBlack.get(i).health <= 0) {
                            target.towerBlack.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() +
                                    (int) target.towerBlack.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].towerBlack.remove(i);
                        }
                    }
                }
                if (target.towerPoison.size() != 0) {
                    for (int i = 0; i < target.towerPoison.size(); i++) {
                        target.towerPoison.get(i).health -= damage;
                        if (target.towerPoison.get(i).health <= 0) {
                            target.towerPoison.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() +
                                    (int) target.towerPoison.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].towerPoison.remove(i);
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
                if (target.barraksScourge.size() != 0) {
                    target.barraksScourge.get(0).health -= damage;
                    if (target.barraksScourge.get(0).health <= 0) {
                        target.barraksScourge.get(0).isAlive = false;
                        map.getGameBoard()[target.getRow()][target.getColumn()].barraksScourge.remove(0);
                    }
                }
                if (target.ancientScourge.size() != 0) {
                    target.ancientScourge.get(0).health -= damage;
                    if (target.ancientScourge.get(0).health <= 0) {
                        target.ancientScourge.get(0).isAlive = false;
                        map.getGameBoard()[target.getRow()][target.getColumn()].ancientScourge.remove(0);
                    }
                }
            }
            if (teamID == 1) {
                if (target.attackerSentinelInfantry.size() != 0) {
                    for (int i = 0; i < target.attackerSentinelInfantry.size(); i++) {
                        target.attackerSentinelInfantry.get(i).health -= damage;
                        if (target.attackerSentinelInfantry.get(i).health <= 0) {
                            target.attackerSentinelInfantry.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() +
                                    (int) target.attackerSentinelInfantry.get(i).value);
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerSentinelInfantry.remove(i);
                        }
                    }
                }
                if (target.attackerSentinelTank.size() != 0) {
                    for (int i = 0; i < target.attackerSentinelTank.size(); i++) {
                        target.attackerSentinelTank.get(i).health -= damage;
                        if (target.attackerSentinelTank.get(i).health <= 0) {
                            target.attackerSentinelTank.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() +
                                    (int) target.attackerSentinelTank.get(i).value);
                            map.getGameBoard()[target.getRow()][target.getColumn()].attackerSentinelTank.remove(i);
                        }
                    }
                }
                if (target.towerStone.size() != 0) {
                    for (int i = 0; i < target.towerStone.size(); i++) {
                        target.towerStone.get(i).health -= damage;
                        if (target.towerStone.get(i).health <= 0) {
                            target.towerStone.get(i).isAlive = false;
                            map.getAncient2()[0].setTreasury(map.getAncient1()[0].getTreasury() +
                                    (int) target.towerStone.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].towerStone.remove(i);
                        }
                    }
                }
                if (target.towerFire.size() != 0) {
                    for (int i = 0; i < target.towerFire.size(); i++) {
                        target.towerFire.get(i).health -= damage;
                        if (target.towerFire.get(i).health <= 0) {
                            target.towerFire.get(i).isAlive = false;
                            map.getAncient1()[0].setTreasury(map.getAncient1()[0].getTreasury() +
                                    (int) target.towerFire.get(i).getValue());
                            map.getGameBoard()[target.getRow()][target.getColumn()].towerFire.remove(i);
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
                if (target.barraksSentinel.size() != 0) {
                    target.barraksSentinel.get(0).health -= damage;
                    if (target.barraksSentinel.get(0).health <= 0) {
                        target.barraksSentinel.get(0).isAlive = false;
                        map.getGameBoard()[target.getRow()][target.getColumn()].barraksSentinel.remove(0);
                    }
                }
                if (target.ancientSentinel.size() != 0) {
                    target.ancientSentinel.get(0).health -= damage;
                    if (target.ancientSentinel.get(0).health <= 0) {
                        target.ancientSentinel.get(0).isAlive = false;
                        map.getGameBoard()[target.getRow()][target.getColumn()].ancientSentinel.remove(0);
                    }
                }
            }
        }
    }

    public void moveAttacker(Map map) {

        for (int j = 0; j < lane.getCells().size(); j++) {
            if (lane.getCells().get(j).getColumn() == column && lane.getCells().get(j).getRow() == row) {
                if (teamID == 0 && !(row == lane.getCells().get(lane.getCells().size() - 1).getRow() &&
                        column == lane.getCells().get(lane.getCells().size() - 1).getColumn())) {
                    if (attackerType == 8) {
                        map.getGameBoard()[row][column].attackerSentinelInfantry.remove(this);
                        column = lane.getCells().get(j + 1).getColumn();
                        row = lane.getCells().get(j + 1).getRow();
                        map.getGameBoard()[row][column].attackerSentinelInfantry.add(this);
                        return;
                    }
                    if (attackerType == 9) {
                        map.getGameBoard()[row][column].attackerSentinelTank.remove(this);
                        column = lane.getCells().get(j + 1).getColumn();
                        row = lane.getCells().get(j + 1).getRow();
                        map.getGameBoard()[row][column].attackerSentinelTank.add(this);
                        return;
                    }
                }
                if (teamID == 1 && !(row == lane.getCells().get(0).getRow() &&
                        column == lane.getCells().get(0).getColumn())) {
                    if (attackerType == 8) {
                        map.getGameBoard()[row][column].attackerScourgeInfantry.remove(this);
                        column = lane.getCells().get(j - 1).getColumn();
                        row = lane.getCells().get(j - 1).getRow();
                        map.getGameBoard()[row][column].attackerScourgeInfantry.add(this);
                        return;
                    }
                    if (attackerType == 9) {
                        map.getGameBoard()[row][column].attackerScourgeTank.remove(this);
                        column = lane.getCells().get(j - 1).getColumn();
                        row = lane.getCells().get(j - 1).getRow();
                        map.getGameBoard()[row][column].attackerScourgeTank.add(this);
                        return;
                    }
                }
            }
        }
    }
}