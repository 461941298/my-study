package com.yjh.study.ch5并发容器.bitwise;

public class IntToBinary {

    public static void main(String[] args) {

        int a = 4, b = 6;
        System.out.println("4的二进制是 " + Integer.toBinaryString(a));//100
        System.out.println("6的二进制是 " + Integer.toBinaryString(b));//110

//        & 规则：1&1=1， 1&0=0， 0&0=0
        System.out.println("4&6 " + Integer.toBinaryString(4 & 6));//100

//        | 规则：1|1=1, 1|0=1, 0|0=0
        System.out.println("4|6 " + Integer.toBinaryString(4 | 6));//110

//        ~ 规则：~1=0， ~0=1
        System.out.println("~4 " + Integer.toBinaryString(~4));//11111111111111111111111111111011

//        ^ 规则：1^1=0, 0^0=0, 1^0=1
        System.out.println("4^6 " + Integer.toBinaryString(4 ^ 6)); //10

//        任何整数a与b（b=2的n次方）取余=a&(b-1)
        System.out.println("345%16 " + (345 % 16) + " or " + (345 & (16 - 1))); //9 or 9

    }
}
