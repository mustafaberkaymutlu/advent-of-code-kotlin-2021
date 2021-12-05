package day3

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val gammaRate = input.first().mapIndexed { index, _ ->
            val countOf0 = input.count { it[index] == '0' }
            val countOf1 = input.size - countOf0
            if (countOf0 > countOf1) '0' else '1'
        }.joinToString("")
        val epsilonRate = gammaRate.map { if (it == '0') '1' else '0' }.joinToString("")

        val gamma = gammaRate.toInt(2)
        val epsilon = epsilonRate.toInt(2)

        return gamma * epsilon
    }

    fun findOxygenGeneratorRating(input: List<String>): String {
        var list = input
        var oxygenGeneratorRating = ""
        filtering@ for (columnIndex in input.first().indices) {
            val countOf0 = list.count { it[columnIndex] == '0' }
            val countOf1 = list.size - countOf0
            val mostCommonValue = if (countOf0 > countOf1) '0' else '1'
            list = list.filter { it[columnIndex] == mostCommonValue }
            if (list.size == 1) {
                oxygenGeneratorRating = list.first()
                break@filtering
            }
        }
        return oxygenGeneratorRating
    }

    fun findCo2ScrubberRating(input: List<String>): String {
        var list = input
        var co2ScrubberRating = ""
        filtering@ for (columnIndex in input.first().indices) {
            val countOf0 = list.count { it[columnIndex] == '0' }
            val countOf1 = list.size - countOf0
            val leastCommonValue = if (countOf0 <= countOf1) '0' else '1'
            list = list.filter { it[columnIndex] == leastCommonValue }
            if (list.size == 1) {
                co2ScrubberRating = list.first()
                break@filtering
            }
        }
        return co2ScrubberRating
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = findOxygenGeneratorRating(input).toInt(2)
        val co2ScrubberRating = findCo2ScrubberRating(input).toInt(2)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day3/Day03_test")
    println("test result part 1: ${part1(testInput)}")
    println("test result part 2: ${part2(testInput)}")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("day3/Day03")
    println("part 1 result: ${part1(input)}")
    println("part 2 result: ${part2(input)}")
}
