package net.util;

/**
 * User: ViaPro
 * Date: 13-11-20
 * Time: 下午2:40
 */
public class ParsePhoneNumber {
    public static void main(String[] args) {
    }

    public static String phoneNumberToCode(String phoneNumber) {
        if (phoneNumber != null) {
            int length = phoneNumber.length();
            StringBuilder cellNumber = new StringBuilder(phoneNumber);
            for (int i = 0; i < 14 - length; i++) cellNumber.append('F');
            StringBuilder code = new StringBuilder("0").append(Integer.toHexString(length));
            cellNumber.charAt(0);
            for (int i = 0; i < 7; i++) {
                code.append(cellNumber.charAt(2 * i + 1));
                code.append(cellNumber.charAt(2 * i));
            }
            return code.toString();
        }
        return null;
    }

    public static String codeToPhoneNumber(String code) {
        if (code != null) {

        }
        return null;
    }
}
