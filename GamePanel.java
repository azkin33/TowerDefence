import sun.java2d.loops.FillRect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GamePanel extends JPanel {

    private int clickedX,clickedY;
    private Vector2D pos;
    private TowerRegularFactory regularFactory;
    private TowerIceFactory iceFactory;
    private TowerPoisonFactory poisonFactory;

    private List<Integer> towerX = new ArrayList<Integer>();
    private List<Integer> towerY = new ArrayList<Integer>();

    private int deadZone = (Commons.TowerZoneDivideLength-Commons.TowerSize)/2;

    private boolean clicked = false;
    public GamePanel() {
        this.setBackground(Color.DARK_GRAY);

        this.setFocusable(true); //For keyboard and mouse actions
        this.requestFocus();

        this.regularFactory = new TowerRegularFactory();
        this.iceFactory = new TowerIceFactory();
        this.poisonFactory = new TowerPoisonFactory();

        int gridSizeX = Commons.TowerZoneWidth/Commons.TowerZoneDivideLength + 1;
        int gridSizeY = Commons.TowerZoneHeight/Commons.TowerZoneDivideLength + 1;
        for (int i = 0; i < gridSizeX ; i++) {
            towerX.add(Commons.TowerZoneX + Commons.TowerZoneDivideLength*i);
            System.out.print(Commons.TowerZoneX + Commons.TowerZoneDivideLength*i+"\n");
        }
        for (int i = 0; i < gridSizeY ; i++) {
            towerY.add(Commons.TowerZoneY + Commons.TowerZoneDivideLength*i);
            System.out.print(Commons.TowerZoneY + Commons.TowerZoneDivideLength*i+"\n");
        }
        //Optional
        //Can be used to add Towers
        //Remove if not used

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.print("x:"+e.getX()+"\ty:"+e.getY()+"\n");
                clickedX = e.getX();
                clickedY = e.getY();

                if(checkTowerZone(clickedX,clickedY)){
                    clicked = true;
                    pos = mapPosition(clickedX,clickedY);
                }

                //System.out.print("tower coun:\t"+towers.size()+"\n");
                //Optional
            }
        });

        //Optional
        //Can be used to add Towers
        //Remove if not used
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();

                if(clicked){
                    Tower tower;

                    switch(key){
                        case KeyEvent.VK_R:
                            tower = regularFactory.createTower(pos);
                            Game.getInstance().addTower(tower);
                            break;
                        case KeyEvent.VK_I:
                            tower = iceFactory.createTower(pos);
                            Game.getInstance().addTower(tower);
                            break;
                        case KeyEvent.VK_P:
                            tower = poisonFactory.createTower(pos);
                            Game.getInstance().addTower(tower);
                            break;
                    }
                    clicked = false;
                }
                //Optional

            }
        });
    }


    /**
     * determines the window size from Commons file
     * @return Dimension dimensions of the window size
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Commons.GamePanelWidth, Commons.GameHeight);
    }

    /**
     * draws the GamePanel
     * @param g Graphics Object
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Optional
        //You can make changes to the visuals
        //This is just an example
        g.setColor(Color.CYAN);
        g.fillRect(Commons.StartX, Commons.StartY, Commons.StartWidth, Commons.StartHeight);


        g.setColor(Color.WHITE);
        g.drawChars("Start Zone".toCharArray(), 0, 10, Commons.StartX, Commons.StartY+12);
        g.setColor(Color.WHITE);
        g.drawRect(Commons.TowerZoneX, Commons.TowerZoneY, Commons.TowerZoneWidth, Commons.TowerZoneHeight);


        //Optional
        //Maybe some additional Drawings


        //Draw Grid Lines
        g.setColor(Color.WHITE);



        for ( int i=1; i<4; i++ ) {
            g.drawLine( Commons.TowerZoneX, Commons.TowerZoneY + (Commons.TowerZoneDivideLength * i),
                    Commons.TowerZoneX + Commons.TowerZoneWidth,
                    Commons.TowerZoneY + (Commons.TowerZoneDivideLength * i));
            g.drawLine( Commons.TowerZoneX + (Commons.TowerZoneDivideLength * i), Commons.TowerZoneY,
                     Commons.TowerZoneX + (Commons.TowerZoneDivideLength * i),
                     Commons.TowerZoneY + Commons.TowerZoneHeight);
        }

        //TODO
        //Maybe some additional Drawings
        if(clicked){
            g.setColor(Color.WHITE);
            g.fillRect(pos.getIntX()-deadZone,pos.getIntY()-deadZone,Commons.TowerZoneDivideLength,Commons.TowerZoneDivideLength);
        }
        Game.getInstance().paint(g);
    }


    /**
     * checks if the coordinates are inside tower zone
     * @param x x variable of the cartesian coordinate system
     * @param y y variable of the cartesian coordinate system
     * @return true if it is inside
     */
    private boolean checkTowerZone(int x,int y){
        if(x>Commons.TowerZoneX && x<Commons.TowerZoneX+Commons.TowerZoneWidth
            && y>Commons.TowerZoneY && y<Commons.TowerZoneY+Commons.TowerZoneHeight) return true;
        return false;
    }

    /**
     * maps the click position to the tower zone grids
     * @param x x variable of the cartesian coordinate system
     * @param y y variable of the cartesian coordinate system
     * @return res Vector2D mapped coordinates
     */
    private Vector2D  mapPosition(int x,int y){
        int gridSizeX = Commons.TowerZoneWidth/Commons.TowerZoneDivideLength + 1;
        int gridSizeY = Commons.TowerZoneHeight/Commons.TowerZoneDivideLength + 1;
        int cur,next;
        Vector2D res;
        for (int i = 0; i < gridSizeX-1; i++) {
            cur = towerX.get(i);
            next = towerX.get(i+1);
            if(x>cur && x<next){
                x=cur;
                break;
            }
        }
        for (int i = 0; i < gridSizeY-1; i++) {
            cur = towerY.get(i);
            next = towerY.get(i+1);
            if(y>cur && y<next){
                y=cur;
                break;
            }
        }

        res = new Vector2D(x+deadZone,y+deadZone);
        return res;
    }
}


