fun main() {
    fun List<Int>.getIncreasesSum() = zipWithNext().count { (a, b) -> a < b }

    fun part1(input: List<Int>): Int = input.getIncreasesSum()

    fun part2(input: List<Int>): Int = input.windowed(3).map { it.sum() }.getIncreasesSum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map { it.toInt() }
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01").map { it.toInt() }
    println(part1(input)) // 1292
    println(part2(input)) // 1262
}
