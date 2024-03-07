import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program asks user if they want to calculate square root, if responses is
 * "y" . it asks user for a epsilon value and non-negative number, then outputs
 * the square root, program repeats until user enters negative value
 *
 * @author Intasar Muhiadin
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     * @param error
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double squareRoot(double x, double error) {

        double c = 2.0;
        double value = x; //initializes value to equal the same "value" of x

        //repeatedly calculates square root estimate if x isn't 0 until it
        //is less than or equal to the epsilon
        if (x != 0) {
            while (Math.abs((value * value) - x) / x >= error * error) {
                value = (value + (x / value)) / c;

            }
        } else {
            value = 0;
        }

        return value; // returns approximate square root
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //Asks user to enter epsilon value
        out.print("Enter a epsilon value: ");
        double epsilon = in.nextDouble();

        //Asks user to enter a non-negative number
        out.print("Enter a number (enter negative # to quit): ");
        double number = in.nextDouble();

        //If user enters a negative number , they are then asked to
        //enter a positive number.
        while (number >= 0) {

            // Calculates and outputs the square root of given number using
            // given epsilon value
            double answer = squareRoot(number, epsilon);
            out.println("The square root of " + number + " is " + answer);

            // asks user for another number
            out.print("Enter a number (enter negative # to quit): ");
            number = in.nextDouble();
        }
        // if user enters n for no, or any other value, program quits
        out.println("Goodbye!");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
