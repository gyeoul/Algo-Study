package programmers.sorting._42748

class gyeoul_42748 {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = ArrayList<Int>()
        val arr = ArrayList<Int>()
        for(i in 0 until commands.size){
            arr.clear()
            for(j in commands[i][0] .. commands[i][1]){
                arr.add(array[j-1])
            }
            arr.sort()
            answer.add(arr[commands[i][2]-1])
        }
        return answer.toIntArray()
    }
}