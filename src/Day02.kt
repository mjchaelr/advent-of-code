fun main() {
    fun part1(input: List<String>): Int {
        var horizontalPos =0
        var depth = 0
        input.forEach { ele ->
            run {
                val action = ele.split(" ")[0]
                val count = ele.split(" ")[1].toInt()

                if (action.equals("forward")){
                    horizontalPos+=count
                } else if (action.equals("down")){
                    depth+=count
                }else if (action.equals("up")){
                    depth-=count
                }
            }
        }
        return horizontalPos*depth
    }


    fun part2(input: List<String>): Int {
        var horizontalPos =0
        var depth = 0
        var aim = 0

        input.forEach { ele ->
            run {
                val action = ele.split(" ")[0]
                val count = ele.split(" ")[1].toInt()

                if (action.equals("forward")){
                    horizontalPos+=count
                    depth+=aim*count
                } else if (action.equals("down")){
                    aim+=count
                }else if (action.equals("up")){
                    aim-=count
                }
            }
        }
        return horizontalPos*depth
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
