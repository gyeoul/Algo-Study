package baekjoon.bruteforce._16197;
/**
 * 작성자: 이지은
 * 문제: N×M 크기의 보드와 4개의 버튼으로 이루어진 게임이 있다. 보드는 1×1크기의 정사각형 칸으로 나누어져 있고, 각각의 칸은 비어있거나, 벽이다.
 *      두 개의 빈 칸에는 동전이 하나씩 놓여져 있고, 두 동전의 위치는 다르다.
 *      버튼은 "왼쪽", "오른쪽", "위", "아래"와 같이 4가지가 있다. 버튼을 누르면 두 동전이 버튼에 쓰여 있는 방향으로 동시에 이동하게 된다.
 *
 *      동전이 이동하려는 칸이 벽이면, 동전은 이동하지 않는다.
 *      동전이 이동하려는 방향에 칸이 없으면 동전은 보드 바깥으로 떨어진다.
 *      그 외의 경우에는 이동하려는 방향으로 한 칸 이동한다.이동하려는 칸에 동전이 있는 경우에도 한 칸 이동한다.
 *      두 동전 중 하나만 보드에서 떨어뜨리기 위해 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.
 *      o: 동전   .: 빈 칸   #: 벽
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class jieun_16197 {
    static int N, M; //세로, 가로 크기
    static char[][] board;
    static ArrayList<Integer> coin[]; //동전의 위치
    static int[] dx = {0, 0, -1, 1}; //상 하 좌 우
    static int[] dy = {1, -1, 0, 0};
    static boolean[][][][] isVisited;

    public static boolean check(int y, int x) {
        if(x >= 0 && x < M  && y >= 0 && y < N && board[y][x] == '#')
            return false;
        return true;
    }

    public static int bfs(int y1, int x1, int y2, int x2) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{y1, x1, y2, x2, 0});  //coin1, coin2의 (y좌표, x좌표), 누적 최소값
        isVisited[y1][x1][y2][x2] = true;

        while (!que.isEmpty()) {
            int[] now = que.poll();

            if (now[4] >= 10) break; //10보다 크면 종료

            for (int i = 0; i < 4; i++) {  //상하좌우 이동
                int ny1 = now[0] + dy[i];
                int nx1 = now[1] + dx[i];
                int ny2 = now[2] + dy[i];
                int nx2 = now[3] + dx[i];

                if (!check(ny1, nx1)) { //범위안에서 coin1이 벽을 만났을 때
                    ny1 = now[0];
                    nx1 = now[1];
                }
                if (!check(ny2, nx2)) { //범위안에서 coin2이 벽을 만났을 때
                    ny2 = now[2];
                    nx2 = now[3];
                }

                int cnt = 0; //떨어지지 않은 동전 개수
                //동전이 모두 떨어졌는지, 떨어지지 않았는 지 확인
                if(nx1 >= 0 &&  nx1 < M && ny1 >= 0 && ny1 < N)
                    cnt++;
                if(nx2 >= 0  && nx2 < M && ny2 >= 0 && ny2 < N)
                    cnt++;

                if (cnt == 1) { //동전 한 개만 떨어졌을 때
                    return (now[4] + 1);
                } else if (cnt == 2 && !isVisited[ny1][nx1][ny2][nx2]) {
                    isVisited[ny1][nx1][ny2][nx2] = true;
                    que.offer(new int[]{ny1, nx1, ny2, nx2, (now[4] + 1)});
                }
            } //for end
        } //while end
        return -1; //두 동전을 떨어뜨릴 수 없거나, 버튼을 10번보다 많이 눌러야 할 때
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); //세로
        M = Integer.parseInt(st.nextToken()); //가로

        board = new char[N][M];
        coin = new ArrayList[2]; //coin 초기화
        for (int i = 0; i < 2; i++) {
            coin[i] = new ArrayList<>();
        }

        int idx = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            board[i] = str.toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'o') {
                    coin[idx].add(i);
                    coin[idx].add(j);
                    idx++;
                }
            }
        }

        isVisited = new boolean[N][M][N][M];
        //눌러야하는 버튼의 최소 횟수
        int result = bfs(coin[0].get(0), coin[0].get(1), coin[1].get(0), coin[1].get(1));
        System.out.println(result);
    }
}