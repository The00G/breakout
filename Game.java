
//import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.util.*;
import java.awt.*;

//for playing sound
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game extends JFrame {

    /**
     * Size of the field
     * <p>
     * Every coordinate are calculated according to this
     */
    final public static Vector FIELD_DEFAULT_SIZE = new Vector(500, 700);

    /**
     * Scale between the coordinate system and the display
     */
    public double fieldScale;
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
     * Score of the player in this game
     */
    public int score;

    public int level = 5;
    public int life;
    public int numberGames = 0;
    /**
     * gets us the size of the player's screen
     */
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * frame that will be used at the end of the game
     */
    JFrame winningFrame;
    /**
     * panel used also at the end of the game in the final frame
     */
    JPanel finalPanel = new JPanel(); 
    /**
     * image shown if the player wins and it's initialization
     */
    ImageIcon iconVictory = new ImageIcon("victoryImage.png");
    JLabel imageVictory = new JLabel(iconVictory, JLabel.CENTER);
    /**
     * image shown if the player loses and it's initialization
     */
    ImageIcon iconGameOver = new ImageIcon("GameOverIcon.png");
    JLabel imageGameOver = new JLabel(iconGameOver, JLabel.CENTER);

    private GameTimer gt;

    private int fps = 90;

    /**
     * Creates a new game
     */
    public Game() {
        super("Breakout!");

        this.createElements();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //this.setBounds((((int)screenSize.getWidth())/2)-250, (((int)screenSize.getHeight())/2)-350, 500, 700);
        this.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());

        this.setLayout(null);

        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Painter());
        // this.getContentPane().paint(this.getContentPane().getGraphics());

        this.life = 3;
        this.score = 0;
        gt = new GameTimer(1000 / fps, this);

        this.setVisible(true);

    }

    /**
     * Creates all the game elements and stores them in the dedicated lists
     */
    public void createElements() {
        
        //this.createBricks();
        //this.createDebugBricks();
        //this.createReboundBricks();
        this.bricks = Level.buildLevel(this.level, FIELD_DEFAULT_SIZE);


        this.createWalls();

        this.platform = new Platform(250, 600, 40, 10, FIELD_DEFAULT_SIZE);

        this.obstacles = new LinkedList<Obstacle>();
        this.obstacles.addAll(this.bricks);
        this.obstacles.addAll(this.walls);
        this.obstacles.add(this.platform);

        this.balls = new LinkedList<Ball>();
        this.balls.add(new Ball(250, 300, 10, 600));
        this.balls.add(new Ball(260, 300, 10, 600));

        this.elements = new LinkedList<GameElement>();
        this.elements.addAll(this.obstacles);
        this.elements.addAll(this.balls);

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
     * Fills the list of bricks with 1 white brick
     */
    public void createDebugBricks() {
        this.bricks = new LinkedList<Brick>();
        Vector brickSize = new Vector(FIELD_DEFAULT_SIZE.x / 20, FIELD_DEFAULT_SIZE.y / 48);
        bricks.add(new Brick(9 * brickSize.x, 5 * brickSize.y, brickSize.x, brickSize.y, 1));
    }

    /**
     * Fills the list of bricks with half the bricks
     */
    public void createReboundBricks() {
        this.bricks = new LinkedList<Brick>();
        Vector brickSize = new Vector(FIELD_DEFAULT_SIZE.x / 20, FIELD_DEFAULT_SIZE.y / 48);
        for (int i = 1; i < 20; i += 4) {
            for (int j = 3; j < 18; j += 4) {
                Brick newBrick = new Brick(i * brickSize.x, j * brickSize.y, brickSize.x, brickSize.y, 9 - j/2);
                bricks.add(newBrick);
            }
        }
    }

    /**
     * Fills the list of walls with the outer walls of the field
     */
    public void createWalls() {
        this.walls = new LinkedList<Wall>();
        walls.add(new Wall(new Vector(0, 0), FIELD_DEFAULT_SIZE.x, false));
        walls.add(new Wall(new Vector(0, 0), FIELD_DEFAULT_SIZE.y, true));
        walls.add(new Wall(new Vector(FIELD_DEFAULT_SIZE.x, 0), FIELD_DEFAULT_SIZE.y, true));
    }

    /**
     * remove the dead bricks from the bricks, obstacles and element lists
     */
    public void removeDeadBricks() {
        LinkedList<Brick> deads = new LinkedList<Brick>();
        for (Brick b : this.bricks) {
            if (b.isDead()) {
                deads.add(b);
            }
        }
        this.bricks.removeAll(deads);
        this.obstacles.removeAll(deads);
        this.elements.removeAll(deads);
    }

    public class Painter extends JComponent {

        @Override
        public void paint(Graphics g) {

            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            fieldScale = Math.min(this.getWidth() / FIELD_DEFAULT_SIZE.x,
                    this.getHeight() / FIELD_DEFAULT_SIZE.y);
            fieldSize = Vector.mult(FIELD_DEFAULT_SIZE, fieldScale);
            fieldOrigin = new Vector((int) ((this.getWidth() - fieldSize.x) / 2),
                    (int) ((this.getHeight() - fieldSize.y) / 2));

            for (GameElement e : elements) {
                e.paint(g, fieldOrigin, fieldScale);
            }

        }
    }

    public void end() {
        if (this.bricks.isEmpty()) {
            this.numberGames++;
            //this.createBricks();
            this.createBricks();
        this.obstacles.addAll(this.bricks);
        this.elements.addAll(this.bricks);
        }
        if (this.numberGames >= 2) {
            // victory
            // create a Jframe to tell he won
            gt.stop();
        } else if (this.balls.isEmpty()) {
            // player loses
            // create a Jframe to tell he loses and close all
            this.life--;
            if (this.life > 0) {
                this.balls.add(new Ball(250, 300, 10, 600));
                this.elements.addAll(this.balls);
            } else if (this.life <= 0) {
                gt.stop();
            }
        }
    }
    /**
     * creates the final frame telling if the player has won or lost
     */
    public void finalFrame (){
        winningFrame =  new JFrame();
        winningFrame.setBounds((((int)screenSize.getWidth())/2)-250, (((int)screenSize.getHeight())/2)-350, iconGameOver.getIconWidth(), iconGameOver.getIconHeight());
        winningFrame.setLayout(null);
        finalPanel.setBounds(0,0,winningFrame.getWidth(), winningFrame.getHeight());
        finalPanel.setBackground(Color.gray);
        if (this.life<=0){
            imageGameOver.setBounds(winningFrame.getWidth() / 2 - iconVictory.getIconWidth() / 2, winningFrame.getHeight() / 2 - iconVictory.getIconHeight() / 2, iconVictory.getIconWidth(),iconVictory.getIconHeight());
            finalPanel.add(imageGameOver);
            finalPanel.setVisible(true);
        }else if (this.numberGames>3) {
            imageVictory.setBounds(winningFrame.getWidth() / 2 - iconVictory.getIconWidth() / 2, winningFrame.getHeight() / 2 - iconVictory.getIconHeight() / 2, iconVictory.getIconWidth(),iconVictory.getIconHeight());
            finalPanel.add(imageVictory);
            finalPanel.setVisible(true);
        }
        winningFrame.add(finalPanel);
        winningFrame.setVisible(true);
    }

    /**
     * Plays a sound when called
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

}
