package src;
import java.util.Collection;

/**
 * Class vector
 */
public class Vector implements Comparable<Vector> {

    public double x, y;

    /**
     * Creates a vector given cartesian coordinates
     * 
     * @param   x   the ordinate coordinate
     * @param   y   the abscissa coordinate
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
     * @param   v   array containing x and y coordinate
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

    /**
     * Adds a given vector to this vector
     * 
     * @param   v   vector added to this vector
     */
    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

    /**
     * Subtracts a given vector to this vector
     * 
     * @param   v   vector substracted to this vector
     */
    public void sub(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    /**
     * Multiplies this vector by a given factor
     * 
     * @param   f   factor applied to this vector
     */
    public void mult(double f) {
        this.x *= f;
        this.y *= f;
    }

    /**
     * Gives back the dot product between the vector and a given vector
     * 
     * @param v
     * @return double
     */
    public double dot(Vector v) {
        return this.x * v.x + this.y * v.y;
    }

    /**
     * Returns the magnitude of this vector
     * 
     * @return  the magnitude of this vector
     */
    public double mag() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Returns the squared magnitude of this vector
     * 
     * @return  the squared magnitude of this vector
     */
    public double sqMag() {
        return this.x * this.x + this.y * this.y;
    }

    /**
     * Normalizes this vector
     */
    public void normalize() {
        this.mult(1 / this.mag());
    }

    /**
     * Returns a copy of this vector with the same coordinates
     * 
     * @return  a new Vector with the same coordinates
     */
    public Vector clone() {
        return new Vector(this.x, this.y);
    }

    /**
     * Indicates whether some other vector has the same coordinates as this vector
     * 
     * @param   v   the reference vector with which to compare.
     * @return  {@code true} if this vector has the same coordinates as the v
     *          argument; {@code false} otherwise.
     */
    public boolean equals(Vector v) {
        return this.x == v.x && this.y == v.y;
    }

    /**
     * Converts the vector to an array with the x component as element 0 and the y
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
     * Sets the magnitude of the vector to a given length
     * 
     * @param m
     */
    public void setMag(double m) {
        this.normalize();
        this.mult(m);
    }

    /**
     * Returns the angle between the vector and the horizontal axis
     * 
     * @return double
     */
    public double getAngle() {
        return Math.atan2(this.y, this.x);
    }

    /**
     * Set the angle the vector to a given angle
     * 
     * @param a
     */
    public void setAngle(double a) {
        double mag = this.mag();
        this.x = mag * Math.cos(a);
        this.y = mag * Math.sin(a);
    }

    /**
     * Rotate the vector by a given angle
     * 
     * @param a
     */
    public void rotate(double a) {
        this.setAngle(a + this.getAngle());
    }

    /**
     * Return the angle from this vector to another vector
     * 
     * @param v
     * @return double
     */
    public double angleWith(Vector v){
        return v.getAngle()-this.getAngle();
    }

    /**
     * set the coordinate of this vector to given values
     * 
     * @param vx    x coordinate
     * @param vy    y coordinate
     */
    public void set(double vx, double vy) {
        this.x = vx;
        this.y = vy;
    }

    /**
     * Set the coordinates of this vector to the same coordinate as a given vectore
     * 
     * @param   v   
     */
    public void set(Vector v){
        this.x = v.x;
        this.y = v.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + "; " + this.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(!(o instanceof Vector)) {
            return false;
        }

        Vector v = (Vector) o;
        return this.equals(v);
    }

    /**
     * Return the sum of two given vectors
     * 
     * @param v1
     * @param v2
     * @return Vector
     */
    public static Vector sum(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }

    /**
     * Return the difference between two given vectors
     * 
     * @param v1
     * @param v2
     * @return Vector
     */
    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * Return a copy of a given vector scaled by a given factor
     * 
     * @param v1
     * @param f
     * @return Vector
     */
    public static Vector mult(Vector v1, double f) {
        return new Vector(v1.x * f, v1.y * f);
    }

    /**
     * Return the dot product of two given vectors
     * 
     * @param v1
     * @param v2
     * @return double
     */
    public static double dot(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    /**
     * Return the magnitude of a given vector
     * 
     * @param v
     * @return double
     */
    public static double mag(Vector v) {
        return Math.sqrt(v.x * v.x + v.y * v.y);
    }

    /**
     * Returns a copy of a given vector
     * 
     * @param v
     * @return Vector
     */
    public static Vector clone(Vector v) {
        return new Vector(v.x, v.y);
    }

    /**
     * Converts a vector to an array with the x component as element 0 and the y
     * component as element 1
     * 
     * @param v
     * @return double[]
     */
    public static double[] toArray(Vector v) {
        double[] a = new double[2];
        a[0] = v.x;
        a[1] = v.y;
        return a;
    }

    /**
     * Returns the angle between a vector and the horizontal axis
     * 
     * @param v
     * @return double
     */
    public static double angle(Vector v) {
        return Math.atan2(v.y, v.x);
    }

    /** 
     * Returns a normalized copy of a vector
     * 
     * @param v
     * @return Vector
    */
    public static Vector normalized(Vector v){
        return new Vector(v.x/v.mag(), v.y/v.mag());
    }

    /**
     * Returns the angle between two vectors
     * 
     * @param v1
     * @param v2
     * @return double
     */
    public static double angleBetween(Vector v1, Vector v2) {
        return v2.getAngle()-v1.getAngle();
    }

    @Override
    public int compareTo(Vector v){
        if(this.mag()<v.mag()) {
            return -1;
        }
        if(this.mag()>v.mag()){
            return 1;
        }
        if(this.getAngle()<v.getAngle()) {
            return -1;
        }
        if(this.getAngle()>v.getAngle()){
            return 1;
        }
        return 0;
    }

    /**
     * Create a vector which is the sum of the given vectors
     * 
     * @param   vectors collection containing the vectors to be summed
     * @return  the sum of the vectors contained in the vectors parameter
     */
    public static Vector sum(Collection<Vector> vectors) {
        Vector sum = new Vector();
        for(Vector v : vectors) {
            sum.add(v);
        }
        return sum;
    }
}