import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This program is uses de Jager formula. It asks the user to enter any positive
 * real valued constant. Then the user is asked to enter four personal numbers
 * that are greater than one. The program then uses these four numbers, with 17
 * values between (-5, -4, -3, -2, -1, -1/2, -1/3, -1/4, 0, 1/4, 1/3, 1/2, 1, 2,
 * 3, 4, 5) as exponents. The program finds the closest approximation with those
 * four numbers and a choice of four exponents that bring it the closest
 * possible to the constant.
 *
 * @author Intasar Muhiadin
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {

        boolean invalidInput = false; //tracks if user enters invalid input

        // tracks if user enters  valid input
        boolean validInput = false;

        double doubleValue = 0.0; //stores valid value

        while (!validInput) {
            if (invalidInput) {
                out.print("Invalid. Please enter a positive value: ");
            } else {
                out.print("Please enter a positive number: ");
            }
            String stringValue = in.nextLine();

            //parses input into a double value
            if (FormatChecker.canParseDouble(stringValue)) {
                doubleValue = Double.parseDouble(stringValue);

                //if value is greater than 0, exits loop
                if (doubleValue > 0) {
                    validInput = true;
                }

            }
            invalidInput = !validInput;
        }

        return doubleValue;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        boolean greaterThanOne = false; //tracks if value is greater than one

        //stores valid value
        double doubleValue = 0.0;

        while (!greaterThanOne) {
            doubleValue = getPositiveDouble(in, out);

            //checks if value is greater than one
            if (doubleValue > 1) {
                greaterThanOne = true;

            } else {
                out.println(
                        "Invalid. Please enter a number that is greater than one. ");
            }
        }

        return doubleValue;
    }

    /**
     * calculates the relative error between the constant and best
     * approximation.
     *
     * @param constant
     *            the constant value
     * @param bestApproximation
     *            the best approximation value
     *
     * @return the relative error in percentage form
     */
    private static double calculateRelativeError(double constant,
            double bestApproximation) {

        //double value to create relative error to percentage
        final double percentageConversion = 100.0;

        //finds relative error
        double relativeError = Math
                .abs((bestApproximation - constant) / constant)
                * percentageConversion;

        return relativeError;
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

        //ask user for positive constant
        out.println("First you will enter any number for positive real-valued"
                + " universal physical or mathematical constant that seems "
                + "interesting to you for μ ");
        double constant = getPositiveDouble(in, out);

        //asks user for 4 personal numbers
        out.println("Next you will enter 4 numbers that are personal to you "
                + "greater than one.");

        out.println("This is your first personal number");
        double w = getPositiveDoubleNotOne(in, out);
        out.println("This is your second personal number");
        double x = getPositiveDoubleNotOne(in, out);
        out.println("This is your third personal number");
        double y = getPositiveDoubleNotOne(in, out);
        out.println("This is your fourth personal number");
        double z = getPositiveDoubleNotOne(in, out);

        //array for exponents
        final double[] exponents = { -5, -4, -3, -2, -1, 0, (-1.0 / 2.0),
                (-1.0 / 3.0), (-1.0 / 4.0), 0, (1.0 / 4.0), (1.0 / 3.0),
                (1.0 / 2.0), 1, 2, 3, 4, 5 };

        //tracks current exponents
        int a;
        int b;
        int c;
        int d;

        //stores the calculated approximate number for w,x,y,z
        double numberW = 0;
        double numberX = 0;
        double numberY = 0;
        double numberZ = 0;

        //stores the exponents for the closest approximation
        double firstExponent = 0;
        double secondExponent = 0;
        double thirdExponent = 0;
        double fourthExponent = 0;

        double calculatedApproximation = 0; //calculated approximation
        double bestApproximation = 0; //the best approximation to constant

        //nested for loop to find the best possible combinations of exponents

        for (a = 0; a < exponents.length; a++) {
            numberW = (Math.pow(w, exponents[a]));

            for (b = 0; b < exponents.length; b++) {
                numberX = (Math.pow(x, exponents[b]));

                for (c = 0; c < exponents.length; c++) {
                    numberY = (Math.pow(y, exponents[c]));

                    for (d = 0; d < exponents.length; d++) {
                        numberZ = (Math.pow(z, exponents[d]));

                        calculatedApproximation = (numberW * numberX * numberY
                                * numberZ);

                        //once the best approximation is found, stores exponents
                        if (Math.abs(constant - calculatedApproximation) < Math
                                .abs(constant - bestApproximation)) {

                            bestApproximation = calculatedApproximation;

                            firstExponent = exponents[a];
                            secondExponent = exponents[b];
                            thirdExponent = exponents[c];
                            fourthExponent = exponents[d];

                        }

                    }

                }

            }

        }

        //prints the results
        out.println("The exponent a is: " + firstExponent);
        out.println("The exponent b is: " + secondExponent);
        out.println("The exponent c is: " + thirdExponent);
        out.println("The exponent d is: " + fourthExponent);
        out.println("The closest approximation is: "
                + String.format("%.2f", bestApproximation));

        //calls relative error method and prints it
        double relativeError = calculateRelativeError(bestApproximation,
                constant);
        out.println("The relative error is: "
                + String.format("%.2f", relativeError));

        /*
         *
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
