fun main() {
    val move = listOf(
        listOf(0, 1),
        listOf(0, -1),
        listOf(1, 0),
        listOf(-1, 0),
        listOf(1, 1),
        listOf(-1, -1),
        listOf(-1, 1),
        listOf(1, -1),
    )

    fun part1(input: List<String>): Int {
        val isVisited: MutableList<MutableList<Boolean>> = MutableList(input.size) { MutableList(input[0].length) { false } }

        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                // find character
                if (input[i][j] != '.' && !input[i][j].isDigit()) {
                    for (m in move) {
                        // find number
                        val moveX = i + m[0]
                        val moveY = j + m[1]
                        if (moveX >= 0 && moveY >= 0 && moveX < input.size && moveY < input[i].length && input[moveX][moveY].isDigit() && !isVisited[moveX][moveY]) {
                            var n = ""
                            var l = moveY-1
                            var r = moveY+1
                            n += input[moveX][moveY]
                            isVisited[moveX][moveY] = true

                            while (l >= 0 && l < input[i].length && input[moveX][l].isDigit() && !isVisited[moveX][l]) {
                                n = input[moveX][l] + n
                                isVisited[moveX][l] = true
                                l--
                            }
                            while (r >= 0 && r < input[i].length && input[moveX][r].isDigit() && !isVisited[moveX][r]) {
                                n += input[moveX][r]
                                isVisited[moveX][r] = true
                                r++
                            }
                            sum += n.toInt()
                        }
                    }
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var isVisited: MutableList<MutableList<Boolean>> = MutableList(input.size) { MutableList(input[0].length) { false } }

        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                // find *
                if (input[i][j] == '*') {
                    val values = ArrayList<Int>()
                    for (m in move) {
                        // find numbers
                        val moveX = i + m[0]
                        val moveY = j + m[1]
                        if (moveX >= 0 && moveY >= 0 && moveX < input.size && moveY < input[i].length && input[moveX][moveY].isDigit() && !isVisited[moveX][moveY]) {
                            var n = ""
                            var l = moveY-1
                            var r = moveY+1
                            n += input[moveX][moveY]
                            isVisited[moveX][moveY] = true

                            while (l >= 0 && l < input[i].length && input[moveX][l].isDigit() && !isVisited[moveX][l]) {
                                n = input[moveX][l] + n
                                isVisited[moveX][l] = true
                                l--
                            }
                            while (r >= 0 && r < input[i].length && input[moveX][r].isDigit() && !isVisited[moveX][r]) {
                                n += input[moveX][r]
                                isVisited[moveX][r] = true
                                r++
                            }
                            values.add(n.toInt())
                        }
                    }
                    isVisited =  MutableList(input.size) { MutableList(input[0].length) { false } }
                    if (values.size == 2) {
                        var v = 1
                        values.forEach {
                            v *= it
                        }
                        sum += v
                    }
                }
            }
        }

        return sum
    }

    val input = readInput("Day03")
//    part1(input).println()
    part2(input).println()
}