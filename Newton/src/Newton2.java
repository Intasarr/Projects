import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program asks user if they want to calculate square root, if responses is
 * y, it asks user for a non-negative number, then outputs the square root.
 *
 * @author Intasar Muhiadin
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double squareRoot(double x) {
        final double error = 0.0001; // value of epsilon
        double c = 2.0;
        double value = x;

        //repeatedly calculates square root estimate if x isn't 0 until it
        //is less than or equal to the epsilon
        if (x != 0) {
            while (Math.abs((value * value) - x) / x >= error * error) {
                value = (value + (x / value)) / c;

            }
        } else {
            value = 0; //outputs zero if value=0
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

        // Asks user if they want to find a square root of a number
        out.println("Do you want to find out the square root of a number?");
        out.print("Reply with y for yes or n for no: ");
        String response = in.nextLine();

        // If user says yes, then asked to enter a number
        if (response.equals("y")) {
            out.print("Enter a number: ");
            double number = in.nextDouble();

            //If user enters a number equal to or less than zero,
            //they are then asked to enter a positive nonzero number
            while (number < 0) {
                out.println("Please enter a positive number!");
                out.print("Enter a number: ");
                number = in.nextDouble();
            }

            // Calculates and outputs the square root of given number
            double answer = squareRoot(number);
            out.println("The square root of " + number + " is " + answer);

            // if user enters n for no, or any other value, program quits
        } else {
            out.println("Goodbye!");
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
