package programmers.sorting._42748;
/**
 * 작성자: 이지은
 * 문제: 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
 *      배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때,
 *      commands의 모든 원소에 대해 앞서 설명한 연산을 적용했을 때 나온 결과를 배열에 담아 return 하도록 solution 함수를 작성.
 * 입출력 예: array : [1, 5, 2, 6, 3, 7, 4]
 *          commands : [[2, 5, 3], [4, 4, 1], [1, 7, 3]]
 *          return : [5, 6, 3]
 * 작성일: 2023-04-13
 * */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int [] answer = new int[commands.length];
        List<Integer> list;

        for(int i=0; i<commands.length; i++){
            list = new ArrayList<>(); //리스트 초기화
            int start = commands[i][0];
            int end = commands[i][1];
            int idx = commands[i][2];

            for(int j=start-1; j<end; j++){ //인덱스는 0부터 시작하기 때문에 -1를 해줌
                list.add(array[j]);
            }

            Collections.sort(list); //추출한 값을 담은 list를 정렬
            answer[i] = list.get(idx-1); //해당하는 인덱스의 값을 answer에 담음
        }
        return answer;
    }
}

public class jieun_42748 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int [] array = {1, 5, 2, 6, 3, 7, 4};
        int [][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int [] answer = solution.solution(array, commands);

        System.out.println(Arrays.toString(answer));
    }
}