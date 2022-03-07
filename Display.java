import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    private static final Display _inst = new Display();
    public static Display getInstance() { return _inst; }

    private final InfoPanel infoPanel;
    private final GamePanel gamePanel;

    public Display() throws HeadlessException {
        this.infoPanel = new InfoPanel();
        this.gamePanel = new GamePanel();

        this.setLayout(new FlowLayout());
        this.add(this.infoPanel);
        this.add(this.gamePanel);
        this.pack();

        this.setTitle("Tower Defense");
        this.setResizable(false);

        this.setLocationRelativeTo(null);


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * returns the InfoPanel object
     * @return InfoPanel
     */
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    /**
     * returns the GamePanel object
     * @return GamePanel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    //No need to make any changes to this class

}
