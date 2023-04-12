package baekjoon.bruteforce._1182;

/**
 * 작성자: 이지은
 * 문제: N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 * 해결: 1. 부분수열 사용
 *      2. 조합 사용
 * 작성일: 2023-04-04
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jieun_1182 {
    static int N, S;
    static int [] numArr; //수를 담을 배열
    static boolean [] isSelect;
    static int [] select; //조합에서만 사용하는 배열
    static boolean isCheck; //부분수열에서만 사용하는 배열
    static int cnt; //합이 S가 되는 부분수열의 개수

    //1. 부분 수열로 풀이
    public static void subSet(int r){
        if(r== N){
            int sum = 0;
            isCheck = false;
            for(int i=0; i<N; i++) {
                if(isSelect[i]) { //선택값이 true이면
                    isCheck = true; //isCheck 변경 후
                    sum += numArr[i]; //sum 값 업
                }
            }
            //isCheck가 true이면서 sum이 S의 값과 같을 때
            if(isCheck && sum == S) cnt++;
            return;
        }
        isSelect[r] = true;
        subSet(r+1);

        isSelect[r] = false;
        subSet(r+1);
    }

    /**
     * 2. 조합으로 풀이
     */
    public static void combination(int r, int start, int R){
        if(r == R){ //종료조건
            int sum = 0;
            for(int i=0; i<R; i++){
                sum += numArr[select[i]];
            }
            if(sum == S) cnt++; //조합의 합이 S와 동일 할 때 카운트 업
            return;
        }

        for(int i=start; i<N; i++){
            if(isSelect[i]) continue;
            isSelect[i] = true;
            select[r] = i;
            combination(r+1, i, R);
            isSelect[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //개수
        S = Integer.parseInt(st.nextToken()); //합

        numArr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i <N; i++){
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        isSelect = new boolean[N];
//        subSet(0);
        //조합 사용
        for(int i=1; i<N+1; i++){
            select = new int[i];
            combination(0, 0, i);
        }

        System.out.println(cnt);
    }
}
