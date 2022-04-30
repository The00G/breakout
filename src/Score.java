package src;

import java.awt.*;

public class Score extends GameElement {

    /**
     * 
     */
    public int points;

    /**
     * creates a {@code score} element at the desired position of a given heigth
     * 
     * @param p      the position {@code Vector} of the score
     * @param height the height of the displayed digits
     */
    public Score(Vector p, int height) {
        super(p, new Vector(0, height));
        this.points = 0;
    }

    /**
     * adds a given amount of points to the score
     * 
     * @param p the amount to add to the score
     */
    public void add(int p) {
        this.points += p;
    }

    @Override
    public void paint(Graphics g, Vector fieldOrigin, double scale) {
        g.setColor(this.color);
        g.setFont(new Font("Calibri", Font.PLAIN, (int) (this.size.y * scale)));
        g.drawString("score: " + points, (int) (fieldOrigin.x + this.pos.x * scale),
                (int) (fieldOrigin.y + this.pos.y * scale));
    }
}