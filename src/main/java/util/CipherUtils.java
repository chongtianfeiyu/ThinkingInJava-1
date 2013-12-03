package util;

import concurrency.Immutable;
import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-12-2
 * Time: 上午9:30
 */

@Immutable
public class CipherUtils {

    //获取会话密钥
    public static byte[] getKc(byte[] random, byte[] key, byte[] phoneNumber) {
        return PBOC(phoneNumber, PBOC(random, key));
    }

    public static byte[] getMAC(byte[] data, byte[] key) {
        return DesUtils.encryptInCBC(appendData(data, 8, (byte) 0x20), key, new byte[8]);
    }

    private static byte[] PBOC(byte[] factor, byte[] key) {
        byte[] seed = appendData(factor, 8, (byte) 0x20);
        return ArrayUtils.addAll(DesUtils.encrypt(seed, key), DesUtils.encrypt(NOT(seed), key));
    }

    //产生定长的byte数组
    public static byte[] randomBytes(int len) {
        byte[] result = new byte[len];
        new Random().nextBytes(result);
        return result;
    }

    //补充数组到指定步长的整数倍
    private static byte[] appendData(byte[] source, int step, byte addData) {
        if (source.length % step == 0)
            return source;
        byte[] result = Arrays.copyOf(source, source.length + (step - source.length % step));
        Arrays.fill(result, source.length, result.length, addData);
        return result;
    }

    private static byte[] NOT(byte[] data) {
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++)
            result[i] = (byte) ~data[i];
        return result;
    }


    public static void main(String[] args) throws Exception {

        for (byte b : getMAC(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) 0xAA}, new byte[]{0x01, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}))
            System.out.println(b);

    }
}
