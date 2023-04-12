package baekjoon.bruteforce._6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 작성자: 이지은
 * 문제: 독일 로또는 {1, 2, ..., 49}에서 수 6개를 고른다.
 *      로또 번호를 선택하는데 사용되는 가장 유명한 전략은 49가지 수 중 k(k>6)개의 수를 골라 집합 S를 만든 다음 그 수만 가지고 번호를 선택하는 것이다.
 * 해결: 조합 사용
 * 작성일: 2023-04-04
 * */
public class jieun_6603 {
    static int K;
    static int [] S;
    static int [] select;
    static boolean [] isSelected;
    static StringBuilder sb = new StringBuilder();

    //중복을 허용하지 않는 순열
    public static void check(int r, int start){
        if(r == 6){ //종료조건
            for(int i=0; i<6; i++){
                sb.append(S[select[i]]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<K; i++){
            if(isSelected[i]) continue;
            isSelected[i] = true;
            select[r] = i;
            check(r+1, i);
            isSelected[i] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) { //마지막 입력시 while 종료
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            if(K==0) break; //while문 종료

            S = new int[K];
            for(int i=0; i<K; i++){
                S[i] = Integer.parseInt(st.nextToken());
            }

            select = new int[6]; //6개를 뽑을 예정이라 크기 6
            isSelected = new boolean[K];

            check(0,0);

            sb.append("\n"); //간격을 주기 위함
        }
        System.out.println(sb); //출력
    }
}
