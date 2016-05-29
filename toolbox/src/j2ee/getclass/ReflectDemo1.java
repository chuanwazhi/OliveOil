package j2ee.getclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import j2ee.io.calendarclass.calendarDemo;

public class ReflectDemo1 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ReflectDemo1 refD = new ReflectDemo1(); 
//		refD.getRefClass_1();
//		refD.getRefClass_2();
//		refD.getRefClass_3();
//		refD.getConstructor_1();
//		refD.getField_1();
		refD.getMethod();
	}
	
	private void getMethod() throws Exception {
		// TODO Auto-generated method stub
		Class clazz = Class.forName("j2ee.io.calendarclass.calendarDemo");
		Object abc = clazz.newInstance();
		Method methodSet = clazz.getDeclaredMethod("setStr",String.class);
		Method methodGet = clazz.getDeclaredMethod("getStr");
		/*Method[] methods = clazz.getDeclaredMethods( );
		for(Method method : methods){
			System.out.println(method.getName());
		}*/
		methodSet.invoke(abc, "asd");
		System.out.println(methodGet.invoke(abc));
		
	}

	private void getField_1() throws  Exception {
		// TODO Auto-generated method stub
		//反射机制是可以获取所有的信息的，无论私有还是公有
		Class clazz = Class.forName("j2ee.io.calendar0class.calendarDemo");
		Object abc = clazz.newInstance();
		Field fid = clazz.getDeclaredField("age");
//		System.out.println(fid.get(abc));//能获取字段，但是仍然无法使用  需要设置accessible的值  变成公有成员
		fid.setAccessible(true);
		fid.set(abc, 123);
		System.out.println(fid.get(abc));
		
		
	}

	private void getConstructor_1() throws Exception {
		// TODO Auto-generated method stub
		Class clazz = Class.forName("j2ee.io.calendarclass.calendarDemo");
		Constructor cons =  clazz.getConstructor(String.class,int.class,Object.class);
		cons.newInstance("dajiahao",12,"asdasd");
		System.out.println("lalalalal");
	}

	private void getRefClass_3() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class clazz = Class.forName("j2ee.io.calendarclass.calendarDemo");
		System.out.println(clazz);
	}

	private void getRefClass_2() {
		// TODO Auto-generated method stub
//		Class clazz = new calendarDemo().getClass();
//		System.out.println(clazz+"sda");
	}

	public  void getRefClass_1(){
		Class clazz = calendarDemo.class;
		System.out.println(clazz.getName().toString()+"sda");
	}

}
