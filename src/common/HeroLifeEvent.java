package common;

public class HeroLifeEvent extends Events {
    private Hero hero;
    public HeroLifeEvent(Hero hero) {
        this.hero = hero;
        this.passedTime = 0;
    }



    @Override
    public void eventAction() {
        if (!hero.isAlive()){
            if (hero.getFlag() != 0){
                hero.setFlag(0);
                this.remainingTime = 30000;
            }
        }if (!hero.isAlive() && hero.getFlag() == 0){
            if (remainingTime > 0){
                hero.setAlive(false);
                this.remainingTime -= 50;
                this.passedTime += 50;
            }else {
                hero.setAlive(true);
                hero.setHealth(5000);
                hero.setFlag(1);
                this.passedTime = 0;
            }
        }
    }

    public Hero getHero() {
        return hero;
    }
}
