package j2ee.reflect.Demo;

public class EPCIS {
	public EPCIS(){
		System.out.println("����EPCISϵͳ");
	}
	public void close(){
		System.out.println("�˳�EPCISϵͳ");
	}
	public void runFonc(Module module){
		module.inIt();
		module.show();
		module.close();
	}
}
