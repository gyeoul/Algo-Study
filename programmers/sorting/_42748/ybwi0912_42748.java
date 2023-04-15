package programmers.sorting._42748;

import java.util.Arrays;

/**
 * 전상희, 2023-04-15
 * Sorting
 * */
public class ybwi0912_42748 {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        // input

        int[] ans = Solution.solution(array, commands);
        // operation

        System.out.println(Arrays.toString(ans));
        // output
    }

    static class Solution {
        public static int[] solution(int[] array, int[][] commands) {
            int count = 0;
            for(int[] a : commands){
                count++;
            }

            int[] answer = new int[count];
            count = 0;

            for(int[] comArr : commands){ // 각 연산
                int len = comArr[1] - comArr[0] + 1;
                int[] newArr = new int[len]; // 각 연산마다 새로 생기는 배열
                for(int i=0; i<len; i++){
                    newArr[i] = array[comArr[0]-1+i];
                }
                Arrays.sort(newArr);
                int i = newArr[comArr[2]-1];
                answer[count] = i;
                count++;
            }
            return answer;
        }
    }
}
