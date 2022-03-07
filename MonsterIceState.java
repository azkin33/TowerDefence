import java.awt.*;

public class MonsterIceState extends MonsterState{
    //TODO


    MonsterIceState(Monster monster){
        super(monster);
        this.setBorderColor(Color.blue);
    }

    /**
     * updates the Monster speed if slow inflicted 5 steps before.
     * Otherwise, sets the MonsterState to null
     */
    @Override
    public void update() {
        //TODO
        int st = Game.getInstance().getSt();
        int dif = st - monster.getIcedStep();
        if(st<=monster.getIcedStep()+Commons.IceDuration){
            monster.setSpeed(Commons.speed*(1-0.2*dif));
        }
        else{
            monster.setSpeed(Commons.speed);
            monster.setState(null);
        }
    }


}
