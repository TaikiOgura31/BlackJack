package test;

import java.util.Scanner;

public class test{
    public static void main(String[] args) {
        String[] target={"A","E","G","I","O","S","Z"};
        String[] numbers={"4","3","6","1","0","5","2"};
        
        Scanner sc = new Scanner(System.in);
        
        // 標準入力から値を取得してinput_lineに入れる
        String input_line = sc.nextLine();
        
        for(int i=0;i<target.length;i++) {
        	input_line=input_line.replace(target[i],numbers[i]);
        }
        
        System.out.println(input_line);
    }
}