import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Game extends JFrame {
    
    final public Vector FIELD_DEFAULT_SIZE = new Vector(500, 700);
    public double fieldScale;
    public Vector fieldOrigin, fieldSize;
    public ArrayList<Brick> bricks;
    public Ball ball;
    public Platform platform;
    public int score;
    public int life;

    public Game() {
        super("Breakout!");
        this.setBounds(10, 10, 250, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        this.fieldScale = Math.min(this.getWidth()/this.FIELD_DEFAULT_SIZE.x, this.getHeight()/this.FIELD_DEFAULT_SIZE.y);
        this.fieldSize = Vector.mult(this.FIELD_DEFAULT_SIZE, this.fieldScale);
        this.fieldOrigin = new Vector((int)( (this.getWidth()-this.fieldSize.x)/2 ),(int)( (this.getHeight()-this.fieldSize.y)/2 ));

        g.setColor(Color.white);
        g.drawRect((int)this.fieldOrigin.x, (int)this.fieldOrigin.y, (int)this.fieldSize.x, (int)this.fieldSize.y);
    }
}
