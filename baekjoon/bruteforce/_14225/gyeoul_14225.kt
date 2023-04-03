package baekjoon.bruteforce._14225

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val arr = ArrayList<Int>()
val ans = TreeSet<Int>()
lateinit var isSel: BooleanArray
var num = 0
fun subset(now: Int) {
    if (now == num) {
        var sum = 0
        for (i in 0 until num) {
            if (isSel[i]) sum += arr[i]
        }
        ans.add(sum)
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

    num = bf.readLine().toInt()
    isSel = BooleanArray(num)

    val st = StringTokenizer(bf.readLine())

    for (i in 0 until num) {
        arr.add(st.nextToken().toInt())
    }

    subset(0)

    var flag = false
    for (i in 0 until ans.size) {
        if (!ans.contains(i + ans.first())) {
            bw.write("$i")
            flag=true
            break
        }
    }
//    bw.write("\n$ans")
    if (!flag)bw.write("${ans.size}")
    bw.flush()
}