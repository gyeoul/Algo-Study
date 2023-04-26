package programmers.stack_queue._42587;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class gyeoul_42587 {
    public int solution(int[] priorities, int location) {
        int size = priorities.length;
        Queue<int[]> q = new ArrayDeque<>();
        ArrayList<Integer> s = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int now = priorities[i];
            s.add(now);
            q.add(new int[]{now, i});
        }
        Collections.sort(s);
        while (size > 0) {
            int p = s.remove(--size);
            while (q.peek()[0] != p) {
                q.offer(q.poll());
            }
            if (q.poll()[1] == location) {
                return priorities.length - q.size();
            }
        }
        return -1;
    }
}
