package algorithms.RecursionPractice;

/**
 * Try out the Euclidean Algorithm
 * @author Nguyen
 * I love this algorithm as even though it is ancient algorithm,
 * the Euclidean Algorithm is still commonly used today,
 * not only for simplifying fractions but also for
 * RSA Algorithm (used in decryption)
 */
public class EuclideanAlg {
    public static void main(String[] args) {
        System.out.println(gcd(10023812, 2933124));
        System.out.println(gcd(27,18));
    }

    /**
     * This is a recursive version of Euclidean Algorithm
     * @param a: given positive integer
     * @param b: given positive integer
     * @return the greatest common divisor between two positive integers
     */
    public static int gcd(int a, int b) {
        int larger = Math.max(a, b);
        int smaller = Math.min(a, b);

        // Base case: the larger number is a multiple of the smaller one
        if (larger % smaller == 0) {
            return smaller;
        }

        /*
         * Reduction step:
         * Substitute the larger number with the result of larger % smaller
         * Reduction proof:
         * Given a > b, we can write: a = q*b + r (all a, b, q, r are integers)
         * Then we have: gcd(a, b) = gcd(b, r)
         */
        else {
            return gcd(smaller, larger % smaller);
        }
    }
}
