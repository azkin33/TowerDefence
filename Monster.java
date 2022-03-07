import java.awt.*;
import java.util.Vector;

public class Monster extends Entity {
    //TODO
    private int health=100;
    private double speed = Commons.speed;
    private int reward = 10;
    private IMonsterStrategy strategy;
    private MonsterState state = null;
    private Vector2D pos;
    private Vector2D dir;
    private int icedStep;
    private int poisonedStep;
    private Color textColor = Color.black;
    private int lastHit=9999;
    private int lastStrategyChange=-1;
    public Monster(){
        double x,y;
        int wave = Game.getInstance().getWave();
        this.setHealth(100+wave*20);
        x = Commons.StartX + Math.random()*Commons.StartWidth;
        y = Commons.StartY + Math.random()*Commons.StartHeight;
        this.pos = new Vector2D(x,y);

        if(Math.random()<0.5){
            strategy = new MonsterCircularStrategy(this);
            dir = new Vector2D(0,-1);
        }
        else{
            strategy = new MonsterZigZagStrategy(this);
            dir = new Vector2D(1,-1);
        }
    }

    /**
     * gets the health of the Monster
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * sets the health of the Monster
     * @param health Monster health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * gets the speed of the Monster
     * @return speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * sets the speed of the Monster
     * @param speed Monster speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * gets the gold reward on Monster kill
     * @return reward
     */
    public int getReward() {
        return reward;
    }

    /**
     * sets the MonsterState of the Monster
     * @param state a MonsterState
     */
    public void setState(MonsterState state) {
        this.state = state;
    }

    /**
     * gets the MonsterState of the Monster
     * @return state a MonsterState
     */
    public MonsterState getState() {
        return state;
    }

    /**
     * gets the movement direction of the Monster
     * @return dir the direction of the Monster
     */
    public Vector2D getDir() {
        return dir;
    }

    /**
     * sets the direction of the Monster
     * @param dir a Vector2D direction
     */
    public void setDir(Vector2D dir) {
        this.dir = dir;
    }

    /**
     * sets the position of the Monster
     * @param pos a Vector2D position
     */
    public void setPos(Vector2D pos) {
        this.pos = pos;
    }

    /**
     * gets the position of the Monster
     * @return pos Vector2D position of the Monster
     */
    public Vector2D getPos() {
        return pos;
    }

    /**
     * gets the center of the Monster
     * @return center Vector2D position of the Monster
     */
    public Vector2D getCenter(){
        Vector2D center = new Vector2D(pos.getIntX()-Commons.MonsterSize/2,
                pos.getIntY()-Commons.MonsterSize/2);
        return center;
    }

    /**
     * gets the step which poison is inflicted on Monster
     * @return poisonedStep
     */
    public int getPoisonedStep() {
        return poisonedStep;
    }

    /**
     * sets the step which poison is inflicted on Monster
     * @param poisonedStep step that Monster is poisoned
     */
    public void setPoisonedStep(int poisonedStep) {
        this.poisonedStep = poisonedStep;
    }

    /**
     * gets the step which ice is inflicted on Monster
     * @return icedStep
     */
    public int getIcedStep() {
        return icedStep;
    }

    /**
     * sets the step which ice is inflicted on Monster
     * @param icedStep step which tower is inflicted with ice
     */
    public void setIcedStep(int icedStep) {
        this.icedStep = icedStep;
    }

    /**
     * sets the Color of the health text of monster
     * @param textColor a Color
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * sets the lastHit variable
     * @param lastHit last step that monster is hit
     */
    public void setLastHit(int lastHit) {
        this.lastHit = lastHit;
    }

    /**
     * changes the MonsterStrategy at random
     */
    private void changeStrategy(){
        double r = Math.random();
        if(r>0.5){
            this.strategy=new MonsterCircularStrategy(this);
        }
        else{
            this.strategy=new MonsterZigZagStrategy(this);
        }

    }

    /**
     * assigns new MonsterStrategy to Monster at corners
     */
    private void assignNewStrategy(){
        int x = pos.getIntX();
        int y = pos.getIntY();

        if(x>=50 && y<=50 && lastStrategyChange!=0){
            this.changeStrategy();
            lastStrategyChange = 0;
        }
        else if(y==50 && x>=350 && lastStrategyChange!=1){
            this.changeStrategy();
            lastStrategyChange = 1;
        }
        else if(x>=350 && y>350 && lastStrategyChange!=2){
            this.changeStrategy();
            lastStrategyChange = 2;
        }

    }

    /**
     * consists of Monster tasks and updates Monster conditions
     */
    @Override
    public void step() {
        //TODO
        assignNewStrategy();
        dir = strategy.updateDirection(pos,dir);
        Vector2D norm = new Vector2D(dir.getX(), dir.getY());
        norm.normalize();
        pos = pos.add(norm.multiply(speed));
        if(state!=null){
            state.update();
        }
        double x,y;
        x = pos.getX();
        y = pos.getY();
        if(y>=300 && x<100 && lastStrategyChange!=-1){
            this.health=0;
            Game.getInstance().updateLives();
        }

    }

    /**
     * draws the Monster
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        //TODO
        int st = Game.getInstance().getSt();
        int size = Commons.MonsterSize;
        if(this.getHealth()>0){
            g.setColor(Color.YELLOW);
            g.fillRect(pos.getIntX()-size/2,pos.getIntY()-size/2, size, size);


            g.setColor(textColor);
            g.drawString(Integer.toString(health),pos.getIntX()-size/4,pos.getIntY());

            if(state!=null){
                state.paint(g);
            }
            if(st>=lastHit+5) {
                textColor = Color.black;
            }
        }
    }
}
