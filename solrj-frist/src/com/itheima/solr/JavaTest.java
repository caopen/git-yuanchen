package com.itheima.solr;

import java.util.Random;
import java.util.Scanner;

public class JavaTest {
	public static void main(String[] args) {
		
		int[] arr = new int[5];
		
		/*Scanner s = new Scanner(System.in);
		System.out.println("请输入5个数");
		for(int i =0;i<arr.length;i++){
			arr[i] = s.nextInt();
			System.out.print(arr[i] + "  ");
		}
		for (int i : arr) {
			System.out.println(i);
		}
		System.out.println("========================");
		for(int i = 0;i<arr.length;i++){
			for(int j =0;j<arr.length-1-i;j++){
				if(arr[j]<arr[j+1]){
					int temp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		for (int a : arr) {
			System.out.println(a);
		}*/
		
		Random r = new Random();
		/*for (int i : arr) {
			i =r.nextInt(10)+1;
		}*/
		for(int i =0;i<arr.length;i++){
			arr[i] = r.nextInt(10)+1;
		}
		for(int i =0;i<arr.length;i++){
			for(int j =i+1;j<arr.length;j++){
				if(arr[i]<arr[j]){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] =temp;
				}
			}
		}
		for (int a : arr) {
			System.out.println(a);
		}
		System.out.println("2".equals("3"));
	      try {  
	            throw new NullPointerException();  
	        } catch (NullPointerException e) {  
	            System.out.println("程序抛出了异常");  
	        } finally {  
	            System.out.println("执行了finally语句块");  
	        }  
	}
}
