import java.awt.Color;
import java.util.LinkedList;

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
        this.direction = Vector.normalized(new Vector(0, -1));
        this.fieldSize = fs;
    }

    public void move(LinkedList<Brick> bricks, Platform platform) {
        this.updateDirection(bricks, platform);
        this.pos.add(Vector.mult(this.direction, this.speed));
    }

    public void updateDirection(LinkedList<Brick> bricks, Platform platform) {
        LinkedList<Vector> collision = new LinkedList<Vector>();
        if (this.pos.x < this.size) {
            collision.add(new Vector(this.pos.x, 0));
        } else if ((this.fieldSize.x - this.pos.x) < this.size) {
            collision.add(new Vector(this.fieldSize.x - this.pos.x, 0));
        }

        if ((this.fieldSize.y - this.pos.y) < this.size) {
            collision.add(new Vector(0, this.fieldSize.y - this.pos.y));
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
            return;
        }

        collision.sort(null);

        this.direction.rotate(2 * this.direction.angleWith(collision.get(0)));
        if (collision.get(0) == platformDis) {
            this.direction.mult(this.speed);
            this.direction.add(new Vector(platform.speed, 0));
            this.direction.normalize();
        }

    }

}
