package baekjoon.bruteforce._1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 작성자: 이지은
 * 문제: 단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 이때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다.
 *      같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.
 *      N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.
 *
 *      모든 단어에 포함되어 있는 알파벳은 최대 10개이고, 수의 최대 길이는 8이다. 서로 다른 문자는 서로 다른 숫자를 나타낸다.
 * 해결: 그리디 알고리즘 사용
 * 작성일: 2023-04-01
2
GCF
ACDEB
 * */
public class jieun_1339 {
    static int N;
    static int [] select;
    static boolean [] isSelected;
    static ArrayList<String> strList = new ArrayList<>();  //문자열을 담을 배열
    static ArrayList<Character> list = new ArrayList<>();
    static long max = Long.MIN_VALUE;

    public static void permutation(int r){
        if(r==list.size()){ //종료 조건
            long total = 0;
            for(String str : strList) {
                int num = 0;
                for(int i=0; i<str.length(); i++){
                    num *= 10;
                    num += select[list.indexOf(str.charAt(i))];
                }
                total += num;
            }
            max = Math.max(max, total);
            return;
        }

        for(int i =0; i<10; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            select[r] = i;
            permutation(r+1);
            isSelected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            String str = br.readLine();
            strList.add(str);
            for(int j=0; j<str.length(); j++) {
                if (!list.contains(str.charAt(j))) { //중복되지 않았을 때
                    list.add(str.charAt(j));  //알파벳 담기
                }
            }
        }

        select = new int[list.size()];
        isSelected = new boolean[10];

        permutation(0);
        System.out.println(max);
    }
}

