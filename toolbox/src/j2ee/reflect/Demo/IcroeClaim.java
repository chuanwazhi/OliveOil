package j2ee.reflect.Demo;

public class IcroeClaim implements Module {

	private String name = "icroe-claim";
	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("退出claim");
	}

	@Override
	public void inIt() {
		// TODO Auto-generated method stub
		System.out.println("进入新一代车险理赔项目");
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
