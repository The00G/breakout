import java.awt.Color;
import java.util.EventListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Platform implements MouseMotionListener{
        
    public Vector pos; //position of the center
    public Vector size;
    public double speed;
    public Color color = Color.white;

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

    @Override
    public void mouseDragged(MouseEvent e) {
            // if (contains(e.getX(),e.getY())) { //check if the mouse is in the zone 
            //     mouseX=e.getX(); 
            //     mouseY=e.getY(); 
            // }
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

}
