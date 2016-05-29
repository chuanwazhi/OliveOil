package tools.drop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	public static String getProperties(String key){
		if(key == null || "".equals(key)){
			System.out.println("获取配置文件中的值失败，因为键为空，使用默认值！");
			return "";
		}
		Properties p = new Properties();
		String value = "";
		try {
			p.load(new FileInputStream(StartProgram.getJarFilePath(new PropertiesUtil())+ConstValue.CONFIG_FILE_NAME));
			value = p.getProperty(key);
		} catch (FileNotFoundException e) {
			System.out.println("config.properties不存在,请在和drop.jar同级目录下创建，所有参数使用默认值！");
		} catch (IOException e) {
			System.out.println("读取配置文件异常！");
		}
		return value;
	}
}
