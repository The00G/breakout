import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * GameTimer
 */
public class GameTimer implements ActionListener {

    Game g;
    Point mouseScreenPosition = new Point(0, 0);
    Point windowPosition;
    Vector mousePosition = new Vector();

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

        mouseScreenPosition = MouseInfo.getPointerInfo().getLocation();
        windowPosition = g.getLocation();
        mousePosition.x = mouseScreenPosition.getX() - windowPosition.getX();
        mousePosition.y = mouseScreenPosition.getY() - windowPosition.getY();
        System.out.println(mousePosition);

    }

}