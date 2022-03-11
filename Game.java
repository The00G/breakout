import javax.swing.*;
import java.util.*;
import java.awt.Color;

public class Game extends JFrame {
    
    public ArrayList<Brick> bricks;
    public Ball ball;
    public Platform platform;
    public int score;
    public int life;

    public JPanel main, field;

    public Game() {
        super("Breakout!");
        this.setBounds(10, 10, 600, 600);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.main = new JPanel();
        this.main.setBounds(0,0,this.getWidth(), this.getHeight());
        this.main.setBackground(Color.green);
        this.main.setLayout(null);

        this.field = new JPanel();
        this.main.setBounds(this.getWidth()/4, this.getHeight()/4, this.getWidth()*3/4, this.getHeight()*3/4);
        this.main.setBackground(Color.red);

        this.main.add(this.field);
        this.add(main);

        this.setVisible(true);
    }


}
