import java.awt.*;

public abstract class MonsterState implements IPaintable{
    //TODO
    public Monster monster;
    private Color borderColor;
    MonsterState(){}
    MonsterState(Monster monster){
        this.monster = monster;
    }
    public abstract void update();

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * draws the state
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        int x,y,size;
        size = Commons.MonsterSize;
        Vector2D pos = monster.getPos();
        x = pos.getIntX() - size/2;
        y = pos.getIntY() - size/2;

        ((Graphics2D)g).setStroke(Game.getInstance().getDefaultStroke());
        g.setColor(borderColor);
        g.drawLine(x,y,x+size,y);
        g.drawLine(x,y,x,y+size);
        g.drawLine(x+size,y,x+size,y+size);
        g.drawLine(x,y+size,x+size,y+size);
    }


}
