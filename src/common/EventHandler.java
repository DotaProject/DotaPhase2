package common;

import java.util.ArrayList;

/**
 * Created by Minam on 1/25/16.
 */
public class EventHandler extends Thread {
    private ArrayList<Events> eventsqueue = new ArrayList<>();
    private Map map;

    @Override
    public void run() {
        checkQueue();
        if (eventsqueue.size() != 0) {
            for (int i = 0; i < eventsqueue.size(); i++) {

                if (eventsqueue.get(i) instanceof TowerRangeAttackEvent){
                    ((TowerRangeAttackEvent) eventsqueue.get(i)).eventaction(map);
                }
                if (eventsqueue.get(i) instanceof AttackForceEvent){
                    ((AttackForceEvent) eventsqueue.get(i)).eventaction(map);
                }
                if (eventsqueue.get(i) instanceof AncientMoneyEvent){
                    eventsqueue.get(i).eventaction();
                }if (eventsqueue.get(i) instanceof HeroLifeEvent){
                    eventsqueue.get(i).eventaction();
                }

                //tabeharo seda bezanam
            }
        }
    }

    //mordeha az saf kharej shan
    public void checkQueue(){
        ArrayList<Events> tempqueue = new ArrayList<>();
        if (eventsqueue != null && eventsqueue.size() != 0){
            for (int i = 0; i < eventsqueue.size(); i++) {
                tempqueue.add(eventsqueue.get(i));
            }
        }


        for (Events item : tempqueue){
            if (item instanceof TowerRangeAttackEvent){
                if(!((TowerRangeAttackEvent) item).getTower().isAlive()){
                    eventsqueue.remove(item);
                }
            }
            if (item instanceof AttackForceEvent){
                if (!((AttackForceEvent) item).getAttacker().isAlive()){
                    eventsqueue.remove(item);
                }
            }
            if (item instanceof AncientMoneyEvent){
                if (!((AncientMoneyEvent) item).getAncient().isAlive()){
                    //TODO end of game
                }
            }
            if (item instanceof HeroMoveEvent){
                if (((HeroMoveEvent) item).getHero().getFlag() == 1){
                    eventsqueue.remove(item);
                }
            }
            if (item instanceof HeroAttackEvent){
                if (((HeroAttackEvent) item).getHero().getFlag() == 1){
                    eventsqueue.remove(item);
                }
            }
        }
    }
    public ArrayList<Events> getEventsqueue() {
        return eventsqueue;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
