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
    Timer t;

    public GameTimer(int interval, Game g) {
        this.g = g;
        t = new Timer(interval, this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        g.ball.move(g.bricks, g.platform);

        mouseScreenPosition = MouseInfo.getPointerInfo().getLocation();
        windowPosition = g.getLocation();
        mousePosition.x = (mouseScreenPosition.getX() - windowPosition.getX() - g.fieldOrigin.x)/g.fieldScale;
        mousePosition.y = (mouseScreenPosition.getY() - windowPosition.getY() - g.fieldOrigin.y)/g.fieldScale;
        //System.out.println(mousePosition);

        g.platform.move(mousePosition);

        refreshBricks();
        //g.end();

        g.repaint();
    }

    public void refreshBricks (){
        Brick b = null;
        for(Brick e: g.bricks){
            if(e.isDead()==true){
                b = e;
            }
        }
        g.bricks.remove(b);
    }

    public void stop() {
        t.stop();
    }

}