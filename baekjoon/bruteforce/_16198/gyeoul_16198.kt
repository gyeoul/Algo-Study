package baekjoon.bruteforce._16198

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

val numbers = ArrayList<Int>()
var ans = 0
var r = 0

lateinit var sel: IntArray
lateinit var isSel: BooleanArray

fun permutation(now: Int) {
    if (now == r) {
//        println(sel.contentToString())
//        val arr = ArrayList<Int>()
        val arr = BooleanArray(numbers.size);
        var sum = 0
//        arr.addAll(numbers)
        for (v in sel) {
            var left = v
            var right = v
            arr[v] = true
            while (left >= 0) {
                if (!arr[left]) break
                left--
            }
            while (right < numbers.size) {
                if (!arr[right]) break
                right++
            }
            sum += (numbers[left] * numbers[right])
//            println("$i[${arr[i]}] $arr $sum")
//            sum += (arr[i + 1] + arr[i - 1])
//            arr.removeAt(i)
        }
        if (ans < sum) ans = sum
        return
    }
    for (i in 0 until r) {
        if (isSel[i]) continue
        isSel[i] = true
        sel[now] = i + 1
        permutation(now + 1)
        isSel[i] = false
    }
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val num = bf.readLine().toInt()
    r = num - 2
    val st = StringTokenizer(bf.readLine())
    for (i in 0 until num) {
        numbers.add(st.nextToken().toInt())
    }

    sel = IntArray(r)
    isSel = BooleanArray(r)

    permutation(0)
//    println(ans.toString())
    bw.write("$ans")
    bw.flush()
}