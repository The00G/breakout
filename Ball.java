import java.awt.Color;
import java.util.LinkedList;

public class Ball {
    
    public int size; // radius
    public Vector pos; // position of the center
    public double speed;
    public Vector direction;
    public Color color = Color.white;
    public Vector fieldSize;

    public Ball(Vector p, int s, double sp, Vector d, Vector fs){
        this.pos = p;
        this.size = s;
        this.speed = sp;
        this.direction = d;
        this.direction.normalize();
        this.fieldSize = fs;
    }

    public Ball(double posx, double posy, int s, double sp){
        this.pos = new Vector(posx, posy);
        this.size = s;
        this.speed = sp;
        this.direction = new Vector(0,-1);
    }

    public void move(LinkedList<Brick> bricks, Platform platform) {
        this.updateDirection(bricks, platform);
        this.pos.add(Vector.mult(this.direction, this.speed));
    }

    public void updateDirection(LinkedList<Brick> bricks, Platform platform) {
        if(this.pos.x < this.size) {
            this.direction.x = Math.abs(this.direction.x);
            return;
        } else if ((this.fieldSize.x- this.pos.x) < this.size){
            this.direction.x = -Math.abs(this.direction.x);
            return;
        }

        if ((this.fieldSize.y- this.pos.y) < this.size){
            this.direction.y = -Math.abs(this.direction.y);
            return;
        }

        for(Brick b : bricks) {
            Vector dis = b.distanceVectorTo(this.pos);
            if(dis.mag()<this.size) {
                this.direction.rotate(2*this.direction.angleWith(dis));
                return;
            }
        }

        Vector dis = platform.distanceVectorTo(this.pos);
        if(dis.mag()<this.size) {
            this.direction.rotate(2*this.direction.angleWith(dis));
            this.direction.mult(this.speed);
            this.direction.add(new Vector(platform.speed, 0));
            this.direction.normalize();
        }
    }

}
