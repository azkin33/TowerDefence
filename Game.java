import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static final Game _inst = new Game();
    public static Game getInstance() {
        return _inst;
    }
    private ArrayList<Tower> towers;
    //TODO
    private ArrayList<Monster> monsters;
    private InfoPanel infoPanel = Display.getInstance().getInfoPanel();
    private int wave=0;
    private int st=0;
    private Stroke defaultStroke = ((Graphics2D)Display.getInstance().getGraphics()).getStroke();

    public Game() {
        //TODO

        monsters = new ArrayList<Monster>();
        towers = new ArrayList<Tower>();

    }

    /**
     * gets the Arraylist of current active Towers
     * @return {@code Arraylist<Tower> Arraylist of active Towers}
     */
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    /**
     * gets the Arraylist of current active Monsters
     * @return {@code Arraylist<Monster> Arraylist of active Monsters}
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /**
     * adds a Tower to the Tower list
     * @param tower a Tower to add
     */
    public void addTower(Tower tower){
        if(checkGoldEnough(tower.getCost())) {
            this.towers.add(tower);
            infoPanel.setGold(infoPanel.getGold()-tower.getCost());
        }

    }

    /**
     * updates the currentLivesLabel inside infoPanel
     */
    public void updateLives(){
        infoPanel.setLives(infoPanel.getLives()-1);
    }

    /**
     * gets the current step
     * @return st int step
     */
    public int getSt() {
        return st;
    }

    /**
     * gets the default stroke inside Graphics class
     * @return defaultStroke the default stroke
     */
    public Stroke getDefaultStroke() {
        return defaultStroke;
    }

    /**
     * gets the current wave
     * @return wave the current wave
     */
    public int getWave() {
        return wave;
    }

    /**
     * checks if current gold is higher than the parameter cost
     * @param cost a gold value
     * @return true if current gold is larger than the cost
     */
    public boolean checkGoldEnough(int cost){
        int gold = infoPanel.getGold();
        return gold>=cost;
    }

    /**
     * checks if any monster is alive
     * @return true if any monster is alive
     */
    private boolean isAnyAlive(){
        for (int i = 0; i < monsters.size(); i++) {
            if(monsters.get(i).getHealth()>0) return true;
        }
        return false;
    }

    /**
     * draws to the screen
     * @param g Graphics object
     */
    public void paint(Graphics g) {
        //TODO

        for (int i = 0; i < towers.size(); i++) {
            Tower tower = towers.get(i);
            tower.paint(g);
        }
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).paint(g);
        }


    }

    /**
     * this function is called with a timer, it creates the game loop
     */
    public void step() {
        //TODO
        if(infoPanel.getLives()<=0){

            return;
        }
        if(monsters.isEmpty()){
            infoPanel.setWave(wave);
            for (int i = 0; i <wave ; i++) {
                Monster monster = new Monster();
                monsters.add(monster);
            }
        }

        Display.getInstance().repaint();
        for(int i=0;i<monsters.size();i++){
            Monster monster = monsters.get(i);
            if(monster.getHealth()>0) {
                monster.step();
            }

        }

        st++;

        for(int i=0;i<towers.size();i++){
            Tower tower = towers.get(i);
            tower.step();
            if(tower.getKillCount()==10) {
                Tower decorated = new TowerDecoratorGrade1(tower);
                towers.remove(tower);
                towers.add(decorated);
            }
            else if(tower.getKillCount()==25){
                Tower decorated = new TowerDecoratorGrade2(tower);
                towers.remove(tower);
                towers.add(decorated);
            }
            else if(tower.getKillCount()==50){
                Tower decorated = new TowerDecoratorGrade3(tower);
                towers.remove(tower);
                towers.add(decorated);
            }
        }
        if(!isAnyAlive()){
            wave++;
            infoPanel.setWave(wave);
            monsters.clear();
        }

    }

    //You can make changes

    /**
     * starts the game
     */
    public static void startGame() {
        Display.getInstance().setVisible(true);
        //Optional additions



        new Timer(5, actionEvent -> {
            Game.getInstance().step();
            //Optional additions
        }).start();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Game::startGame);
    }
}
