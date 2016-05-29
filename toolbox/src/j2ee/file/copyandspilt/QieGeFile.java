package j2ee.file.copyandspilt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class QieGeFile {

	private static final int SIZE = 1024*1024;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\chuan\\Desktop\\�½��ļ���\\2 (1).png");
		FileInputStream fr = new FileInputStream(file);
		byte[] buf = new byte[SIZE];
		int len = 0;
		int count = 1;
		File dir  = new File("D:\\����\\eclipse\\java projects\\qiegeoutput");
		if (!dir.exists()){
			dir.mkdirs();
		}
		System.out.print(file.length());
		FileOutputStream fw =null;
		while((len = fr.read(buf)) != -1){
			fw = new FileOutputStream(new File(dir,(count++)+".part"));
			fw.write(buf,0,len);

		}
		fr.close();
		fw.close();
	}

}
