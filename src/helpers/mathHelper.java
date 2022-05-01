package helpers;

/**
 * Set of methods than can help with mathematical operations
 */
public class mathHelper {

    /**
     * The {@code constrain} method returns a value if it's in a given range,
     * returns the max if it exceeds that range and the min if below the range.
     * 
     * @param value the value to test
     * @param min   the minimum acceptable value
     * @param max   the maximun acceptable value
     * @return the value adapted to the given interval
     */
    public static double constrain(double value, double min, double max) {
        if (value >= min && value <= max) {
            return value;
        } else if (value < min) {
            return min;
        } else {
            return max;
        }
    }

    /**
     * returns the absolute value of a given value
     * 
     * @param value
     * @return double
     */
    public static double abs(double value) {
        if (value < 0) {
            return -value;
        } else {
            return value;
        }
    }

    /**
     * The method {@code lerp} is a linear interpolation. Calculates a number
     * between two numbers at a specific increment. The amt parameter is the amount
     * to interpolate between the two values where 0.0 equal to the first point, 0.1
     * is very near the first point, 0.5 is half-way in between, and 1.0 is equal to
     * the second point. If the value of amt is more than 1.0 or less than 0.0, the
     * number will be calculated accordingly in the ratio of the two given numbers.
     * The lerp function is convenient for creating motion along a straight path and
     * for drawing dotted lines.
     * 
     * @param min
     * @param max
     * @param factor
     * @return
     */
    public static double lerp(double min, double max, double factor) {
        return min + (max - min) * factor;
    }

    /**
     * Returns a random integer between two given integers.
     * 
     * @param min
     * @param max
     * @return random integer between min and max
     */
    public static int RandomIntBetween(int min, int max) {
        return (int) lerp(min, max + 1, Math.random());
    }
}
