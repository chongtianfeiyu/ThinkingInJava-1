package net.protocol.impl;

import net.protocol.Header;
import net.protocol.LengthException;

import static net.util.BitConverter.getBytes;
import static net.util.BitConverter.toShort;
import static org.apache.commons.lang.ArrayUtils.addAll;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-12-4
 * Time: 上午11:53
 * To change this template use File | Settings | File Templates.
 */
public class ResponseHeader implements Header {
    private final byte firstByte = 0x60;
    private final byte secondByte = 0x1F;
    private final byte thirdByte = 0x00;//暂无意义
    private short length;   //根据协议来修改！！！！！！！！！！！！！

    public int getLength() throws LengthException {
        if(length<=0)
            throw new LengthException();
        return length;
    }

    public Header setLength(byte[] len) {
        this.length = toShort(len);
        return this;
    }

    public byte[] toByteArray(){
        return addAll(new byte[]{firstByte, secondByte, thirdByte}, getBytes(length));
    }
}
