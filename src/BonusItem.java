package src;
import java.awt.*;

public abstract class BonusItem{

    protected Vector pos;
    protected int size;
    protected Color color;
    protected int[] xPoints;
    protected int[] yPoints;
    protected Vector speed = new Vector(0,1);

    /**
     * Creates a bonus item with a given position, size and color
     * 
     * @param p vector representing the position of the game element
     * @param s vector representing the size of the game element
     * @param c color of the game element
     */
    public BonusItem(Vector p, int s, Color c){
        pos=p;
        size=s;
        color=c;
    }

    /**
     * Paint the bonus item 
     * 
     * @param g the {@code Graphics} context in which to paint
     * @param fieldOrigin the origin of the coordinate system
     * @param fieldScale the scale to apply to coordinates
     */
    public abstract void paint(Graphics g, Vector fieldOrigin, double fieldScale);

    /**
     * Updates the position of the object
     */
    public void move(){
        pos.add(speed);
    }
}
