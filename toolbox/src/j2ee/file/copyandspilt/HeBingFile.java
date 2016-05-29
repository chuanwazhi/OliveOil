package j2ee.file.copyandspilt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class HeBingFile {
	private static final int COUNT = 8;
	private static final int SIZE = 1024*1023;

	public static void main(String[] args) throws IOException{
		ArrayList<FileInputStream> arr = new ArrayList<FileInputStream>();
		File dir = new File("D:\\����\\eclipse\\java projects\\qiegeoutput");
		for(int i = 1;i <= COUNT;i++){
			arr.add(new FileInputStream(new File(dir,i+".part")));
		}
		System.out.print(arr.toString());
		Enumeration<FileInputStream> en = Collections.enumeration(arr);
		SequenceInputStream sis = new SequenceInputStream(en);
		byte[] buf = new byte[SIZE];
		int len = 0;

		FileOutputStream fw = new FileOutputStream(new File(dir,"hecheng.png"));
		while((len = sis.read(buf)) != -1){

			fw.write(buf,0,len);
		}
		sis.close();
		fw.close();
	}
}
