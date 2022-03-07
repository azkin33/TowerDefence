import java.awt.*;
import java.util.ArrayList;

public class TowerIce extends Tower{
    //TODO


    public TowerIce(){
        this.setRange(100);
        this.setRateOfFire(20);
        this.setDamage(10);
        this.setCost(15);
        this.setColor(Color.cyan);
    }
    @Override
    public void step() {
        //TODO
        int st = Game.getInstance().getSt();
        ArrayList<Monster> monsters = Game.getInstance().getMonsters();
        Monster monster = getClosestInRange(monsters);
        this.hit(monster,st);
        if(monster!=null && this.getLastFired()==st){
            monster.setIcedStep(st);
            MonsterState state = new MonsterIceState(monster);
            monster.setState(state);
        }
    }


}
