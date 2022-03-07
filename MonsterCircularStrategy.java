public class MonsterCircularStrategy implements IMonsterStrategy {
    //TODO

    MonsterCircularStrategy(Monster monster){
        Vector2D pos = monster.getPos();
        int x,y;
        x = pos.getIntX();
        y = pos.getIntY();
        if(x>100 && y>=300) monster.setDir(new Vector2D(-1,0));
        else if(x>=300) monster.setDir(new Vector2D(0,1));
        else if(y<=100) monster.setDir(new Vector2D(1,0));
        else if(x<=100) monster.setDir(new Vector2D(0,-1));
    }

    /**
     * updates the directory of the Monster
     * @param position position of the Monster
     * @param direction direction of the Monster
     * @return direction the altered direction of the Monster
     */
    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {
        double x = position.getX();
        double y = position.getY();
        if(x<=50 && y<=50) return (new Vector2D(1,0));
        else if(x>=350 && y<=50) return (new Vector2D(0,1));
        else if(x>=300 && y>=350) return (new Vector2D(-1,0));
        else if(x<=50 && y>=350) return (new Vector2D(0,-1));


        return direction;
    }
}
