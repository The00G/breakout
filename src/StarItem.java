package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class StarItem extends BonusItem {

    private LinkedList<Vector> points = new LinkedList<Vector>();

    /**
     * Creates a star item. A star item, when caught, makes the ball break every
     * brick without bouncing off them, essentially making it unstoppable for a
     * short period of time
     * 
     * @param p the position of the venter of the star
     * @param s the size of the star
     * @param c the color of the star
     */
    public StarItem(Vector p, int s, Color c) {
        super(p, s, c);
    }

    @Override
    public void paint(Graphics g, Vector fieldOrigin, double fieldScale) {
        points.clear();
        for (int i = 0; i < 5; i++) {
            points.add(Vector.sum(fieldOrigin, Vector.sum(Vector.mult(pos, fieldScale), new Vector(size * fieldScale, Math.PI*2 / 5.0 * i, true))));
            points.add(Vector.sum(fieldOrigin, Vector.sum(Vector.mult(pos, fieldScale), new Vector(size * 0.3 * fieldScale, Math.PI*2 / 5.0 * i + Math.PI*2 / 10, true))));
        }

        xPoints = new int[points.size()];
        yPoints = new int[points.size()];

        for (int i = 0; i < points.size(); i++) {
            xPoints[i] = (int) points.get(i).x;
            yPoints[i] = (int) points.get(i).y;
        }
        g.setColor(this.color);
        g.fillPolygon(xPoints, yPoints, points.size());
    }

}