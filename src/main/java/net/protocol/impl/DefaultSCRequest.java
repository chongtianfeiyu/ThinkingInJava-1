package net.protocol.impl;

import net.protocol.Header;
import util.SCRequest;

/**
 * Created with IntelliJ IDEA.
 * User: ViaPro
 * Date: 13-12-4
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public class DefaultSCRequest extends SCRequest {
    private Header header;
    private SecurData securData;
    private CommData commData;

    public Header header() {
        return header;
    }

    public SCRequest setHeader(Header header) {
        this.header = header;
        return this;
    }

    public SecurData securData() {
        return securData;
    }

    public SCRequest setSecurData(SecurData securData) {
        this.securData = securData;
        return this;
    }

    public CommData commData() {
        return commData;
    }

    public SCRequest setCommData(CommData commData) {
        this.commData = commData;
        return this;
    }
}
