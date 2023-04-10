package baekjoon.bruteforce._16197

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(bf.readLine())
    val y = st.nextToken().toInt()
    val x = st.nextToken().toInt()
    val dy = intArrayOf(1, -1, 0, 0)
    val dx = intArrayOf(0, 0, 1, -1)

    // TODO 너무 복잡함.. data class 사용해서 바꿔보기
    val q: Queue<Pair<Pair<Pair<Int, Int>, Pair<Int, Int>>, Int>> = LinkedList()

    val coin = Array(2) { Pair(0, 0) }
    var point = 0

    val arr = Array(y) { BooleanArray(x) }
    for (i in 0 until y) {
        val v = bf.readLine().toCharArray()
        for (j in 0 until x) {
            when (v[j]) {
                'o' -> {
                    arr[i][j] = true
                    coin[point++] = Pair(i, j)
                }

                '.' -> arr[i][j] = true
                '#' -> arr[i][j] = false
            }
        }
    }

    q.add(Pair(Pair(coin[0].copy(), coin[1].copy()), 0))
    while (!q.isEmpty()) {
        val now = q.remove()
        val nowCount = now.second
        if (nowCount > 10) { // >= 아니고 > 10번까지 포함한다.
            bw.write("-1")
            break
        }
        val nowAy = now.first.first.first
        val nowAx = now.first.first.second
        val nowBy = now.first.second.first
        val nowBx = now.first.second.second
        var statusA = false
        var statusB = false

        if (nowAy in 0 until y && nowAx in 0 until x && arr[nowAy][nowAx]) statusA = true
        if (nowBy in 0 until y && nowBx in 0 until x && arr[nowBy][nowBx]) statusB = true
//        println("$nowAy $nowAx $statusA | $nowBy $nowBx $statusB")
        if (statusA.xor(statusB)) {
//            println(nowCount)
            bw.write("$nowCount")
            break
        }
        if (!statusA || !statusB) continue
        for (i in 0..3) {
            var newAy = nowAy + dy[i]
            var newAx = nowAx + dx[i]
            var newBy = nowBy + dy[i]
            var newBx = nowBx + dx[i]
            if (newAy in 0 until y && newAx in 0 until x) {
                if (!arr[newAy][newAx]) {
                    newAy = nowAy
                    newAx = nowAx
                }
            }
            if (newBy in 0 until y && newBx in 0 until x) {
                if (!arr[newBy][newBx]) {
                    newBy = nowBy
                    newBx = nowBx
                }
            }
            if (newAy == newBy && newAx == newBx) continue
            q.add(Pair(Pair(Pair(newAy, newAx), Pair(newBy, newBx)), nowCount + 1))
        }
    }
    bw.flush()
}