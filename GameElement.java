import java.awt.*;

public abstract class GameElement {
    
    public Vector pos;
    public Vector size;
    public Color color;

    public GameElement(Vector p, Vector s, Color c){
        this.pos = p;
        this.size = s;
        this.color = c;
    }

    public GameElement(Vector p, Vector s){
        this.pos = p;
        this.size = s;
        this.color = Color.white;
    }

    public void paint(Graphics g, Vector fieldOrigin, double fieldScale) {
        g.setColor(this.color);
        g.fillRect( (int) (fieldOrigin.x + (this.pos.x - this.size.x) * fieldScale),
                    (int) (fieldOrigin.y + (this.pos.y - this.size.y) * fieldScale),
                    (int) (2 * this.size.x * fieldScale),
                    (int) (2 * this.size.y * fieldScale));
    }
}
