package day1

import readInput

fun main() {
    fun part1(input: List<Int>): Int {
        return input
            .zipWithNext { a, b -> a < b }
            .filter { it }
            .size
    }

    fun part2(input: List<Int>): Int {
        val windowed = input.windowed(3) { it.sum() }
        return part1(windowed)
    }

    val testInput: List<Int> = readInput("day1/Day01_test").map { it.toInt() }
    val testResultPart1 = part1(testInput)
    println("Test result part 1: $testResultPart1")
    check(testResultPart1 == 7)

    val input: List<Int> = readInput("day1/Day01").map { it.toInt() }
    val resultPart1 = part1(input)
    println("Result part 1: $resultPart1")
    check(resultPart1 == 1581)

    val testResultPart2 = part2(testInput)
    println("Test result part 2: $testResultPart2")
    check(testResultPart2 == 5)

    val resultPart2 = part2(input)
    println("Result part 2: $resultPart2")
    check(resultPart2 == 1618)
}
