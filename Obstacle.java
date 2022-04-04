import java.awt.*;

public abstract class Obstacle extends GameElement{
    
    public Obstacle(Vector p, Vector s, Color c){
        super(p, s, c);
    }

    public Obstacle(Vector p, Vector s){
        super(p, s);
    }

    public Vector distanceVectorTo(Vector p) {
        double dx, dy;

        dx = Math.max(0, Math.max(this.pos.x - this.size.x - p.x, p.x - (this.pos.x + this.size.x)));
        if (p.x < this.pos.x) {
            dx *= -1;
        }

        dy = Math.max(0, Math.max(this.pos.y - this.size.y - p.y, p.y - (this.pos.y + this.size.y)));
        if (p.y < this.pos.y) {
            dy *= -1;
        }

        return new Vector(dx, dy);
    }
}