import java.lang.Integer.max

fun main() {
    class Board(val data: IntArray) {
        fun check(number: Int) {
            data.forEachIndexed { index, value ->
                if (value == number) {
                    data[index] = -1
                    return
                }
            }
        }

        fun isWinner(): Boolean {
            for (i in 0 until 5) {
                if (getLine(i).all { it == -1 }) return true
            }
            for (i in 0 until 5) {
                if (getRow(i).all { it == -1 }) return true
            }
            return false
        }

        fun getSum(): Int = data.sumOf { max(it, 0) }

        private fun getLine(index: Int) = data.copyOfRange(index * 5, index * 5 + 5)

        private fun getRow(index: Int) = mutableListOf<Int>().apply {
            for (i in 0 until 5) {
                add(data[index + i * 5])
            }
        }
    }

    data class Bingo(val numbers: List<Int>, val boards: List<Board>)

    fun part1(bingo: Bingo): Int {
        bingo.numbers.forEach { number ->
            bingo.boards.forEach { board ->
                board.check(number)
                if (board.isWinner()) {
                    return board.getSum() * number
                }
            }
        }

        return 0
    }

    fun part2(bingo: Bingo): Int {
        var lastWinner = 0
        bingo.numbers.forEach { number ->
            bingo.boards.forEach { board ->
                if (!board.isWinner()) {
                    board.check(number)
                    if (board.isWinner()) {
                        lastWinner = board.getSum() * number
                    }
                }
            }
        }

        return lastWinner
    }

    fun List<String>.toBoard() = Board(
        mutableListOf<Int>().also { list ->
            take(5).forEach {
                list.addAll(it.trim().split("\\s+".toRegex()).map { number -> number.toInt() })
            }
        }.toIntArray()
    )

    fun List<String>.toBingo() = Bingo(
        numbers = get(0).split(',').map { it.toInt() },
        boards = mutableListOf<Board>().also { list ->
            for (i in 2 until size step 6) {
                list.add(subList(i, i + 5).toBoard())
            }
        }
    )

//     test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test").toBingo()
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04").toBingo()
    println(part1(input)) // 11536
    println(part2(input)) // 1284

}
