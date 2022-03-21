import java.awt.Color;

public class Brick {
    
    public Vector pos; //position of the center
    public Vector size;
    public int totalLife, life; // if the brick is unbreakable, set life inferior to 0
    public Color color; 
    private Color [] colorList = {Color.white, Color.red, Color.magenta, Color.orange, Color.yellow, Color.green, Color.cyan, Color.blue, Color.pink};

    public Brick(double posx, double posy, double width, double height, int tl, Color c) {
        this.pos = new Vector(posx, posy);
        this.size = new Vector(width, height);
        this.totalLife = tl;
        this.life= tl;
        this.color = c;
    }

    public Brick(double posx, double posy, double width, double height, int tl) {
        this.pos = new Vector(posx, posy);
        this.size = new Vector(width, height);
        this.totalLife = tl;
        this.life= tl;
        this.color = Color.white;
    }

    public Brick(Vector pos, Vector size, int tl, Color c) {
        this.pos = pos;
        this.size = size;
        this.totalLife = tl;
        this.life= tl;
        this.color = c;
    }

    public Brick(Vector pos, Vector size, int tl) {
        this.pos = pos;
        this.size = size;
        this.totalLife = tl;
        this.life= tl;
        this.color = Color.white;
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
    /**
     * changes the color of the brick according to it's life
     */
    public void setColorBrick (){
        if (this.life<8 && this.life>0){
            this.color = colorList [this.life];
        } else if(this.life >7){
            this.color = Color.pink;
        } else {
            this.color = Color.gray;
        }
    } 

    /**
     * tells if a brick is dead and retruns true if it's the case
     * @return
     */
    public boolean isDead (){
        if(this.life == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * decreases the life of the brick by one and changes the color according to it's new life
     */
    public void ballTouchesBrick (){
        this.life--;
        this.setColorBrick();
    }

}
