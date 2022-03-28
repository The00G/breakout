//import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Game extends JFrame {

    /**
     * for the painting method see:
     * the problem : http://www.herongyang.com/Swing/JFrame-Draw-Graphics-paint-on-Frame.html
     * solution 1 :http://www.herongyang.com/Swing/JFrame-Draw-Graphics-paint-on-Component.html
     * solution 2 (the one implemented) : http://www.herongyang.com/Swing/JFrame-Draw-Graphics-paint-on-Content-Pane.html
     * 
     * what the fuck is a class in a class? see:
     * https://www.geeksforgeeks.org/nested-classes-java/#:~:text=In%20Java%2C%20it%20is%20possible,more%20readable%20and%20maintainable%20code.
     */
    
    final public Vector FIELD_DEFAULT_SIZE = new Vector(500, 700);
    public double fieldScale;
    public Vector fieldOrigin, fieldSize;
    public LinkedList <Brick> bricks;
    public Ball ball;
    public Platform platform;
    public int score;
    public int life;



    public Game() {
        super("Breakout!");

        this.bricks = new LinkedList<Brick>();
        this.createBricks();

        this.ball = new Ball(250, 500, 20, 2);

        this.platform = new Platform(250, 600, 40, 10);

        this.setBounds(10, 10, 250, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Painter());

        //Timer t = new Timer(1000,this);
        //t.start();
        
        this.setVisible(true);
        
    }


    /*public void ActionPerformed (ActionEvent e){
        if (e.getSource() == t){
            platform.move((int) fieldSize.x);
            ball.move(bricks, platform);
        }
    }*/


    

    /**
     * fills the list of bricks of the game according to the length and height of the standart frame
     */
    public void createBricks (){
        Vector brickSize = new Vector(FIELD_DEFAULT_SIZE.x/15 , FIELD_DEFAULT_SIZE.y/24);
        for (int i = 1; i<=8; i++){
            for(int j = 0; j<15; j++){
                Brick newBrick = new Brick (j*brickSize.x, i*brickSize.y, brickSize.x, brickSize.y, 9-i);
                bricks.add(newBrick);
            }
        }
    }


    public class Painter extends JComponent {
        public void paint(Graphics g) {
            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            fieldScale = Math.min(this.getWidth()/FIELD_DEFAULT_SIZE.x, this.getHeight()/FIELD_DEFAULT_SIZE.y);
            fieldSize = Vector.mult(FIELD_DEFAULT_SIZE, fieldScale);
            fieldOrigin = new Vector((int)( (this.getWidth()-fieldSize.x)/2 ),(int)( (this.getHeight()-fieldSize.y)/2 ));

            for(Brick b : bricks) {
                g.setColor(b.color);
                g.fillRect((int)(fieldOrigin.x + b.pos.x*fieldScale), (int)(fieldOrigin.y + b.pos.y*fieldScale), (int)(b.size.x*fieldScale), (int)(b.size.y*fieldScale));
            }
            g.setColor(Color.black);
            for(Brick b : bricks) {
                g.drawRect((int)(fieldOrigin.x + b.pos.x*fieldScale), (int)(fieldOrigin.y + b.pos.y*fieldScale), (int)(b.size.x*fieldScale), (int)(b.size.y*fieldScale));
            }

            g.setColor(ball.color);
            g.fillOval((int)(fieldOrigin.x + ball.pos.x*fieldScale), (int)(fieldOrigin.y + ball.pos.y*fieldScale), (int)(ball.size*fieldScale), (int)(ball.size*fieldScale));
            
            g.setColor(platform.color);
            g.drawRect((int)(fieldOrigin.x + platform.pos.x*fieldScale), (int)(fieldOrigin.y + platform.pos.y*fieldScale), (int)(platform.size.x*fieldScale), (int)(platform.size.y*fieldScale));

            g.setColor(Color.white);
            g.drawRect((int)fieldOrigin.x, (int)fieldOrigin.y, (int)fieldSize.x, (int)fieldSize.y);
            
        }
    }
    
    
}
