import java.awt.*;
import java.util.ArrayList;

public class TowerPoison extends Tower{
    //TODO



    public TowerPoison(){
        this.setRange(75);
        this.setRateOfFire(10);
        this.setDamage(5);
        this.setCost(25);
        this.setColor(Color.green);

    }

    @Override
    public void step() {
        int st = Game.getInstance().getSt();
        ArrayList<Monster> monsters = Game.getInstance().getMonsters();
        Monster monster = getClosestInRange(monsters);
        this.hit(monster,st);
        if(monster!=null && this.getLastFired()==st){
            monster.setPoisonedStep(st);
            MonsterState state = new MonsterPoisonState(monster);
            monster.setState(state);
        }

    }


}
