public class Platform {
    
    public Vector pos; //position of the center
    public Vector size;

    public Platform(Vector p, Vector s) {
        this.pos = p;
        this.size = s;
    }

    public Platform(double posx, double posy, double width, double height) {
        this.pos = new Vector(posx, posy);
        this.size = new Vector(width, height);
    }

}
