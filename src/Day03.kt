fun main() {
    fun getHighestOccurringBit(input: List<String>, pos: Int): String {
        var count0 = 0
        var count1 = 0
        input.forEach {
                ele ->
            run {
                val currentElement = ele[pos-1];
                if (currentElement.toString() == "1"){
                    count1++
                }else {
                    count0++
                }

            }
        }
        if (count0 > count1){
            return "0";
        }
        return "1";
    }

    fun part1(input: List<String>): Int {
        var gammaRate = ""
        var epsilon= ""

        for (i in 1 .. input[0].length){
            if (getHighestOccurringBit(input, i) == "0"){
                gammaRate+= "0";
                epsilon+="1";
            }else {
                gammaRate+= "1";
                epsilon+="0";
            }
        }
        return convertBinaryToDecimal(gammaRate.toLong()) * convertBinaryToDecimal(epsilon.toLong());
    }


    fun transform(input: List<String>, pos: Int, bitToConsider: String): List<String> {
        return input.filter { ele -> ele[pos-1].toString() == bitToConsider }
            .toList()
    }

    fun part2(input: List<String>): Int {
        var oxygenGeneratorRating = 0L;
        var CO2ScrubberRating = 0L;

        var inputCopy = input

        for (i in 1 .. input[0].length){
            if (inputCopy.size > 1){
                var bitToConsider = getHighestOccurringBit(inputCopy, i);
                inputCopy = transform(inputCopy, i, bitToConsider);
            }
        }

        oxygenGeneratorRating = inputCopy[0].toLong()

        inputCopy = input

        for (i in 1 .. input[0].length){
            if (inputCopy.size > 1){
                var bitToConsider = if (getHighestOccurringBit(inputCopy, i) == "0")  "1" else "0"
                inputCopy = transform(inputCopy, i, bitToConsider)
            }
        }
        CO2ScrubberRating = inputCopy[0].toLong()

        return convertBinaryToDecimal(oxygenGeneratorRating) * convertBinaryToDecimal(CO2ScrubberRating)

    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
