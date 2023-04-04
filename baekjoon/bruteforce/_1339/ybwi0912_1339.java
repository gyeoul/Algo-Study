package baekjoon.bruteforce._1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ybwi0912_1339 {
    static int N;
    static ArrayList<String> arr = new ArrayList<>(); // N개의 단어
    static int[] alphabet = new int[26]; // A to Z

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine()); // 단어의 개수
        int index = 9; // 9~0 수
        int sum = 0;

        for(int i=0; i<N; i++) {
            arr.add(bf.readLine());
        }
        // input

        for(int i=0; i<N; i++){
            int temp = (int)Math.pow(10,arr.get(i).length()-1); // 제곱 - 10의 (str.length()-1)승
            for(int j=0; j<arr.get(i).length(); j++){
                alphabet[(int)arr.get(i).charAt(j)-65] += temp;
                temp /= 10; // 자릿수 반영
            }
        }

        Arrays.sort(alphabet); // 오름차순 정렬

        for(int i=alphabet.length-1; i>=0; i--){
            if(alphabet[i] == 0) break; // 모든 알파벳에 대한 계산이 끝남

            sum += alphabet[i] * index;
            // 가장 많이 더해지는 알파벳부터 차례대로 9~0 대입 후 계산
            index--;
        }
        System.out.println(sum);

    }
}
