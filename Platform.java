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

    public Platform(Vector p, Vector s) {
        this.pos = p;
        this.size = s;
    }

    public Platform(double posx, double posy, double width, double height) {
        this.pos = new Vector(posx, posy);
        this.size = new Vector(width, height);
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

    public void move(int xMax) { // takes in parameter fieldSize.x
        pos.x += dx; // updates the plateform position
        if (pos.x - size.x / 2 <= 0) { 
            pos.x = size.x / 2;
        }

        if (pos.x + size.x / 2 >= xMax) {
            pos.x = xMax - size.x / 2;
        }
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

    public Vector MousePosition (){
        PointerInfo i = MouseInfo.getPointerInfo();
        Point b = i.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();
        Vector position = new Vector (x,y); 
        return position;         
    }

}
