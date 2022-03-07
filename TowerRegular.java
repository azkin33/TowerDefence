import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TowerRegular extends Tower{

    //TODO

    public TowerRegular(){
        this.setRange(150);
        this.setRateOfFire(20);
        this.setDamage(20);
        this.setCost(20);
        this.setColor(Color.YELLOW);

    }



    @Override
    public void step() {
        int st = Game.getInstance().getSt();
        ArrayList<Monster> monsters = Game.getInstance().getMonsters();
        Monster monster = getClosestInRange(monsters);
        this.hit(monster,st);
    }


}
