package j2ee.test;

import java.util.ArrayList;
import java.util.List;

public class yinzi {

	public static void main(String[] agrs){
		/*int[] arr = {0,0,0,0} ; 
		result(arr,4);*/
		int[] arr ; 
		for(int n = 2;n<=9;n++){
			arr = aa(n);
			if (result(arr,n)){
				System.out.println(n+"断言"+true);
			}else{
				System.out.println(n+"断言"+false);
			}
		}
	}

	private static boolean result(int arr[],int n) {
		int[] arrTemp={0,0,0,0,0,0}; 
		int j = 0;
		for(int i = 2;i<=n;i++){
			if (n%i == 0 ){
				n=n/i;
				arrTemp[j] = i;
				//System.out.println("---"+arrTemp[j]);
				j++;
				i = 1;
			}
			if (n == 1)break;
		}
		boolean flag = true;
		for(int k = 0;k<j;k++){
			if (arr[k] != arrTemp[k]) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	private static int[] aa(int n) {
		int[] a={0,0,0,0,0,0,0,0,0,0};
		a[0] = n;
		int constValue = 2;
		int j = 0;
		while(n!=1){
			for(constValue = 2;constValue <= n;constValue++){
				if (n%constValue == 0){
					a[j] = constValue;
					n= n/constValue;
					break;
				}
			}
			j++;
		}
		return a;
	}

	
	
}
