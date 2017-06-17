package httpClientProgram.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;

import httpClientProgram.HttpClientUtil;

public class WeddingDressImg {
	public static void main(String[] args){
		String httpUrl = "http://222.46.19.88:1001/%E5%AE%A2%E6%88%B7%E6%AF%9B%E7%89%87/20161229/Z1612290043-%E5%B0%B9%E9%92%8F-%E5%85%A8%E5%8A%9B/";
		String sessionId2 = "ASP.NET_SessionId=uoswnuajblnkpy45z1ztho55";
		String prefix = "CW9A";
		String postfix = ".jpg";
		for(int index = 7727 ; index < 8236;index++ )
		{
			String fileName = prefix +index + postfix;
			String filePath = "D://图片//wedingdressimg//"+fileName;
			downloadWeddingDressImg(httpUrl+fileName,sessionId2,filePath);
			System.out.println(fileName+"ok");
		}
	}

	private static void downloadWeddingDressImg(String httpUrl,
			String sessionId2,String filePath) {
		HttpResponse rsp = HttpClientUtil.sendHttpRequestWithGet(httpUrl, sessionId2);
		try {
			if(rsp == null){
				System.out.println(filePath+"照片不存在");
				return ;
			}
			InputStream inputStream = rsp.getEntity().getContent();
			File file = new File(filePath);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int length = 0;
			while((length = inputStream.read(buf)) != -1){
				fos.write(buf, 0, length);
				fos.flush();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
