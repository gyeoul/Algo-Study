package baekjoon.bruteforce._1987;
/**
 * 작성자: 이지은
 * 문제: 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.
 *      말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 *      좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 * 해결: DFS 재귀사용. Set사용해서 중복 체크
 * 작성일: 2023-04-13
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class jieun_1987 {
    static int R, C; //세로 가로
    static char [][] board; //알파벳을 담을 2차원 배열
    static boolean [][] isVisited;
    static Set<Character> set = new HashSet<>(); //동일한 알파벳인지를 체크하기 위해 사용
    static int [] dx = {0, 0, -1, 1};
    static int [] dy = {-1, 1, 0, 0};
    static int cnt = 1; //시작지점은 하나 가지고 시작

    public static boolean check(int ny, int nx){
        if(nx<0 || nx>=C || ny<0 || ny>=R) return false;
        else return true;
    }
    public static void dfs(int y, int x){
        set.add(board[y][x]);
        for(int i=0; i<4; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(check(ny, nx)) { //이동 가능한 영역일 떄
                if (!set.contains(board[ny][nx]) && !isVisited[ny][nx]) { //set에 포함되어있지 않으며, 방문하지 않았을 떄
                    set.add(board[ny][nx]);
                    isVisited[ny][nx] = true;
                    dfs(ny, nx);
                    cnt = Math.max(cnt, set.size()); //최대값 찾기
                    isVisited[ny][nx] = false;
                    set.remove(board[ny][nx]); //제거
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); //세로
        C = Integer.parseInt(st.nextToken()); //가로

        board = new char[R][C];
        for(int i=0; i<R; i++){
            String str = br.readLine();
            board[i] = str.toCharArray();
        }
        isVisited = new boolean[R][C];
        isVisited[0][0] = true; // 시작 지점 true
        dfs(0,0);
        System.out.println(cnt);
    }
}