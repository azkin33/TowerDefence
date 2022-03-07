import java.awt.*;
import java.util.ArrayList;

public abstract class TowerDecorator extends Tower {
    //TODO
    protected Tower tower;
    public TowerDecorator(){}
    public TowerDecorator(Tower tower){
        this.tower = tower;
    }

    @Override
    public void setPosition(Vector2D position) {
        tower.setPosition(position);
    }

    @Override
    public Vector2D getPosition() {
        return tower.getPosition();
    }

    @Override
    public void paint(Graphics g) {
        tower.paint(g);
    }

    @Override
    public Vector2D getCenter() {
        return tower.getCenter();
    }

    @Override
    public void setCenter() {
        tower.setCenter();
    }

    @Override
    public double getDistance(Vector2D mon) {
        return tower.getDistance(mon);
    }

    @Override
    public Monster getClosestInRange(ArrayList<Monster> monsters) {
        return tower.getClosestInRange(monsters);
    }

    @Override
    public Color getColor() {
        return tower.getColor();
    }

    @Override
    public void setColor(Color color) {
        tower.setColor(color);
    }

    @Override
    public int getCost() {
        return tower.getCost();
    }

    @Override
    public void setCost(int cost) {
        tower.setCost(cost);
    }

    @Override
    public int getDamage() {
        return tower.getDamage();
    }

    @Override
    public void setDamage(int damage) {
        tower.setDamage(damage);
    }

    @Override
    public void setKillCount(int killCount) {
        tower.setKillCount(killCount);
    }

    @Override
    public int getKillCount() {
        return tower.getKillCount();
    }

    @Override
    public int getLastFired() {
        return tower.getLastFired();
    }

    @Override
    public void setLastFired(int lastFired) {
        tower.setLastFired(lastFired);
    }

    @Override
    public void setRange(int range) {
        tower.setRange(range);
    }

    @Override
    public int getRange() {
        return tower.getRange();
    }

    @Override
    public int getRateOfFire() {
        return tower.getRateOfFire();
    }

    @Override
    public void setRateOfFire(int rateOfFire) {
        tower.setRateOfFire(rateOfFire);
    }

    public abstract void step();

}
