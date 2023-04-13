package baekjoon.bruteforce._15658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전상희, 2023-04-13
 * Bruteforce, Backtracking
 * 14888번과 같은 문제
 * */
public class ybwi0912_15658 {
    static int N;
    static int[] arr;
    static int[] operator = new int[4];
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer token = new StringTokenizer(bf.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++)  arr[i] = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(bf.readLine());
        for(int i=0; i<4; i++) operator[i] = Integer.parseInt(token.nextToken());
        // input

        operate(arr[0], 1);
        // operation

        System.out.println(max);
        System.out.println(min);
        // output
    }

    static void operate(int num, int idx){
        if(idx==N){
            min = Math.min(min, num);
            max = Math.max(max, num);
            return;
        }

        for(int i=0; i<4; i++){
            if(operator[i]>0) {
                operator[i]--;

                switch (i) {
                    case 0:
                        operate(num + arr[idx], idx + 1);
                        break;
                    case 1:
                        operate(num - arr[idx], idx + 1);
                        break;
                    case 2:
                        operate(num * arr[idx], idx + 1);
                        break;
                    case 3:
                        operate(num / arr[idx], idx + 1);
                        break;

                }
                operator[i]++;
            }
        }
    }
}
