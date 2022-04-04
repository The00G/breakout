import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.MouseInfo;
import java.awt.Point; 
import java.awt.PointerInfo; 

import helpers.mathHelper;

public class Platform {

    public Vector pos; // position of the center
    public Vector size;
    public double speed;
    public Color color = Color.white;
    public int dx;
    public Vector fieldSize;

    public Platform(Vector p, Vector s, Vector fs) {
        this.pos = p;
        this.size = s;
        this.fieldSize = fs;
    }

    public Platform(double posx, double posy, double width, double height, Vector fs) {
        this.pos = new Vector(posx, posy);
        this.size = new Vector(width, height);
        this.fieldSize = fs;
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

    public void move(Vector mousePos) {
        double newPosX = mathHelper.constrain(mousePos.x, this.size.x, this.fieldSize.x-this.size.x);
        this.speed = newPosX - this.pos.x;
        this.pos.x = newPosX;
    }

    

    public void KeyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {// if the left arrow is pressed
            dx = 1;
        }
        if (key == KeyEvent.VK_RIGHT) {// if the right arrow is pressed
            dx = -1;
        }
    }

    public void KeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {// if the left arrow is pressed
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {// if the right arrow is pressed
            dx = 0;
        }
    }

}
