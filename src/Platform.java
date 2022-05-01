package src;

import java.awt.Color;
import helpers.mathHelper;

public class Platform extends Obstacle {

    public double speed;
    public int dx;
    public Vector fieldSize;

    /**
     * Constructor with size and vector position
     * 
     * @param p  the position vector
     * @param s  the x and y dimension vector
     * @param fs the size of the scaled field
     */
    public Platform(Vector p, Vector s, Vector fs) {
        super(p, s);
        this.fieldSize = fs;
    }

    /**
     * Constuctor with explicit x and y for postion and dimension
     * 
     * @param posx   the x pos of the center of the platform
     * @param posy   the y pos of the center of the platform
     * @param width  the width of the platform
     * @param height the height of the platform
     * @param fs     the size of the scaled field
     */
    public Platform(double posx, double posy, double width, double height, Vector fs) {
        super(new Vector(posx, posy), new Vector(width, height), Color.white);
        this.fieldSize = fs;
    }

    /**
     * Moves the platform according to the x position of the mouse on the screen
     * 
     * @param mousePos the x position of the mouse on the screen
     */
    public void move(Vector mousePos) {
        double newPosX = mathHelper.constrain(mousePos.x, this.size.x, this.fieldSize.x - this.size.x);
        this.speed = newPosX - this.pos.x;
        this.pos.x = newPosX;
    }

    /**
     * Widens the platform by 10 pixels
     */
    public void widen() {
        this.size.x += 10;
    }

    @Override
    public int bounce(Vector bPos, Vector bDir) {
        if (bPos.y >= this.pos.y) {
            return 0;
        }
        bDir.set((bPos.x - this.pos.x) / this.size.x, -1);
        bDir.normalize();
        return this.hit();
    }

}
