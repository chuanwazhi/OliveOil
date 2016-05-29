package j2ee.fanxing;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class fanxing
{

	public static  void   main(String[] args)  {
		
		String puts = "asAdzaSDsSDdSAwDwdDSsDSSADasdsdsad";
		String s = getCharCount(puts);
		System.out.println(s);

	}
	
	
	public  static String getCharCount(String puts) {
		// TODO Auto-generated method stub
		char[] chs = puts.toLowerCase().toCharArray();
		TreeMap<Character,Integer> tm = new TreeMap<Character,Integer>();
		for (int i = 0; i < chs.length; i++) {
			Integer value = tm.get(chs[i]);
			if (value==null) tm.put(chs[i], 1);
			else {
				tm.put(chs[i], value+1);
			}
		}
/*		Iterator<Map.Entry<Character,Integer>> it = tm.entrySet().iterator();
		String s = "";
		while (it.hasNext()) {
			Map.Entry<Character,Integer> mm = it.next();
			s += mm.getKey() + "(" + mm.getValue() + "),";
			
		}*/
		System.out.println(tm.toString());
		Iterator<Character> it = tm.keySet().iterator();
		String s = "";
		while (it.hasNext()) {
			Character cha = it.next();
			s += cha + "(" + tm.get(cha) + "),";
		}
		return s;
	}


	private static void fxextends() {
		// TODO Auto-generated method stub
		ArrayList<Person> al = new ArrayList<Person>();
		al.add(new Person("asd",11));
		al.add(new Person("asd2",12));
		ArrayList<Student> al2 = new ArrayList<Student>();
		al2.add(new Student("stu",111));
		al2.add(new Student("stu",112));
		show(al);
		show(al2);
		
	}


	public static void show(ArrayList<? extends Person> al){
		Iterator<? extends Person> it = al.iterator();
		while(it.hasNext()){
			Person p = it.next();
			System.out.println(p.getName()+":"+p.getAge());
		}
	}
}

