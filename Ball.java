import java.awt.Color;
import java.util.LinkedList;

import javax.swing.text.Position;

public class Ball {

    public int size; // radius
    public Vector pos; // position of the center
    public double speed;
    public Vector direction;
    public Color color = Color.white;
    public Vector fieldSize;

    public Ball(Vector p, int s, double sp, Vector d, Vector fs) {
        this.pos = p;
        this.size = s;
        this.speed = sp;
        this.direction = Vector.normalized(d);
        this.fieldSize = fs;
    }

    /**
     * Create a ball with a default direction of movement
     * 
     * @param posx the x position of the center of the ball
     * @param posy the y position of the center of the ball
     * @param s    the diameter of the ball
     * @param sp   the speed of the ball
     * @param fs   the field size vector
     */
    public Ball(double posx, double posy, int s, double sp, Vector fs) {
        this.pos = new Vector(posx, posy);
        this.size = s;
        this.speed = sp;
        this.direction = Vector.normalized(new Vector(2, -1));
        this.fieldSize = fs;
    }

    public Vector move(LinkedList<Brick> bricks, Platform platform) {
        Vector vec = this.updateDirection(bricks, platform);
        this.pos.add(Vector.mult(this.direction, this.speed));
        return vec;
    }

    public Vector updateDirection(LinkedList<Brick> bricks, Platform platform) {
        LinkedList<Vector> collision = new LinkedList<Vector>();
        if (this.pos.x < this.size) {
            collision.add(new Vector(this.pos.x, 0));
        } else if ((this.fieldSize.x - this.pos.x) < this.size) {
            collision.add(new Vector(-(this.fieldSize.x - this.pos.x), 0));
        }

        if (this.pos.y < this.size) {
            collision.add(new Vector(0, this.pos.y));
        }

        for (Brick b : bricks) {
            Vector dis = b.distanceVectorTo(this.pos);
            if (dis.mag() < this.size) {
                collision.add(dis);
            }
        }

        Vector platformDis = platform.distanceVectorTo(this.pos);
        if (platformDis.mag() < this.size) {
            collision.add(platformDis);
        }

        if (collision.isEmpty()) {
            return null;
        }

        collision.sort(null);
        Vector normalVector = Vector.normalized(collision.get(0));
        double normalSpeed = this.direction.dot(normalVector);
        if (normalSpeed < 0) {
            this.direction.sub(Vector.mult(normalVector, 2 * normalSpeed));
            if (collision.get(0) == platformDis) {
                this.direction.mult(this.speed);
                this.direction.add(new Vector(platform.speed, 0));
                this.direction.normalize();
            }
        }
        bricks.get(0).hit();
        return collision.get(0);

    }

}
