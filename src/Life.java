package src;

import java.awt.*;

public class Life extends GameElement{
    
    public static Color heartcolor = Color.red;

    public Vector heartSize;
    public int points;

    public Life(Vector p, Vector hs, int l) {
        super(p, new Vector(), heartcolor);
        this.heartSize = hs;
        this.points = l;
    }

    public boolean lose() {
        this.points-=1;
        return this.points<0;
    }

    public boolean isDead() {
        return this.points<0;
    }
    public void addLife (){
        this.points++;
    }
    @Override
    public void paint(Graphics g, Vector fieldOrigin, double scale) {
        g.setColor(this.color);
        for(int i = 0; i<this.points; i++){
            Vector origin = new Vector(fieldOrigin.x + this.pos.x*scale + (i*heartSize.x*scale*1.25), fieldOrigin.y + this.pos.y*scale);
            g.fillOval( (int)(origin.x),
                        (int)(origin.y),
                        (int)(heartSize.x*scale/2), 
                        (int)(heartSize.y*scale/2));
            g.fillOval( (int)(origin.x + heartSize.x*scale/2),
                        (int)(origin.y),
                        (int)(heartSize.x*scale/2), 
                        (int)(heartSize.y*scale/2));
            int[] x = { (int)(origin.x+heartSize.x*scale*(1-Math.sqrt(2)/2)/4), 
                        (int)(origin.x+heartSize.x*scale/2), 
                        (int)(origin.x+heartSize.x*scale*(3+Math.sqrt(2)/2)/4), 
                        (int)(origin.x+heartSize.x*scale/2)};
            int[] y = { (int)(origin.y+heartSize.y*scale*(1+Math.sqrt(2)/2)/4),
                        (int)(origin.y+heartSize.y*scale/4),
                        (int)(origin.y+heartSize.y*scale*(1+Math.sqrt(2)/2)/4),
                        (int)(origin.y+heartSize.y*scale)};
            g.fillPolygon(x, y, 4);
        }
    }
}
