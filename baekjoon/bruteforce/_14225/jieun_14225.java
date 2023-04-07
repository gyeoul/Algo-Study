package baekjoon.bruteforce._14225;

/**
 * 작성자: 이지은
 * 문제: 수열 S가 주어졌을 때, 수열 S의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를 구하는 프로그램을 작성하시오.
 * 해결: 부분수열, Set 사용
 * 작성일: 2023-04-07
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class jieun_14225 {
    static int N;
    static int [] numArr; //수를 담을 배열
    static boolean [] isSelect;
    static boolean isCheck; //사용했는지 체크하는 boolean 변수
    static Set<Integer> set = new HashSet<>(); //부분 수열의 합을 담을 Set

    //부분 수열로 풀이
    public static void subSet(int r){
        if(r== N){
            int sum = 0;
            isCheck = false;
            for(int i=0; i<N; i++) {
                if(isSelect[i]) { //선택값이 true이면
                    isCheck = true; //isCheck 변경 후
                    sum += numArr[i]; //sum 값 더함
                }
            }
            if(isCheck) //isCheck가 true일 때
                set.add(sum);
            return;
        }
        isSelect[r] = true;
        subSet(r+1);

        isSelect[r] = false;
        subSet(r+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); //개수

        numArr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i <N; i++){
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        isSelect = new boolean[N];
        subSet(0); //부분수열

        int min = 0;
        int num = 1;

        List <Integer> list =  new ArrayList(set); //set을 리스트에 담음 -> 정렬하기 위해
        Collections.sort(list); //set을 정렬

        for(int i: list){
            if(i>num){
                min = num;
                break;
            }
            num++;
        }

        if(min == 0){ //최소값이 바뀌지않았을 경우
            min = list.get(list.size()-1) +1; //부분수열의 최대값+1
        }
        System.out.println(min);
    }
}
