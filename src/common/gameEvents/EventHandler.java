package common.gameEvents;

import common.Map;

import java.util.ArrayList;

/**
 * Created by Minam on 1/25/16.
 */
public class EventHandler extends Thread {
    private ArrayList<Events> eventsqueue = new ArrayList<>();
    private Map map;

    @Override
    public void run() {
        //tamole mapha
        if (eventsqueue.size() != 0) {
            for (int i = 0; i < eventsqueue.size(); i++) {
                if (eventsqueue.get(i) instanceof TowerRangeAttackEvent){
                    ((TowerRangeAttackEvent) eventsqueue.get(i)).eventaction(map);
                }
                //tabeharo seda bezanam
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
