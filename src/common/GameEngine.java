package common;

import common.event.*;
import common.exception.DotaExceptionBase;

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
    private EventHandler handler = new EventHandler();

    public void makeMap (int row, int column, ArrayList<ArrayList<Cell>> path1, ArrayList<ArrayList<Cell>> path2,
                         ArrayList<ArrayList<Cell>> path3, Cell[][] ancient1, Cell[][] ancient2, ArrayList<Cell[][]> barraks1,
                         ArrayList<Cell[][]> barraks2, ArrayList<Cell> goldMines){
        map = new Map(row, column, path1, path2, path3, ancient1, ancient2, barraks1, barraks2, goldMines);
        this.handler.setMap(map);

        //ancient sentinel money event
        Ancient ancientSentinel = map.getAncient1()[0];
        AncientMoneyEvent sentinelEvent = new AncientMoneyEvent(ancientSentinel);
        handler.getEventsQueue().add(sentinelEvent);

        //ancinet scourge money event
        Ancient ancientScourge = map.getAncient2()[0];
        AncientMoneyEvent scourgeEvent = new AncientMoneyEvent(ancientScourge);
        handler.getEventsQueue().add(scourgeEvent);

        //heroes life event for checking death
        Hero tiny = map.getTiny();
        HeroLifeEvent tinyEvent = new HeroLifeEvent(tiny);
        handler.getEventsQueue().add(tinyEvent);

        Hero venomancer = map.getVenomancer();
        HeroLifeEvent venomEvent = new HeroLifeEvent(venomancer);
        handler.getEventsQueue().add(venomEvent);

    }

    public Tower createTower(int teamID, int towerType, Path path,Lane lane,int index, int row, int column, int time) {

        Tower tower = new Tower(teamID,towerType,path,lane,index,row,column,time,map);
        TowerRangeAttackEvent towerEvent = new TowerRangeAttackEvent(tower);
        handler.getEventsQueue().add(towerEvent);

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
        AttackForceEvent attackEvent = new AttackForceEvent(attackForce);
        handler.getEventsQueue().add(attackEvent);

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


    //hero morde = 0
    //hero zende = 1
    //hero zendeo dargire move = 2
    //hero zende o dargire hamle = 3
    public void callHeroMove(Hero hero , Cell dest , int direction , Map map) throws DotaExceptionBase{
        if (hero.getFlag() == 1 || hero.getFlag() == 2) {
            hero.heroMove(dest, direction, map);
            HeroMoveEvent moveEvent = new HeroMoveEvent(hero);
            handler.getEventsQueue().add(moveEvent);
        }
        //yaani faghat zamani ke hero faghat zendas ye bar seda mizane tabe ro va baadesh oun etefagh flag ro 2 mikone o dg ejazeye
        //seda zadane tabe ro nemide be ma ta zamani ke vaghtesh bere
    }

    public void callHeroAttack(Hero hero , Cell target , Map map) throws DotaExceptionBase{
        if (hero.getFlag() == 1){
            hero.attack(target,map);
            HeroAttackEvent attackEvent = new HeroAttackEvent(hero);
            handler.getEventsQueue().add(attackEvent);
        }//mese manteghe bala
    }

    public EventHandler getHandler() {
        return handler;
    }
}
