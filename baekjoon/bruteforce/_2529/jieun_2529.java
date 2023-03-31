package baekjoon.bruteforce._2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 작성자: 이지은
 * 문제: 제시된 k개의 부등호 순서를 만족하는 (k+1)자리의 정수 중에서 최댓값과 최솟값을 찾는다.
 *      각 부등호의 앞뒤에 들어가는 숫자는 { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }중에서 선택해야 하며 선택된 숫자는 모두 달라야 한다.
 * 해결: 백트래킹 알고리즘 사용
 * 작성일: 2023-03-30
 * */
public class jieun_2529 {
    static int K;
    static StringBuilder sb;
    static String [] list; //부등호를 담을 배열
    static int [] select;
    static boolean [] isSelected;
    static ArrayList<String> numList = new ArrayList<>(); //순열의 값을 담을 배열

    static void permutation(int r) {
        if(r == K+1) { //종료조건
            sb = new StringBuilder();
            //연산자와 비교
            for(int i=0; i<K; i++) {
                if (list[i].equals("<")) {
                    if (select[i] < select[i+1]) {
                        sb.append(select[i]);
                    }else{
                        return;
                    }
                } else {
                    if (select[i] > select[i+1]) {
                        sb.append(select[i]);
                    } else{
                        return;
                    }
                }
            } //for end

            sb.append(select[K]);
            if(sb.toString().length() == K+1){
                numList.add(sb.toString());
            }
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
        K = Integer.parseInt(br.readLine());
        list = new String[K];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            list[i] = st.nextToken();  //부등호
        }

        select = new int [K+1];
        isSelected = new boolean[10];

        permutation(0);
        System.out.println(numList.get(numList.size()-1)); //최대값
        System.out.println(numList.get(0)); //최솟값
    }
}
