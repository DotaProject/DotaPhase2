package common.event;

import common.Map;

import java.util.ArrayList;

public class EventHandler extends Thread {

    private ArrayList<Events> eventsQueue = new ArrayList<>();
    private Map map;

    @Override
    public void run() {
        checkQueue();
        if (eventsQueue.size() != 0) {

            for (int i = 0; i < eventsQueue.size(); i++) {

                if (eventsQueue.get(i) instanceof TowerRangeAttackEvent) {
                    ((TowerRangeAttackEvent) eventsQueue.get(i)).eventAction(map);
                }
                if (eventsQueue.get(i) instanceof AttackForceEvent) {
                    ((AttackForceEvent) eventsQueue.get(i)).eventAction(map);
                }
                if (eventsQueue.get(i) instanceof AncientMoneyEvent) {
                    eventsQueue.get(i).eventAction();
                }
                if (eventsQueue.get(i) instanceof HeroLifeEvent) {
                    eventsQueue.get(i).eventAction();
                }

                //tabeharo seda bezanam
            }
        }
    }

    //mordeha az saf kharej shan
    public void checkQueue() {
        ArrayList<Events> tempQueue = new ArrayList<>();
        if (eventsQueue != null && eventsQueue.size() != 0) {
            for (int i = 0; i < eventsQueue.size(); i++) {
                tempQueue.add(eventsQueue.get(i));
            }
        }


        for (Events item : tempQueue) {
            if (item instanceof TowerRangeAttackEvent) {
                if (!((TowerRangeAttackEvent) item).getTower().isAlive()) {
                    eventsQueue.remove(item);
                }
            }
            if (item instanceof AttackForceEvent) {
                if (!((AttackForceEvent) item).getAttacker().isAlive()) {
                    eventsQueue.remove(item);
                }
            }
            if (item instanceof AncientMoneyEvent) {
                if (!((AncientMoneyEvent) item).getAncient().isAlive()) {
                    //TODO end of game
                }
            }
            if (item instanceof HeroMoveEvent) {
                if (((HeroMoveEvent) item).getHero().getFlag() == 1) {
                    eventsQueue.remove(item);
                }
            }
            if (item instanceof HeroAttackEvent) {
                if (((HeroAttackEvent) item).getHero().getFlag() == 1) {
                    eventsQueue.remove(item);
                }
            }
        }
    }

    public ArrayList<Events> getEventsQueue() {
        return eventsQueue;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
