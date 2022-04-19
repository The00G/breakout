package src;
import java.awt.*;

public class Wall extends Obstacle {
    
    /**
     * Creates a wall with a given position, length and orientation
     * 
     * @param p the position of the center of the wall
     * @param length the length of the wall
     * @param vertical {@code true} if the wall is vertical; {@code false} if the wall is horizontal
     */
    public Wall(Vector p, double length, boolean vertical){
        super(p, (vertical)?    //one line condition beause super must be the first instructon of the
                    new Vector(0,length):   //if vertical is true
                    new Vector(length,0));  //if vertical is false
    }

    @Override
    public void paint(Graphics g, Vector fieldOrigin, double fieldScale) {
        g.setColor(this.color);
        g.drawLine( (int) (fieldOrigin.x + (this.pos.x - this.size.x) * fieldScale),
                    (int) (fieldOrigin.y + (this.pos.y - this.size.y) * fieldScale),
                    (int) (fieldOrigin.x + (this.pos.x + this.size.x) * fieldScale),
                    (int) (fieldOrigin.y + (this.pos.y + this.size.y) * fieldScale));
    }
    
}
