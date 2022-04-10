import java.awt.*;
import java.util.LinkedList;

import javax.swing.text.Position;

import org.w3c.dom.events.MouseEvent;

public class Ball extends GameElement {

    public int radius; // radius
    public double speed;
    public Vector direction;
    public Vector fieldSize;

    public Ball(Vector p, int r, double sp, Vector d, Vector fs) {
        super(p, new Vector(r,r));
        this.radius = r;
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
    public Ball(double posx, double posy, int r, double sp, Vector fs) {
        super(new Vector(posx, posy), new Vector(r, r));
        this.radius = r;
        this.speed = sp;
        this.direction = Vector.normalized(new Vector(1, -1));
        this.fieldSize = fs;
    }

    public void move(LinkedList<Obstacle> elements, int dt) {
        double movement = this.speed * (dt/1000.0);
        LinkedList<Obstacle> reachable = new LinkedList<Obstacle>();
        Obstacle nearestReachable = null;
        double distanceNearest = 0;

        for(GameElement e : elements) {
            if(e instanceof Obstacle) {
                Obstacle o = (Obstacle)e;
                double distance = o.distanceVectorTo(this.pos).mag()-this.radius;

                if(distance <= movement) {
                    reachable.add(o);
                    if(nearestReachable == null) {
                        nearestReachable = o;
                        distanceNearest = distance;
                    } else if(distance<distanceNearest){
                        nearestReachable = o;
                        distanceNearest = distance;
                    }
                }
            }
        }

        Obstacle lastBounce = null;
        while(nearestReachable != null) {
            if(distanceNearest>0.5
            ) {
                this.pos.add(Vector.mult(this.direction, distanceNearest));
                movement -= distanceNearest;
            } else {
                this.bounce(nearestReachable);
                lastBounce = nearestReachable;
            }
            nearestReachable = null;
            for(Obstacle o : reachable) {
                if(o!=lastBounce) {
                    double distance = o.distanceVectorTo(this.pos).mag()-this.radius;
                    if(distance < movement){
                        if(nearestReachable == null) {
                            nearestReachable = o;
                            distanceNearest = distance;
                        } else if(distance<distanceNearest){
                            nearestReachable = o;
                            distanceNearest = distance;
                        }
                    }
                }
            }
        }
        this.pos.add(Vector.mult(this.direction,movement));

        
        
    }

    public void bounce(Obstacle o) {
        Vector normalVector = Vector.normalized(o.distanceVectorTo(this.pos));
        double normalSpeed = this.direction.dot(normalVector);
        if (normalSpeed < 0) {
            this.direction.sub(Vector.mult(normalVector, 2 * normalSpeed));
        }
        o.hit();
    }

    @Override
    public void paint(Graphics g, Vector fieldOrigin, double fieldScale) {
        g.setColor(this.color);
        g.fillOval( (int) (fieldOrigin.x + (this.pos.x - this.radius) * fieldScale),
                    (int) (fieldOrigin.y + (this.pos.y - this.radius) * fieldScale),
                    (int) (2 * this.radius * fieldScale),
                    (int) (2 * this.radius * fieldScale));
    }

}
