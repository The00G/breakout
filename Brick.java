import java.awt.Color;

public class Brick {
    
    public int posx, posy; //position of the center
    public int width, height;
    public int totalLife, life;
    public Color color;

    public Brick(int x, int y, int w, int h, int tl, Color c) {
        this.posx = x;
        this.posy = y;
        this.width = w;
        this.height = h;
        this.totalLife = tl;
        this.color = c;
    }

    public Brick(int x, int y, int w, int h, int tl) {
        this.posx = x;
        this.posy = y;
        this.width = w;
        this.height = h;
        this.totalLife = tl;
        this.color = Color.white;
    }

}
