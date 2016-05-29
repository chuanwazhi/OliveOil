package improve.beautycode.findsomefromall;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CreateNumber {

	public static void main(String[] args) {
		File f = new File("D:/program/workspacing/mytest/src/ychuan/beautycode/findsomefromall/allNum.txt");
		int sum = 1000000;
		BufferedWriter bw = null; 
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new  FileOutputStream(f)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < sum; i++){
			try {
				bw.write(String.valueOf(Math.round(sum*Math.random())));
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (bw!= null){
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
