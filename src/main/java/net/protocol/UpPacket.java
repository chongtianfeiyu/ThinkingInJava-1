package net.protocol;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-11-26
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
public class UpPacket implements Packet {
    public static final short APDU_LEN_EXCEPTION = (short) 0x9A01;      //APDU长度错误
    public static final short SE_IDN_EXCEPTION = (short) 0x9A02;      //安全数据域安全标识错误
    public static final short SE_MAC_EXCEPTION = (short) 0x9A03;      //安全数据域MAC校验错误
    public static final short SE_DEST_ADDRESS_EXCEPTION = (short) 0x9A04;      //安全数据域中目的地址错误
    public static final short COM_TYPE_EXCEPTION = (short) 0x9A05;      //命令数据中命令类型错误
    public static final short COM_OPE_EXCEPTION = (short) 0x9A06;      //命令参数中操作类型错误
    public static final short OP_TYPE_EXCEPTION = (short) 0x9A07;      //操作类型所在流程错误
    public static final short AUTH_EXCEPTION = (short) 0x9A08;      //权限错误
    public static final short DATA_PER_EXCEPTION = (short) 0x9A09;      //数据参数错误

    private byte[] header;
    private byte securityMark;
    private byte addressMark;
    private Tag cardID;
    private byte[] time;
    private int random;
    private int mac;
    private short length;
    private Command command;

}
