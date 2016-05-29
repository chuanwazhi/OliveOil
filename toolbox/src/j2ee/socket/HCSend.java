package j2ee.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.util.Scanner;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HCSend {
	public static void main(String[] args) throws IllegalStateException, IOException{
		HttpClient httpclient = new DefaultHttpClient();
		try {  
	        HttpGet httpget = new HttpGet("http://localhost:8080/Struts2Demo/");  
	        System.out.println("executing request " + httpget.getURI());  
	        HttpResponse response = httpclient.execute(httpget);  
	        HttpEntity entity = response.getEntity();  
	  
	        System.out.println("----------------------------------------");  
	        System.out.println(response.getStatusLine());  
	        if (entity != null) {  
	            System.out.println("Response content length: " + entity.getContentLength());  
	        }  
	        System.out.println("----------------------------------------");  
	  
	        InputStream inSm = entity.getContent();  
	        Scanner sca = new Scanner(inSm);
	        while(sca.hasNextLine()){
	        	System.out.println(sca.nextLine());
	        }
	        // Do not feel like reading the response body  
	        // Call abort on the request object  
	        httpget.abort();  
	    } finally {  
	        // When HttpClient instance is no longer needed,  
	        // shut down the connection manager to ensure  
	        // immediate deallocation of all system resources  
	        httpclient.getConnectionManager().shutdown();  
	    }  
	}  
}
