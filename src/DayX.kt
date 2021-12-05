fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("dayX/DayX_test")
    check(part1(testInput) == 1)

    val input = readInput("dayX/DayX")
    println("part 1 result: ${part1(input)}")
    println("part 2 result: ${part2(input)}")
}
