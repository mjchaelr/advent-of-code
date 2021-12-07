fun main() {

    fun countFish(input: List<String>, days: Int): Long {
        val state = input.single().split(",").map { it.toInt() }

        val timers = LongArray(9)
        state.forEach { timers[it]++ }

        repeat(days) {
            val zeros = timers[0]

            for (i in 1.. 8){
                timers[i - 1] = timers[i]
            }
            timers[6] += zeros
            timers[8] = zeros
        }

        return timers.sum()
    }

    fun part1(input: List<String>) = countFish(input, 80)

    fun part2(input: List<String>) = countFish(input, 256)

    val testInput = readInput("Day06_test")

    val input = readInput("Day06")

    check(part1(testInput).toInt() == 5934)
    println(part1(input))

    check(part2(testInput) == 26984457539)
    println(part2(input))
}
