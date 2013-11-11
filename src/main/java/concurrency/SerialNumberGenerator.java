package concurrency;

/**
 * User: ViaPro
 * Date: 11/11/13
 * Time: 6:12 PM
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static int nextSerialNumber(){
        return serialNumber++;
    }
}
