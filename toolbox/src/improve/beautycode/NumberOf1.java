package improve.beautycode;

import java.util.Calendar;

import javax.xml.crypto.Data;

public class NumberOf1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number = 1000000;
		int currentNo = 1;
		int count = 0;
		int i = 0;
		System.out.println(Calendar.getInstance().getTime());
		while(currentNo <= number){
			count += getCount(currentNo,getI(currentNo)-1);
			currentNo++;
		}
		System.out.println(count);
		System.out.println(Calendar.getInstance().getTime());
	}
	
	private static int getI(int currentNo) {
		// TODO Auto-generated method stub
		int count = 0;
		while (currentNo > 0){
			currentNo /= 10; 
			count++;
		}
		return count;
	}

	public static int getCount(int number ,int i ){
		//int temp = number%(int)Math.pow(10, 1);
		if (number < 10){
			if (number == 1){
				return 1;
			}
		}else {
			if (number/(int)Math.pow(10, i) == 1){
				return 1+getCount(number%(int)Math.pow(10, i), i-1);
			}else return getCount(number%(int)Math.pow(10, i), i-1);
		}
		return 0;
	}

}
