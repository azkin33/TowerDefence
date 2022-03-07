import java.awt.*;

public class MonsterPoisonState extends MonsterState{

    //TODO


    MonsterPoisonState(Monster monster){
        super(monster);
        this.setBorderColor(Color.green);
    }

    /**
     * updates the Monster health if poison is inflicted 3 steps before,
     * otherwise sets the MonsterState to null
     */
    @Override
    public void update() {
        //TODO
        int st = Game.getInstance().getSt();
        if(st<monster.getPoisonedStep()+Commons.PoisonDuration ){
            monster.setHealth(monster.getHealth()-Commons.PoisonDamage);
        }
        else{
            monster.setState(null);
        }
    }


}
