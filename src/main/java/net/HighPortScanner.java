package net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * User: ViaPro
 * Date: 13-11-19
 * Time: 下午3:42
 *
 * Doesn't work out, WHY???
 */
public class HighPortScanner {
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) host = args[0];
        try {
            InetAddress theAddress = InetAddress.getByName(host);
            for (int i = 132; i < 1024; i++) {
                Socket theSocet = new Socket(theAddress, i);
                System.out.println("存在一个服务器端口：" + i + " of " + host);
            }
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (IOException e) {
        }
    }
}
