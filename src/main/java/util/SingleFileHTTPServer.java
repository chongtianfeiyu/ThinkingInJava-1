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
 * SingleFileHTTPServer类本身是Thread的子类。它的run()方法处理入站连接。
 * 此服务器可能只是提供小文件，而且只支持低吞吐量的web网站。
 * 由于服务器对每个连接所需完成的所有工作就是检查客户端是否支持HTTP/1.0，并为连接生成一两个较小的字节数组，因此这可能已经足够了。
 * 另一方面，如果你发现客户端被拒绝，则可以使用多线程。许多事情取决于所提供文件的大小，每分钟所期望连接的峰值数和主机上Java的线程模型。
 * 对弈这个程序复杂写的服务器，使用多线程将会有明显的收益。
 * Run()方法在指定端口创建一个ServerSocket。然后它进入无限循环，不断地接受连接并处理连接。
 * 当接受一个socket时，就会由一个InputStream从客户端读取请求。它查看第一行是否包含字符串HTTP。
 * 如果包含此字符串，服务器就假定客户端理解HTTP/1.0或以后的版本，因此为该文件发送一个MIME首部；然后发送数据。
 * 如果客户端请求不包含字符串HTTP，服务器就忽略首部，直接发送数据。最后服务器关闭连接，尝试接受下一个连接。
 * 而main()方法只是从命令行读取参数。从第一个命令行参数读取要提供的文件名。
 * 如果没有指定文件或者文件无法打开，就显示一条错误信息，程序退出。
 * 如果文件能够读取，其内容就读入byte数组data.关于文件的内容类型，将进行合理的猜测，结果存储在contentType变量中。
 * 接下来，从第二个命令行参数读取端口号。如果没有指定端口或第二个参数不是0到65535之间的整数，就使用端口80。
 * 从第三个命令行参数读取编码方式（前提是提供了）。否则，编码方式就假定为ASCII。
 * 然后使用这些值构造一个SingleFileHTTPServer对象，开始运行。这是唯一可能的接口。
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
            ServerSocket server = new ServerSocket(port);
            System.out.println("Accepting connections on port " + server.getLocalPort());
            System.out.println("Data to be sent:");
            System.out.write(content);

            while (true) {
                Socket connection = null;
                try {
                    connection = server.accept();
                    OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                    InputStream in = new BufferedInputStream(connection.getInputStream());

                    StringBuffer request = new StringBuffer();
                    while (true) {
                        int c = in.read();
                        if (c == '\r' || c == '\n' || c == -1) break;
                        request.append((char) c);
                    }

                    //如果检测到是HTTP/1.0及以后的协议，按照规范，需要发送一个MIME首部
                    if (request.toString().indexOf("HTTP/") != -1)
                        out.write(this.header);

                    out.write(this.content);
                    out.flush();

                } catch (IOException e) {
                    // TODO: handle exception
                } finally {
                    if (connection != null)
                        connection.close();
                }
            }

        } catch (IOException e) {
            System.err.println("Could not start server. Port Occupied");
        }
    }

    public static void main(String[] args) {
        try {
            String contentType = "text/plain";
            if (args[0].endsWith(".html") || args[0].endsWith(".htm"))
                contentType = "text/html";

            InputStream in = new FileInputStream(args[0]);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;

            while ((b = in.read()) != -1)
                out.write(b);

            byte[] data = out.toByteArray();

            //设置监听端口
            int port;
            try {
                port = Integer.parseInt(args[1]);
                if (port < 1 || port > 65535)
                    port = 80;
            } catch (Exception e) {
                port = 80;
            }

            String encoding = "ASCII";
            if (args.length > 2)
                encoding = args[2];

            Thread t = new SingleFileHTTPServer(data, encoding, contentType, port);
            t.start();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage:java SingleFileHTTPServer filename port encoding");
        } catch (Exception e) {
            System.err.println(e);// TODO: handle exception
        }
    }
}