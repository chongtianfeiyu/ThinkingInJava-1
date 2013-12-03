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
    public static byte[] getKc() {
        return null;
    }

    private static byte[] PBOC(byte[] key, byte[] factor) {
        byte[] seed = appendData(factor, 8, (byte) 0x20);
        return ArrayUtils.addAll(DesUtils.encrypt(seed, key),DesUtils.encrypt(NOT(seed),key));
    }

    private static byte[] mac() {
        return null;
    }

    //产生定长的byte数组
    private static byte[] randomBytes(int len) {
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

    //以字节为单位进行异或运算
    private static byte xOr(byte[] data) {
        byte result = 0;
        for (byte b : data)
            result ^= b;
        return result;
    }

    private static byte[] NOT(byte[] data){
        byte[] result = new byte[data.length];
        for(int i =0;i<data.length;i++)
            result[i]= (byte) ~data[i];//未验证！
        return result;
    }



    public static void main(String[] args) throws Exception {

        for(byte b : DesUtils.encrypt(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00},
                new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}, true))
            System.out.println(b);

//        for(byte b: NOT(new byte[]{0x00,0x11,0x02}))
//            System.out.println(b);


//        System.out.println(new BASE64Decoder().decodeBuffer("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4").length);




/*        byte[] randBytes = randomBytes(3);
        for (byte b : randBytes)
            System.out.println(b);
        for (byte b : comData(randBytes, 8, (byte) 0x20))
            System.out.println(b);
            */
/*
        byte[] data = new byte[]{(byte) 0x99, (byte) 0x88,(byte)0x11};
        System.out.println(xOr(data));
        */
    }

}
