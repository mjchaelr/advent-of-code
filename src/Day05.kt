fun main() {
    var diagram = Array(10) {Array(10) { 0 } }

    fun resetDiagram(){
        diagram = Array(1000) {Array(1000) { 0 } }
    }
    fun plot(x1: Int, y1: Int, x2: Int, y2: Int){
        if (x1 == x2) {
            if (y1 > y2){
                for (i in y2 .. y1){
                    diagram[x1][i] += 1
                }
            }
            if (y2 > y1){
                for (i in y1 .. y2){
                    diagram[x1][i] += 1
                }
            }
            if (y1 == y2){
                diagram[x1][y1] += 1
            }
        }

        if (y1 == y2) {
            if (x1 > x2){
                for (i in x2 .. x1){
                    diagram[i][y1] += 1
                }
            }
            if (x2 > x1){
                for (i in x1 .. x2){
                    diagram[i][y1] += 1
                }
            }
            if (x1 == x2){
                diagram[x1][y1] += 1
            }
        }
    }

    fun plotDiagonal(x1: Int, y1: Int, x2: Int, y2: Int){

        if ((x1 != x2) && (y1 !=y2)) {

//            if (x1== 0 && y1 ==0){
//                println()
//            }
            if (x1 > x2){
                var increment = 0
                loop@for (i in x2 .. x1){
                    if (y1> y2){
                        diagram[i][y2+increment] += 1
//                        println(i.toString() + ", " + (y2+increment).toString())
                        increment++
                        if (y2+increment > y1){
                            break@loop
                        }
                    }
                    if (y2> y1){
                        diagram[i][y2-increment] += 1
//                        println(i.toString() + ", " + (y2-increment).toString())
                        increment++
                        if (y2-increment < y1){
                            break@loop
                        }
                    }

                }
            }
            if (x2 > x1){
                var increment = 0
                loop@for (i in x1 .. x2){
                    if (y1> y2){
                        diagram[i][y1-increment] += 1
//                        println(i.toString() + ", " + (y1-increment).toString())
                        increment++
                        if (y1-increment < y2){
                            break@loop
                        }
                    }
                    if (y2> y1){
                        diagram[i][y1+increment] += 1
//                        println(i.toString() + ", " + (y1+increment).toString())
                        increment++
                        if (y1+increment > y2){
                            break@loop
                        }
                    }

                }
            }
        }
    }

    fun countAtLeastTwoLinesOverlap(): Int{
        var count = 0

        for (i in diagram.indices){
            for (j in diagram[i].indices){
                if (diagram[i][j] >=2){
                    count++
                }
            }
        }

        return count;
    }

    fun printDiagram() {
        for (i in diagram.indices){
            for (j in diagram[i].indices){

                if (diagram[i][j] == 0){
                    print(".")
                }else {
                    print(diagram[i][j] )
                }
            }
            println()
        }
    }
    fun part1(input: List<String>): Int {
        input.map { ele -> ele.split("->") }
            .forEach {
                ele ->
                    run {
                        val x1 = ele[0].split(",")[0].trim().toInt()
                        val y1 = ele[0].split(",")[1].trim().toInt()
                        val x2 = ele[1].split(",")[0].trim().toInt()
                        val y2 = ele[1].split(",")[1].trim().toInt()
                        plot(y1, x1, y2, x2)

                    }
            }


//        printDiagram()
        return countAtLeastTwoLinesOverlap()
    }


    fun part2(input: List<String>): Int {
        input.map { ele -> ele.split("->") }
            .forEach {
                    ele ->
                run {
                    val x1 = ele[0].split(",")[0].trim().toInt()
                    val y1 = ele[0].split(",")[1].trim().toInt()
                    val x2 = ele[1].split(",")[0].trim().toInt()
                    val y2 = ele[1].split(",")[1].trim().toInt()
                    plotDiagonal(y1, x1, y2, x2)

                }
            }
//        printDiagram()
        return countAtLeastTwoLinesOverlap()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    resetDiagram()
    println(part1(input))
    println(part2(input))
}
