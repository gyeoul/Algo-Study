package baekjoon.bruteforce._14225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 전상희, 2023-04-12
 * Bruteforce, Subset
 * */
public class ybwi0912_14225 {
    static int N;
    static int[] arr;
    static boolean[] isSelected;
    static List<Integer> sums = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer token = new StringTokenizer(bf.readLine());
        arr = new int[N];
        isSelected = new boolean[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(token.nextToken());
        }
        // input

        subset(0);
        // operate

        Collections.sort(sums);
        Set<Integer> set = new TreeSet<>(sums); // 중복 제거 위해 list -> set

        int min = 0;
        int size = set.size();

        for(int i=0; i<size; i++){
            if(!set.contains(i)){
                min = i;
                break;
            }
        }
        if(min==0) min = size;
        // check

        System.out.println(min);
        // output
    }

    static void subset(int idx){
        if(idx==N){
            int sum = 0;
            for(int i=0; i<N; i++){
                if(isSelected[i]) sum += arr[i];
            }
            sums.add(sum);
            return;
        }

        isSelected[idx] = true;
        subset(idx+1);

        isSelected[idx] = false;
        subset(idx+1);
    }
}
