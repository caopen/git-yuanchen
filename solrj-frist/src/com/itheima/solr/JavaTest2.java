package com.itheima.solr;

public class JavaTest2 {
	public static void main(String[] args) {
		class Fu {
			int num = 4;
			void show()	{
				System.out.println("Fu show num");
			}
		}
		class Zi extends Fu {
			int num = 5;
			void show()	{
				System.out.println("Zi show num");
			}
		}
		
		Fu f = new Zi();
		f.show();	
			
		}
	}

