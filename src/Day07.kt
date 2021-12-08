import kotlin.math.abs

fun main() {
    fun getFuel(input: List<String>, constantRate: Boolean): Int {
        val positions = input.first().split(",").map { it.toInt() }

        val min = positions.minOrNull() ?: 0
        val max = positions.maxOrNull() ?: 0

        var minFuel = -1
        for (i in min .. max){
            val fuel = positions.sumOf { position ->
                val n = abs(i - position)
                if (constantRate) n else n * (n + 1) / 2
            }

            if (minFuel == -1 ){
                minFuel = fuel
            } else if (fuel < minFuel ){
                minFuel = fuel
            }
        }
        return minFuel
    }

    fun part1(input: List<String>): Int {
        return getFuel(input, constantRate = true)
    }


    fun part2(input: List<String>): Int {
        return  getFuel(input, constantRate = false)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
