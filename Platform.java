import java.awt.Color;
import helpers.mathHelper;

public class Platform extends Obstacle{

    public double speed;
    public int dx;
    public Vector fieldSize;

    public Platform(Vector p, Vector s, Vector fs) {
        super(p, s);
        this.fieldSize = fs;
    }

    public Platform(double posx, double posy, double width, double height, Vector fs) {
        super(new Vector(posx, posy), new Vector(width, height), Color.white);
        this.fieldSize = fs;
    }

    public void move(Vector mousePos) {
        double newPosX = mathHelper.constrain(mousePos.x, this.size.x, this.fieldSize.x-this.size.x);
        this.speed = newPosX - this.pos.x;
        this.pos.x = newPosX;
    }


    @Override
    public void bounce(Vector bPos, Vector bDir) {
        if(bPos.y >= this.pos.y) {
            return;
        }
        bDir.set(Vector.sub(bPos, this.pos));
        bDir.normalize();
        this.hit();
    }

}
