public class TowerRegularFactory implements ITowerFactory {

    //TODO

    /**
     * creates a TowerRegular
     * @param position Vector2D position for the created Tower
     * @return created TowerRegular
     */
    @Override
    public Tower createTower(Vector2D position) {
        Tower tower = new TowerRegular();
        tower.setPosition(position);
        tower.setCenter();

        return tower;
    }
}
