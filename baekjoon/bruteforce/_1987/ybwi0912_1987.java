package baekjoon.bruteforce._1987;

import java.io.IOException;
import java.util.Scanner;

/**
 * 전상희, 2023-05-01
 * Bruteforce, Backtracking, BFS
 * */

public class ybwi0912_1987 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1}; // 델타 탐색
    static int R; // 세로 R칸
    static int C; // 가로 C칸
    static int[][] array;
    static boolean[] alphabet; // 만난 알파벳 체크
    static int max = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();

        array = new int[R][C];
        for (int i = 0; i < R; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < C; j++) {
                array[i][j] = s.charAt(j)-'A';
            }
        } // input
        alphabet = new boolean[26];
        move(0, 0, 1);

        System.out.println(max);
    }

    static void move(int x, int y, int moved) {
        alphabet[array[x][y]] = true;
        max = Math.max(max, moved);

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny >= 0 && ny < C && nx >= 0 && nx < R) {
                if (!alphabet[array[nx][ny]]) {
                    move(nx, ny, moved + 1);
                    alphabet[array[nx][ny]] = false;
                }
            }
        }
    }
}
