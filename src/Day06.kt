fun main() {

    fun extractNumbers(text: String): List<Int> =
        "\\d+".toRegex().findAll(text)
            .map { it.value.toInt() }
            .toList()

    fun part1(input: List<String>): Int {
        var answer = 1

        val times = extractNumbers(input[0])
        val distances = extractNumbers(input[1])

        for (i in times.indices) {
            val time = times[i]
            val distance = distances[i]
            var win = 0

            for (pressTime in 1 .. time) {
                val remainTime = time - pressTime
                val moveDistance = remainTime * pressTime

                if (distance < moveDistance)
                    win++
            }

            answer *= win
        }

        return answer
    }

    fun joinToInt(text: String): Long =
        "\\d+".toRegex().findAll(text)
            .map { it.value }
            .joinToString("")
            .toLong()

    fun part2(input: List<String>): Int {
        val time = joinToInt(input[0])
        val distance = joinToInt(input[1])
        var win = 0

        for (pressTime in 1 .. time) {
            val remainTime = time - pressTime
            val moveDistance = remainTime * pressTime

            if (distance < moveDistance)
                win++
        }

        return win
    }

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}