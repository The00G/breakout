import java.awt.*;

public abstract class GameElement {
    
    /**
     * The position of the center of this game element
     */
    public Vector pos;

    /**
     * The size this game element
     */
    public Vector size;

    /**
     * Color of this game element
     */
    public Color color;

    /**
     * Creates a game element with a given position, size and color
     * 
     * @param p vector representing the position of the game element
     * @param s vector representing the size of the game element
     * @param c color of the game element
     */
    public GameElement(Vector p, Vector s, Color c){
        this.pos = p;
        this.size = s;
        this.color = c;
    }

    /**
     * Creates a game element with a given position and size
     * Set the color of the game element to white
     * 
     * @param p vector representing the position of the game element
     * @param s vector representing the size of the game element
     */
    public GameElement(Vector p, Vector s){
        this.pos = p;
        this.size = s;
        this.color = Color.white;
    }

    /**
     * Paint the game element 
     * 
     * @param g the {@code Graphics} context in which to paint
     * @param fieldOrigin the origin of the coordinate system
     * @param fieldScale the scale to apply to coordinates
     */
    public void paint(Graphics g, Vector fieldOrigin, double fieldScale) {
        g.setColor(this.color);
        g.fillRect( (int) (fieldOrigin.x + (this.pos.x - this.size.x) * fieldScale),
                    (int) (fieldOrigin.y + (this.pos.y - this.size.y) * fieldScale),
                    (int) (2 * this.size.x * fieldScale),
                    (int) (2 * this.size.y * fieldScale));
    }
}
