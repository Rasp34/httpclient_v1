package Http;


import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * First http test
 */
public class Http_v1 {

    public static void main(String[] args) throws Exception {

        String host = "habrahabr.ru";
        int port = 80;

//        Считываем запросы
        byte[] buf = new byte[64*1024];
//        В запросе в конце обязательно необходимо два сброса на новую строку \n\n
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        fileInputStream.read(buf);
        fileInputStream.close();

//        String header = "HEAD https://ru.wikipedia.org/wiki/HTTP HTTP/1.1\nHost: ru.wikipedia.org\nUser-Agent: HTTPClient\n\n";
//        buf = header.getBytes("UTF-8");

        try {

            Socket socket = new Socket(host, port);

            if (socket.isConnected()) System.out.println("Connect with host: " + host);
            else System.out.println("Fail connected");

            socket.getOutputStream().write(buf);
            System.out.println("Request send\n" + "Wait response...\n" );

            InputStreamReader inputStreamReader= new InputStreamReader(socket.getInputStream());
            BufferedReader buff = new BufferedReader(inputStreamReader);

            String dataLine;
            while (true){

                dataLine = buff.readLine();
                if (dataLine!=null) System.out.println(dataLine);
                else break;


            }


            System.out.println("Complete");
            socket.close();
            System.out.println("Socket closed");


        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
