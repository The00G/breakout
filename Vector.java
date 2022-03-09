/**
 * Class vector
 */
public class Vector {

    public double x, y;

    /**
     * Creates a vector given cartesian coordinates
     * 
     * @param x
     * @param y
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a vector given polar coordinates
     * Can also be used with cartesian coordinates, dictaded by the polar boolean
     * 
     * @param a
     * @param b
     * @param polar
     */
    public Vector(double a, double b, boolean polar) {
        if (polar) {
            this.x = a * Math.cos(b);
            this.y = a * Math.sin(b);
        } else {
            this.x = a;
            this.y = b;
        }
    }

    /**
     * Creates a vector using an array as imput
     * 
     * @param v
     */
    public Vector(double[] v) {
        this.x = v[0];
        this.y = v[1];
    }

    /**
     * Creates an empty vector
     */
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

    /**
     * Multiplies the vector by a given factor
     * 
     * @param f
     */
    public void mult(double f) {
        this.x *= f;
        this.y *= f;
    }

    /**
     * Gives back the dot product between this and a given vector
     * 
     * @param v
     * @return double
     */
    public double dot(Vector v) {
        return this.x * v.x + this.y * v.y;
    }

    /**
     * Returns the magnitude of the vector
     * 
     * @return double
     */
    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * sets the length to this to 1
     */
    public void normalize() {
        this.mult(1 / this.mag());
    }

    /**
     * returns a vector with the same coordinates
     * 
     * @return Vector
     */
    public Vector clone() {
        return new Vector(this.x, this.y);
    }

    /**
     * Returns true if this vector has the same components as a given vector
     * 
     * @param v
     * @return boolean
     */
    public boolean equals(Vector v) {
        return this.x == v.x && this.y == v.y;
    }

    /**
     * Returns the vector as an array with the x component as element 0 and the y
     * component as element 1
     * 
     * @return double[]
     */
    public double[] toArray() {
        double[] a = new double[2];
        a[0] = this.x;
        a[1] = this.y;
        return a;
    }

    /**
     * Sets the magnitude of this vector at a given length
     * 
     * @param f
     */
    public void setMag(double f) {
        this.normalize();
        this.mult(f);
    }

    /**
     * Returns the angle made by the vector to the horizontal axis
     * 
     * @return double
     */
    public double getAngle() {
        return Math.atan2(this.y, this.x);
    }

    public void setAngle(double a) {
        double mag = this.mag();
        this.x = mag * Math.cos(a);
        this.y = mag * Math.sin(a);
    }

    public void rotate(double a) {
        this.setAngle(a + this.angle());
    }

    public String toString() {
        return "(" + this.x + "; " + this.y + ")";
    }

    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector mult(Vector v1, double f) {
        return new Vector(v1.x * f, v1.y * f);
    }

    public static double dot(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static double mag(Vector v) {
        return Math.sqrt(v.x * v.x + v.y * v.y);
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
