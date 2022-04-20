package src;
import java.awt.*;
import java.awt.Color;

public class hearts extends GameElement {
    public int [] xPoints = {(int) this.pos.x, (int) (this.pos.x + 2*this.size.x), (int) (this.pos.x + this.size.x)};
    public int [] yPoints = {(int) (this.pos.y+this.size.y), (int) (this.pos.y+this.size.y),(int) (this.pos.y+2*this.size.y)};

    public hearts (Vector p) {
        
        super(p, new Vector (10,10));
        this.color = Color.PINK;
    }

    /*@Override
    public void paint(Graphics g, Vector fieldOrigin, double fieldScale) {
        g.fillPolygon( xPoints, yPoints, 6);
    }*/
}