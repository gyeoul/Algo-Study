package baekjoon.bfs._14502

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
    val (n, m) = bf.readLine().split(" ").map { it.toInt() }
    val raw = Array(n) { IntArray(m) }
    val arr = ArrayList<Loc>()
    val checkList = ArrayList<Loc>()
    val q: Queue<Loc> = ArrayDeque()
    val vector = arrayOf(intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))
    var max = 0
    repeat(n) {
        raw[it] = bf.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

//    val arr = raw.flatten()
//    repeat(n) { y ->
//        repeat(m) { x ->
//            when (raw[y][x]) {
//                0 -> arr.add(Loc(y, x))
//                2 -> checkList.add(Loc(y, x))
//            }
//            if (raw[y][x] == 0) arr.add(Loc(y, x))
//        }
//    }
//    println(arr)
    println(checkList)

    val sel = IntArray(3)
    val isSel = BooleanArray(arr.size) { false }

    fun check(input: Array<IntArray>): Int {
//        var count = 0
//        var x = 0
//        q.clear()
//        for (v in checkList) q.add(v)
//        while (q.isNotEmpty()) {
//            val now = q.poll()
//            for (v in 0..3) {
//                val next = Loc(now.y + vector[v][0], now.x + vector[v][1])
//                if (next.y !in 0 until n || next.x !in 0 until m) continue
////                print(input[next.y][next.x])
//                x++
//                when (input[next.y][next.x]) {
//                    1, 2 -> continue
//                    0 -> {
//                        input[next.y][next.x] = 2
//                        q.add(next)
//                    }
//                }
//            }
//        }
//        for (i in input) {
//            for (j in i) {
//                if (j == 0) count++
//            }
//        }
////        println("\n")
//        if ()
//        return count
        for (v in input) {
            for (d in v) {
                print(d)
            }
            println()
        }
        println()
        return 0
    }

    fun permutation(now: Int) {
        if (now == 3) {
            val clone = raw.copyOf()
            println("$clone $raw")
            for (i in 0..2) clone[arr[sel[i]].y][arr[sel[i]].x] = 1
            max = Math.max(check(clone), max)
            return
        }
        for (i in arr.indices) {
            if (isSel[i]) continue
            isSel[i] = true
            sel[now] = i
            permutation(now + 1)
            isSel[i] = false
        }
    }
    permutation(0)
    bw.write("$max")
    bw.flush()
}