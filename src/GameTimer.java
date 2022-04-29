package src;

import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;

/**
 * GameTimer
 */
public class GameTimer implements ActionListener {

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
     * @param interval time interval of the game timer in ms
     * @param g        game window linked with the game timer
     */
    public GameTimer(int interval, Game g) {
        this.g = g;
        this.dt = interval;
        t = new Timer(this.dt, this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBalls();

        mouseScreenPosition = MouseInfo.getPointerInfo().getLocation();
        windowPosition = g.getLocation();
        mousePosition.x = (mouseScreenPosition.getX() - windowPosition.getX() - g.fieldOrigin.x) / g.scale;
        mousePosition.y = (mouseScreenPosition.getY() - windowPosition.getY() - g.fieldOrigin.y) / g.scale;
        // System.out.println(mousePosition);

        g.platform.move(mousePosition);

        for (int i = 0; i < g.bonusItems.size(); i++) {
            g.bonusItems.get(i).move();
            if (starTouchesPlatform(g.bonusItems.get(i), g.platform)) {
                Game.playSound("pongBonus.wav");
                g.createBonus();
                g.bonusItems.remove(i);
            }
        }

        g.removeDeadBricks();
        g.end();
        g.repaint();

    }

    /**
     * Moves the balls and deletes the out of bounds ones
     */
    public void updateBalls() {
        LinkedList<Ball> lostBalls = new LinkedList<Ball>();
        for (Ball b : g.balls) {
            g.score.add(b.move(g.obstacles, dt));
            if (b.pos.y > Game.FIELD_DEFAULT_SIZE.y + b.size.y) {
                lostBalls.add(b);
                Game.playSound("pongLose.wav");
            }
        }
        g.balls.removeAll(lostBalls);
        g.elements.removeAll(lostBalls);
    }

    /**
     * Stops the game, closes the window and display the end of game window
     */
    public void stop() {
        t.stop();
        g.dispose();
        g.finalFrame();
    }

    /**
     * Returns whether or not a star touches the platform
     * 
     * @param star  the bonus item
     * @param platf the game platform
     * @return true or false
     */
    public boolean starTouchesPlatform(BonusItem star, Platform platf) {
        if (star.pos.x < (platf.pos.x + platf.size.x) && star.pos.x > (platf.pos.x - platf.size.x)
                && star.pos.y < (platf.pos.y + platf.size.y) && star.pos.y > (platf.pos.y - platf.size.y)) {
            return true;
        } else {
            return false;
        }
    }

}