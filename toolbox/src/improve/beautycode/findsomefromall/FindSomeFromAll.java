package improve.beautycode.findsomefromall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindSomeFromAll {
	private static int MOVE_STEP = 0;
	private static int COMPARE_STEP = 0;
	private static int COUNT = 0;
	

	public static void main(String[] args) {
		BufferedReader br  = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/program/workspacing/mytest/src/ychuan/beautycode/findsomefromall/allNum.txt"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String readLine= null;
		int needCount  = 100;
		int[] some = new int[needCount+1];
		try {
			some[0] = Integer.parseInt(br.readLine());
			COUNT++;
			some[1] = Integer.parseInt(br.readLine());
			COUNT++;
			if(some[0] < some[1]){
				COMPARE_STEP++;
				int temp = some[0];
				some[0] = some[1];
				some[1] = temp;
			}
			for(int i = 2; i<needCount;i++){
				some[i] = Integer.parseInt(br.readLine());
				COUNT++;
				sort(some,0,i);
				/*String out = "";
				for(int j = 0;j <= i;j++){
					out += some[j]+",";
				}
				System.out.println(out);*/
			}
			while((readLine = br.readLine())!=null){
				COUNT++;
				if (Integer.parseInt(readLine) <= some[needCount-1]){
					continue;
				}else{
					some[needCount] = Integer.parseInt(readLine) ;
					sort(some,0,needCount);
				}
				/*String out = "";
				for(int j = 0;j < needCount;j++){
					out += some[j]+",";
				}
				System.out.println(out);*/
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("MOVE_STEP="+MOVE_STEP+";COMPARE_STEP="+COMPARE_STEP);
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void sort(int[] some, int off, int len) {
		System.out.println("COUNT="+COUNT+"move_step="+MOVE_STEP+";change_step="+COMPARE_STEP);
		int son = some[0] - some[len] >= 0 ? some[0] - some[len] : 0;
		int mother = some[0] - some[len-1];
		int index = (int)Math.ceil((len -1 )*((son+0.0)/mother));
		if(index == 0){
			COMPARE_STEP++;
			move(some,index,len);
			return;
		}
		if (index >= len){
			COMPARE_STEP++;
			return;
		}
		while(true){
			if(some[len] > some[index]){
				COMPARE_STEP++;
				if(some[len] > some[index-1]){
					COMPARE_STEP++;
					index --;
				}
				if (some[len] <= some[index-1]){
					COMPARE_STEP++;
					move(some,index,len);
					return;
				}
			}else if(some[len] < some[index]){
				COMPARE_STEP++;
				if(some[len] < some[index+1]){
					COMPARE_STEP++;
					index ++;
				}
				if (some[len] >= some[index+1]){
					COMPARE_STEP++;
					move(some,index+1,len);
					return;
				}
			}else{
				move(some,index,len);
				return;
			}
		}
	}

	private static void move(int[] some, int index,int len) {
		int temp = some[len];
		MOVE_STEP++;
		for (; index < len;len --){
			some[len] = some[len-1];
			MOVE_STEP++;
		}
		some[index] = temp;
		MOVE_STEP++;
	}

}
