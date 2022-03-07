import java.awt.*;

public class TowerIceFactory implements ITowerFactory {
    //TODO

    /**
     * creates a TowerIce
     * @param position Vec2D position for the created Tower
     * @return TowerIce created
     */
    @Override
    public Tower createTower(Vector2D position) {
        Tower tower = new TowerIce();
        tower.setPosition(position);
        tower.setCenter();
        return tower;
    }
}
