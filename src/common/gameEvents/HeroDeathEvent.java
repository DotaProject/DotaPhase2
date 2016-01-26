package common.gameEvents;

import common.Hero;

public class HeroDeathEvent extends Events {
    Hero hero;
    public HeroDeathEvent(Hero hero) {
        this.hero = hero;
        this.remainingTime = 30000;
        this.passedTime = 0;
    }

    @Override
    public void eventaction() {
        if (remainingTime > 0){
            hero.setAlive(false);
            this.remainingTime -= 50;
            this.passedTime += 50;
        }else {
            hero.setAlive(true);
        }
    }
}
