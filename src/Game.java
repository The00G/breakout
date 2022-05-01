package src;

//import javax.lang.model.util.ElementScanner14;
import javax.swing.*;

import java.util.*;
import java.awt.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//for playing sound
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game extends JFrame implements WindowListener{

    /**
     * Size of the field
     * <p>
     * Every ingame coordinate are calculated according to this
     */
    final public static Vector FIELD_DEFAULT_SIZE = new Vector(500, 700);

    final public static Vector SCORE_DEFAULT_POS = new Vector(600, 200);
    final public static int SCORE_DEFAULT_SIZE = 40;

    /**
     * Scale between the coordinate system and the display
     */
    public double scale;
    public Vector fieldOrigin, fieldSize;

    /**
     * List of the bricks in this game
     */
    public LinkedList<Brick> bricks;

    /**
     * List of the balls of this game
     */
    public LinkedList<Ball> balls;

    /**
     * Platform of this game
     */
    public Platform platform;

    /**
     * List of the walls in this game
     */
    public LinkedList<Wall> walls;

    /**
     * List of the obstacles in this game
     * <p>
     * Contains the bricks, the walls and the platform
     */
    public LinkedList<Obstacle> obstacles;

    /**
     * List of the elements of this game
     * <p>
     * Contains the bricks, the walls, the platform and the ball
     */
    public LinkedList<GameElement> elements;

    /**
     * Bonus items are random elements that randomly appear when you break a brick.
     * They can give you special abilities or disabilies.
     */
    public ArrayList<BonusItem> bonusItems = new ArrayList<BonusItem>();

    public int level = Level.getLevel();

    public Life life;
    /**
     * number of bonus invented
     */
    public int nbBonus = 4;

    public int numberGames = 0;
    /**
     * gets us the size of the player's screen
     */
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * frame that will be used at the end of the game
     */
    public JFrame winningFrame;
    /**
     * panel used also at the end of the game in the final frame
     */
    public JPanel finalPanel = new JPanel();
    /**
     * image shown if the player wins and it's initialization
     */
    public ImageIcon iconVictory = new ImageIcon("media/victoryImage.png");
    public JLabel imageVictory = new JLabel(iconVictory, JLabel.CENTER);
    /**
     * image shown if the player loses and it's initialization
     */
    public ImageIcon iconGameOver = new ImageIcon("media/GameOverIcon.png");
    public JLabel imageGameOver = new JLabel(iconGameOver, JLabel.CENTER);

    public Score score;

    private GameTimer gt;

    private int fps = 90;

    /**
     * Creates a new game
     */
    public Game() {
        super("Breakout!");
        this.createElements();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // this.setBounds((((int)screenSize.getWidth())/2)-250,
        // (((int)screenSize.getHeight())/2)-350, 500, 700);
        this.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());

        this.setLayout(null);

        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Painter());
        // this.getContentPane().paint(this.getContentPane().getGraphics());

        gt = new GameTimer(1000 / fps, this);
        this.addWindowListener(this);
        this.setVisible(true);

    }

    /**
     * Creates all the game elements and stores them in the dedicated lists
     */
    public void createElements() {

        // this.createBricks();
        // this.createDebugBricks();
        // this.createReboundBricks();
        this.bricks = Level.buildLevel(this.level, FIELD_DEFAULT_SIZE);

        this.createWalls();
        // this.createHearts();

        this.platform = new Platform(250, 600, 40, 10, FIELD_DEFAULT_SIZE);

        this.obstacles = new LinkedList<Obstacle>();
        this.obstacles.addAll(this.bricks);
        this.obstacles.addAll(this.walls);
        this.obstacles.add(this.platform);

        this.balls = new LinkedList<Ball>();
        this.balls.add(new Ball(250, 300, 10, 600));

        this.score = new Score(new Vector(FIELD_DEFAULT_SIZE.x + 10, 30), 40);

        this.life = new Life(new Vector(FIELD_DEFAULT_SIZE.x, 40),
                new Vector(40, 40),
                3);

        this.elements = new LinkedList<GameElement>();
        this.elements.addAll(this.obstacles);
        this.elements.addAll(this.balls);
        this.elements.add(this.score);
        this.elements.add(this.life);

    }

    /**
     * Fills the list of bricks of the game
     */
    public void createBricks() {
        this.bricks = new LinkedList<Brick>();
        Vector brickSize = new Vector(FIELD_DEFAULT_SIZE.x / 20, FIELD_DEFAULT_SIZE.y / 48);
        for (int i = 1; i < 20; i += 2) {
            for (int j = 3; j < 18; j += 2) {
                Brick newBrick = new Brick(i * brickSize.x, j * brickSize.y, brickSize.x, brickSize.y, 9 - j / 2);
                bricks.add(newBrick);
            }
        }
    }

    /**
     * Fills the list of bricks with half the bricks
     */
    public void createReboundBricks() {
        this.bricks = new LinkedList<Brick>();
        Vector brickSize = new Vector(FIELD_DEFAULT_SIZE.x / 20, FIELD_DEFAULT_SIZE.y / 48);
        for (int i = 1; i < 20; i += 4) {
            for (int j = 3; j < 18; j += 4) {
                Brick newBrick = new Brick(i * brickSize.x, j * brickSize.y, brickSize.x, brickSize.y, 9 - j / 2);
                bricks.add(newBrick);
            }
        }
    }

    /**
     * Fills the list of walls with the outer walls of the field
     */
    public void createWalls() {
        this.walls = new LinkedList<Wall>();
        walls.add(new Wall(new Vector(FIELD_DEFAULT_SIZE.x / 2, 0), FIELD_DEFAULT_SIZE.x / 2, false));
        walls.add(new Wall(new Vector(0, FIELD_DEFAULT_SIZE.y / 2), FIELD_DEFAULT_SIZE.y / 2, true));
        walls.add(new Wall(new Vector(FIELD_DEFAULT_SIZE.x, FIELD_DEFAULT_SIZE.y / 2), FIELD_DEFAULT_SIZE.y / 2, true));
    }

    /**
     * remove the dead bricks from the bricks, obstacles and element lists
     */
    public void removeDeadBricks() {
        LinkedList<Brick> deads = new LinkedList<Brick>();
        for (Brick b : this.bricks) {
            if (b.isDead()) {
                deads.add(b);
                if (Math.random() < b.bonusChance) {
                    bonusItems.add(new StarItem(b.pos, 13, Color.yellow));
                }
            }
        }
        this.bricks.removeAll(deads);
        this.obstacles.removeAll(deads);
        this.elements.removeAll(deads);
    }

    /**
     * This method goes through every current bonus item and removes any that are
     * out of bounds (that haven't been collected)
     */
    public void removeUnusedBonusItems() {
        LinkedList<BonusItem> toDelete = new LinkedList<BonusItem>();
        for (BonusItem b : bonusItems) {
            if (b.pos.y > FIELD_DEFAULT_SIZE.y) {
                toDelete.add(b);
            }
        }
        for (BonusItem b : toDelete) {
            bonusItems.remove(b);
        }
        toDelete.clear();
    }

    /**
     * methods necessary to implement WindowListener to know when the game methos is closed
     * @param e
     */
    @Override
    public void windowOpened(WindowEvent e) { }

    /**
     * method to close the gameTimer when the Game Frame is closed
     * @param e
     */
    @Override
    public void windowClosing(WindowEvent e) {
        gt.stop();
    }

    @Override
    public void windowClosed(WindowEvent e) { 
    }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }


    /**
     * This is the painter class, its constructer takes in a new graphic element and
     * paints over it whatever it is indicated
     */
    public class Painter extends JComponent {

        @Override
        public void paint(Graphics g) {

            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            scale = Math.min(this.getWidth() / FIELD_DEFAULT_SIZE.x,
                    this.getHeight() / FIELD_DEFAULT_SIZE.y);
            fieldSize = Vector.mult(FIELD_DEFAULT_SIZE, scale);
            fieldOrigin = new Vector((int) ((this.getWidth() - fieldSize.x) / 2),
                    (int) ((this.getHeight() - fieldSize.y) / 2));

            for (GameElement e : elements) {
                e.paint(g, fieldOrigin, scale);
            }

            for (BonusItem b : bonusItems) {
                b.paint(g, fieldOrigin, scale);
            }
        }
    }

    /**
     * This method ends the computation behind the game when it is won or recreates
     * the bricks when they have all been broken
     */
    public void end() {
        if (this.bricks.isEmpty()) {
            this.numberGames++;
            this.bricks = Level.buildLevel(this.level, FIELD_DEFAULT_SIZE);
            this.obstacles.addAll(this.bricks);
            this.elements.addAll(this.bricks);
        }
        if (this.numberGames >= 2) {
            gt.stop();
        } else if (this.balls.isEmpty()) {
            if (this.life.lose()) {
                gt.stop();
            } else {
                this.balls.add(new Ball(250, 300, 10, 600));
                this.elements.addAll(this.balls);
            }
        }
    }

    /**
     * creates the final frame telling if the player has won or lost
     */
    public void finalFrame() {
        if (this.life.isDead()) {
            winningFrame = new JFrame();
            finalPanel.setBackground(Color.gray);
            winningFrame.setBounds((((int) screenSize.getWidth()) / 2) - 250,
                    (((int) screenSize.getHeight()) / 2) - 350,
                    iconGameOver.getIconWidth(), iconGameOver.getIconHeight());
            winningFrame.setLayout(null);
            finalPanel.setBounds(0, 0, winningFrame.getWidth(), winningFrame.getHeight());
            imageGameOver.setBounds(winningFrame.getWidth() / 2 - iconGameOver.getIconWidth() / 2,
                    winningFrame.getHeight() / 2 - iconGameOver.getIconHeight() / 2, iconGameOver.getIconWidth(),
                    iconGameOver.getIconHeight());
            finalPanel.add(imageGameOver);
            finalPanel.setVisible(true);
            winningFrame.add(finalPanel);
            winningFrame.setVisible(true);
        } else if (this.numberGames >= 2) {
            winningFrame = new JFrame();
            finalPanel.setBackground(Color.gray);
            winningFrame.setBounds((((int) screenSize.getWidth()) / 2) - 250,
                    (((int) screenSize.getHeight()) / 2) - 350,
                    iconVictory.getIconWidth(), iconVictory.getIconHeight());
            winningFrame.setLayout(null);
            finalPanel.setBounds(0, 0, winningFrame.getWidth(), winningFrame.getHeight());
            imageVictory.setBounds(winningFrame.getWidth() / 2 - iconVictory.getIconWidth() / 2,
                    winningFrame.getHeight() / 2 - iconVictory.getIconHeight() / 2, iconVictory.getIconWidth(),
                    iconVictory.getIconHeight());
            finalPanel.add(imageVictory);
            finalPanel.setVisible(true);
            winningFrame.add(finalPanel);
            winningFrame.setVisible(true);
        }
    }

    /**
     * Plays a sound when called
     * 
     * @param soundFile the path to the .wav file
     */
    public static void playSound(String soundFile) {
        try {
            File f = new File("./" + soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to be exectuted when a star touches the platfrom. It randomly
     * creates a bonus in the game under the form of a new ball, a bigger ball, a
     * bigger platform or an additionnal life
     */
    public void createBonus() {
        if (Math.random() < this.bricks.get(0).bonusChance) {
            switch ((int) (this.nbBonus * Math.random() % this.nbBonus)) {

                case 0:
                    this.life.addLife();
                    break;

                case 1:
                    this.balls.add(new Ball(250, 300, 10, 600));
                    this.elements.addAll(this.balls);
                    break;

                case 2:
                    this.platform.widen();
                    break;

                case 3:
                    int index = (int) ((this.balls.size()-1)*Math.random())%(this.balls.size()-1);
                    this.balls.get(index).widenRadius();
                    break;

            }
        }
    }

}
