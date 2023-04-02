package baekjoon.bruteforce._2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
/*
 * 9
 * > < < < > > > < <
 * */
public class ybwi0912_2529 {
    static int k;
    static char[] arr = new char[10]; // 입력받은 부등호 저장
    static boolean isSelected[] = new boolean[10];
    static ArrayList answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();

        for(int i=0; i<k; i++){
            arr[i] = sc.next().toCharArray()[0];
        }

        permutation(0, "");
        Collections.sort(answer);

        System.out.println(answer.get(answer.size()-1));
        System.out.println(answer.get(0));
    }

    static boolean check(char x, char y, char z){
        if(z == '<'){
            if(x>y) return false;
        }
        if(z=='>'){
            if(x<y) return false;
        }

        return true;
    }

    static void permutation(int index, String number){
        if(index == k+1){
            answer.add(number);
            return;
        }

        for(int i=0; i<10; i++){
            if(isSelected[i]) continue;

            if(index == 0 || check(number.charAt(index-1), (char)(i+'0'), arr[index-1])){
                isSelected[i] = true;
                permutation(index+1, number + Integer.toString(i));
                isSelected[i] = false;
            }
        }
    }
}
