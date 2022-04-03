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
        
        this.direction.sub(Vector.mult(collision.get(0), 2*this.direction.dot(collision.get(0))/collision.get(0).sqMag()));
        if (collision.get(0) == platformDis) {
            this.direction.mult(this.speed);
            this.direction.add(new Vector(platform.speed, 0));
            this.direction.normalize();
        }

        return collision.get(0);

    }

}
