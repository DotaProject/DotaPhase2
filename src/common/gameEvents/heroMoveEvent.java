package common.gameEvents;

import common.Hero;

/**
 * Created by Minam on 1/25/16.
 */
public class HeroMoveEvent extends Events {
    Hero hero;

    public HeroMoveEvent(Hero hero) {
        this.hero = hero;
        this.remainingTime = hero.getSpeed();
        this.passedTime = 0;
    }

}
