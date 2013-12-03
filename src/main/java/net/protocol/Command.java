package net.protocol;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-12-3
 * Time: 下午6:23
 * To change this template use File | Settings | File Templates.
 */
public abstract class Command {
    public static final byte DATA_SET = 0x01;
    public static final byte DATA_REQUEST = 0x02;
    public static final byte DATA_VALIDATION = 0x03;
    public static final byte COMMAND_CONFIRM = 0x04;

    protected byte type;
    protected short length;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }
}
