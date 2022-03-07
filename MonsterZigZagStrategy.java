public class MonsterZigZagStrategy implements IMonsterStrategy {
    //TODO

    MonsterZigZagStrategy(Monster monster){
        Vector2D pos = monster.getPos();
        int x,y;
        x = pos.getIntX();
        y = pos.getIntY();
        if(x<100 && y<100) monster.setDir(new Vector2D(1,-1));
        else if(x<300 && y<100) monster.setDir(new Vector2D(1,1));
        else if(x<300 && y<300) monster.setDir(new Vector2D(-1,1));
        else if(x<=100 && y>=300) monster.setDir(new Vector2D(1,-1));
    }

    /**
     * Updates the direction of the Monster
     * @param position Vector2D position of the Monster
     * @param direction Vector2D direction of the Monster
     * @return direction Vector2D altered direction of the Monster
     */
    @Override
    public Vector2D updateDirection(Vector2D position, Vector2D direction) {
        double x = position.getX();
        double y = position.getY();
        double dirX = direction.getX();
        double dirY = direction.getY();

        if(x<=0 && dirX == -1){
            direction = new Vector2D(1,dirY);
        }
        else if(x>=100 && dirX==1 && y>=100 && dirY==-1){
            direction = new Vector2D(-1,dirY);
        }
        else if(y<=0 && dirY == -1){
            direction = new Vector2D(dirX,1);
        }
        else if(y>=100 && dirY==1 && x>=100 && x<=300 && dirX==1){
            direction = new Vector2D(dirX,-1);
        }
        else if(x>=400 && dirX==1){
            direction = new Vector2D(-1,dirY);
        }
        else if(x<=300 && dirX==-1 && y>=100 && y<=300 && dirY==1){
            direction = new Vector2D(1,dirY);
        }

        else if(y>=400 && dirY == 1){
            direction = new Vector2D(dirX,-1);
        }
        else if(y<=300 && dirY==-1 && x>=100 && x<=300 && dirX==-1){
            direction = new Vector2D(dirX,1);
        }
        return direction;
    }
}
