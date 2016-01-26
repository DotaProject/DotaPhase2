package common;

public class AttackForceEvent extends Events {

    AttackForces attacker;
    int flag = 0;
    //hamle = 2
    //move = 1

    public AttackForceEvent(AttackForces attacker) {
        this.attacker = attacker;
        this.passedTime = 0;
    }
//flag hamleye move

    //gameengine run tabeha null male gameengine
    public void eventAction(Map map) {
        if (flag == 0 && !attacker.getInRange(map).isEmpty()){
           // System.out.println(attacker.getInRange(map).isEmpty());
            //System.out.println(attacker.getInRange(map));

          //  System.out.println("say hi from 1st if" + flag);
            this.remainingTime = attacker.getReloadTime();
            flag = 2;
        }if (flag == 2){
          //  System.out.println("say hi from 2nd if" + flag);
            if (remainingTime > 0) {
                this.remainingTime = -50;
                this.passedTime += 50;
            } else {
                attacker.attack(map);
                this.passedTime = 0;
                flag = 0;
            }
        }if (flag == 0 && attacker.getInRange(map).isEmpty()){
           // System.out.println("say hi from 3rd if" + flag);
            this.remainingTime = attacker.getSpeed();
            flag = 1;
        }if (flag == 1){
         //   System.out.println("say hi from 4th if" + flag);
            if (remainingTime > 0) {
                this.remainingTime = -50;
                this.passedTime += 50;
            } else {
                attacker.moveAttacker(map);
                this.passedTime = 0;
                flag = 0;
            }
        }
    }

    public AttackForces getAttacker() {
        return attacker;
    }
}
//oun find target inaro khodetun seda mizanid??


