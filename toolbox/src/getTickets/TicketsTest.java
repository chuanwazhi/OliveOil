package getTickets;

import httpClientProgram.HttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSONObject;

public class TicketsTest {

	public static void main(String[] args) throws UnsupportedOperationException, IOException {
		Map<String,String> param = new HashMap<String ,String>();
		int index = 14107;
		String formhash[][] = {
				{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"},
				{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"},
				{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"},
				{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"},
				{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"},
				{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"},
				{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"},
				{"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"}
		};
		param.put("id", "hejin_toupiao");
		param.put("model", "ticket");
		param.put("zid", "369");
		long a[] = {16*16*16*16*16*16*16*16l,16*16*16*16*16*16*16,16*16*16*16*16*16,16*16*16*16*16,16*16*16*16,16*16*16,16*16,16};
		int j =10;
		long p = 16*16*16*16*16*16*16*16l;
		for(int i = 0;i < p ; i++){
			
			param.put("formhash", formhash[0][(int) (j%a[0])]+formhash[1][(int) (j%a[1])]+formhash[2][(int) (j%a[2])]+formhash[3][(int) (j%a[3])]+formhash[4][(int) (j%a[4])]+formhash[5][(int) (j%a[5])]+formhash[6][j%256]+formhash[7][j%16]);
			param.put("_", "14622005"+index++);
			j++;
			String params5 = JSONObject.toJSONString(param);
			System.out.println(params5);
			HttpResponse response = HttpClientUtil.sendHttpRequest("http://u3.trip05.com/plugin.php", params5, null);
			String resStr = HttpClientUtil.getResponseContent(response);
			System.out.println(resStr);
		}
	}

}
