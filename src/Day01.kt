fun main() {

    fun computeNewList(input: List<String>):  List<String> {
        val newList = ArrayList<String>()
        var index = 0
        input.map { ele -> ele.toLong() }
            .forEach { ele ->
                run {
                    val checkExit = index + 3 > input.size
                    if (checkExit) {
                        return@forEach
                    }
                    val sum = ele + input[index+1].toLong() + input[index+2].toLong()
                    newList.add(sum.toString())
                    index++;
                }
            }
        return newList
    }

    fun part1(input: List<String>): Int {
        var countIncreased = 0
        var countDecreased = 0
        var previousValue = 0
        var count = 0
        input.forEach { ele ->
            run {
                if (count != 0 ){
                    if (previousValue > ele.toInt()) {
                        countDecreased++
                    } else if (previousValue < ele.toInt())  {
                        countIncreased++
                    }
                }

                previousValue = ele.toInt()
                count++

            }
        }
        return countIncreased
    }


    fun part2(input: List<String>): Int {
        return part1(computeNewList(input));
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
