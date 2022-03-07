import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private JLabel currentGoldLabel;
    private JLabel currentLivesLabel;
    private JLabel currentKillsLabel;
    private JLabel currentWaveLabel;
    private int gold = 25;
    private int lives = 3;
    private int kills = 0;
    private int wave = 1;
    public InfoPanel() {


        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(300, 400));
        this.setMinimumSize(new Dimension(300, 400));
        this.setSize(300, 400);
        this.setLayout(new GridLayout(4, 2));

        currentGoldLabel =new JLabel("25"); //TODO Update Gold
        currentLivesLabel = new JLabel("3");; //TODO Update Lives when necessary
        currentKillsLabel = new JLabel("0"); //TODO Update Kills
        currentWaveLabel = new JLabel("1"); //TODO Update Wave

        JLabel temp = new JLabel("Gold:");
        temp.setHorizontalAlignment(SwingConstants.RIGHT);
        temp.setFont(new Font("Serif", Font.BOLD, 20));
        temp.setForeground(Color.ORANGE);
        this.add(temp);this.add(currentGoldLabel);
        temp = new JLabel("Lives:");
        temp.setHorizontalAlignment(SwingConstants.RIGHT);
        temp.setFont(new Font("Serif", Font.BOLD, 20));
        temp.setForeground(Color.GREEN);
        this.add(temp);this.add(currentLivesLabel);
        temp = new JLabel("Kills:");
        temp.setHorizontalAlignment(SwingConstants.RIGHT);
        temp.setFont(new Font("Serif", Font.BOLD, 20));
        temp.setForeground(Color.RED);
        this.add(temp);this.add(currentKillsLabel);
        temp = new JLabel("Wave:");
        temp.setHorizontalAlignment(SwingConstants.RIGHT);
        temp.setFont(new Font("Serif", Font.BOLD, 20));
        this.add(temp);this.add(currentWaveLabel);


        currentGoldLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentLivesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentKillsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentWaveLabel.setHorizontalAlignment(SwingConstants.LEFT);

        currentGoldLabel.setFont(new Font("Serif", Font.BOLD, 20));
        currentLivesLabel.setFont(new Font("Serif", Font.BOLD, 20));
        currentKillsLabel.setFont(new Font("Serif", Font.BOLD, 20));
        currentWaveLabel.setFont(new Font("Serif", Font.BOLD, 20));

    }

    /**
     * gets gold
     * @return int the gold
     */
    public int getGold() {
        return Integer.parseInt(currentGoldLabel.getText());
    }

    /**
     * gets lives
     * @return int the lives
     */
    public int getLives() {
        return Integer.parseInt(currentLivesLabel.getText());
    }

    /**
     * gets kills
     * @return int the kills
     */
    public int getKills() {
        return Integer.parseInt(currentKillsLabel.getText());
    }

    /**
     * gets wave count
     * @return int the wave count
     */
    public int getWave() {
        return Integer.parseInt(currentWaveLabel.getText());
    }

    /**
     * sets gold label
     * @param gold gold
     */
    public void setGold(int gold) {
        currentGoldLabel.setText(Integer.toString(gold));
    }

    /**
     * set lives label
     * @param lives life count
     */
    public void setLives(int lives) {
        currentLivesLabel.setText(Integer.toString(lives));
    }

    /**
     * sets kills label
     * @param kills kill count
     */
    public void setKills(int kills) {
        currentKillsLabel.setText(Integer.toString(kills));
    }

    /**
     * sets wave label
     * @param wave Wave count
     */
    public void setWave(int wave) {
        currentWaveLabel.setText(Integer.toString(wave));
    }

    //TODO


    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
