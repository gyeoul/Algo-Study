package baekjoon.bruteforce._1339;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class gyeoul_1339 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add(0);
        }

        int num = Integer.parseInt(bf.readLine());
        for (int i = 0; i < num; i++) {
            char[] chars = bf.readLine().toCharArray();
            for (int j = 1; j <= chars.length; j++) {
                int multiplier = 1;
                for (int k = 1; k < j; k++) {
                    multiplier *= 10;
                }
                int index = chars[chars.length - j] - 'A';
                list.set(index, list.get(index) + multiplier);
            }
        }

        Collections.sort(list);
        Collections.reverse(list);

        int answer = 0;
        for (int i = 1; i <= 10; i++) {
            answer += list.get(i - 1) * (10 - i);
        }
        bw.write(""+answer);
        bw.flush();
        bw.close();
        bf.close();
    }
}
