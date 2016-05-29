package tools.drop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class StartProgram {
	public static void main(String[] args) {
		System.out.println("转换开始，稍等！！！");
		//1.获取模板所在Excel的文件名
		String filePath = getJarFilePath(new StartProgram())+"模板.xls";
		//2.创建DROP对象
		Drop drop = new Drop();
		HSSFWorkbook hssfWorkbook = null;
		try {
			//根据路径创建workbook
			hssfWorkbook = new HSSFWorkbook(new FileInputStream(filePath));
			//复制模板sheet到实体sheet
			drop.copyTemplateSheetToObjectSheet(hssfWorkbook);
			//数据写入到文件中
			drop.writeWorkBook(filePath,hssfWorkbook);
		} catch (FileNotFoundException e) {
			System.out.println("创建workbook异常，filepath="+filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("转换结束！！！");
	}
	public static String getJarFilePath(Object object) {
		String jarPath = object.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		String  jarFilePath = jarPath.substring(6,jarPath.indexOf("drop.jar")).replace("%20", " ");
		return jarFilePath;
	}

}