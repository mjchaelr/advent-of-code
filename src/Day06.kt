fun main() {
    fun processFishList(fishList: List<String>): List<String> {
        var fishListCopy = fishList.toMutableList()
        for( i in fishListCopy.indices){
            if (fishListCopy[i].toInt() == 0){
                fishListCopy[i] = "6"
            }else {
                fishListCopy[i] = (fishListCopy[i].toInt() -1).toString()
            }
        }
        return fishListCopy
    }

    fun part1(input: List<String>): Int {
        val fishList = input[0].split(",")
        var temp = fishList.toMutableList()
        for (i in 1 .. 80){
            var count6 = temp.filter { ele -> ele == "0" }.size

            temp = processFishList(temp) as MutableList<String>
            for (j in 1 .. count6){
                temp.add("8")
            }
//            println(temp.toString())
        }
        return temp.size
    }


    fun part2_(input: List<String>): Long {
        val fishList = input[0].split(",")
        var count = 0L;
        var i = 0


        while(i< fishList.size){
            println(i)
            var temp1 = fishList.subList(i, i+5) as MutableList<String>
            if (i+5 < fishList.size){
                i+=5
            }else {
                i+=fishList.size - i
            }

            for (i in 1 .. 80){
                var count6 = temp1.filter { ele -> ele == "0" }.size

                temp1 = processFishList(temp1) as MutableList<String>
                for (j in 1 .. count6){
                    temp1.add("8")
                }
//            println(temp.toString())
            }
            count+=temp1.size
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val fishList = input[0].split(",")
        var temp = fishList.toMutableList()
        for (i in 1 .. 256){
            var count6 = temp.filter { ele -> ele == "0" }.size

            temp = processFishList(temp) as MutableList<String>
            for (j in 1 .. count6){
                temp.add("8")
            }
//            println(temp.toString())
        }
        return temp.size
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)

//    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    println(part1(input))
//    println(part2(input))
}
