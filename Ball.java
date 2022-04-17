import java.awt.*;
import java.util.*;

public class Ball extends GameElement {

    /**
     * Radius of this ball
     */
    public int radius;

    /**
     * Speed of this ball in /s
     */
    public double speed;

    /**
     * Direction of this ball movement
     */
    public Vector direction;

    /**
     * Creates a ball with a given position, size, speed and direction of movement
     * 
     * @param p    the position of the center of the ball
     * @param s    the radius of the ball
     * @param sp   the speed of the ball
     * @param d    the direction of movement of the ball
     */
    public Ball(Vector p, int r, double sp, Vector d) {
        super(p, new Vector(r,r));
        this.radius = r;
        this.speed = sp;
        this.direction = Vector.normalized(d);
    }

    /**
     * Creates a ball with a given position, size and speed and a default direction of movement
     * 
     * @param posx the x position of the center of the ball
     * @param posy the y position of the center of the ball
     * @param s    the radius of the ball
     * @param sp   the speed of the ball
     */
    public Ball(double posx, double posy, int r, double sp) {
        super(new Vector(posx, posy), new Vector(r, r));
        this.radius = r;
        this.speed = sp;
        this.direction = Vector.normalized(new Vector(0, -1));
    }

    /**
     * Moves this ball with a given time interval and set of obstacles
     * 
     * @param obstacles obstacles that can affect this ball trajectory
     * @param dt        time interval
     * @return          the points made by the ball movement
     */
    public int move(Collection<Obstacle> obstacles, int dt) {
        double movementLeft = this.speed * (dt/1000.0);
        int points = 0;

        while(movementLeft>0) {
            Obstacle nearest = null;
            double distanceNearest = 0;
            for(Obstacle o : obstacles) {
                double d = o.distanceVectorTo(this.pos).mag() - this.radius;
                if(nearest == null){
                    nearest = o;
                    distanceNearest = d;
                } else if(d<distanceNearest){
                    nearest = o;
                    distanceNearest = d;
                }
            }
            if(distanceNearest<0) {
                points += nearest.bounce(this.pos,this.direction);
            }
            double step = Math.min(movementLeft, Math.max(0.5, distanceNearest));
            this.pos.add(Vector.mult(this.direction,step));
            movementLeft-=Math.max(0.5, step);
        }
        return points;
        
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
