package j2ee.reflect.Demo;

public class UPC implements Module {

	private String name = "UPC";
	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("退出UPC");
	}

	@Override
	public void inIt() {
		// TODO Auto-generated method stub
		System.out.println("进入财产险核保系统");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.println("显示各功能模块");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

}
