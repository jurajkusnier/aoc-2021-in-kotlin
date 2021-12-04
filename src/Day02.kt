fun main() {
    fun part1(input: List<Command>): Int = input.fold(State()) { state, command ->
        when (command.direction) {
            Command.Direction.FORWARD -> {
                state.copy(position = state.position + command.units)
            }
            Command.Direction.DOWN -> {
                state.copy(depth = state.depth + command.units)
            }
            Command.Direction.UP -> {
                state.copy(depth = state.depth - command.units)
            }
        }
    }.let {
        it.position * it.depth
    }

    fun part2(input: List<Command>): Int = input.fold(State()) { state, commad ->
        when (commad.direction) {
            Command.Direction.FORWARD -> {
                state.copy(position = state.position + commad.units, depth = state.depth + state.aim * commad.units)
            }
            Command.Direction.DOWN -> {
                state.copy(aim = state.aim + commad.units)
            }
            Command.Direction.UP -> {
                state.copy(aim = state.aim - commad.units)
            }
        }
    }.let {
        it.position * it.depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test").map { it.toCommand() }
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02").map { it.toCommand() }
    println(part1(input)) // 1990000
    println(part2(input)) // 1975421260
}
