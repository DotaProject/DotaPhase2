package common.gameEvents;

import common.Cell;
import common.Hero;
import common.Map;
import common.exception.DotaExceptionBase;

public class HeroAttackEvent extends Events {
    private Hero hero;

    public HeroAttackEvent(Hero hero) {
        this.hero = hero;
        this.remainingTime = hero.getReloadTime();
        this.passedTime = 0;
    }

    public void eventaction(Cell dest ,int direction, Map map) throws DotaExceptionBase{
        if (remainingTime > 0){
            this.remainingTime -= 50;
            this.passedTime += 50;
        }else{
            hero.heroMove(dest,direction,map);
            this.remainingTime = hero.getReloadTime();
            this.passedTime = 0;
        }
    }
}
