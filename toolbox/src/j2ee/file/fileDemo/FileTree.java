package j2ee.file.fileDemo;

import java.io.File;

public class FileTree {
	public static void main(String[] args){
		fileTree_1(new File(""),0);
	}

	private static void fileTree_1(File dir,int nbsp) {
		// TODO Auto-generated method stub
		File[] files = dir.listFiles(new HiddenFilter());
/*		for(File file : files ){
			System.out.println(file.getName());
			if(file.isDirectory()){
				System.out.println("true-------"+file.getName());
			}
		}*/
		for(File file : files ){
			for(int i = 0; i <= nbsp;i++){
				System.out.print(" ");
			}
			System.out.print("|");
			System.out.println();
			for(int i = 0; i <= nbsp;i++){
				System.out.print(" ");
			}
			System.out.println("|-"+file.getName());
			if(file.isDirectory()){
				fileTree_1(file,nbsp+1);
			}
		}
		
	}
}
