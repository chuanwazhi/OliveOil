package j2ee.reflect.Demo;

public class UPC implements Module {

	private String name = "UPC";
	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("�˳�UPC");
	}

	@Override
	public void inIt() {
		// TODO Auto-generated method stub
		System.out.println("����Ʋ��պ˱�ϵͳ");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.println("��ʾ������ģ��");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

}
