package src;

import java.awt.*;

public abstract class Obstacle extends GameElement {

    /**
     * Creates an obstacle with a given position, size and color
     * 
     * @param p vector representing the position of the obstacle
     * @param s vector representing the size of the obstacle
     * @param c color of the obstacle
     */
    public Obstacle(Vector p, Vector s, Color c) {
        super(p, s, c);
    }

    /**
     * Creates an obstacle with a given position and size
     * 
     * @param p vector representing the position of the obstacle
     * @param s vector representing the size of the obstacle
     */
    public Obstacle(Vector p, Vector s) {
        super(p, s);
    }

    /**
     * Returns the normal vector of the surface portion nearest to a given point
     * 
     * @param p
     * @return the normal vector of the surface portion nearest to a given point
     */
    public Vector normalVectorTo(Vector p) {
        double dx, dy;

        dx = Math.max(0, Math.max(this.pos.x - this.size.x - p.x, p.x - (this.pos.x + this.size.x)));
        if (p.x < this.pos.x) {
            dx *= -1;
        }

        dy = Math.max(0, Math.max(this.pos.y - this.size.y - p.y, p.y - (this.pos.y + this.size.y)));
        if (p.y < this.pos.y) {
            dy *= -1;
        }

        return Vector.normalized(new Vector(dx, dy));
    }

    /**
     * Returns the precise distance between a point and the closest point of the
     * edge to said point, similar to ray tracing
     * 
     * @param p the point with which to calculate the distance
     * @return the distance to the point
     */
    public double distanceTo(Vector p) {
        double dx, dy;
        dx = Math.max(0, Math.max(this.pos.x - this.size.x - p.x, p.x - (this.pos.x + this.size.x)));
        dy = Math.max(0, Math.max(this.pos.y - this.size.y - p.y, p.y - (this.pos.y + this.size.y)));

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Signals to this obstacle that it has been hit
     * 
     * @return the points given when hitting the obstacle
     */

    public int hit() {
        Game.playSound("pongHit.wav");
        return 0;

    }

    /**
     * Update the direction of a ball to simulate a bounce on this obstacle
     * 
     * @param bPos the position of the ball
     * @param bDir the direction of the ball
     * @return the points made by the bounce
     */
    public int bounce(Vector bPos, Vector bDir) {
        Vector normalVector = Vector.normalized(this.normalVectorTo(bPos));
        double normalSpeed = bDir.dot(normalVector);
        if (normalSpeed < 0) {
            bDir.sub(Vector.mult(normalVector, 2 * normalSpeed));
            return this.hit();
        }
        return 0;
    }
}
