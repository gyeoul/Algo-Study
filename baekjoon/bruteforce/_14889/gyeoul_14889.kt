package baekjoon.bruteforce._14889

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

lateinit var sel: IntArray
lateinit var isSel: BooleanArray
lateinit var synergy: Array<IntArray>

val memberList = ArrayList<Int>()
val answer = TreeSet<Int>()
var r: Int = 0

fun combination(now: Int, start: Int) {
    if (now == r) {
        val teamA = ArrayList<Int>()
        val teamB = ArrayList<Int>()
        var synergySumA = 0
        var synergySumB = 0
        for (user in memberList) if (user in sel) teamA.add(user) else teamB.add(user)
        for (to in teamA) for (from in teamA) synergySumA += (synergy[to][from] + synergy[from][to])
        for (to in teamB) for (from in teamB) synergySumB += (synergy[to][from] + synergy[from][to])
        answer.add(abs(synergySumA - synergySumB) / 2)
//        println("$teamA $teamB")
//        println("$synergySumA $synergySumB")
        return
    }
    for (i in start until r * 2) {
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

    val teamNum = bf.readLine().toInt()
    memberList.addAll(0 until teamNum)
    r = teamNum / 2
    sel = IntArray(r)
    isSel = BooleanArray(teamNum)
    synergy = Array(teamNum) { IntArray(teamNum) }

    for (i in 0 until teamNum) {
        val st = StringTokenizer(bf.readLine())
        for (j in 0 until teamNum) {
            synergy[i][j] = st.nextToken().toInt()
        }
    }

    combination(0, 0)

    bw.write(answer.first().toString())
    bw.flush()
    bw.close()
    bf.close()
}