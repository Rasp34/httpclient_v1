package Http;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * First http test
 */
public class Http_v1 {

    public static void main(String[] args) {

        String link = "GET https://ru.wikipedia.org/wiki/HTTP";
        String host = "ru.wikipedia.org";
        int port = 3001;

        try {

//        Откроем сокет
//          Добавить сокет!!!
            System.out.println("Socket was open: " + host + "; Port: " + port);

            if (socket.isBound()) {
                System.out.println("Connected");
            }else System.out.println("Fail connected");

            /*
//        Отправим сообщение
            socket.getOutputStream().write(link.getBytes());
            System.out.println("Send GET request;");

//        Поймаем ответ
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader buffer = new BufferedReader(inputStreamReader);
            FileWriter fileWriter = new FileWriter("ResponceHttp_v1.txt");
            System.out.println("Create socket input stream;");

//        Запишем ответ в файл
            System.out.println("Response will be write into file...");
            String line = null;
            while (true){

                line = buffer.readLine();
                if (line != null) {
                    fileWriter.write(line);
                }
                else break;

            }
            System.out.println("End operation;");*/

//        Закроем сокет
            socket.close();
            System.out.println("Socket was closed;");

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
