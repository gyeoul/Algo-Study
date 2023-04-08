package baekjoon.bruteforce._14888;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 전상희, 2023-04-08
 * Bruteforce, Backtracking
 * */
public class ybwi0912_14888 {
    static int N; // 숫자 개수
    static int MAX = Integer.MIN_VALUE;
    // 처음에 초기값을 0으로 뒀었는데, 최댓값이 음수가 나올 경우를 고려해 Integer.MIN_VALUE로 줘야 한다
    // https://www.acmicpc.net/board/view/110806
    static int MIN = Integer.MAX_VALUE;
    static int[] operator = new int[4]; // 연산자
    static List<Integer> arr; // 숫자

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = new ArrayList<>();
        for(int i=0; i<N; i++) arr.add(sc.nextInt());
        for(int i=0; i<4; i++) operator[i] = sc.nextInt();
        // input

        dfs(arr.get(0), 1);

        System.out.println(MAX);
        System.out.println(MIN);
        // output
    }

    public static void dfs(int num, int index){
        if(index == N){
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }

        for(int i=0; i<4; i++){
            if(operator[i] > 0){
                operator[i]--;

                switch(i){
                    case 0 :
                        dfs(num + arr.get(index), index+1);
                        break;
                    case 1:
                        dfs(num - arr.get(index), index+1);
                        break;
                    case 2:
                        dfs(num * arr.get(index), index+1);
                        break;
                    case 3:
                        dfs(num / arr.get(index), index+1);
                        break;
                }

                operator[i]++; // backtracking
            }
        }
    }
}
