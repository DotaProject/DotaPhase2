package logic.common.event;

import logic.common.AttackForces;
import logic.common.Map;

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
            this.remainingTime = attacker.getReloadTime();
            flag = 2;
        }if (flag == 2){
            if (remainingTime > 0) {
                this.remainingTime = -50;
                this.passedTime += 50;
            } else {
                attacker.attack(map);
                this.passedTime = 0;
                flag = 0;
            }
        }if (flag == 0 && attacker.getInRange(map).isEmpty()){
            this.remainingTime = attacker.getSpeed();
            flag = 1;
        }if (flag == 1){
            if (remainingTime > 0) {
                this.remainingTime = -50;
                this.passedTime += 50;
            } else {
//                System.out.println(attacker.getRow());
//                System.out.println(attacker.getColumn());
//                System.out.println("***");
                attacker.moveAttacker(map);
//                System.out.println(attacker.getRow());
//                System.out.println(attacker.getColumn());
//                System.out.println("***");
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


