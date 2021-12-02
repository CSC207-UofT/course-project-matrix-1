package utilities;

/**
 * Euclidean Algorithm is used to compute the greatest common divisor efficiently.
 */
public class EuclideanAlgorithm {

    /**
     * While the values are not equal, compute the remainder from dividing one number from the other, then use the
     * remainder to divide the divisor of the last operator, and repeat. In the end, the final remainder will be the
     * greatest common divisor.
     * <p>
     * Implementation is based on Euclidean Algorithm's Wikipedia page.
     *
     * @param a first integer
     * @param b second integer
     * @return the greatest common divisor (integer) of two numbers
     */
    public static int findGreatestCommonDivisor(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
