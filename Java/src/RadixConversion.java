package com.Vauke;

import java.util.Scanner;

public class RadixConversion {
	public static void main(String[] args) {
		int num; // 数
		int radix; // 进制
		int and; // 参与 与 操作的数
		int move; // 移位数
		
		while (true) {
			System.out.println("pls input the number you want to convert:");
			Scanner sc = new Scanner(System.in);
			num = sc.nextInt();
			System.out.println("pls input the target radix:only support 2,8,16 for now");
			radix = sc.nextInt(); 
			
			if (radix == 2) {
				and = 0x01; // 和1与
				move = 1; // 2进制右移1位
			} else if (radix == 8) {
				and = 0x07; // 和7与
				move = 3; // 8进制右移3位
			} else if (radix == 16) {
				and = 0x0f; // 和15与
				move = 4; // 16进制右移4位
			} else {
				System.out.println("the radix you input is illegal, pls try again...");
				continue;
			}
			
			convert(num, and, move); //转换
//			System.out.println(num+"---"+radix);
			// api
//			System.out.println(Integer.toBinaryString(num)); // 2进制
//			System.out.println(Integer.toOctalString(num)); // 8进制
//			System.out.println(Integer.toHexString(num)); // 16进制
		}
	}
	
	private static void convert(int num, int and, int move) {
		StringBuffer sb = new StringBuffer();
		int rst;
		for (int i = num; i > 0; i >>>= move) {
			rst = i & and;
			rst = rst > 9 ? rst - 10 + 'A' : rst + '0';
			sb.append((char)rst);
		}
		System.out.println("the result is:" + sb.reverse());
	}
}
