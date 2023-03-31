package baekjoon.bruteforce._1339

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val bf = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val rawNum = bf.readLine().toInt()
    val charMap = HashMap<Char, Int>()
    for (c in 'A'..'Z') {
        charMap[c] = 0
    }
    for (i in 0 until rawNum) {
        val it = bf.readLine().split("")
        val temp = it.subList(1, it.size - 1)
        for (j in temp.indices) {
            var value = 1
            for (k in 0 until temp.size - j - 1) value *= 10
            charMap[temp[j].toCharArray()[0]] = charMap[temp[j].toCharArray()[0]]!! + value
        }
    }
    val charList = charMap.toList().sortedByDescending { it.second }

//    bw.write("$charMap\n")
//    bw.write(charList.toString()+"\n")
    var sum = 0
    for (i in 0..9) {
        sum += (charList[i].second * (9 - i))
    }
    bw.write(sum.toString() + "\n")

//    bw.write(charArr.contentToString() +"\n")
//    bw.write(charMap.toString()+"\n")

    bw.flush()
    bw.close()
    bf.close()
}
