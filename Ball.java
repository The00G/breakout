import java.awt.Color;

public class Ball {
    
    public int size; // radius
    public Vector pos; // position of the center
    public double speed;
    public Vector direction;
    public Color color = Color.white;

    public Ball(Vector p, int s, double sp, Vector d){
        this.pos = p;
        this.size = s;
        this.speed = sp;
        this.direction = d;
        this.direction.normalize();
    }
    
    public Ball(Vector p, int s, double sp){
        this.pos = p;
        this.size = s;
        this.speed = sp;
        this.direction = new Vector(0,-1);
    }

    public Ball(double posx, double posy, int s, double sp){
        this.pos = new Vector(posx, posy);
        this.size = s;
        this.speed = sp;
        this.direction = new Vector(0,-1);
    }
}
