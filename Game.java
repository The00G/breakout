
//import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import javax.swing.Timer;

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
     * Ball of this game
     */
    public Ball ball;

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

    public int score;
    public int life;
    public int numberGames = 0;
    JFrame winningFrame;

    private GameTimer gt;

    private int fps = 90;

    /**
     * Creates a new game
     */
    public Game() {
        super("Breakout!");

        this.createElements();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((((int)screenSize.getWidth())/2)-250, (((int)screenSize.getHeight())/2)-350, 500, 700);
        this.setLayout(null);

        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Painter());
        // this.getContentPane().paint(this.getContentPane().getGraphics());

        this.life = 3;
        gt = new GameTimer(1000 / fps, this);

       

        this.setVisible(true);

    }

    /**
     * Creates all the game elements and stores them in the dedicated lists
     */
    public void createElements() {
        
        this.createBricks();

        this.createWalls();

        this.platform = new Platform(250, 600, 40, 10, FIELD_DEFAULT_SIZE);

        this.obstacles = new LinkedList<Obstacle>();
        this.obstacles.addAll(this.bricks);
        this.obstacles.addAll(this.walls);
        this.obstacles.add(this.platform);

        this.ball = new Ball(250, 500, 10, 600);

        this.elements = new LinkedList<GameElement>();
        this.elements.addAll(this.obstacles);
        this.elements.add(this.ball);
        
    }

    /**
     * Fills the list of bricks of the game
     */
    public void createBricks() {
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
    public void createWalls(){
        this.walls = new LinkedList<Wall>();
        walls.add(new Wall(new Vector(0,0), this.FIELD_DEFAULT_SIZE.x, false));
        walls.add(new Wall(new Vector(0,0), this.FIELD_DEFAULT_SIZE.y, true));
        walls.add(new Wall(new Vector(this.FIELD_DEFAULT_SIZE.x,0), this.FIELD_DEFAULT_SIZE.y, true));
    }

    /**
     * remove the dead bricks from the bricks, obstacles and element lists
     */
    public void removeDeadBricks (){
        LinkedList<Brick> deads = new LinkedList<Brick>();
        for(Brick b : this.bricks){
            if(b.isDead()){
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

            for(GameElement e : elements) {
                e.paint(g, fieldOrigin, fieldScale);
            }

        }
    }

    public void noMoreBricks() {
        numberGames++;
        if (numberGames >= 3) {
            end();
        }
    }

    public void end() {
        if (numberGames >= 3) {
            // victory
            // create a Jframe to tell he won
            gt.stop();
        } else if (ball.pos.y > FIELD_DEFAULT_SIZE.y || ball.pos.x > FIELD_DEFAULT_SIZE.x || ball.pos.x < 0) {
            // player loses
            // create a Jframe to tell he loses and close all
            this.life --;
            if (this.life>0){
                this.ball.pos = new Vector(250, 350);
                this.ball.direction = new Vector(0,1);
            }else if(this.life <= 0){
                gt.stop();
            }
        }
    }
    public void finalFrame (){
        winningFrame =  new JFrame();
        winningFrame.setBounds(20, 20, 500, 500);
        winningFrame.setBackground(Color.yellow);
        JLabel finalLabel = new JLabel();
        finalLabel.setBounds(50, 50, 700, 700);
        if (this.life<=0){
            finalLabel.setText("LOOOSER");
        }else if (this.numberGames>3) {
            finalLabel.setText("VICTORY");
        }
        winningFrame.add(finalLabel);
        winningFrame.setVisible(true);
    }

}
