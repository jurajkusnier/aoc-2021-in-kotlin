import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

data class Command(val direction: Direction, val units: Int) {
    enum class Direction {
        FORWARD, DOWN, UP
    }
}

fun String.toCommand(): Command = split(' ').let {
    Command(Command.Direction.valueOf(it[0].uppercase()), it[1].toInt())
}

data class State(val position: Int = 0, val depth: Int = 0, val aim: Int = 0)