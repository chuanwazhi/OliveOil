package j2ee.reflect.Demo;

public class IcroeClaim implements Module {

	private String name = "icroe-claim";
	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("�˳�claim");
	}

	@Override
	public void inIt() {
		// TODO Auto-generated method stub
		System.out.println("������һ������������Ŀ");
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
