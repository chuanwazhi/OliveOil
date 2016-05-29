package j2ee.reflect.Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class DemoStart {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws  
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		EPCIS epcis = new EPCIS();
		File file = new File("D:/程序/eclipse/java projects/mytest/src/j2ee/reflect/Demo/module.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties pro = new Properties();
		pro.load(fis);
		for(int i = 1; i <= pro.size(); i++){
			String moduleName = pro.getProperty("module"+i);
			Class module = Class.forName(moduleName);
			Module mod = (Module) module.newInstance();
			Field name = module.getDeclaredField("name");
			name.setAccessible(true);
//			System.out.println("这里是"+name.get(mod));
			name.set(mod, "hahahhahahah");
			System.out.println("这里是"+name.get(mod));
			epcis.runFonc(mod);
		}
		epcis.close();
	}

}
