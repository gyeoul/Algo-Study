package baekjoon.bruteforce._15658

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

var r = 0
val nums = ArrayList<Int>()
val operArr = ArrayList<Int>()
val ans = TreeSet<Int>()
lateinit var sel: IntArray
//lateinit var isSel: BooleanArray
fun permutation(now: Int) {
    if (now == r) {
//        println(sel.contentToString())
        var sum = nums[0]
        for (i in 1 ..  r) {
            when (sel[i-1]) {
                0 -> sum += nums[i]
                1 -> sum -= nums[i]
                2 -> sum *= nums[i]
                3 -> sum /= nums[i]
            }
        }
        ans.add(sum)
        return
    }
    for (i in 0 until operArr.size) {
//        if (isSel[i]) continue
//        isSel[i] = true
        if(operArr[i]<=0)continue
        operArr[i] = operArr[i]-1
        sel[now] = i
        permutation(now + 1)
        operArr[i] = operArr[i]+1
//        isSel[i] = false
    }
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    r = bf.readLine().toInt() - 1
    var st = StringTokenizer(bf.readLine())
    for (i in 0..r) {
        nums.add(st.nextToken().toInt())
    }

    st = StringTokenizer(bf.readLine())
    for (i in 0..3) {
        operArr.add(st.nextToken().toInt())
    }
//    println(nums.size)
//    println(operArr)

//    isSel = BooleanArray(operArr.size)
    sel = IntArray(r)
    permutation(0)

    bw.write("${ans.last()}\n${ans.first()}")
    bw.flush()
}