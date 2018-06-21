package com.ksw.test;

public class TestCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char ch  = 'A';
		int code = (int)ch;
		
		System.out.printf("%c = %d(%#X)%n%n",ch,code,code);
		
		char hch = '가';
		System.out.printf("%c = %d(%#X)\n", hch, (int)hch, (int)hch);
	}

}
