package concurrency;

/**
 * User: ViaPro
 * Date: 11/10/13
 * Time: 1:23 PM
 */
public class SerialNumberChecker {

}

class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        // Initialize to a value
        for (int i = 0; i < size; i++)
            array[i] = -1;
    }

}
