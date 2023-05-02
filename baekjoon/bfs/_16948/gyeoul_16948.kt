package baekjoon.bfs._16948

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

// (r-2, c-1),
// (r-2, c+1),
// (r, c-2),
// (r, c+2),
// (r+2, c-1),
// (r+2, c+1)

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val dy = arrayOf(-2, -2, 0, 0, 2, 2)
    val dx = arrayOf(-1, 1, -2, 2, -1, 1)
    val q: Queue<Pair<Int, Int>> = ArrayDeque()
    fun solution(): Int {
        val n = bf.readLine().toInt()
        val arr = Array(n) { IntArray(n) }
        val st = StringTokenizer(bf.readLine())
        val from = Pair(st.nextToken().toInt(), st.nextToken().toInt())
        val to = Pair(st.nextToken().toInt(), st.nextToken().toInt())
        arr[to.first][to.second] = -1
        q.add(from)
        while (q.isNotEmpty()) {
            val now = q.poll()
            if (now == to) {
                q.clear()
                break
            }
            for (i in 0..5) {
                val next: Pair<Int, Int> = Pair(now.first + dy[i], now.second + dx[i])
                if (next.first !in 0 until n || next.second !in 0 until n) continue
                if (arr[next.first][next.second] > 0) continue
                arr[next.first][next.second] = arr[now.first][now.second] + 1
                q.add(next)
            }
        }
        return arr[to.first][to.second]
    }
    bw.write("${solution()}\n")
    bw.flush()
}
