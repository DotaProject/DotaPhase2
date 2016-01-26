package common;

public class HeroMoveEvent extends Events {
    private Hero hero;

    public HeroMoveEvent(Hero hero) {
        this.hero = hero;
        this.remainingTime = hero.getSpeed();
        this.passedTime = 0;
        hero.setFlag(2);
    }

    @Override
    public void eventaction() {
        if (remainingTime > 0){
            this.remainingTime -= 50;
            this.passedTime += 50;
        }else {
            hero.setFlag(1);
        }
    }

    public Hero getHero() {
        return hero;
    }
}
