package common;


public class TowerRangeAttackEvent extends Events {

    private Tower tower;

    public TowerRangeAttackEvent(Tower tower) {
        this.tower = tower;
        this.remainingTime = tower.getReloadTime();
        this.passedTime = 0;
    }

    public void eventAction(Map map){
        if (tower.getInRange(map) != null) {
            if (remainingTime > 0) {
                this.remainingTime -= 50;
                this.passedTime += 50;
            } else {
                tower.attack(map);
                this.remainingTime = tower.getReloadTime() - 50;
                this.passedTime = 50;
            }
        }
    }

    public Tower getTower() {
        return tower;
    }

    //rangesh ro flag 1 bezare ke hamle nakone
}
