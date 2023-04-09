package baekjoon.bruteforce._14500

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


lateinit var numbers2: Array<IntArray>
lateinit var isVisited: Array<BooleanArray>
val ans2 = TreeSet<Int>()
val delta2 = ArrayList<ArrayList<Pair<Int, Int>>>()
fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(bf.readLine())

    val rawi = st.nextToken().toInt()
    val rawj = st.nextToken().toInt()

    numbers2 = Array(rawi) { IntArray(rawj) }
    isVisited = Array(rawi) { BooleanArray(rawj) }

    for (i in 0 until rawi) {
        st = StringTokenizer(bf.readLine())
        for (j in 0 until rawj) {
            numbers2[i][j] = st.nextToken().toInt()
        }
    }

    tmino2()
    for (i in 0 until rawi) {
        for (j in 0 until rawj) {
            calculate2(i, j)
            for (k in isVisited){
                k.fill(false)
            }
            isVisited[i][j] = true
            dfs(i, j, 1, numbers2[i][j])
        }
    }

    bw.write("${ans2.last()}\n")
    bw.flush()
}

fun dfs(i: Int, j: Int, count: Int, sum: Int) {
    if (count == 4) {
        ans2.add(sum)
        return
    }

    val dx = arrayOf(0, 0, 1, -1)
    val dy = arrayOf(1, -1, 0, 0)

    for (n in 0..3) {
        val newi = dy[n] + i
        val newj = dx[n] + j
        if (newi < 0 || newi >= numbers2.size) continue
        if (newj < 0 || newj >= numbers2[newi].size) continue
        if (isVisited[newi][newj]) continue
        isVisited[newi][newj] = true
        dfs(newi, newj, count + 1, sum + numbers2[newi][newj])
        isVisited[newi][newj] = false
    }
}

fun tmino2() {
    delta2.add(ArrayList())
    delta2.last().add(Pair(0, 0))
    delta2.last().add(Pair(0, 1))
    delta2.last().add(Pair(-1, 0))
    delta2.last().add(Pair(1, 0))
    delta2.add(ArrayList())
    delta2.last().add(Pair(0, 0))
    delta2.last().add(Pair(0, -1))
    delta2.last().add(Pair(-1, 0))
    delta2.last().add(Pair(1, 0))
    delta2.add(ArrayList())
    delta2.last().add(Pair(0, 0))
    delta2.last().add(Pair(0, -1))
    delta2.last().add(Pair(-1, 0))
    delta2.last().add(Pair(0, 1))
    delta2.add(ArrayList())
    delta2.last().add(Pair(0, 0))
    delta2.last().add(Pair(0, -1))
    delta2.last().add(Pair(1, 0))
    delta2.last().add(Pair(0, 1))
}

fun calculate2(i: Int, j: Int) {
    for (arrs in delta2) {
        var sum = 0
        for (d in arrs) {
            val newi = d.first + i
            val newj = d.second + j
            if (newi < 0 || newi >= numbers2.size) break
            if (newj < 0 || newj >= numbers2[newi].size) break
            sum += numbers2[newi][newj]
        }
        ans2.add(sum)
    }
}