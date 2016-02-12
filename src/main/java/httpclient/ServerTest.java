package httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 */
public class ServerTest {

    public static void main(String[] args) throws Exception {

        int port = 3001;

        ServerSocket socket = new ServerSocket(port, 2, InetAddress.getByName("192.168.10.146"));
        System.out.println("Server socket open;");

        System.out.println("waiting request...");
        Socket clientSocket = socket.accept();

        InputStream inputStream = clientSocket.getInputStream();
        BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));

        if (buff.readLine()!= null)  System.out.println(buff.readLine());
        else System.out.println("Null string");

        System.out.println("Request catch");


    }

}
