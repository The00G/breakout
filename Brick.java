import java.awt.*;

public class Brick extends Obstacle {

    public int totalLife, life; // if the brick is unbreakable, set life inferior to 0
    private static Color[] colorList = { Color.black,
            Color.white,
            new Color(185, 213, 146),
            new Color(251, 240, 156),
            new Color(241, 154, 123),
            new Color(237, 103, 93),
            new Color(235, 128, 175),
            new Color(175, 109, 205),
            new Color(72, 106, 216)
    };

    public Brick(double posx, double posy, double width, double height, int tl) {
        super(new Vector(posx, posy), new Vector(width, height));
        this.totalLife = tl;
        this.life = tl;
        setColorBrick();
    }


    /**
     * changes the color of the brick according to it's life
     */
    public void setColorBrick() {
        if (this.life < 8 && this.life > 0) {
            this.color = colorList[this.life];
        } else if (this.life >= 8) {
            this.color = colorList[8];
        } else {
            this.color = Color.gray;
        }
    }

    /**
     * tells if a brick is dead and retruns true if it's the case
     * 
     * @return
     */
    public boolean isDead() {
        if (this.life == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * decreases the life of the brick by one and changes the color according to
     * it's new life
     */
    @Override
    public void hit() {
        this.life--;
        this.setColorBrick();
        System.out.println("brick touchée");
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