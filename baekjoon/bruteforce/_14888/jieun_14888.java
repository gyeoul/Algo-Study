package baekjoon.bruteforce._14888;

/*
 * 작성자: 이지은
 * 문제: N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
 * 해결: 재귀 사용
 * 작성일: 2023-04-03
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jieun_14888 {
    static int N;
    static int [] numArr; //숫자를 담을 배열
    static int [] operArr; //연산자를 담을 배열
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

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

        N = Integer.parseInt(br.readLine());
        numArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++){
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        operArr = new int[4];
        // 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<4; i++){
            operArr[i] = Integer.parseInt(st.nextToken());
        }

        check(numArr[0], 1);  //numArr의 0번 인덱스부터 시작
        System.out.println(max);
        System.out.println(min);
    }

}
