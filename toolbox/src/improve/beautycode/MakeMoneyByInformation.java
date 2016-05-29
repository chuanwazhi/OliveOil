package improve.beautycode;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MakeMoneyByInformation {

	public static void main(String[] args) throws IOException {
//		encodingAndOr();
//		unencoding();
//		encoding();
		unencodingAndOr();
	}

	private static void unencodingAndOr() throws IOException {
		FileReader fr = new FileReader(new File("D:/company/用户数据/userBanCardInformationTemp.TXT"));
		FileWriter fw = new FileWriter(new File("D:/company/用户数据/userBanCardInformation1.TXT"));
		char[] c = new char[24];
		while(fr.read(c) != -1){
			int[] data = new int[24];
			int sum = 0;
			for(int i = 0; i<24 ; i++){
				data[i] = (c[i] - 48)^1;
			}
			for(int i = 0; i<24 ; i++){
				sum += data[23-i] << i;
			}
			fw.write(String.valueOf((char)sum));
			fw.flush();
		}
		fw.close();
		fr.close();
		System.out.println("ok");
	}

	private static void encoding() throws IOException {
		FileReader fr = new FileReader(new File("D:/company/用户数据/abc2.TXT"));
		FileWriter fw = new FileWriter(new File("D:/company/用户数据/userBanCardInformationTemp.TXT"));
		int a = 0;
		while ((a = fr.read()) != -1){
			int[] b = new int[24];
			for(int index = 23;a > 0;index --){
				b[index] = a%2;
				a = a/2;
			}
			String out = "";
			for(int i = 0;i<24;i++){
				out += b[i];
			}
			fw.write(out);
			fw.flush();
		};
		fw.close();
		fr.close();
	}

	private static void unencoding() throws IOException {
		FileReader fr = new FileReader(new File("D:/company/用户数据/userBanCardInformationTemp.TXT"));
		FileWriter fw = new FileWriter(new File("D:/company/用户数据/userBanCardInformation1.TXT"));
		char[] c = new char[24];
		while(fr.read() != -1){
			int[] data = new int[24];
			int sum = 0;
			for(int i = 0; i<24 ; i++){
				data[i] = c[i] - 48;
			}
			for(int i = 0; i<24 ; i++){
				sum += data[23-i] << i;
			}
			fw.write(String.valueOf((char)sum));
			fw.flush();
		}
		fw.close();
		fr.close();
	}

	private static void encodingAndOr() throws IOException {
		FileReader fr = new FileReader(new File(""));
		FileWriter fw = new FileWriter(new File(""));
		int a = 0;
		while ((a = fr.read()) != -1){
			int[] b = new int[24];
			for(int index = 23;a > 0;index --){
				b[index] = a%2;
				a = a/2;
			}
			String out = "";
			for(int i = 0;i<24;i++){
				out += b[i]^1;
			}
			fw.write(out);
			fw.flush();
		};
		fw.close();
		fr.close();
	}

}
