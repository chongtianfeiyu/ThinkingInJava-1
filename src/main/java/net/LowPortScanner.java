package net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-11-19
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class LowPortScanner {
    public static void main(String[] args) {

        String host = "localhost";

        if (args.length > 0) host = args[0];

        for (int i = 1; i < 1024; i++) {
            try {
                Socket s = new Socket(host, i);
                System.out.println("存在一个服务器端口：" + i + " of " + host);
            } catch (UnknownHostException e) {
                System.err.println(e);
                break;
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }
}

/*

存在一个服务器端口：135 of localhost
存在一个服务器端口：445 of localhost

 */
