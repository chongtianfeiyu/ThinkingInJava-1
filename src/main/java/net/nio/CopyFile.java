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
 * Time: 下午3:07
 * To change this template use File | Settings | File Templates.
 */
public class CopyFile {
    public static boolean copy(String source, String destination){
        try {
            FileInputStream fin = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(destination);
            FileChannel fcin = fin.getChannel();
            FileChannel fcout = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (fcin.read(buffer) !=-1){
                buffer.clear();
                fcin.read(buffer);
                buffer.flip();
                fcout.write(buffer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }
}
