package baekjoon.bruteforce._14888

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var sel: IntArray
lateinit var isSel: BooleanArray

val oper = ArrayList<Int>()
val numList = ArrayList<Int>()

//val answer = ArrayList<Int>() // + answer.sort // 146368KB 1596ms
val answer = TreeSet<Int>()                      //  78800KB 1232ms
var r: Int = 0

fun action(now: Int) {
    if (now == r) {
        var sum:Int = numList[0]
        for (i in 0 until r) {
            when (oper[sel[i]]) {
                0 -> sum += numList[i + 1]
                1 -> sum -= numList[i + 1]
                2 -> sum *= numList[i + 1]
                3 -> sum /= numList[i + 1]
            }
        }
        answer.add(sum)
        return
    }
    for (i in 0 until oper.size) {
        if (isSel[i]) continue
        isSel[i] = true
        sel[now] = i
        action(now + 1)
        isSel[i] = false
    }
}


fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    r = bf.readLine().toInt() - 1
    for (v in bf.readLine().split(" ")) {
        numList.add(v.toInt())
    }

    val st = StringTokenizer(bf.readLine())
    for (i in 0..3) { // + - * /
        for (j in 0 until st.nextToken().toInt()) {
            oper.add(i)
        }
    }

    bf.close()

    sel = IntArray(r)
    isSel = BooleanArray(oper.size)
    action(0)

    bw.write(answer.last().toString() + "\n")
    bw.write(answer.first().toString() + "\n")

    bw.flush()
    bw.close()
}