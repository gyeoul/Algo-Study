package baekjoon.bfs._6087

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    data class Loc(val y: Int, val x: Int)

    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (w, h) = bf.readLine().split(" ").map { it.toInt() }
    val arr = ArrayList<ArrayList<Int>>()
    val q: Queue<Loc> = ArrayDeque()
    val target = Array(2) { IntArray(2) }
    repeat(h) { i ->
        arr.add(bf.readLine().toList().map { it.code - '.'.code } as ArrayList<Int>)
    }
    repeat(2) {
        var flag = false
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                if (arr[i][j] > 0) {
                    flag = true
                    target[it][0] = i
                    target[it][1] = j
                    arr[i][j] = 0
                    break
                }
            }
            if (flag) break
        }
    }
    q.add(Loc(target[0][0], target[0][1]))
    while (q.isNotEmpty()) {
        val now = q.poll()
        if (arr[now.y][now.x] < 0) continue
        val count = arr[now.y][now.x] + 1
        for (i in now.x downTo 0) {
            if (arr[now.y][i] < 0) break
            if (arr[now.y][i] == 0 || arr[now.y][i] > count) {
                arr[now.y][i] = count
                q.add(Loc(now.y, i))
            }
        }
        for (i in now.x + 1 until w) {
            if (arr[now.y][i] < 0) break
            if (arr[now.y][i] == 0 || arr[now.y][i] > count) {
                arr[now.y][i] = count
                q.add(Loc(now.y, i))
            }
        }
        for (i in now.y downTo 0) {
            if (arr[i][now.x] < 0) break
            if (arr[i][now.x] == 0 || arr[i][now.x] > count) {
                arr[i][now.x] = count
                q.add(Loc(i, now.x))
            }
        }
        for (i in now.y + 1 until h) {
            if (arr[i][now.x] < 0) break
            if (arr[i][now.x] == 0 || arr[i][now.x] > count) {
                arr[i][now.x] = count
                q.add(Loc(i, now.x))
            }
        }
    }
    bw.write("${arr[target[1][0]][target[1][1]] - 1}\n")
    bw.flush()
}