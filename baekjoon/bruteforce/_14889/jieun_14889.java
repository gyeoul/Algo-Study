package baekjoon.bruteforce._14889;
/**
 * 작성자: 이지은
 * 문제: 축구를 하기 위해 모인 사람은 총 N명이고 신기하게도 N은 짝수이다. 이제 N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람들을 나눠야 한다.
 *      BOJ를 운영하는 회사 답게 사람에게 번호를 1부터 N까지로 배정했고, 아래와 같은 능력치를 조사했다. 능력치 S_ij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치이다.
 *      팀의 능력치는 팀에 속한 모든 쌍의 능력치 S_ij의 합이다. S_ij는 S_ji와 다를 수도 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 S_ij와 S_ji이다.
 *      축구를 재미있게 하기 위해서 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하려고 한다.
 * 해결: 조합 사용.
 * 이슈: int 배열을 asList로 사용하게 되면 int[]으로 선언됨 -> Integer[]로 변경
 * 이슈 참고: https://stackoverflow.com/questions/1467913/arrays-aslist-not-working-as-it-should
 * 작성일: 2023-04-10
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class jieun_14889 {
    static int N, R;
    static int [][] arr; //수를 담을 배열
    static int []  select; //combination에서 사용하는 배열
    static boolean [] isSelected; //combination에서 사용하는 배열
    static Integer [] startArr;
    static Integer [] linkArr;
    static int [] select2; //calc에서 사용하는 배열
    static boolean [] isSelected2; //calc에서 사용하는 배열
    static int sum = 0; //능력치 누적합
    static int startSum, linkSum;
    static int min = Integer.MAX_VALUE; //최소값

    static void combination(int r, int start) {
        if(r == R) {
            //start 값
            for(int i=0; i<R; i++) {
                startArr[i] = select[i];
            }
            int cnt = 0;
            //link 값
            for(int i=0; i<N; i++){
                if(!Arrays.asList(startArr).contains(i)){ //포함되지 않은 것만
                    linkArr[cnt] = i;
                    cnt++;
                }
            }
            //배열 초기화
            select2 = new int[2];
            isSelected2 = new boolean[R];
            sum = 0;
            startSum = calc(startArr, 0,0); //start의 누적합

            sum = 0;
            linkSum = calc(linkArr, 0,0); //link의 누적합

            min = Math.min(min, Math.abs(startSum-linkSum)); //최소값 비교
            return;
        }

        for(int i=start; i<N; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            select[r] = i;
            combination(r+1,i);
            isSelected[i] = false;
        }
    }

    public static int calc(Integer [] array, int r, int start){
        // 종료 조건
        if (r == 2) {
            int a = array[select2[0]];
            int b = array[select2[1]];
            sum += (arr[a][b]+arr[b][a]);
            return sum;
        }

        // 재귀 확장 부분
        for (int i = start; i <R; i++) {
            if(isSelected2[i]) continue;
            isSelected2[i] = true;
            select2[r] = i;
            calc(array , r+1, i);
            isSelected2[i] = false;
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N]; //배열 초기화
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        R = N/2; //고르는 수
        select = new int[R];
        isSelected = new boolean[N];
        startArr = new Integer[R]; //start의 수를 담을 배열
        linkArr = new Integer[R]; //link를 수를 담을 배열
        combination(0,0);

        System.out.println(min);
    }
}
