import java.awt.*;

public class TowerDecoratorGrade3 extends TowerDecorator{
    //TODO
    TowerDecoratorGrade3(Tower tower){
        super(tower);
    }

    @Override
    public void step() {
        tower.step();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Vector2D pos = tower.getPosition();
        g.setColor(Color.black);
        g.drawString("*",pos.getIntX()+Commons.TowerSize/4+20,pos.getIntY()+Commons.TowerSize/2);
    }
}
