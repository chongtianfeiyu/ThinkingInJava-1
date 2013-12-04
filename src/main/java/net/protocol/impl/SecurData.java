package net.protocol.impl;

import net.protocol.SCObject;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-12-4
 * Time: AM 10:53
 * To change this template use File | Settings | File Templates.
 */
public class SecurData implements SCObject {
    private byte securityMark;
    private byte addressMark;
    private Tag cardID;
    private byte[] time;
    private int random;
    private int mac;
    private short length;
}
