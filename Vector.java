public class Vector {
    
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(double[] v) {
        this.x = v[0];
        this.y = v[1];
    }

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    //multiply by a factor
    public void mult(double f) {
        this.x *= f;
        this.y *= f;
    }

    //dot product
    public double dot(Vector v) {
        return this.x*v.x + this.y*v.y;
    }

    //magnitude of the vector
    public double mag() {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }
    
    public void normalize(){
        this.mult(1/this.mag());
    }

    public Vector clone() {
        return new Vector(this.x, this.y);
    }

    public boolean equals(Vector v) {
        return this.x==v.x && this.y==v.y;
    }

    public double[] toArray() {
        double[] a = new double[2];
        a[0] = this.x;
        a[1] = this.y;
        return a;
    }

    public void setMag(double f) {
        this.normalize();
        this.mult(f);
    }

    public double angle() {
        return Math.atan2(this.y, this.x);
    }

    public void setAngle(double a) {
        double mag = this.mag();
        this.x = mag*Math.cos(a);
        this.y = mag*Math.sin(a);
    }

    public void rotate(double a) {
        this.setAngle(a+this.angle());
    }


    public static Vector add(Vector v1, Vector v2){
        return new Vector(v1.x+v2.x, v1.y+v2.y);
    }

    public static Vector sub(Vector v1, Vector v2){
        return new Vector(v1.x-v2.x, v1.y-v2.y);
    }

    public static Vector mult(Vector v1, double f){
        return new Vector(v1.x*f, v1.y*f);
    }

    public static double dot(Vector v1, Vector v2){
        return v1.x*v2.x + v1.y*v2.y;
    }

    public static double mag(Vector v) {
        return Math.sqrt(v.x*v.x + v.y*v.y);
    }

    public static Vector clone(Vector v) {
        return new Vector(v.x, v.y);
    }

    public static double[] toArray(Vector v) {
        double[] a = new double[2];
        a[0] = v.x;
        a[1] = v.y;
        return a;
    }

    public static double angle(Vector v) {
        return Math.atan2(v.y, v.x);
    }
}
