package baekjoon.bruteforce._14500

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


lateinit var numbers: Array<IntArray>
val ans = TreeSet<Int>()
val delta = ArrayList<ArrayList<Pair<Int, Int>>>()
fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(bf.readLine())

    val rawi = st.nextToken().toInt()
    val rawj = st.nextToken().toInt()

    numbers = Array(rawi) { IntArray(rawj) }

    for (i in 0 until rawi) {
        st = StringTokenizer(bf.readLine())
        for (j in 0 until rawj) {
            numbers[i][j] = st.nextToken().toInt()
        }
    }

    imino()
    omino()
    tmino()
    lmino()
    jmino()
    smino()
    zmino()

    for (i in 0 until rawi) {
        for (j in 0 until rawj) {
            calculate(i, j)
        }
    }

    bw.write("${ans.last()}\n")
    bw.flush()
}

fun imino() {
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, 1))
    delta.last().add(Pair(0, 2))
    delta.last().add(Pair(0, 3))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(1, 0))
    delta.last().add(Pair(2, 0))
    delta.last().add(Pair(3, 0))
}

fun omino() {
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, 1))
    delta.last().add(Pair(1, 1))
    delta.last().add(Pair(1, 0))
}

fun tmino() {
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, 1))
    delta.last().add(Pair(-1, 0))
    delta.last().add(Pair(1, 0))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, -1))
    delta.last().add(Pair(-1, 0))
    delta.last().add(Pair(1, 0))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, -1))
    delta.last().add(Pair(-1, 0))
    delta.last().add(Pair(0, 1))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, -1))
    delta.last().add(Pair(1, 0))
    delta.last().add(Pair(0, 1))
}

fun zmino() {
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, 1))
    delta.last().add(Pair(1, 1))
    delta.last().add(Pair(1, 2))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, -1))
    delta.last().add(Pair(-1, -1))
    delta.last().add(Pair(-1, -2))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(1, 0))
    delta.last().add(Pair(1, -1))
    delta.last().add(Pair(2, -1))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(-1, 0))
    delta.last().add(Pair(-1, 1))
    delta.last().add(Pair(-2, 1))
}

fun smino() {
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, 1))
    delta.last().add(Pair(-1, 1))
    delta.last().add(Pair(-1, 2))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, -1))
    delta.last().add(Pair(1, -1))
    delta.last().add(Pair(1, -2))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(1, 0))
    delta.last().add(Pair(1, 1))
    delta.last().add(Pair(2, 1))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(-1, 0))
    delta.last().add(Pair(-1, -1))
    delta.last().add(Pair(-2, -1))
}


fun jmino() {
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, 1))
    delta.last().add(Pair(0, 2))
    delta.last().add(Pair(-1, 2))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(1, 0))
    delta.last().add(Pair(2, 0))
    delta.last().add(Pair(2, 1))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, -1))
    delta.last().add(Pair(0, -2))
    delta.last().add(Pair(1, -2))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(-1, 0))
    delta.last().add(Pair(-2, 0))
    delta.last().add(Pair(-2, -1))
}


fun lmino() {
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, 1))
    delta.last().add(Pair(0, 2))
    delta.last().add(Pair(1, 2))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(1, 0))
    delta.last().add(Pair(2, 0))
    delta.last().add(Pair(2, -1))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(0, -1))
    delta.last().add(Pair(0, -2))
    delta.last().add(Pair(-1, -2))
    delta.add(ArrayList())
    delta.last().add(Pair(0, 0))
    delta.last().add(Pair(-1, 0))
    delta.last().add(Pair(-2, 0))
    delta.last().add(Pair(-2, 1))
}

fun calculate(i: Int, j: Int) {
    for (arrs in delta) {
        var sum = 0
        for (d in arrs) {
            val newi = d.first + i
            val newj = d.second + j
            if (newi < 0 || newi >= numbers.size) break
            if (newj < 0 || newj >= numbers[newi].size) break
            sum += numbers[newi][newj]
        }
        ans.add(sum)
    }
}