package src;
import java.awt.*;

public class Brick extends Obstacle {

    final private static int POINTS_BY_HIT = 20;
    /**
     * Bricks' color depending on life left
     * <ul><li>the color in position 0 is the color for indestructible bricks
     * <li>the colors in position 1 to 7 are the colors for the corresponding life level
     * <li>the color in position 8 is the color for bricks with a life level of 8 or more</ul>
     */
    private static Color[] colorList = { Color.gray,
        Color.white,
        new Color(185, 213, 146),
        new Color(251, 240, 156),
        new Color(241, 154, 123),
        new Color(237, 103, 93),
        new Color(235, 128, 175),
        new Color(175, 109, 205),
        new Color(72, 106, 216)
    };

    /**
     * The total life of this brick
     */
    public int totalLife;

    /**
     * The current life of this brick
     */
    public int life;

    public double bonusChance = 1;
    
    /**
     * Create a new brick with a given position, size and life
     * 
     * @param posx      the x coordinate of the center of the brick
     * @param posy      the y coordinate of the center of the brick
     * @param width     the width of the brick
     * @param height    the height of the brick
     * @param tl        the total life of the brick
     */
    public Brick(double posx, double posy, double width, double height, int tl) {
        super(new Vector(posx, posy), new Vector(width, height));
        this.totalLife = tl;
        this.life = tl;
        setColorBrick();
    }

    /**
     * Create a new brick with a given position, size and life
     * 
     * @param pos       the coordinates of the center of the brick
     * @param size      the size of the brick
     * @param tl        the total life of the brick
     */
    public Brick(Vector pos, Vector size, int tl) {
        super(pos, size);
        this.totalLife = tl;
        this.life = tl;
        setColorBrick();
    }

    /**
     * Set the color of this brick according to its life
     */
    public void setColorBrick() {
        if (this.life < 8 && this.life > 0) {
            this.color = colorList[this.life];
        } else if (this.life >= 8) {
            this.color = colorList[8];
        } else {
            this.color = colorList[0];
        }
    }

    /**
     * Indicatess whether or not a brick is dead
     * 
     * @return {@code true} if this brick isDead; {@code false} else
     */
    public boolean isDead() {
        if (this.life == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decreases the life of this brick by one and updates the color according to its new life
     */
    @Override
    public int hit() {
        super.hit();
        if(this.life > 0){
            this.life--;
            this.setColorBrick();
        }
        return POINTS_BY_HIT;
        //System.out.println("brick touchée");
    }

    @Override
    public void paint(Graphics g, Vector fieldOrigin, double fieldScale) {
        super.paint(g, fieldOrigin, fieldScale);
        g.setColor(Color.black);
        g.drawRect( (int) (fieldOrigin.x + (this.pos.x - this.size.x) * fieldScale),
                    (int) (fieldOrigin.y + (this.pos.y - this.size.y) * fieldScale),
                    (int) (2 * this.size.x * fieldScale),
                    (int) (2 * this.size.y * fieldScale));
    }

}