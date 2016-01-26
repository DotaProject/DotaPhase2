package common.gameEvents;

import common.Map;
import common.Tower;

/**
 * Created by Minam on 1/25/16.
 */
public class TowerRangeAttackEvent extends Events {
    Tower tower;

    public TowerRangeAttackEvent(Tower tower) {
        this.tower = tower;
        this.remainingTime = tower.getReloadTime();
        this.passedTime = 0;
    }

    public void eventaction(Map map){
        if (tower.getInRange(map) != null) {
            if (remainingTime > 0) {
                this.remainingTime -= 50;
                this.passedTime += 50;
            } else {
                tower.attack(map);
                this.remainingTime = tower.getReloadTime();
                this.passedTime = 0;
            }
        }
    }

    //rangesh ro flag 1 bezare ke hamle nakone
}
