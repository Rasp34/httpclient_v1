package httpclient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * HTTP client from
 */

public class HTTPClient {
	
	public static void main(String[] args) {

		try {
			
			byte buf[] = new byte[64*1024];
			int r;
			
//			Считываем запросы
			FileInputStream fileInputStream = new FileInputStream(args[0]);
			r = fileInputStream.read(buf);
			String header = new String(buf, 0, r);
			fileInputStream.close();
			
//			Парсинг строк
			String host = extract(header, "Host:", "\n");
			
//			Проверка хоста
			if ( host == null) {
				System.out.println("invalid request:\n" + header);
				return;
			}
			
//
			int port = host.indexOf(":", 0);
			if (port < 0 ) {
				port = 80;
			}else{
				
				port = Integer.parseInt(host.substring(port+1));
				host = host.substring(0, port);
			}

//			Watch
			System.out.println(port);
			System.out.println(host);
			System.out.println(header);

			Socket s = new Socket(host, port);

			s.getOutputStream().write(header.getBytes());

			InputStream inputStream = s.getInputStream();

			FileOutputStream fos = new FileOutputStream(args[1]);

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
