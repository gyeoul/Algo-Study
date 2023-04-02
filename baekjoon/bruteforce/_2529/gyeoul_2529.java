package baekjoon.bruteforce._2529;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class gyeoul_2529 {
    static int r;
    static boolean[] isSel = new boolean[10];
    static int[] sel;
    static ArrayList<Integer> arr = new ArrayList<>();
    static ArrayList<Character> chars = new ArrayList<>();
    static ArrayList<String> answer = new ArrayList<>();

    static void permutation(int now) {
        if (now == r) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r; i++) {
                sb.append(arr.get(sel[i]));
            }
            answer.add(sb.toString());
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (isSel[i]) continue;
            if (now > 0) {
                switch (chars.get(now - 1)) {
                    case '>':
                        if (sel[now - 1] < i) continue;
                        break;
                    case '<':
                        if (sel[now - 1] > i) continue;
                        break;
                }
            }
            isSel[i] = true;
            sel[now] = i;
            permutation(now + 1);
            isSel[i] = false;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < num; i++) {
            chars.add(st.nextToken().charAt(0));
        }

        r = num + 1;
        sel = new int[r];
        for (int i = 0; i < 10; i++) arr.add(i);

        permutation(0);

        bw.write(answer.get(0) + "\n" + answer.get(answer.size() - 1));
        bw.flush();
        bw.close();
        bf.close();
    }
}
