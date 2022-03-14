import java.awt.Color;

public class Brick {
    
    public Vector pos; //position of the center
    public Vector size;
    public int totalLife, life;
    public Color color;

    public Brick(double posx, double posy, double width, double height, int tl, Color c) {
        this.pos = new Vector(posx, posy);
        this.size = new Vector(width, height);
        this.totalLife = tl;
        this.color = c;
    }

    public Brick(double posx, double posy, double width, double height, int tl) {
        this.pos = new Vector(posx, posy);
        this.size = new Vector(width, height);
        this.totalLife = tl;
        this.color = Color.white;
    }

    public Brick(Vector pos, Vector size, int tl, Color c) {
        this.pos = pos;
        this.size = size;
        this.totalLife = tl;
        this.color = c;
    }

    public Brick(Vector pos, Vector size, int tl) {
        this.pos = pos;
        this.size = size;
        this.totalLife = tl;
        this.color = Color.white;
    }

    public Vector distanceVectorTo(Vector p) {
        double dx, dy;

        dx = Math.max(0,Math.max(this.pos.x-this.size.x-p.x, p.x - (this.pos.x+this.size.x)));
        if(p.x<this.pos.x) {
            dx *=-1;
        }

        dy = Math.max(0,Math.max(this.pos.y-this.size.y-p.y, p.y - (this.pos.y+this.size.y)));
        if(p.y<this.pos.y) {
            dy *=-1;
        }

        return new Vector(dx, dy);
    }

}
