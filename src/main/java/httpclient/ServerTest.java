package httpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Server
 */
public class ServerTest {

    public static void main(String[] args) throws Exception {

        int port = 3001;

        ServerSocket socket = new ServerSocket(port, 2, InetAddress.getByName("192.168.10.146"));
        System.out.println("Server socket open;");

        System.out.println("waiting request...");
        socket.accept();

        System.out.println("Request catch");


    }

}
