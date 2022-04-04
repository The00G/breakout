import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.MouseInfo;
import java.awt.Point; 
import java.awt.PointerInfo; 

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
