import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int = input.fold(listOf<Int>()) { acc, line ->
        mutableListOf<Int>().apply {
            line.forEachIndexed { index, char ->
                add(Character.getNumericValue(char) * 2 - 1 + (acc.getOrNull(index) ?: 0))
            }
        }
    }.map {
        if (it > 0) '1' else '0'
    }.joinToString("").let {
        val gamaRate = it.toInt(2)
        val epsilonRate = (2.0.pow(it.length).toInt() - 1) and gamaRate.inv()
        gamaRate * epsilonRate
    }

    fun getMostCommonListAt(input: List<String>, position: Int): List<String> {
        val onceCount = input.count { it[position] == '1' }
        return when {
            onceCount * 2 >= input.size -> input.filter { it[position] == '1' }
            else -> input.filter { it[position] != '1' }
        }
    }

    fun getLeastCommonListAt(input: List<String>, position: Int): List<String> {
        val onceCount = input.count { it[position] == '0' }
        return when {
            onceCount * 2 <= input.size -> input.filter { it[position] == '0' }
            else -> input.filter { it[position] != '0' }
        }
    }

    fun getOxygenGeneratorRating(input: List<String>, rounds: Int): Int {
        var list = input
        for (i in 0 until rounds) {
            list = getMostCommonListAt(list, i)
            if (list.size == 1)
                return list.first().toInt(2)
        }
        return list.first().toInt(2)
    }

    fun getCO2ScrubberRating(input: List<String>, rounds: Int): Int {
        var list = input
        for (i in 0 until rounds) {
            list = getLeastCommonListAt(list, i)
            if (list.size == 1)
                return list.first().toInt(2)
        }
        return list.first().toInt(2)
    }

    fun part2(input: List<String>): Int {
        val size = input[0].length
        return getOxygenGeneratorRating(input, size) * getCO2ScrubberRating(input, size)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input)) // 3687446
    println(part2(input)) // 4406844
}
