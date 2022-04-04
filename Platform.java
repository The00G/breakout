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

    /*public void move(int xMax) { // takes in parameter fieldSize.x
        pos.x += dx; // updates the plateform position
        if (pos.x - size.x / 2 <= 0) { 
            pos.x = size.x / 2;
        }

        if (pos.x + size.x / 2 >= xMax) {
            pos.x = xMax - size.x / 2;
        }
    }

    public void move ( ){
        Vector mouse=MousePosition();
        pos.x += mouse.x; 
        if (pos.x-size.x/2<=0){
            pos.x=size.x/2;
        }
        double field=Game.fieldsize.x; 
        if (pos.x + size.x/2>=field)
            pos.x = field - size.x / 2;


    }*/

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


    /**
     * https://www.tabnine.com/code/query/java.awt@Robot@mouseMove+java.awt@Robot@%3Cinit%3E
     * 
     * @return Vector containing the x and y coordinates from the mouse
     */
    public Vector MousePosition (){
        PointerInfo i = MouseInfo.getPointerInfo();
        Point b = i.getLocation();
        int x = (int) b.getX();
        int y = (int) b.getY();
        Vector position = new Vector (x,y); 
        //position.sub(Game.FieldOrigin); 
        return position;         
    }

}
