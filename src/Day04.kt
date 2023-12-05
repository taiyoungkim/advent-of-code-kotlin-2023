fun main() {

    fun stringToInts(input: String): Set<Int> {
        return input.trim()
            .split(" ")
            .filter(String::isNotEmpty)
            .map ( String::toInt )
            .toSet()
    }

    fun getMatchesCount(card: Pair<Set<Int>, Set<Int>>): Int {
        val winningNumbers = card.first
        val guessedNumbers = card.second
        return winningNumbers.count(guessedNumbers::contains)
    }

    fun part1(input: List<String>): Int {
        var score = 0
        for (data in input) {
            val numbers = data.split(":")[1].split("|")
            val winningNumber = numbers[0].trim().split("\\s+".toRegex()).map { it.toInt() }.sorted().toSet()
            val myNumber = numbers[1].trim().split("\\s+".toRegex()).map { it.toInt() }.sorted().toSet()

            val duplicatedNumbers = winningNumber.intersect(myNumber).sorted()
            score += Math.pow(2.0, (duplicatedNumbers.size - 1).toDouble()).toInt()
        }
        return score
    }

    fun part2(input: List<String>): Int {
        val startingCards = input.map{
            it.substringAfter(": ")
        }
            .map { it.split("|") }
            .map { Pair(stringToInts(it[0]), stringToInts(it[1]))}
        val cardCounts = startingCards.map { 1 }.toMutableList()
        for (cardIndex in startingCards.indices) {
            val score = getMatchesCount(startingCards[cardIndex])
            for (i in cardIndex+ 1 ..< cardIndex+ 1 + score) {
                cardCounts[i] += cardCounts[cardIndex]
            }
        }
        return cardCounts.sum()
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}