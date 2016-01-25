package judge;

import common.*;
import common.exception.DotaExceptionBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Judge extends JudgeAbstract {

    public int time;
    HashMap<Tower, GameObjectID> towers = new HashMap<>();
    HashMap<AttackForces, GameObjectID> attackers = new HashMap<>();
    HashMap<Ancient[], GameObjectID[]> ancients = new HashMap<>();
    HashMap<Barraks[], GameObjectID[]> barrakses = new HashMap<>();
    HashMap<Path, GameObjectID> paths = new HashMap<>();
    HashMap<GoldMine, GameObjectID> goldMines = new HashMap<>();
    HashMap<Hero, GameObjectID> heroes = new HashMap<>();
    HashMap<Lane[], GameObjectID[]> pathLanesID = new HashMap<>();
    HashMap<String, Integer> info = new HashMap<>();
    GameEngine engine = new GameEngine();
    boolean tinyTarget;
    boolean venomancerTarget;
    boolean tinyMove;
    boolean venomancerMove;
    int tinyMoveTime;
    int venomancerMoveTime;

    public void makeGoldHashMap() {
        for (int i = 0; i < engine.map.getGoldMines().size(); i++) {
            GoldMine goldMine = engine.map.getGoldMines().get(i);
            GameObjectID g12 = GameObjectID.create(GoldMine.class);
            goldMines.put(goldMine, g12);
        }
    }

    public void makeLaneHashMap() {
        //1
        Lane[] path1Lanes = engine.map.getPath1();
                /*get lane ha bayad bashe bepors bebin mituni berizi tu araye ya na;*/
        GameObjectID[] g13 = new GameObjectID[5];
        for (int i = 0; i < 5; i++) {
            g13[i] = GameObjectID.create(Lane.class);
        }
        pathLanesID.put(path1Lanes, g13);

        //2
        Lane[] path2Lanes = engine.getPath2Lanes();
        GameObjectID[] g14 = new GameObjectID[5];
        for (int i = 0; i < 5; i++) {
            g14[i] = GameObjectID.create(Lane.class);
        }
        pathLanesID.put(path2Lanes, g14);

        //3
        Lane[] path3Lanes = engine.map.getPath3Lanes();
        GameObjectID[] g15 = new GameObjectID[5];
        for (int i = 0; i < 5; i++) {
            g15[i] = GameObjectID.create(Lane.class);
        }
        pathLanesID.put(path3Lanes, g15);
    }

    public void makeBuildingsHashMap() {
        Ancient[] ancient1 = engine.map.getAncient1();
        GameObjectID[] g1 = new GameObjectID[1];
        g1[0] = GameObjectID.create(Ancient.class);
        ancients.put(ancient1, g1);

        Ancient[] ancient2 = engine.map.getAncient2();
        GameObjectID[] g2 = new GameObjectID[1];
        g2[0] = GameObjectID.create(Ancient.class);
        ancients.put(ancient2, g2);

        Barraks[] barraks1 = engine.map.getBarraks1();
        Barraks[] barraks2 = engine.map.getBarraks2();
        GameObjectID[] g3 = new GameObjectID[3];
        GameObjectID[] g4 = new GameObjectID[3];
        for (int i = 0; i < 3; i++) {
            g3[i] = GameObjectID.create(Barraks.class);
            g4[i] = GameObjectID.create(Barraks.class);
        }
        barrakses.put(barraks1, g3);
        barrakses.put(barraks2, g4);
    }

    public void makePathHashMap() {
        Path path1 = engine.map.getPath1();
        Path path2 = engine.map.getPath2();
        Path path3 = engine.map.getPath3();
        GameObjectID g5 = GameObjectID.create(Path.class);
        GameObjectID g6 = GameObjectID.create(Path.class);
        GameObjectID g7 = GameObjectID.create(Path.class);
        paths.put(path1, g5);
        paths.put(path2, g6);
        paths.put(path3, g7);
    }

//    public void makeHeroHashmap() {
//        Hero tiny = engine.getTiny();
//        GameObjectID g8 = GameObjectID.create(Hero.class);
//        heroes.put(tiny, g8);
//
//        Hero venomancer = engine.getVenomancer();
//        GameObjectID g9 = GameObjectID.create(Hero.class);
//        heroes.put(venomancer, g9);
//
//    }

    @Override
    public void loadMap(int columns, int rows, ArrayList<ArrayList<Cell>> path1, ArrayList<ArrayList<Cell>> path2, ArrayList<ArrayList<Cell>> path3,
                        Cell[][] ancient1, Cell[][] ancient2, ArrayList<Cell[][]> barraks1, ArrayList<Cell[][]> barraks2, ArrayList<Cell> goldMines) {
        engine.makeMap(columns, rows, path1, path2, path3, ancient1, ancient2, barraks1, barraks2, goldMines);
        makeGoldHashMap();
        makeBuildingsHashMap();
        makePathHashMap();
        makeLaneHashMap();

        Hero hero = engine.map.getTiny();
        GameObjectID g10 = GameObjectID.create(Hero.class);
        heroes.put(hero, g10);

        Hero hero2 = engine.map.getVenomancer();
        GameObjectID g11 = GameObjectID.create(Hero.class);
        heroes.put(hero2, g11);
    }
    @Override
    public void setup() {

        time = 0;
        tinyMoveTime = 0;
        venomancerMoveTime = 0;
        tinyTarget = false;
        venomancerTarget = false;
        tinyMove = false;
        venomancerMove = false;

//        gameEvents event1 = new gameEvents(engine.ancient1[0], eventHandler);
//        gameEvents event2 = new gameEvents(engine.ancient2[0], eventHandler);
//        gameEvents event3 = new gameEvents(engine.getTiny(), eventHandler);
//        gameEvents event4 = new gameEvents(engine.getVenomancer(), eventHandler);
//
//        event1.start();
//        event2.start();
//        event3.start();
//        event4.start();
    }
    @Override
    public int getMapHeight() {
        return engine.map.getColumn();
    }

    @Override
    public int getMapWidth() {
        return engine.map.getRow();
    }

    @Override
    public GameObjectID getGoldMineID(int goldMineNumber) throws DotaExceptionBase {
        if (goldMineNumber > engine.map.getGoldMines().size()) {
            throw new DotaExceptionBase();
        }
        GoldMine goldmine = engine.map.getGoldMines().get(goldMineNumber);
        return goldMines.get(goldmine);
    }

    @Override
    public GameObjectID[] getBuildingID(int teamID, int buildingType) throws DotaExceptionBase {
        if (teamID != 0 && teamID != 1) {
            throw new DotaExceptionBase();
        }
        if (buildingType != 6 && buildingType != 7) {
            throw new DotaExceptionBase();
        }
        if (buildingType == 6) {//ancient

            if (teamID == 0) {//Sentinel
                Ancient[] ancient1 = engine.map.getAncient1();
                return ancients.get(ancient1);
            } else {//Scourge
                Ancient[] ancient2 = engine.map.getAncient2();
                return ancients.get(ancient2);
            }

        } else if (buildingType == 7) {//barraks
            Barraks[] barrak1 = engine.map.getBarraks1();
            Barraks[] barrak2 = engine.map.getBarraks2();
            if (teamID == 0) {
                return barrakses.get(barrak1);
            } else {
                return barrakses.get(barrak2);
            }
        }
        return null;
    }

    @Override
    public GameObjectID getPathID(int pathNumber) {
        Path path1 = engine.map.getPath1();
        Path path2 = engine.map.getPath2();
        Path path3 = engine.map.getPath3();
        if (pathNumber == 1) {
            return paths.get(path1);
        } else if (pathNumber == 2) {
            return paths.get(path2);
        } else {
            return paths.get(path3);
        }
    }

    @Override
    public GameObjectID[] getLaneID(int pathNumber) {
        if (pathNumber == 1) {
            Lane[] pathLanes1 = engine.map.getPath1Lanes();
            return pathLanesID.get(pathLanes1);

        } else if (pathNumber == 2) {
            Lane[] pathLanes2 = engine.map.getPath1Lanes();
            return pathLanesID.get(pathLanes2);
        } else {
            Lane[] pathLanes3 = engine.map.getPath1Lanes();
            return pathLanesID.get(pathLanes3);
        }
    }

    @Override
    public GameObjectID getHeroID(int teamID, int heroID) {
        if (teamID == 0) {
            return heroes.get(engine.map.getTiny());
        } else if (teamID == 1) {
            return heroes.get(engine.map.getVenomancer());
        }
        return null;
    }

    @Override
    public GameObjectID createAttacker(int teamID, int attackerType, GameObjectID path, GameObjectID lane, int rowNumber, int colNumber) throws DotaExceptionBase {
        if (teamID != 0 && teamID != 1) {
            throw new DotaExceptionBase();
        }
        if (attackerType != 8 && attackerType != 9) {
            throw new DotaExceptionBase();
        }
        if (rowNumber > engine.map.getRow() - 1 || colNumber > engine.map.getColumn() - 1 || rowNumber < 0 || colNumber < 0) {
            throw new DotaExceptionBase();
        }
        //find path
        Path mypath;
        for (Map.Entry<Path, GameObjectID> entry : paths.entrySet()) {
                if (Objects.equals(path, entry.getValue())) {
                    mypath = entry.getKey();
                }
        }

        AttackForces attacker = engine.createAttacker(teamID,attackerType,mypath,lane,rowNumber,colNumber,time);
//        gameEvents event = new gameEvents(attacker, eventHandler);
//        event.start();
        GameObjectID g10 = GameObjectID.create(AttackForces.class);
        attackers.put(attacker, g10);
        return attackers.get(attacker);
    }

    @Override
    public GameObjectID createTower(int teamID, int towerType, GameObjectID path, GameObjectID lane, int index, int rowNumber, int colNumber) throws DotaExceptionBase {
        Lane mylane = null;
        if (teamID != 0 && teamID != 1) {
            throw new DotaExceptionBase();
        }
        if (towerType != 0 && towerType != 1 && towerType != 2 && towerType != 3) {
            throw new DotaExceptionBase();
        }
        if (rowNumber > engine.map.getRow() - 1 || colNumber > engine.map.getColumn() - 1 || rowNumber < 0 || colNumber < 0) {
            throw new DotaExceptionBase();
        }
        for (Map.Entry<Lane[], GameObjectID[]> entry : pathLanesID.entrySet()) {
            for (int i = 0; i < entry.getKey().length; i++) {
                if (Objects.equals(lane, entry.getValue()[i])) {
//                    if (i != 3) {
//                        throw new DotaExceptionBase();
//                    }
                    mylane = entry.getKey()[i];
                }
            }
        }
        if (mylane == null) {
            throw new DotaExceptionBase();
        }
        int tempNumber = 0;
        for (int i = 0; i < mylane.getCells().size(); i++) {
            if (mylane.getCells().get(i).getColumn() == colNumber && mylane.getCells().get(i).getRow() == rowNumber) {
                tempNumber = i;
            }
        }
        if (teamID == 0) {
            if (tempNumber > mylane.getCells().size() / 2) {
                throw new DotaExceptionBase();
            }
        }
        if (teamID == 1) {

            if (tempNumber < mylane.getCells().size() / 2) {
                throw new DotaExceptionBase();
            }

        }
        //az path o lane ina estefade mishe?
        Tower tower = engine.createTower(teamID, towerType, rowNumber, colNumber, time);
//        gameEvents event = new gameEvents(tower, eventHandler);
//        event.start();
        GameObjectID g11 = GameObjectID.create(Tower.class);
        towers.put(tower, g11);
        return towers.get(tower);
    }

    @Override
    public void purchaseAttackersPowerup(int teamID, int powerupType) throws DotaExceptionBase {
        if (powerupType == 2) {
            if (teamID == 0) {
                AttackForces.upgradePower(engine.map, teamID);
            } else if (teamID == 1) {
                AttackForces.upgradePower(engine.map, teamID);
            }
        } else if (powerupType == 3) {
            if (teamID == 0) {
                AttackForces.upgradeHealth(engine.map, teamID);
            } else if (teamID == 1) {
                AttackForces.upgradeHealth(engine.map, teamID);
            }
        }
    }

    @Override
    public void purchaseTowerPowerup(int teamID, GameObjectID towerID, int powerupType) throws DotaExceptionBase {
        if (powerupType == 0) {
            if (teamID == 0) {
                for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                    if (Objects.equals(towerID, entry.getValue())) {
                        entry.getKey().upgradePower(engine.map);
                    }
                }
            } else if (teamID == 1) {
                for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                    if (Objects.equals(towerID, entry.getValue())) {
                        entry.getKey().upgradePower(engine.map);
                    }
                }
            }
        } else if (powerupType == 1) {
            if (teamID == 0) {
                for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                    if (Objects.equals(towerID, entry.getValue())) {
                        entry.getKey().upgradeRange(engine.map);
                    }
                }
            } else if (teamID == 1) {
                for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                    if (Objects.equals(towerID, entry.getValue())) {
                        entry.getKey().upgradeRange(engine.map);
                    }
                }
            }
        }
    }

    @Override
    public GameObjectID heroMove(GameObjectID hero, Cell dest, int direction) throws DotaExceptionBase {

        for (Map.Entry<Hero, GameObjectID> entry : heroes.entrySet()) {
            if (Objects.equals(hero, entry.getValue())) {
                if ((entry.getKey().getTeamID() == 0 && tinyMove == true) || (entry.getKey().getTeamID() == 1 && venomancerMove == true)) {
                    entry.getKey().heroMove(dest, direction, engine.map);
//                    for (int i = 0; i < eventHandler.gameEvents.size(); i++) {
//                        if (eventHandler.gameEvents.get(i).getFlag() == 1) {
//                            if (eventHandler.gameEvents.get(i).hero.team_ID == entry.getKey().team_ID) {
//                                eventHandler.gameEvents.get(i).hero.heroMove(dest, direction, engine);
//                            }
//                        }
//                    }
                    return heroes.get(entry.getKey());
                }
            }
        }
        return null;
    }

    @Override
    public GameObjectID heroAttack(GameObjectID hero, Cell target) throws DotaExceptionBase {
        for (Map.Entry<Hero, GameObjectID> entry : heroes.entrySet()) {
            if (Objects.equals(hero, entry.getValue())) {
                if (entry.getKey().getTeamID() == 0) {
                    tinyTarget = true;
                }
                if (entry.getKey().getTeamID() == 1) {
                    venomancerTarget = true;
                }
                entry.getKey().attack(target, engine.map);
//                for (int i = 0; i < eventHandler.gameEvents.size(); i++) {
//                    if (eventHandler.gameEvents.get(i).getFlag() == 1) {
//                        if (eventHandler.gameEvents.get(i).hero.team_ID == entry.getKey().team_ID) {
//                            eventHandler.gameEvents.get(i).hero.attack(target, engine);
//                        }
//                    }
//                }
                return heroes.get(entry.getKey());
            }
        }
        return null;
    }

    @Override
    public int getMoney(int teamID) {
        if (teamID == 0) {
            engine.map.getAncient1()[0].getTreasury();
        }
        if (teamID == 1) {
            engine.map.getAncient2()[0].getTreasury();
        }
        return 0;
    }

    @Override
    public ArrayList<Integer> getAttackerPowerups(int teamID) {
        if (teamID == 0) {
            return AttackForces.getAttackerPowerUpsSentinel();
        } else {
            return AttackForces.getAttackerPowerUpsScourge();
        }
    }

    @Override
    public ArrayList<GameObjectID> getTeamGoldMines(int teamID) throws DotaExceptionBase {
        ArrayList<GameObjectID> teamGoldMines = new ArrayList<>();
        for (int i = 0; i < engine.map.getGoldMines().size(); i++) {
            if (engine.map.getGoldMines().get(i).getTeamID() == teamID) {
                teamGoldMines.add(goldMines.get(engine.map.getGoldMines().get(i)));
            }
        }
        return teamGoldMines;
    }

    @Override
    public HashMap<String, Integer> getInfo(GameObjectID id) throws DotaExceptionBase {
        GameObjectID[] tempID = new GameObjectID[1];
        tempID[0] = id;
        info.clear();

        if (ancients.containsValue(tempID)) {
            for (Map.Entry<Ancient[], GameObjectID[]> entry : ancients.entrySet()) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    if (Objects.equals(id, entry.getValue()[i])) {
                        Ancient ancient = entry.getKey()[i];
                        info.put("id", ancient.getTeamID());
                        info.put("health", ancient.getHealth());
                        if (ancient.isAlive()){
                            info.put("alive", 1);
                        }else {
                            info.put("alive", 0);
                        }

                    }
                }

            }
        } else if (barrakses.containsValue(tempID)) {
            for (Map.Entry<Barraks[], GameObjectID[]> entry : barrakses.entrySet()) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    if (Objects.equals(id, entry.getValue()[i])) {
                        Barraks barraks = entry.getKey()[i];
                        info.put("id", barraks.getTeamID());
                        info.put("health", barraks.getHealth());
                        if (barraks.isAlive()){
                            info.put("alive", 1);
                        }else {
                            info.put("alive", 0);
                        }

                    }
                }

            }

        } else if (towers.containsValue(id)) {
            for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    Tower tower = entry.getKey();
                    info.put("id", tower.getTeamID());
                    info.put("health", tower.getHealth());
                    info.put("range", tower.getRange());
                    info.put("time", tower.getReloadTime());
                    info.put("row", tower.getRow());
                    info.put("col", tower.getColumn());
                    info.put("value", (int) tower.getValue());
                    if (tower.isAlive()){
                        info.put("alive", 1);
                    }else {
                        info.put("alive", 0);
                    }
                    info.put("IA", tower.getInfantryAttackPower());
                    info.put("TA", tower.getTankAttackPower());
                }
            }
        } else if (attackers.containsValue(id)) {
            for (Map.Entry<AttackForces, GameObjectID> entry : attackers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    AttackForces attacker = entry.getKey();
                    info.put("id", attacker.getTeamID());
                    info.put("health", attacker.getHealth());
                    info.put("range", attacker.getRange());
                    info.put("time", attacker.getReloadTime());
                    info.put("row", attacker.getRow());
                    info.put("col", attacker.getColumn());
                    info.put("value", (int) attacker.getValue());
                    if (attacker.isAlive()){
                        info.put("alive", 1);
                    }else {
                        info.put("alive", 0);
                    }
                    info.put("speed", attacker.getSpeed());
                    info.put("attack", attacker.getDamage());
                }
            }

        } else if (heroes.containsValue(id)) {

            for (Map.Entry<Hero, GameObjectID> entry : heroes.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    Hero hero = entry.getKey();
                    info.put("id", hero.getTeamID());
                    info.put("health", hero.getHealth());
                    info.put("range", hero.getRange());
                    info.put("time", hero.getReloadTime());
                    info.put("row", hero.getRow());
                    info.put("col", hero.getColumn());
                    if (hero.isAlive()){
                        info.put("alive", 1);
                    }else {
                        info.put("alive", 0);
                    }
                    info.put("speed", hero.getSpeed());
                    info.put("attack", hero.getSpeed());
                }
            }
        } else {
            throw new DotaExceptionBase();
        }
        return info;
    }

    @Override
    public GameObjectID[] getInRange(GameObjectID id) throws DotaExceptionBase {
        ArrayList<GameObjectID> objects = new ArrayList<>();
        //tush ino dare ke range ro seda bezane o ba oun key ha be in arraye ezafe kone
        if (attackers.containsValue(id)) {
            for (Map.Entry<AttackForces, GameObjectID> entry : attackers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    ArrayList<Cell> rangeCell = entry.getKey().getInRange(engine);
                    if (entry.getKey().getTeamID() == 0) {
                        for (int i = 0; i < rangeCell.size(); i++) {
                            if (rangeCell.get(i).AttackerScourgeInfantry.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).AttackerScourgeInfantry) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Attacker_Scourge_Tank.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Scourge_Tank) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Ancient_Scourge.size() != 0) {
                                for (Ancient ancient : rangeCell.get(i).Ancient_Scourge) {
                                    Ancient[] arrayAncient = new Ancient[1];
                                    arrayAncient[0] = ancient;
                                    objects.add(ancients.get(arrayAncient)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks1_Scourge.size() != 0) {
                                for (Barraks barrak1 : rangeCell.get(i).Barraks1_Scourge) {
                                    Barraks[] arrayBarrak1 = new Barraks[1];
                                    arrayBarrak1[0] = barrak1;
                                    objects.add(ancients.get(arrayBarrak1)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks2_Scourge.size() != 0) {
                                for (Barraks barrak2 : rangeCell.get(i).Barraks2_Scourge) {
                                    Barraks[] arrayBarrak2 = new Barraks[1];
                                    arrayBarrak2[0] = barrak2;
                                    objects.add(ancients.get(arrayBarrak2)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks3_Scourge.size() != 0) {
                                for (Barraks barrak3 : rangeCell.get(i).Barraks3_Scourge) {
                                    Barraks[] arrayBarrak3 = new Barraks[1];
                                    arrayBarrak3[0] = barrak3;
                                    objects.add(ancients.get(arrayBarrak3)[0]);
                                }
                            }
                            if (rangeCell.get(i).Venomancer.size() != 0) {
                                for (Hero venomancer : rangeCell.get(i).Venomancer) {
                                    objects.add(heroes.get(venomancer));
                                }
                            }
                            if (rangeCell.get(i).Tower_Black.size() != 0) {
                                for (Tower tower : rangeCell.get(i).Tower_Black) {
                                    objects.add(towers.get(tower));
                                }
                            }
                            if (rangeCell.get(i).Tower_Poison.size() != 0) {
                                for (Tower tower : rangeCell.get(i).Tower_Poison) {
                                    objects.add(towers.get(tower));
                                }
                            }
                        }
                    }
                    if (entry.getKey().getTeamID() == 1) {
                        for (int i = 0; i < rangeCell.size(); i++) {
                            if (rangeCell.get(i).Attacker_Sentinel_Infantry.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Sentinel_Infantry) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Attacker_Sentinel_Tank.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Sentinel_Tank) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Ancient_Sentinel.size() != 0) {
                                for (Ancient ancient : rangeCell.get(i).Ancient_Sentinel) {
                                    Ancient[] arrayAncient = new Ancient[1];
                                    arrayAncient[0] = ancient;
                                    objects.add(ancients.get(arrayAncient)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks1_Sentinel.size() != 0) {
                                for (Barraks barrak1 : rangeCell.get(i).Barraks1_Sentinel) {
                                    Barraks[] arrayBarrak1 = new Barraks[1];
                                    arrayBarrak1[0] = barrak1;
                                    objects.add(ancients.get(arrayBarrak1)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks2_Sentinel.size() != 0) {
                                for (Barraks barrak2 : rangeCell.get(i).Barraks2_Sentinel) {
                                    Barraks[] arrayBarrak2 = new Barraks[1];
                                    arrayBarrak2[0] = barrak2;
                                    objects.add(ancients.get(arrayBarrak2)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks3_Sentinel.size() != 0) {
                                for (Barraks barrak3 : rangeCell.get(i).Barraks3_Sentinel) {
                                    Barraks[] arrayBarrak3 = new Barraks[1];
                                    arrayBarrak3[0] = barrak3;
                                    objects.add(ancients.get(arrayBarrak3)[0]);
                                }
                            }
                            if (rangeCell.get(i).Tiny.size() != 0) {
                                for (Hero tiny : rangeCell.get(i).Tiny) {
                                    objects.add(heroes.get(tiny));
                                }
                            }
                            if (rangeCell.get(i).Tower_Stone.size() != 0) {
                                for (Tower tower : rangeCell.get(i).Tower_Stone) {
                                    objects.add(towers.get(tower));
                                }
                            }
                            if (rangeCell.get(i).Tower_Fire.size() != 0) {
                                for (Tower tower : rangeCell.get(i).Tower_Fire) {
                                    objects.add(towers.get(tower));
                                }
                            }
                        }
                    }
                }
            }

        } else if (towers.containsValue(id)) {
            for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    ArrayList<Cell> rangeCell = entry.getKey().getInRange(engine);
                    if (entry.getKey().getTeamID() == 0) {
                        for (int i = 0; i < rangeCell.size(); i++) {
                            if (rangeCell.get(i).Attacker_Scourge_Infantry.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Scourge_Infantry) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Attacker_Scourge_Tank.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Scourge_Tank) {
                                    objects.add(attackers.get(attacker));
                                }
                            }

                            if (rangeCell.get(i).Venomancer.size() != 0) {
                                for (Hero venomancer : rangeCell.get(i).Venomancer) {
                                    objects.add(heroes.get(venomancer));
                                }
                            }

                        }
                    }
                    if (entry.getKey().getTeamID()== 1) {
                        for (int i = 0; i < rangeCell.size(); i++) {
                            if (rangeCell.get(i).Attacker_Sentinel_Infantry.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Sentinel_Infantry) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Attacker_Sentinel_Tank.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Sentinel_Tank) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Tiny.size() != 0) {
                                for (Hero tiny : rangeCell.get(i).Tiny) {
                                    objects.add(heroes.get(tiny));
                                }
                            }
                        }
                    }
                }
            }

        } else if (heroes.containsValue(id)) {
            for (Map.Entry<Hero, GameObjectID> entry : heroes.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    ArrayList<Cell> rangeCell = entry.getKey().getInRange(engine);
                    if (entry.getKey().getTeamID() == 0) {
                        for (int i = 0; i < rangeCell.size(); i++) {
                            if (rangeCell.get(i).Attacker_Scourge_Infantry.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Scourge_Infantry) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Attacker_Scourge_Tank.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Scourge_Tank) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Ancient_Scourge.size() != 0) {
                                for (Ancient ancient : rangeCell.get(i).Ancient_Scourge) {
                                    Ancient[] arrayAncient = new Ancient[1];
                                    arrayAncient[0] = ancient;
                                    objects.add(ancients.get(arrayAncient)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks1_Scourge.size() != 0) {
                                for (Barraks barrak1 : rangeCell.get(i).Barraks1_Scourge) {
                                    Barraks[] arrayBarrak1 = new Barraks[1];
                                    arrayBarrak1[0] = barrak1;
                                    objects.add(ancients.get(arrayBarrak1)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks2_Scourge.size() != 0) {
                                for (Barraks barrak2 : rangeCell.get(i).Barraks2_Scourge) {
                                    Barraks[] arrayBarrak2 = new Barraks[1];
                                    arrayBarrak2[0] = barrak2;
                                    objects.add(ancients.get(arrayBarrak2)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks3_Scourge.size() != 0) {
                                for (Barraks barrak3 : rangeCell.get(i).Barraks3_Scourge) {
                                    Barraks[] arrayBarrak3 = new Barraks[1];
                                    arrayBarrak3[0] = barrak3;
                                    objects.add(ancients.get(arrayBarrak3)[0]);
                                }
                            }
                            if (rangeCell.get(i).Venomancer.size() != 0) {
                                for (Hero venomancer : rangeCell.get(i).Venomancer) {
                                    objects.add(heroes.get(venomancer));
                                }
                            }
                            if (rangeCell.get(i).Tower_Black.size() != 0) {
                                for (Tower tower : rangeCell.get(i).Tower_Black) {
                                    objects.add(towers.get(tower));
                                }
                            }
                            if (rangeCell.get(i).Tower_Poison.size() != 0) {
                                for (Tower tower : rangeCell.get(i).Tower_Poison) {
                                    objects.add(towers.get(tower));
                                }
                            }
                        }
                    }
                    if (entry.getKey().getTeamID() == 1) {
                        for (int i = 0; i < rangeCell.size(); i++) {
                            if (rangeCell.get(i).Attacker_Sentinel_Infantry.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Sentinel_Infantry) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Attacker_Sentinel_Tank.size() != 0) {
                                for (AttackForces attacker : rangeCell.get(i).Attacker_Sentinel_Tank) {
                                    objects.add(attackers.get(attacker));
                                }
                            }
                            if (rangeCell.get(i).Ancient_Sentinel.size() != 0) {
                                for (Ancient ancient : rangeCell.get(i).Ancient_Sentinel) {
                                    Ancient[] arrayAncient = new Ancient[1];
                                    arrayAncient[0] = ancient;
                                    objects.add(ancients.get(arrayAncient)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks1_Sentinel.size() != 0) {
                                for (Barraks barrak1 : rangeCell.get(i).Barraks1_Sentinel) {
                                    Barraks[] arrayBarrak1 = new Barraks[1];
                                    arrayBarrak1[0] = barrak1;
                                    objects.add(ancients.get(arrayBarrak1)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks2_Sentinel.size() != 0) {
                                for (Barraks barrak2 : rangeCell.get(i).Barraks2_Sentinel) {
                                    Barraks[] arrayBarrak2 = new Barraks[1];
                                    arrayBarrak2[0] = barrak2;
                                    objects.add(ancients.get(arrayBarrak2)[0]);
                                }
                            }
                            if (rangeCell.get(i).Barraks3_Sentinel.size() != 0) {
                                for (Barraks barrak3 : rangeCell.get(i).Barraks3_Sentinel) {
                                    Barraks[] arrayBarrak3 = new Barraks[1];
                                    arrayBarrak3[0] = barrak3;
                                    objects.add(ancients.get(arrayBarrak3)[0]);
                                }
                            }
                            if (rangeCell.get(i).Tiny.size() != 0) {
                                for (Hero tiny : rangeCell.get(i).Tiny) {
                                    objects.add(heroes.get(tiny));
                                }
                            }
                            if (rangeCell.get(i).Tower_Stone.size() != 0) {
                                for (Tower tower : rangeCell.get(i).Tower_Stone) {
                                    objects.add(towers.get(tower));
                                }
                            }
                            if (rangeCell.get(i).Tower_Fire.size() != 0) {
                                for (Tower tower : rangeCell.get(i).Tower_Fire) {
                                    objects.add(towers.get(tower));
                                }
                            }
                        }
                    }
                }
            }

        }
        return new GameObjectID[0];
    }

    @Override
    public GameObjectID getTarget(GameObjectID id) throws DotaExceptionBase {
        GameObjectID object = null;
        Cell targetCell = new Cell();
        if (attackers.containsValue(id)) {
            for (Map.Entry<AttackForces, GameObjectID> entry : attackers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    targetCell = entry.getKey().getTarget(engine);
                    if (entry.getKey().getTeamID() == 0) {
                        if (targetCell.Attacker_Scourge_Infantry.size() != 0) {
                            for (AttackForces attacker : targetCell.Attacker_Scourge_Infantry) {
                                object = attackers.get(attacker);
                            }
                        }
                        if (targetCell.Attacker_Scourge_Tank.size() != 0) {
                            for (AttackForces attacker : targetCell.Attacker_Scourge_Tank) {
                                object = attackers.get(attacker);
                            }
                        }
                        if (targetCell.Ancient_Scourge.size() != 0) {
                            for (Ancient ancient : targetCell.Ancient_Scourge) {
                                Ancient[] arrayAncient = new Ancient[1];
                                arrayAncient[0] = ancient;
                                object = ancients.get(arrayAncient)[0];
                            }
                        }
                        if (targetCell.Barraks1_Scourge.size() != 0) {
                            for (Barraks barrak1 : targetCell.Barraks1_Scourge) {
                                Barraks[] arrayBarrak1 = new Barraks[1];
                                arrayBarrak1[0] = barrak1;
                                object = ancients.get(arrayBarrak1)[0];
                            }
                        }
                        if (targetCell.Barraks2_Scourge.size() != 0) {
                            for (Barraks barrak2 : targetCell.Barraks2_Scourge) {
                                Barraks[] arrayBarrak2 = new Barraks[1];
                                arrayBarrak2[0] = barrak2;
                                object = ancients.get(arrayBarrak2)[0];
                            }
                        }
                        if (targetCell.Barraks3_Scourge.size() != 0) {
                            for (Barraks barrak3 : targetCell.Barraks3_Scourge) {
                                Barraks[] arrayBarrak3 = new Barraks[1];
                                arrayBarrak3[0] = barrak3;
                                object = ancients.get(arrayBarrak3)[0];
                            }
                        }
                        if (targetCell.Venomancer.size() != 0) {
                            for (Hero venomancer : targetCell.Venomancer) {
                                object = heroes.get(venomancer);
                            }
                        }
                        if (targetCell.Tower_Black.size() != 0) {
                            for (Tower tower : targetCell.Tower_Black) {
                                object = towers.get(tower);
                            }
                        }
                        if (targetCell.Tower_Poison.size() != 0) {
                            for (Tower tower : targetCell.Tower_Poison) {
                                object = towers.get(tower);
                            }
                        }
                    }
                }

                if (entry.getKey().getTeamID() == 1) {
                    if (targetCell.Attacker_Sentinel_Infantry.size() != 0) {
                        for (AttackForces attacker : targetCell.Attacker_Sentinel_Infantry) {
                            object = attackers.get(attacker);
                        }
                    }
                    if (targetCell.Attacker_Sentinel_Tank.size() != 0) {
                        for (AttackForces attacker : targetCell.Attacker_Sentinel_Tank) {
                            object = attackers.get(attacker);
                        }
                    }
                    if (targetCell.Ancient_Sentinel.size() != 0) {
                        for (Ancient ancient : targetCell.Ancient_Sentinel) {
                            Ancient[] arrayAncient = new Ancient[1];
                            arrayAncient[0] = ancient;
                            object = ancients.get(arrayAncient)[0];
                        }
                    }
                    if (targetCell.Barraks1_Sentinel.size() != 0) {
                        for (Barraks barrak1 : targetCell.Barraks1_Sentinel) {
                            Barraks[] arrayBarrak1 = new Barraks[1];
                            arrayBarrak1[0] = barrak1;
                            object = ancients.get(arrayBarrak1)[0];
                        }
                    }
                    if (targetCell.Barraks2_Sentinel.size() != 0) {
                        for (Barraks barrak2 : targetCell.Barraks2_Sentinel) {
                            Barraks[] arrayBarrak2 = new Barraks[1];
                            arrayBarrak2[0] = barrak2;
                            object = ancients.get(arrayBarrak2)[0];
                        }
                    }
                    if (targetCell.Barraks3_Sentinel.size() != 0) {
                        for (Barraks barrak3 : targetCell.Barraks3_Sentinel) {
                            Barraks[] arrayBarrak3 = new Barraks[1];
                            arrayBarrak3[0] = barrak3;
                            object = ancients.get(arrayBarrak3)[0];
                        }
                    }
                    if (targetCell.Tiny.size() != 0) {
                        for (Hero venomancer : targetCell.Tiny) {
                            object = heroes.get(venomancer);
                        }
                    }
                    if (targetCell.Tower_Stone.size() != 0) {
                        for (Tower tower : targetCell.Tower_Stone) {
                            object = towers.get(tower);
                        }
                    }
                    if (targetCell.Tower_Fire.size() != 0) {
                        for (Tower tower : targetCell.Tower_Fire) {
                            object = towers.get(tower);
                        }
                    }
                }
            }
        } else if (towers.containsValue(id)) {
            for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    targetCell = entry.getKey().findTarget(engine);
                    if (entry.getKey().getTeamID() == 0) {
                        if (targetCell.Attacker_Scourge_Infantry.size() != 0) {
                            for (AttackForces attacker : targetCell.Attacker_Scourge_Infantry) {
                                object = attackers.get(attacker);
                            }
                        }
                        if (targetCell.Attacker_Scourge_Tank.size() != 0) {
                            for (AttackForces attacker : targetCell.Attacker_Scourge_Tank) {
                                object = attackers.get(attacker);
                            }
                        }

                        if (targetCell.Venomancer.size() != 0) {
                            for (Hero venomancer : targetCell.Venomancer) {
                                object = heroes.get(venomancer);
                            }
                        }
                    }
                }

                if (entry.getKey().getTeamID() == 1) {
                    if (targetCell.Attacker_Sentinel_Infantry.size() != 0) {
                        for (AttackForces attacker : targetCell.Attacker_Sentinel_Infantry) {
                            object = attackers.get(attacker);
                        }
                    }
                    if (targetCell.Attacker_Sentinel_Tank.size() != 0) {
                        for (AttackForces attacker : targetCell.Attacker_Sentinel_Tank) {
                            object = attackers.get(attacker);
                        }
                    }

                    if (targetCell.Tiny.size() != 0) {
                        for (Hero venomancer : targetCell.Tiny) {
                            object = heroes.get(venomancer);
                        }
                    }

                }
            }
            //TODO HERO
        }
        return object;
    }

    @Override
    public void startTimer() {

    }

    @Override
    public void pauseTimer() {

    }

    @Override
    public float getTime() {
        return time;
    }

    public void next50milis() {
        if (engine.map.getAncient1()[0].getTreasury() == 0 || engine.map.getAncient2()[0].getTreasury() == 0) {
            time += 50;

            return;
        } else {
            //eventHandler.game(time, engine);
            time += 50;
            if (tinyTarget == false) {
//                for (int i = 0; i < eventHandler.gameEvents.size(); i++) {
//                    if (eventHandler.gameEvents.get(i).hero != null && eventHandler.gameEvents.get(i).hero.team_ID == 0) {
//                        if (tinyMoveTime % eventHandler.gameEvents.get(i).hero.speed == 0) {
//                            tinyMove = true;
//                        } else {
//                            tinyMove = false;
//                        }
//                        tinyMoveTime += 50;
//                    }
//
//                }

            }
            if (venomancerTarget == false) {
//                for (int i = 0; i < eventHandler.gameEvents.size(); i++) {
//                    if (eventHandler.gameEvents.get(i).hero != null && eventHandler.gameEvents.get(i).hero.team_ID == 1) {
//                        if (venomancerMoveTime % eventHandler.gameEvents.get(i).hero.speed == 0) {
//                            venomancerMove = true;
//                        } else {
//                            venomancerMove = false;
//                        }
//                        venomancerMoveTime += 50;
//                    }
//
//                }

            }
        }
    }


    @Override
    public void setMoney(int teamID, int amount) {
        if (teamID == 0) {
            engine.map.getAncient1()[0].setTreasury(amount);
        } else if (teamID == 1) {
            engine.map.getAncient2()[0].setTreasury(amount);
        }
    }


    @Override
    public void updateInfo(GameObjectID id, String infoKey, Integer infoValue) throws DotaExceptionBase {
        GameObjectID[] tempID = new GameObjectID[1];
        tempID[0] = id;
        if (ancients.containsValue(tempID)) {
            for (Map.Entry<Ancient[], GameObjectID[]> entry : ancients.entrySet()) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    if (Objects.equals(id, entry.getValue()[i])) {
                        if (infoKey == null) {
                            throw new DotaExceptionBase();
                        } else if (infoKey.equals("TEAM_ID")) {
                            entry.getKey()[i].setTeamID(infoValue);
                        } else if (infoKey.equals("HEALTH")) {
                            entry.getKey()[i].setHealth(infoValue);
                        } else if (infoKey.equals("IS_ALIVE")) {
                            if (infoValue == 0){
                                entry.getKey()[i].setAlive(false);
                            }else if(infoValue == 1){
                                entry.getKey()[i].setAlive(true);
                            }else {
                                throw new DotaExceptionBase();
                            }
                        } else {
                            throw new DotaExceptionBase();
                        }

                    }
                }

            }

        } else if (barrakses.containsValue(tempID)) {
            for (Map.Entry<Barraks[], GameObjectID[]> entry : barrakses.entrySet()) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    if (Objects.equals(id, entry.getValue()[i])) {
                        if (infoKey == null) {
                            throw new DotaExceptionBase();
                        } else if (infoKey.equals("TEAM_ID")) {
                            entry.getKey()[i].setTeamID(infoValue);
                        } else if (infoKey.equals("HEALTH")) {
                            entry.getKey()[i].setHealth(infoValue);
                        } else if (infoKey.equals("IS_ALIVE")) {
                            if (infoValue == 0){
                                entry.getKey()[i].setAlive(false);
                            }else if(infoValue == 1){
                                entry.getKey()[i].setAlive(true);
                            }else {
                                throw new DotaExceptionBase();
                            }
                        } else {
                            throw new DotaExceptionBase();
                        }
                    }
                }

            }

        } else if (towers.containsValue(id)) {
            for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    if (infoKey == null) {
                        throw new DotaExceptionBase();
                    } else if (infoKey.equals("TEAM_ID")) {
                        entry.getKey().setTeamID(infoValue);
                    } else if (infoKey.equals("HEALTH")) {
                        entry.getKey().setHealth(infoValue);
                    } else if (infoKey.equals("IS_ALIVE")) {
                        if (infoValue == 0){
                            entry.getKey().setAlive(false);
                        }else if(infoValue == 1){
                            entry.getKey().setAlive(true);
                        }else {
                            throw new DotaExceptionBase();
                        }
                    } else if (infoKey.equals("RANGE")) {
                        entry.getKey().setRange(infoValue);
                    } else if (infoKey.equals("RELOAD_TIME")) {
                        entry.getKey().setReloadTime(infoValue);
                    } else if (infoKey.equals("ROW")) {
                        if (infoValue > engine.map.getRow() || infoValue < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setRow(infoValue);
                        }
                    } else if (infoKey.equals("COLUMN")) {
                        if (infoValue > engine.map.getColumn() || infoValue < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setColumn(infoValue);
                        }
                    } else if (infoKey.equals("VALUE")) {
                        entry.getKey().setValue(infoValue);
                    } else if (infoKey.equals("INFANTRY_ATTACK")) {
                        entry.getKey().setInfantryAttackPower(infoValue);
                    } else if (infoKey.equals("TANK_ATTACK")) {
                        entry.getKey().setTankAttackPower(infoValue);
                    } else {
                        throw new DotaExceptionBase();
                    }
                }
            }
        } else if (attackers.containsValue(id)) {
            for (Map.Entry<AttackForces, GameObjectID> entry : attackers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    if (infoKey == null) {
                        throw new DotaExceptionBase();
                    } else if (infoKey.equals("TEAM_ID")) {
                        entry.getKey().setTeamID(infoValue);
                    } else if (infoKey.equals("HEALTH")) {
                        entry.getKey().setHealth(infoValue);
                    } else if (infoKey.equals("IS_ALIVE")) {
                        if (infoValue == 0){
                            entry.getKey().setAlive(false);
                        }else if(infoValue == 1){
                            entry.getKey().setAlive(true);
                        }else {
                            throw new DotaExceptionBase();
                        }
                    } else if (infoKey.equals("RANGE")) {
                        entry.getKey().setRange(infoValue);
                    } else if (infoKey.equals("RELOAD_TIME")) {
                        entry.getKey().setReloadTime(infoValue);
                    } else if (infoKey.equals("ROW")) {
                        if (infoValue > engine.map.getRow() || infoValue < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setRow(infoValue);
                        }
                    } else if (infoKey.equals("COLUMN")) {
                        if (infoValue > engine.map.getColumn() || infoValue < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setColumn(infoValue);
                        }
                    } else if (infoKey.equals("VALUE")) {
                        entry.getKey().setValue(infoValue);
                    } else if (infoKey.equals("SPEED")) {
                        entry.getKey().setSpeed(infoValue);
                    } else if (entry.getKey().equals("ATTACK")) {
                        entry.getKey().setDamage(infoValue);
                    } else {
                        throw new DotaExceptionBase();
                    }
                }
            }

        } else if (heroes.containsValue(id)) {
            for (Map.Entry<Hero, GameObjectID> entry : heroes.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    if (infoKey == null) {
                        throw new DotaExceptionBase();
                    } else if (infoKey.equals("TEAM_ID")) {
                        entry.getKey().setTeamID(infoValue);
                    } else if (infoKey.equals("HEALTH")) {
                        entry.getKey().setHealth(infoValue);
                    } else if (infoKey.equals("IS_ALIVE")) {
                        if (infoValue == 0){
                            entry.getKey().setAlive(false);
                        }else if(infoValue == 1){
                            entry.getKey().setAlive(true);
                        }else {
                            throw new DotaExceptionBase();
                        }
                    } else if (infoKey.equals("RANGE")) {
                        entry.getKey().setRange(infoValue);
                    } else if (infoKey.equals("RELOAD_TIME")) {
                        entry.getKey().setReloadTime(infoValue);
                    } else if (infoKey.equals("ROW")) {
                        if (infoValue > engine.map.getRow() || infoValue < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setRow(infoValue);
                        }
                    } else if (infoKey.equals("COLUMN")) {
                        if (infoValue > engine.map.getColumn() || infoValue < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setColumn(infoValue);
                        }
                    } else if (infoKey.equals("SPEED")) {
                        entry.getKey().setSpeed(infoValue);
                    } else if (entry.getKey().equals("ATTACK")) {
                        entry.getKey().setDamage(infoValue);
                    } else {
                        throw new DotaExceptionBase();
                    }

                }
            }
        }
    }

    @Override
    public void updateInfo(GameObjectID id, HashMap<String, Integer> newInfo) throws DotaExceptionBase {
        GameObjectID[] tempID = new GameObjectID[1];
        tempID[0] = id;
        if (ancients.containsValue(tempID)) {
            for (Map.Entry<Ancient[], GameObjectID[]> entry : ancients.entrySet()) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    if (Objects.equals(id, entry.getValue()[i])) {
                        if (newInfo.isEmpty()) {
                            throw new DotaExceptionBase();
                        } else if (newInfo.containsKey("TEAM_ID")) {
                            entry.getKey()[i].setTeamID(newInfo.get("TEAM_ID"));
                        } else if (newInfo.containsKey("HEALTH")) {
                            entry.getKey()[i].setHealth(newInfo.get("HEALTH"));
                        } else if (newInfo.containsValue("IS_ALIVE")) {
                            if (newInfo.get("IS_ALIVE") == 0){
                                entry.getKey()[i].setAlive(false);
                            }else if (newInfo.get("IS_ALIVE") == 1){
                                entry.getKey()[i].setAlive(true);
                            }

                        } else {
                            throw new DotaExceptionBase();
                        }

                    }
                }

            }

        } else if (barrakses.containsValue(tempID)) {
            for (Map.Entry<Barraks[], GameObjectID[]> entry : barrakses.entrySet()) {
                for (int i = 0; i < entry.getValue().length; i++) {
                    if (Objects.equals(id, entry.getValue()[i])) {
                        if (newInfo.isEmpty()) {
                            throw new DotaExceptionBase();
                        } else if (newInfo.containsValue("TEAM_ID")) {
                            entry.getKey()[i].setTeamID(newInfo.get("TEAM_ID"));
                        } else if (newInfo.containsValue("HEALTH")) {
                            entry.getKey()[i].setHealth(newInfo.get("HEALTH"));
                        } else if (newInfo.containsValue("IS_ALIVE")) {
                            if (newInfo.get("IS_ALIVE") == 0){
                                entry.getKey()[i].setAlive(false);
                            }else if (newInfo.get("IS_ALIVE") == 1){
                                entry.getKey()[i].setAlive(true);
                            }
                        } else {
                            throw new DotaExceptionBase();
                        }
                    }
                }

            }

        } else if (towers.containsValue(id)) {
            for (Map.Entry<Tower, GameObjectID> entry : towers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    if (newInfo.isEmpty() || newInfo.containsValue("")) {
                        throw new DotaExceptionBase();
                    } else if (newInfo.containsValue("TEAM_ID")) {
                        entry.getKey().setTeamID(newInfo.get("TEAM_ID"));
                    } else if (newInfo.containsValue("HEALTH")) {
                        entry.getKey().setHealth(newInfo.get("HEALTH"));
                    } else if (newInfo.containsValue("IS_ALIVE")) {
                        if (newInfo.get("IS_ALIVE") == 0){
                            entry.getKey().setAlive(false);
                        }else if (newInfo.get("IS_ALIVE") == 1){
                            entry.getKey().setAlive(true);
                        }
                    } else if (newInfo.containsValue("RANGE")) {
                        entry.getKey().setRange(newInfo.get("RANGE"));
                    } else if (newInfo.containsValue("RELOAD_TIME")) {
                        entry.getKey().setReloadTime(newInfo.get("RELOAD_TIME"));
                    } else if (newInfo.containsValue("ROW")) {
                        if (newInfo.get("ROW") > engine.map.getRow() || newInfo.get("ROW") < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setRow(newInfo.get("ROW"));
                        }
                    } else if (newInfo.containsValue("COLUMN")) {
                        if (newInfo.get("COLUMN") > engine.map.getColumn() || newInfo.get("COLUMN") < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setColumn(newInfo.get("COLUMN"));
                        }
                    } else if (newInfo.containsValue("VALUE")) {
                        entry.getKey().setValue(newInfo.get("VALUE"));
                    } else if (newInfo.containsValue("INFANTRY_ATTACK")) {
                        entry.getKey().setInfantryAttackPower(newInfo.get("INFANTRY_ATTACK"));
                    } else if (newInfo.containsValue("TANK_ATTACK")) {
                        entry.getKey().setTankAttackPower(newInfo.get("TANK_ATTACK"));
                    } else {
                        throw new DotaExceptionBase();
                    }
                }
            }
        } else if (attackers.containsValue(id)) {
            for (Map.Entry<AttackForces, GameObjectID> entry : attackers.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    if (newInfo.isEmpty()) {
                        throw new DotaExceptionBase();
                    } else if (newInfo.containsValue("TEAM_ID")) {
                        entry.getKey().setTeamID(newInfo.get("TEAM_ID"));
                    } else if (newInfo.containsValue("HEALTH")) {
                        entry.getKey().setHealth(newInfo.get("HEALTH"));
                    } else if (newInfo.containsValue("IS_ALIVE")) {
                        if (newInfo.get("IS_ALIVE") == 0){
                            entry.getKey().setAlive(false);
                        }else if (newInfo.get("IS_ALIVE") == 1){
                            entry.getKey().setAlive(true);
                        }
                    } else if (newInfo.containsValue("RANGE")) {
                        entry.getKey().setRange(newInfo.get("RANGE"));
                    } else if (newInfo.containsValue("RELOAD_TIME")) {
                        entry.getKey().setReloadTime(newInfo.get("RELOAD_TIME"));
                    } else if (newInfo.containsValue("ROW")) {
                        if (newInfo.get("ROW") > engine.map.getRow() || newInfo.get("ROW") < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setRow(newInfo.get("ROW"));
                        }
                    } else if (newInfo.containsValue("COLUMN")) {
                        if (newInfo.get("COLUMN") > engine.map.getColumn() || newInfo.get("COLUMN") < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setColumn(newInfo.get("COLUMN"));
                        }
                    } else if (newInfo.containsValue("VALUE")) {
                        entry.getKey().setValue(newInfo.get("VALUE"));
                    } else if (newInfo.containsValue("SPEED")) {
                        entry.getKey().setSpeed(newInfo.get("SPEED"));
                    } else if (entry.getKey().equals("ATTACK")) {
                        entry.getKey().setDamage(newInfo.get("ATTACK"));
                    } else {
                        throw new DotaExceptionBase();
                    }
                }
            }

        } else if (heroes.containsValue(id)) {
            for (Map.Entry<Hero, GameObjectID> entry : heroes.entrySet()) {
                if (Objects.equals(id, entry.getValue())) {
                    if (newInfo.isEmpty()) {
                        throw new DotaExceptionBase();
                    } else if (newInfo.containsValue("TEAM_ID")) {
                        entry.getKey().setTeamID(newInfo.get("TEAM_ID"));
                    } else if (newInfo.containsValue("HEALTH")) {
                        entry.getKey().setHealth(newInfo.get("HEALTH"));
                    } else if (newInfo.containsValue("IS_ALIVE")) {
                        if (newInfo.get("IS_ALIVE") == 0){
                            entry.getKey().setAlive(false);
                        }else if (newInfo.get("IS_ALIVE") == 1){
                            entry.getKey().setAlive(true);
                        }
                    } else if (newInfo.containsValue("RANGE")) {
                        entry.getKey().setRange(newInfo.get("RANGE"));
                    } else if (newInfo.containsValue("RELOAD_TIME")) {
                        entry.getKey().setReloadTime(newInfo.get("RELOAD_TIME"));
                    } else if (newInfo.containsValue("ROW")) {
                        if (newInfo.get("ROW") > engine.map.getRow() || newInfo.get("ROW") < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setRow(newInfo.get("ROW"));
                        }
                    } else if (newInfo.containsValue("COLUMN")) {
                        if (newInfo.get("COLUMN") > engine.map.getColumn() || newInfo.get("COLUMN") < 0) {
                            throw new DotaExceptionBase();
                        } else {
                            entry.getKey().setColumn(newInfo.get("COLUMN"));
                        }
                    } else if (newInfo.containsValue("SPEED")) {
                        entry.getKey().setSpeed(newInfo.get("SPEED"));
                    } else if (newInfo.containsValue("ATTACK")) {
                        entry.getKey().setDamage(newInfo.get("ATTACK"));
                    } else {
                        throw new DotaExceptionBase();
                    }

                }
            }
        }
    }

}

