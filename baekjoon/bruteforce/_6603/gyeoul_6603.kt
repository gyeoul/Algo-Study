package baekjoon.bruteforce._6603

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var isSel: BooleanArray
lateinit var sel: IntArray
lateinit var curr: IntArray
val answer = StringBuilder()
fun combination(now: Int, start: Int) {
    if (now == 6) {
        val sb = StringBuilder()
        for (i in 0 until 6) {
            sb.append(curr[sel[i]]).append(" ")
        }
        answer.append(sb.toString() + "\n")
        return
    }
    for (i in start until curr.size) {
        if (isSel[i]) continue
        isSel[i] = true
        sel[now] = i
        combination(now + 1, i)
        isSel[i] = false
    }
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val arrs = ArrayList<IntArray>()
    sel = IntArray(6)

    while (true) {
        val st = StringTokenizer(bf.readLine())
        val nowLine = st.nextToken().toInt()
        if (nowLine == 0) break
        arrs.add(IntArray(nowLine))
        for (i in 0 until nowLine) {
            arrs.last()[i] = st.nextToken().toInt()
        }
    }

    for (v in arrs) {
        curr = v
        isSel = BooleanArray(v.size)
        combination(0, 0)
        answer.append("\n")
    }

    bw.write(answer.toString())
    bw.flush()
}