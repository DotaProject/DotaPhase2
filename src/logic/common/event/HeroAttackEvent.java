package logic.common.event;

import logic.common.Cell;
import logic.common.Hero;
import logic.common.Map;
import logic.common.exception.DotaExceptionBase;

public class HeroAttackEvent extends Events {
    private Hero hero;

    public HeroAttackEvent(Hero hero) {
        this.hero = hero;
        this.remainingTime = hero.getReloadTime();
        this.passedTime = 0;
        hero.setFlag(3);
    }

    public void eventAction(Cell dest , int direction, Map map) throws DotaExceptionBase{
        if (remainingTime > 0){
            this.remainingTime -= 50;
            this.passedTime += 50;
        }else{
            hero.setFlag(1);
        }
    }

    public Hero getHero() {
        return hero;
    }
}
