package claim;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Encoder;

public class TokenAndFile {
//	public static String host = "http://211.95.76.89:43237";//  stg1
//	public static String host = "http://211.95.76.89:42080";//  stg3
	public static String host = "http://114.141.178.23:53832";// stg4
	public static void main(String[] args) throws  IOException{
		Map<String, String> paraMap = getToken();
		uploadFile(paraMap.get("sessionId"),paraMap.get("token"));
		
	}
	
	private static Map<String,String> getToken(){
		String sessionId = "";
		Map<String,String> retMap = new HashMap<String, String>();
		String token = "";
		try {
			String userName = "GZTRAFFICPOLICE001";
			String password = "abcd1234";
			Long timeStamp = new Long(System.currentTimeMillis());
			System.out.println(timeStamp);
			HttpClient httpClient = new DefaultHttpClient();
			String uri = host+"/do/app/outerLoginController/getToken";
			String miwen = signData(userName,password,timeStamp);
			System.out.println(miwen);
			List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
			formparams.add(new BasicNameValuePair("userName", userName));
			formparams.add(new BasicNameValuePair("timeStamp", timeStamp.toString()));//"747969"  理赔系统的ID
			formparams.add(new BasicNameValuePair("cipherText", miwen));
			HttpPost httpGet = new HttpPost(uri);
			UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
			httpGet.setEntity(urlEntity);
			System.out.println(httpGet.getURI());
			HttpResponse response = httpClient.execute(httpGet);
			Header[] map = response.getHeaders("Set-Cookie");
			StringBuffer sbf = new StringBuffer();
			for (Header o : map) {
				sbf.append(o.getValue()).append(";");
				sessionId = sbf.toString();
			}
			String responseStr = EntityUtils.toString(response.getEntity());
			System.out.println(responseStr);
			Map<String,Object > respMap  = JSON.parseObject(responseStr, Map.class);
			token = (String)((Map)respMap.get("data")).get("token");
			System.out.println(sessionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		retMap.put("sessionId", sessionId);
		retMap.put("token",token);
		return retMap;
	}
	private static String signData(String userName,String passWord,Long timeStamp) 
	{
		String signValue="";
		String data = "";
		data += userName+passWord +timeStamp;
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");  
	        byte[] srcBytes = data.getBytes();  
	        //使用srcBytes更新摘要  
	        sha.update(srcBytes);  
	        //完成哈希计算，得到result  
	        byte[] resultBytes = sha.digest();  
			BASE64Encoder encoder=new BASE64Encoder();
			signValue=encoder.encodeBuffer(resultBytes);//BASE64编码
		}catch(Exception e){
			e.printStackTrace();
		}
		return signValue;
	}
	
	public static void sendHttpRequest(String httpUrl, String sendData, String sessionId2) {
		// 获取服务器返回数据
		StringBuffer contentBuffer = new StringBuffer();
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadFile(String sessionId,String token){
		String url5 = host+"/do/app/fileUploadDMZController/batchFileUpload";
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("reportNo", "90516004100000021393");
		param.put("token", token);
		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
		Map<String,Object> fileMap = new HashMap<String,Object>();
		fileMap.put("fileName", "fileName");
		String filedata =GetImageStr("D:/图片/6cf6889a8def8a90c8eaf4da.jpg");
		fileMap.put("fileData", filedata);
		fileMap.put("fileMD5", "fileMD5");
		fileMap.put("fileSource", "fileSource");
		fileList.add(fileMap);
		/*fileMap = new HashMap<String,Object>();
		fileMap.put("fileName", "fileName2");
		filedata =GetImageStr("D:/green/img/afile2.jpg");
		fileMap.put("fileData", filedata);
		fileMap.put("fileMD5", "fileMD52");
		fileMap.put("fileSource", "fileSource2");
		fileList.add(fileMap);*/
		param.put("fileList", fileList);
		String params5 = JSONObject.toJSONString(param);
		sendHttpRequest(url5, params5, sessionId);
	}
	
	 public static String GetImageStr(String imgFilePath) {//
	      byte[] data = null;
	      // 读取图片字节数组
	      try {
	         InputStream in = new FileInputStream(imgFilePath);
	         data = new byte[in.available()];
	         in.read(data);
	         in.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      // 对字节数组Base64编码
	      BASE64Encoder encoder = new BASE64Encoder();
	      return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	   }
}
