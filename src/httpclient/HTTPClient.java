package httpclient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;


public class HTTPClient {
	
	public static void main(String[] args) {

		try {
			
			byte buf[] = new byte[64*1024];
			int r;
			
//			читаем файл с запросом в сроку header
			FileInputStream fileInputStream = new FileInputStream(args[0]);
			r = fileInputStream.read(buf);
			String header = new String(buf, 0, r);
			fileInputStream.close();
			
//			выделим хост, порт и url
			String host = extract(header, "Host:", "\n");
			
//			ошибка отсутствия хоста
			if ( host == null) {
				System.out.println("invalid request:\n" + header);
				return;
			}
			
//			находим порт сервера
			int port = host.indexOf(":", 0);
			if (port < 0 ) {
				port = 80;
			}else{
				
				port = Integer.parseInt(host.substring(port+1));
				host = host.substring(0, port);
			}
			
//			открываем сокет до сервера
			Socket s = new Socket(host, port);
			
//			отправляем http request
			s.getOutputStream().write(header.getBytes());
			
//			получаем поток данных от сервера
			InputStream inputStream = s.getInputStream();
			
//			запишем в файл
			FileOutputStream fos = new FileOutputStream(args[1]);
			
//			читаем ответ сервера
			r = 1;
			while (r > 0) {
				
				r = inputStream.read(buf);
				if (r > 0) {
					fos.write(buf, 0, r);
					
				}
				
			}
			
			fileInputStream.close();
			fos.close();
			s.close();
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	static String extract(String str, String start, String end){
		int s = str.indexOf("\n\n", 0);
		if (s < 0) s =  str.indexOf("\r\n\r\n", 0);
		if (s > 0) str = str.substring(0, s);
		s = str.indexOf(start, 0) + start.length();
		if (s < start.length()) return null;
		int e = str.indexOf(end, s);
		return (str.substring(s, e)).trim();
	}

}
