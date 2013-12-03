package util;


import org.apache.commons.lang.ArrayUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;
import java.util.Arrays;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-12-2
 * Time: 下午6:41
 */


public class DesUtils {

    public static final String TriDES_ECB_MODE = "DESede/ECB/NoPadding";
//    public static final String DES_ECB_MODE = "DES/ECB/NoPadding";
    public static final String DES_CBC_MODE = "DES/CBC/NoPadding";

/*    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(ENCRYPT_MODE, new SecretKeySpec(key, "DES"));
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            byte[] newKey = key.length == 24 ? key : ArrayUtils.addAll(key, ArrayUtils.subarray(key, 0, 8));
            Cipher cipher = Cipher.getInstance(TriDES_ECB_MODE);
            cipher.init(ENCRYPT_MODE, new SecretKeySpec(newKey, "DESede"));
            return cipher.doFinal(data);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encryptInCBC(byte[] data, byte[] key, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance(DES_CBC_MODE);
            cipher.init(ENCRYPT_MODE, new SecretKeySpec(key, "DES"), new IvParameterSpec(iv));
            return cipher.doFinal(data);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher=Cipher.getInstance(TriDES_ECB_MODE);
            cipher.init(DECRYPT_MODE, new SecretKeySpec(key,"DESede"));
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
//        SecureRandom sr = new SecureRandom();
//        DESKeySpec dks = new DESKeySpec(key);
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//        SecretKey securekey = keyFactory.generateSecret(dks);
//        Cipher cipher = Cipher.getInstance("DES");
//        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
//        return cipher.doFinal(data);
    }


    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
/*    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }*/


    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
/*    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }*/

}