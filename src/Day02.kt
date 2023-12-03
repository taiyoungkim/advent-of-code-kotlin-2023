import kotlin.math.max

fun main() {
//    12 red cubes, 13 green cubes, and 14 blue cubes
    val redCube = 12
    val greenCube = 13
    val blueCube = 14

    // Game 1: 7 red, 8 blue; 6 blue, 6 red, 2 green; 2 red, 6 green, 8 blue; 9 green, 2 red, 4 blue; 6 blue, 4 green
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { data ->
            // set Game
            val gameSplit = data.split(":")
            val gameData = gameSplit[0].split(" ")
            val gameIndex = gameData[1].toInt()
            var isPass = false

            val sets = gameSplit[1].split(";")

            for (set in sets) {
                val setMap = mutableMapOf<String, Int>()
                // set cube
                set.split(",").forEach { cubeData ->
                    val parts = cubeData.trim().split(" ")
                    val count = parts[0].toInt()
                    val color = parts[1]

                    setMap[color] = setMap.getOrDefault(color, 0) + count
                }

                val r = setMap.getOrDefault("red", 0)
                val g = setMap.getOrDefault("green", 0)
                val b = setMap.getOrDefault("blue", 0)

                if (redCube < r || greenCube < g || blueCube < b) {
                    isPass = false
                    break
                } else
                    isPass = true
            }
            if (isPass)
                sum += gameIndex
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var answer = 0
        input.forEach { data ->
            // set Game
            val gameSplit = data.split(":")
            val gameData = gameSplit[0].split(" ")
            val gameIndex = gameData[1].toInt()

            var r = 0
            var g = 0
            var b = 0

            val colors = gameSplit[1].split(";").flatMap { it.split(",") }
            for (color in colors) {
                val parts = color.trim().split(" ")
                val count = parts[0].toInt()
                val s = parts[1]

                if (s.equals("red"))
                    r = max(r, count)
                else if (s.equals("green"))
                    g = max(g, count)
                else if (s.equals("blue"))
                    b = max(b, count)
            }
            answer += r * g * b
        }
        return answer
    }

    val input = readInput("Day02")
//    part1(input).println()
    part2(input).println()
}