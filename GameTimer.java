import java.awt.event.*;
import javax.swing.*;

/**
 * GameTimer
 */
public class GameTimer implements ActionListener {

    Game g;

    public GameTimer(int interval, Game g) {
        this.g = g;
        Timer t = new Timer(interval, this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        g.ball.move(g.bricks, g.platform);
        // g.platform.move();
        g.repaint();
    }

}