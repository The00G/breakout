package src;

/**
 * Class vector
 */
public class Vector{

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
     * Normalizes this vector
     */
    public void normalize() {
        this.mult(1 / this.mag());
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
     * Returns the angle between the vector and the horizontal axis
     * 
     * @return double
     */
    public double getAngle() {
        return Math.atan2(this.y, this.x);
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
     * Returns a normalized copy of a vector
     * 
     * @param v
     * @return Vector
    */
    public static Vector normalized(Vector v){
        return new Vector(v.x/v.mag(), v.y/v.mag());
    }
   
}