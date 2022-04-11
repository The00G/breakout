import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * GameTimer
 */
public class GameTimer implements ActionListener {

    // ceci est un commentaire inutile mais pas si inutile que ça

    /**
     * Game window linked with this game timer
     */
    Game g;

    /**
     * Position of the mouse on the screen
     */
    Point mouseScreenPosition = new Point(0, 0);

    /**
     * Position of the game's window
     */
    Point windowPosition;

    /**
     * Position of the mouse in the game's coordinate system
     */
    Vector mousePosition = new Vector();

    /**
     * Game timer
     */
    Timer t;

    /**
     * Time interval of this game timer
     */
    int dt;

    /**
     * Creates a new game timer with a given time interval and game window
     * 
     * @param interval  time interval of the game timer in ms
     * @param g         game window linked with the game timer
     */
    public GameTimer(int interval, Game g) {
        this.g = g;
        this.dt = interval;
        t = new Timer(this.dt, this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        g.ball.move(g.obstacles, dt);

        mouseScreenPosition = MouseInfo.getPointerInfo().getLocation();
        windowPosition = g.getLocation();
        mousePosition.x = (mouseScreenPosition.getX() - windowPosition.getX() - g.fieldOrigin.x)/g.fieldScale;
        mousePosition.y = (mouseScreenPosition.getY() - windowPosition.getY() - g.fieldOrigin.y)/g.fieldScale;
        //System.out.println(mousePosition);

        g.platform.move(mousePosition);

        g.removeDeadBricks();
        g.end();

        g.repaint();
    }

    public void stop() {
        t.stop();
        g.dispose();
        g.finalFrame();
    }

}