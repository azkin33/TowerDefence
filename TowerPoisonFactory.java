public class TowerPoisonFactory implements ITowerFactory {

    //TODO

    /**
     * creates a TowerPoison
     * @param position Vector2D position for the created Tower
     * @return created TowerPoison
     */
    @Override
    public Tower createTower(Vector2D position) {
        Tower tower = new TowerPoison();
        tower.setPosition(position);
        tower.setCenter();
        return tower;
    }
}
