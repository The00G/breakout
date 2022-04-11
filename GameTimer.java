import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * GameTimer
 */
public class GameTimer implements ActionListener {

    Game g;
    Point mouseScreenPosition = new Point(0, 0);
    Point windowPosition;
    Vector mousePosition = new Vector();
    Timer t;
    int dt;

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

        refreshBricks();
        g.e+nd();

        g.repaint();
    }

    public void refreshBricks (){
        LinkedList<Brick> deads = new LinkedList<Brick>();
        for(Brick b : g.bricks){
            if(b.isDead()){
                deads.add(b);
            }
        }
        g.bricks.removeAll(deads);
        g.obstacles.removeAll(deads);
        g.elements.removeAll(deads);
    }

    public void stop() {
        t.stop();
        g.dispose();
        g.finalFrame();
    }

}