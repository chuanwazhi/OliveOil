package httpClientProgram;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil {
	public static HttpResponse sendHttpRequest(String httpUrl, String sendData, String sessionId2) {
		try {
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 15 * 100000);
			HttpConnectionParams.setSoTimeout(httpParams, 15 * 100000);
			HttpClient httpClient = new DefaultHttpClient(httpParams);
			HttpPost postMethod = new HttpPost(httpUrl);
			postMethod.addHeader("Content-Type", "application/json;charset=UTF-8");
			if (null != sessionId2) {
				postMethod.setHeader("Cookie", sessionId2);
			}
			postMethod.setEntity(new StringEntity(sendData, "utf-8"));
			// 执行post请求
			HttpResponse response = httpClient.execute(postMethod);
//			String sessionId = null;
			// 判断是否成功
			if (response.getStatusLine().getStatusCode() == 200) {
				// 登陆 保存cookie
				/*StringBuffer sbf = new StringBuffer();
				Header[] map = response.getHeaders("Set-Cookie");
				for (Header o : map) {
					sbf.append(o.getValue()).append(";");
					sessionId = sbf.toString();
				}*/
				return response;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getResponseContent(HttpResponse response) throws UnsupportedOperationException, IOException{
		// 获取服务器返回数据
		StringBuffer contentBuffer = new StringBuffer();

		// 读取服务器数据
		InputStream inputStream = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		String inputLine = null;
		while ((inputLine = reader.readLine()) != null) {
			contentBuffer.append(inputLine);
		}
		inputStream.close();
		reader.close();
		System.out.println("结果:" + contentBuffer.toString());
		return contentBuffer.toString();
	}
	
}
