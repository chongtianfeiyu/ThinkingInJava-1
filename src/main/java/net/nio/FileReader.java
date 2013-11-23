package net.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-11-23
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
public class FileReader {
    public static void read(String path){
        try {
            FileInputStream fin = new FileInputStream(path);
            FileChannel fc = fin.getChannel();//(1) 从FileInputStream中获取通道
            ByteBuffer buffer = ByteBuffer.allocate(1024);//(2) 创建缓冲区
            fc.read(buffer); //(3) 将数据从通道读到缓冲区中
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static boolean write(String fileName, byte[] message){
        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            FileChannel fc = fout.getChannel();//(1) 从FileOutputStream中获取通道
            ByteBuffer buffer = ByteBuffer.allocate(1024);//(2) 创建缓冲区
            for (int i =0;i<message.length;++i)
                buffer.put(message[i]);
            buffer.flip();
            fc.write(buffer);//(3) 将数据从通道写入到缓冲区
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }
    public static boolean write(String fileName, String message){
        byte[] msg = new byte[message.length()];
        for(int i =0;i<message.length();i++){
//            msg[i] = System.T;
        }
        return write(fileName, msg);
    }

    public static void main(String[] args){
        FileReader.read("C:\\Dev\\test\\aaa.htm");
        FileReader.write("C:\\Dev\\test\\bbb.txt",new byte[]{11,23,45,22,33});
    }
}
