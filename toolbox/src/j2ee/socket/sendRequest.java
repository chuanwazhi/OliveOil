package j2ee.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class sendRequest {

	public static void main(String[] args) {
		// TODO Auto-generated method stubentry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), reqCharset)).append("&"); 
		Socket ss ;
		try {
			Map<String,String> data = new HashMap<String,String>();
			data.put("name", "name");
			data.put("name2", "name2");
			//ss = new Socket("127.0.0.1",8080);
			String add = "www.baidu.com";
			ss = new Socket(add,80);
			PrintWriter pw = new PrintWriter(ss.getOutputStream(),true);
			pw.println("GET / HTTP/1.1");
			pw.println("Accept: image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
			pw.println("Accept-Language: zh-CN");
			pw.println("Connection: keep-alive");
			pw.println("Host: "+add);
			pw.println();
			//pw.println("name=yinchuan");
			pw.println();
			
			//entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), reqCharset)).append("&"); 
			//pw.flush();
			InputStream ist = ss.getInputStream();
			byte[] buf = new byte[10240];
			int length = ist.read(buf);
			if (length==-1) {
				System.out.println("aaaaaaaaaaaa==="+length);
				return ;
			}
			String s = new String(buf,0,length);
			System.out.println("===="+s);
			/*while((lenth=ist.read(buf))!=-1){
				System.out.println("===="+buf);
				System.out.println("----"+new String(buf,0,lenth));
			}*/
			ss.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
