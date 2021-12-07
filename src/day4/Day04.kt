package day4

import readInput

fun main() {
    fun readBoard(input: List<String>): Board {
        return input
            .flatMap { line -> line.split(" ") }
            .filter { it.isNotBlank() }
            .map { number -> number.toInt() }
            .let { Board(it) }
    }

    fun part1(input: List<String>): Int {
        val allSelectedNumbers = input.first().split(",").map { it.toInt() }

        val boards = input
            .drop(1)
            .filter { it.isNotBlank() }
            .windowed(size = BOARD_SIZE, step = BOARD_SIZE, transform = ::readBoard)

        val selectedNumbers = mutableListOf<Int>()
        allSelectedNumbers.forEach { selectedNumber ->
            selectedNumbers.add(selectedNumber)
            boards.forEach { board ->
                if (board.hasWon(selectedNumbers)) {
                    return board.calculateScore(selectedNumbers)
                }
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        val allSelectedNumbers = input.first().split(",").map { it.toInt() }

        val boards = input
            .drop(1)
            .filter { it.isNotBlank() }
            .windowed(size = BOARD_SIZE, step = BOARD_SIZE, transform = ::readBoard)

        val selectedNumbers = mutableListOf<Int>()

        val remainingBoards = boards.toMutableList()
        val wonBoards = mutableListOf<Board>()
        allSelectedNumbers.forEach { selectedNumber ->
            selectedNumbers.add(selectedNumber)

            wonBoards.clear()
            remainingBoards.forEach { board ->
                if (board.hasWon(selectedNumbers)) {
                    wonBoards.add(board)
                }
            }

            remainingBoards.removeAll(wonBoards)
            if (remainingBoards.isEmpty()) {
                return wonBoards.last().calculateScore(selectedNumbers)
            }
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day4/Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("day4/Day04")
    println("part 1 result: ${part1(input)}")
    println("part 2 result: ${part2(input)}")
}

const val BOARD_SIZE: Int = 5
const val TOTAL_BOARD_SIZE: Int = BOARD_SIZE * BOARD_SIZE
val BOARD_RANGE: IntRange = 0 until BOARD_SIZE

class Board(
    private val numbers: List<Int>,
) {
    init {
        require(numbers.size == TOTAL_BOARD_SIZE) { "Board size must be $TOTAL_BOARD_SIZE." }
    }

    fun hasWon(selectedNumbers: List<Int>): Boolean {
        fun checkRow(rowIndex: Int): Boolean {
            val rowStartIndex = rowIndex * BOARD_SIZE
            val row = numbers.subList(rowStartIndex, rowStartIndex + BOARD_SIZE)
            return row.all { selectedNumbers.contains(it) }
        }

        fun checkColumn(columnIndex: Int): Boolean {
            val column = BOARD_RANGE.map { columnIndex + BOARD_SIZE * it }.map { numbers.elementAt(it) }
            return column.all { selectedNumbers.contains(it) }
        }

        return BOARD_RANGE.any { checkRow(it) || checkColumn(it) }
    }

    fun calculateScore(selectedNumbers: List<Int>): Int {
        return numbers.filterNot { selectedNumbers.contains(it) }.sum() * selectedNumbers.last()
    }
}
