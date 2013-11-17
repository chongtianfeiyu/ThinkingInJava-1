package util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: ViaPro
 * Date: 11/17/13
 * Time: 2:46 PM
 *
 * 该服务器的功能：无论接受到何种请求，都始终发送同一个文件。
 * 这个服务器命名为SingleFileHTTPServer，文件名、本地端口和内容编码方式从命令行读取。
 * 如果缺省端口，则假定端口号为80。如果缺省编码方式，则假定为ASCII。
 *
 */
public class SingleFileHTTPServer extends Thread {

    private byte[] content;
    private byte[] header;
    private int port = 80;

    private SingleFileHTTPServer(String data, String encoding, String MIMEType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, MIMEType, port);
    }

    public SingleFileHTTPServer(byte[] data, String encoding, String MIMEType, int port) throws UnsupportedEncodingException {
        this.content = data;
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n" +
                "Server: OneFile 1.0\r\n" +
                "Content-length: " + this.content.length + "\r\n" +
                "Content-type: " + MIMEType + "\r\n\r\n";
        this.header = header.getBytes("ASCII");
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Accepting connections on port " + server.getLocalPort());
            System.out.println("Data to be sent:");
            System.out.write(this.content);

            while (true) {
                Socket connection = null;
                try {
                    connection = server.accept();
                    OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                    InputStream in = new BufferedInputStream(connection.getInputStream());

                    StringBuffer request = new StringBuffer();
                    while (true) {
                        int c = in.read();
                        if (c == '\r' || c == '\n' || c == -1) {
                            break;
                        }
                        request.append((char) c);

                    }

                    //如果检测到是HTTP/1.0及以后的协议，按照规范，需要发送一个MIME首部
                    if (request.toString().indexOf("HTTP/") != -1) {
                        out.write(this.header);
                    }

                    out.write(this.content);
                    out.flush();

                } catch (IOException e) {
                    // TODO: handle exception
                } finally {
                    if (connection != null) {
                        connection.close();
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Could not start server. Port Occupied");
        }
    }

    public static void main(String[] args) {
        try {
            String contentType = "text/plain";
            if (args[0].endsWith(".html") || args[0].endsWith(".htm")) {
                contentType = "text/html";
            }

            InputStream in = new FileInputStream(args[0]);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            byte[] data = out.toByteArray();

            //设置监听端口
            int port;
            try {
                port = Integer.parseInt(args[1]);
                if (port < 1 || port > 65535) {
                    port = 80;
                }
            } catch (Exception e) {
                port = 80;
            }

            String encoding = "ASCII";
            if (args.length > 2) {
                encoding = args[2];
            }

            Thread t = new SingleFileHTTPServer(data, encoding, contentType, port);
            t.start();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage:java SingleFileHTTPServer filename port encoding");
        } catch (Exception e) {
            System.err.println(e);// TODO: handle exception
        }
    }
}