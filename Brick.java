import java.awt.Color;

public class Brick extends Obstacle {

    public int totalLife, life; // if the brick is unbreakable, set life inferior to 0
    public Color color;
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
    public void ballTouchesBrick() {
        this.life--;
        this.setColorBrick();
    }

}