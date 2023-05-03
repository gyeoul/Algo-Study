package baekjoon.bfs._14395

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    fun solution(): String {
        val bf = BufferedReader(InputStreamReader(System.`in`))
        val st = StringTokenizer(bf.readLine()," ")
        val n = st.nextToken().toLong()
        val m = st.nextToken().toLong()
        if (n == m) return "0"
        val visited = TreeSet<Long>()
        val q: Queue<Pair<Long, String>> = ArrayDeque()
        q.add(Pair(n, ""))
        while (q.isNotEmpty()) {
            val now = q.poll()
            if (now.first == m) return now.second
            if (now.first !in 0..1_000_000_000) continue
            for (v in arrayOf(
                Pair(now.first * now.first, now.second.plus("*")),
                Pair(now.first + now.first, now.second.plus("+")),
                Pair(now.first / now.first, now.second.plus("/")),
            )) {
                if (v.first == 0.toLong() || visited.contains(v.first)) continue
                visited.add(v.first)
                q.add(v)
            }
        }
        return "-1"
    }
    bw.write(solution())
    bw.flush()
}
