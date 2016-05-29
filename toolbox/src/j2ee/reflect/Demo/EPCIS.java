package j2ee.reflect.Demo;

public class EPCIS {
	public EPCIS(){
		System.out.println("进入EPCIS系统");
	}
	public void close(){
		System.out.println("退出EPCIS系统");
	}
	public void runFonc(Module module){
		module.inIt();
		module.show();
		module.close();
	}
}
