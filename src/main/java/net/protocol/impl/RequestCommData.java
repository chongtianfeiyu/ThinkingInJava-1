package net.protocol.impl;

import net.protocol.Command;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-12-3
 * Time: 下午7:04
 * To change this template use File | Settings | File Templates.
 */
public class RequestCommData extends CommData implements Command {
    public static final byte OP_OPEN_CARD = 0x01;
    public static final byte OP_DEL_CARD = 0x02;
    public static final byte OP_LOST_CARD = 0x03;
    public static final byte OP_NAME_PWD = 0x04;
    public static final byte OP_FILE = 0x05;
    public static final byte OP_DIVCE = 0x06;
    public static final byte OP_BIND_DIVCE = 0x07;
    public static final byte OP_MODIFY_USERINFO = 0x08;
    public static final byte OP_BACKUP = 0x09;
    public static final byte OP_RECOVER = 0x0A;
    public static final byte OP_SPACE_INFO = 0x0B;
    public static final byte OP_SYNC_INFO = 0x0C;

}
