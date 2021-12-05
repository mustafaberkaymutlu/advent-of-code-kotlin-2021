package day2

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var totalDept = 0
        var totalDistance = 0

        input.forEach {
            val (command, distance) = it.split(" ")

            when (command) {
                "forward" -> totalDistance += distance.toInt()
                "up" -> totalDept -= distance.toInt()
                "down" -> totalDept += distance.toInt()
            }
        }

        return totalDept * totalDistance
    }

    fun part2(input: List<String>): Int {
        var totalDept = 0
        var totalDistance = 0
        var aim = 0

        input.forEach {
            val (command, distance) = it.split(" ")

            when (command) {
                "forward" -> {
                    totalDistance += distance.toInt()
                    totalDept += aim * distance.toInt()
                }
                "up" -> {
                    aim -= distance.toInt()
                }
                "down" -> {
                    aim += distance.toInt()
                }
            }
        }

        return totalDept * totalDistance
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day2/Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("day2/Day02")
    println(part1(input))
    println(part2(input))
}
