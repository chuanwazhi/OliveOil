package j2ee.io.calendarclass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class calendarDemo {
	private int age ;
	public String str;
	public Object obj;

	public calendarDemo() {
		super();
	}
	public calendarDemo(String str , int age ,Object obj){
		System.out.println("hehehehheehhehehheh");
		System.out.println(str+age+(String)obj);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//demo_1();
		demo_2();
		
		
	}

	private static void demo_2() {
		// TODO Auto-generated method stub
		String strd  = "2013-7-12";
//		DateFormat df = DateFormat.getDateInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date da = null;
		try {
			da = sdf.parse(strd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(da);
	}

	private static void demo_1() {
		// TODO Auto-generated method stub
		Date da = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy...MM...dd");
//		DateFormat da = DateFormat.getInstance();
		String strda = sdf.format(da);
		System.out.println(strda);
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}

}
