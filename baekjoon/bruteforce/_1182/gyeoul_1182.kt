package baekjoon.bruteforce._1182

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val isSel = BooleanArray(20)
val numArr = IntArray(20)
var answer = 0
var last = 0
var find = 0
fun subset(now: Int) {
    if (now == last) {
//        if (!isSel.toList().contains(true)) return
//        contains 사용 121368KB 456ms
//        flag 사용      12916KB 164ms
        var sum = 0
        var flag = false
        for (i in 0 until last) if (isSel[i]) {
            sum += numArr[i]
            flag = true
        }
        if (flag && find == sum) answer++
        return
    }
    isSel[now] = true
    subset(now + 1)
    isSel[now] = false
    subset(now + 1)
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(bf.readLine())

    last = st.nextToken().toInt()
    find = st.nextToken().toInt()

    st = StringTokenizer(bf.readLine())
    for (i in 0 until last) numArr[i] = st.nextToken().toInt()

    subset(0)

    bw.write("$answer")
    bw.flush()
}