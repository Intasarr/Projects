import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Intasar Muhiadin
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */

    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        //if n is 1, just returns 1 without entering loop
        if (n.compareTo(new NaturalNumber1L(1)) == 0) {
            n.copyFrom(new NaturalNumber1L(1));
            return;
        }

        //initializing variables
        NaturalNumber lowBound = new NaturalNumber1L(0);
        NaturalNumber two = new NaturalNumber1L(2);
        NaturalNumber one = new NaturalNumber1L(1);
        NaturalNumber highBound = new NaturalNumber1L(n);
        NaturalNumber middleBound = new NaturalNumber1L();
        NaturalNumber pow = new NaturalNumber1L();
        NaturalNumber highSubLow = new NaturalNumber1L(highBound);
        highSubLow.subtract(lowBound);

        //increments highBound with 1
        highBound.increment();

        //while comparison is greater than 1
        while (highSubLow.compareTo(one) > 0) {

            //finds middle bound
            middleBound.copyFrom(highBound);
            middleBound.add(lowBound);
            middleBound.divide(two);

            // calculates the power of middle bound
            pow.copyFrom(middleBound);
            pow.power(r);

            int compare = pow.compareTo(n);

            // if power is equal than n, copies into middle bound and returns

            if (compare == 0) {
                n.copyFrom(middleBound);
                return;

                // else if power is greater than n, updates high bound
            } else if (compare > 0) {
                highBound.copyFrom(middleBound);

                //else if power is less than n, updates low bound

            } else {
                lowBound.copyFrom(middleBound);

            }
            //updates highSubLow
            highSubLow.copyFrom(highBound);
            highSubLow.subtract(lowBound);
        }
        // copies low bound to n, which is the result
        n.copyFrom(lowBound);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
