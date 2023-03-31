package baekjoon.bruteforce._2529

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

lateinit var sel: IntArray
lateinit var isSel: BooleanArray
lateinit var eqArr: Array<String>

const val n = 10
var r: Int = 0

val arr = ArrayList<Int>((0..9).toList())
val answer = ArrayList<String>()

val bf = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun action(now: Int) {
    if (now == r) {
        val sb = StringBuilder()
        for (i in 0 until r) {
//            print(arr[sel[i]])
            sb.append(arr[sel[i]])
        }
        answer.add(sb.toString())
        return
    }
    for (i in 0 until n) {
        if (isSel[i]) continue
        if (now > 0) when (eqArr[now - 1]) {
            "<" -> {
                if (sel[now - 1] > i) continue
            }

            ">" -> {
                if (sel[now - 1] < i) continue
            }
        }
        isSel[i] = true
        sel[now] = i
        action(now + 1)
        isSel[i] = false
    }
}

fun main() {
    r = bf.readLine().toInt() + 1
    eqArr = bf.readLine().split(" ").toTypedArray()
    sel = IntArray(r)
    isSel = BooleanArray(n)
    action(0)

//    bw.write(eqArr.contentToString() + "\n")
    answer.sort()
    bw.write(answer.last() + "\n")
    bw.write(answer.first() + "\n")

    bw.flush()
    bw.close()
    bf.close()
}