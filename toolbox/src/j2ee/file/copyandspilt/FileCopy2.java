package j2ee.file.copyandspilt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy2 {
	public static void main(String[] args) throws IOException{
		FileReader fileRead = new FileReader("C:/Users/chuan/Desktop/1234.txt");
		BufferedReader bufr = new BufferedReader(fileRead);
		FileWriter fileWrite = new FileWriter("C:/Users/chuan/Desktop/4321.txt");
		BufferedWriter bufw = new BufferedWriter(fileWrite);
		int c ;	
		while ((c = bufr.read()) != -1) {				
			bufw.write(c);
			bufw.flush();
		}
		
		fileRead.close();
		fileWrite.close();

		
	}

	private static boolean  checkchar(int c, int cnext) {
		// TODO Auto-generated method stub
		return c == '\r' && cnext == '\n' ? true : false;
	}
}
