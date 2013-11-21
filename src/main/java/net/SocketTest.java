package net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-11-19
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class SocketTest {
    public static void main(String[] args){
        try{
            Socket toBaidu = new Socket("www.baidu.com",80);
            System.out.println(toBaidu.isConnected());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
