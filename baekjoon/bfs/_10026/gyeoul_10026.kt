package baekjoon.bfs._10026

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val n = bf.readLine().toInt()
    val arr = Array(n) { CharArray(n) }
    val visited = Array(n) { BooleanArray(n) }

    fun dfs(y: Int, x: Int, c: Char) {
        if (y !in 0 until n || x !in 0 until n || visited[y][x]) return
        if (c == 'X') {
            if (arr[y][x] == 'B') return
        } else {
            if(arr[y][x] != c) return
        }
        visited[y][x] = true
        for (v in arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))) {
            dfs(y + v.first, x + v.second, c)
        }
    }

    fun solution(): String {
        for (i in 0 until n) {
            arr[i] = bf.readLine().toCharArray()
        }
//        println(arr.contentDeepToString())
        val map = mutableMapOf(Pair('R', 0), Pair('G', 0), Pair('B', 0), Pair('X', 0))

        for (i in 0 until n) {
            for (j in 0 until n) {
                if (!visited[i][j]) {
                    map[arr[i][j]] = map[arr[i][j]]!! + 1
                    dfs(i, j, arr[i][j])
                }
            }
        }
        for (i in 0 until n) {
            Arrays.fill(visited[i], false)
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (!visited[i][j] && arr[i][j] != 'B') {
                    map['X'] = map['X']!! + 1
                    dfs(i, j, 'X')
                }
            }
        }
        return "${map['R']!! + map['G']!! + map['B']!!} ${map['X']!! + map['B']!!}"
    }
    bw.write(solution())
    bw.flush()
}
