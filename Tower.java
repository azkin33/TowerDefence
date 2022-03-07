import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public abstract class Tower extends Entity{

    private int range;
    private int rateOfFire;
    private int damage;
    private int cost;
    private Color color;
    private Vector2D position = new Vector2D(0,0);
    private Vector2D center = new Vector2D(0,0);
    private int lastFired=0;
    private int killCount=0;


    /**
     * gets Tower range
     * @return range range of the Tower
     */
    public int getRange() {
        return range;
    }

    /**
     * sets Tower range
     * @param range Tower range
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * gets rate of fire
     * @return rateOfFire
     */
    public int getRateOfFire() {
        return rateOfFire;
    }

    /**
     * sets rate of Fire
     * @param rateOfFire Tower rate of fire
     */
    public void setRateOfFire(int rateOfFire) {
        this.rateOfFire = rateOfFire;
    }

    /**
     * gets Tower damage
     * @return tower Damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * sets Tower damage
     * @param damage the Tower damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * gets Tower cost
     * @return the cost of Tower
     */
    public int getCost() {
        return cost;
    }

    /**
     * sets the Tower cost
     * @param cost the Tower cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * gets the Tower color
     * @return Tower color
     */
    public Color getColor() {
        return color;
    }

    /**
     * sets the Tower color
     * @param color the Tower color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * gets the Tower position
     * @return Tower position
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * sets the Tower position
     * @param position the Tower position
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * sets the Tower center
     */
    public void setCenter() {
        int size = Commons.TowerSize/2;
        this.center = this.position.add(new Vector2D(size,size));
    }

    /**
     * gets the Tower center
     * @return Vector2D Tower center
     */
    public Vector2D getCenter() {
        return center;
    }

    /**
     * gets the kill count of the Tower
     * @return kill count
     */
    public int getKillCount() {
        return killCount;
    }

    /**
     * sets the kill count of the Tower
     * @param killCount kill count
     */
    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }

    /**
     * gets the last step when Tower fired
     * @return last step when Tower fired
     */
    public int getLastFired() {
        return lastFired;
    }

    /**
     * sets the last step when Tower fired
     * @param lastFired last step that Tower fired
     */
    public void setLastFired(int lastFired) {
        this.lastFired = lastFired;
    }

    /**
     * computes the distance to the Nonster
     * @param mon a Monster
     * @return double distance to the monster
     */
    public double getDistance(Vector2D mon){
        double x =(mon.getX()-position.getX());
        double y =(mon.getY()-position.getY());
        return Math.sqrt(x*x+y*y);
    }

    /**
     * gives the closest Monster
     * @param monsters list of Monsters
     * @return closest Monster
     */
    public Monster getClosestInRange(ArrayList<Monster> monsters){
        double current,lowest;
        int lowestIndex = -1;
        lowest = 999999;
        for(int i=0;i<monsters.size();i++){
            Monster monster = monsters.get(i);
            if(monster.getHealth()<=0) continue;
            current = getDistance(monster.getCenter());
            if(current<=lowest){
                lowest = current;
                lowestIndex = i;
            }
        }
        if(lowestIndex!=-1 && lowest<=this.range){

            return monsters.get(lowestIndex);
        }
        return null;
    }

    /**
     * compares rate of fire and step and check if turret can fire
     * @param step step count
     * @return true if tower can fire
     */
    private boolean canFire(int step){
        if(lastFired+rateOfFire<=step) return true;
        return false;
    }

    /**
     * hits the Monster and updates the Monster status depending on step count
     * @param monster a Monster
     * @param step step count
     */
    public void hit(Monster monster,int step){
        if(this.canFire(step) && monster!=null && getDistance(monster.getCenter())<=range){
            int health = monster.getHealth();
            monster.setHealth(health-this.damage);
            this.setLastFired(step);
            monster.setTextColor(Color.red);
            monster.setLastHit(step);
            if(monster.getHealth()<=0){
                killCount++;
                InfoPanel info = Display.getInstance().getInfoPanel();
                info.setKills(info.getKills()+1);
                info.setGold(info.getGold()+Commons.KillReward);
            }
        }
    }
    //TODO
    public abstract void step();

    /**
     * draws the Tower
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {

        int size = Commons.TowerSize;
        g.setColor(this.color);
        g.fillOval(position.getIntX(), position.getIntY(),size,size);
        float dash[] = {10.0f};
        BasicStroke dashed = new BasicStroke(1.0f,
                                                    BasicStroke.CAP_BUTT,
                                                    BasicStroke.JOIN_MITER,
                                            10.0f, dash, 0.0f);

        g.setColor(Color.red);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(dashed);
        int range = this.getRange()*2;
        g2d.drawOval(center.getIntX()-range/2, center.getIntY()-range/2,range,range);

    }
}
