import java.awt.*;

public class TowerDecoratorGrade1 extends TowerDecorator{
    //TODO

    public TowerDecoratorGrade1(Tower tower){
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
        g.drawString("*",pos.getIntX()+Commons.TowerSize/4,pos.getIntY()+Commons.TowerSize/2);
    }
}

