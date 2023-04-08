package baekjoon.bruteforce._15658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 작성자: 이지은
 * 문제: N개의 수와 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 * 해결: 재귀 사용_14888번과 같은 방식으로 풀이
 * 작성일: 2023-04-08
 */
public class jieun_15658 {
    static int N; //수의 개수
    static int [] numArr; //수를 담을 배열
    static int [] operArr; //연산자의 개수를 담을 배열
    static int min = Integer.MAX_VALUE; //최대 값
    static int max = Integer.MIN_VALUE; //최소 값

    public static void check(int sum, int idx){
        if(idx == N){
            max = Math.max(max, sum); //최대
            min = Math.min(min, sum);  //최소
            return;
        }

        for(int i=0; i<4; i++){
            if(operArr[i]>0){ //연산할 연산자가 남아있을 때
                operArr[i]--; //연산자 값 감소

                switch (i){
                    case 0: //더하기
                        check(sum+numArr[idx], idx+1);
                        break;
                    case 1: //빼기
                        check(sum-numArr[idx], idx+1);
                        break;
                    case 2: //곱하기
                        check(sum*numArr[idx], idx+1);
                        break;
                    case 3: //나누기
                        check(sum/numArr[idx], idx+1);
                        break;
                }
                operArr[i]++;  //연산자 업
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        numArr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        operArr = new int[4]; //연산자 개수만큼
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<4; i++){
            operArr[i] = Integer.parseInt(st.nextToken());
        }

        check(numArr[0], 1);  //numArr의 0번 인덱스부터 시작

        System.out.println(max);
        System.out.println(min);
    }
}
