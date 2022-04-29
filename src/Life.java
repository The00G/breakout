package src;

import java.awt.*;

public class Life extends GameElement {

    public static Color heartcolor = Color.red;

    public Vector heartSize;
    public int points;

    /**
     * Constructor for the {@code Life} class
     * 
     * @param p  the position of the leftmost heart
     * @param hs the wanted size of the hearts
     * @param l  the initial amout of lives given to the player
     */
    public Life(Vector p, Vector hs, int l) {
        super(p, new Vector(), heartcolor);
        this.heartSize = hs;
        this.points = l;
    }

    /**
     * Reduces the amount of lives by 1
     * 
     * @return whether or not the amount of lives has reached 0
     */
    public boolean lose() {
        this.points -= 1;
        return this.points <= 0;
    }

    /**
     * @return whether of not the player has any lives left or not
     */
    public boolean isDead() {
        return this.points <= 0;
    }

    /**
     * adds a life for the player
     */
    public void addLife() {
        this.points++;
    }

    @Override
    public void paint(Graphics g, Vector fieldOrigin, double scale) {
        g.setColor(this.color);
        for (int i = 0; i < this.points; i++) {
            Vector origin = new Vector(fieldOrigin.x + this.pos.x * scale + (i * heartSize.x * scale * 1.25),
                    fieldOrigin.y + this.pos.y * scale);
            g.fillOval((int) (origin.x),
                    (int) (origin.y),
                    (int) (heartSize.x * scale / 2),
                    (int) (heartSize.y * scale / 2));
            g.fillOval((int) (origin.x + heartSize.x * scale / 2),
                    (int) (origin.y),
                    (int) (heartSize.x * scale / 2),
                    (int) (heartSize.y * scale / 2));
            int[] x = { (int) (origin.x + heartSize.x * scale * (1 - Math.sqrt(2) / 2) / 4),
                    (int) (origin.x + heartSize.x * scale / 2),
                    (int) (origin.x + heartSize.x * scale * (3 + Math.sqrt(2) / 2) / 4),
                    (int) (origin.x + heartSize.x * scale / 2) };
            int[] y = { (int) (origin.y + heartSize.y * scale * (1 + Math.sqrt(2) / 2) / 4),
                    (int) (origin.y + heartSize.y * scale / 4),
                    (int) (origin.y + heartSize.y * scale * (1 + Math.sqrt(2) / 2) / 4),
                    (int) (origin.y + heartSize.y * scale) };
            g.fillPolygon(x, y, 4);
        }
    }
}
