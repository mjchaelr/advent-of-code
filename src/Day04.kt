fun main() {
    fun part1(input: List<String>, checkLastToWin: Boolean = false): Int {
        val numberDraws = input[0].split(",");
        val numberOfBoards = (input.size -1)/6;
        // init board
        val boards = Array(numberOfBoards){ Board(5)}
        var board =  arrayListOf<String>();
        var boardNumber= 0;
        for (i in 2 until input.size){
            if (input[i].isEmpty()){
                continue
            }
            board.add(input[i])
            if (i == input.size-1 || input[i+1].isEmpty()){
                boards[boardNumber].initBoard(board)
                boardNumber++
                board =   arrayListOf<String>()
            }
        }

        var unmarkedSum =0
        var bingoDraw = 0
        var countWinner =0
        var lastWinnerIndex =0
        loopNumberDraws@ for (i in numberDraws.indices){
            bingoDraw = numberDraws[i].toInt()



            for (boardIndex in boards.indices){
                boards[boardIndex].updateWithValue(numberDraws[i].toInt())
                val rowBingo =  boards[boardIndex].rowBingo()

                if (rowBingo >= 0){
                    unmarkedSum = boards[boardIndex].sumRemainingWithoutRow(rowBingo)
                    countWinner++
                    boards[boardIndex].setWinner(countWinner)
                    if (checkLastToWin){
                        if (boards.filter { board -> board.getWinner() }.toList().size == boards.size){
                            lastWinnerIndex = boardIndex
                            break@loopNumberDraws
                        }
                    }
                    if (!checkLastToWin) {
                        break@loopNumberDraws
                    }

                }

                val columnBingo = boards[boardIndex].columnBingo(0, 0)

                if (columnBingo >= 0){
                    unmarkedSum = boards[boardIndex].sumRemainingWithoutColumn(columnBingo)
                    countWinner++
                    boards[boardIndex].setWinner(countWinner)
                    if (checkLastToWin){
                        if (boards.filter { board -> board.getWinner() }.toList().size == boards.size){
                            lastWinnerIndex = boardIndex
                            break@loopNumberDraws
                        }
                    }
                    if (!checkLastToWin) {
                        break@loopNumberDraws
                    }
                }
            }

        }

        if (checkLastToWin){
            return bingoDraw * boards[lastWinnerIndex].sumRemainingWithoutColumn(-1, bingoDraw)
        }

        return bingoDraw * unmarkedSum
    }


    fun part2(input: List<String>): Int {
        return part1(input, true)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput, false) == 4512)
    check(part2(testInput) == 1924)
    val input = readInput("Day04")
    println(part1(input, false))
    println(part2(input))
}

class Board(dimension: Int) {

    private var array = Array(dimension) {Array(dimension) { Cell(false, 0) } }
    private var winnerBoard = -1;
    private var winner = false;

    fun initBoard( values: List<String>){
        var index = 0
        values.forEach {
                ele ->
            run {
                val data = ele.split(" ").filter { data -> data.trim().isNotEmpty() }.toList()
                for (i in data.indices){
                    array[index][i] =  Cell(false, data[i].toInt())
                }
                index++
            }
        }
    }

    fun updateWithValue(value: Int){
        array.forEach firstLoop@{
                ele ->
            run {
                ele.forEach {
                        col -> run {
                    if (col.value == value){
                        col.selected = true
                        return@firstLoop
                    }
                }
                }
            }
        }
    }

    fun rowBingo(): Int {
        for (i in array.indices){
            val size = array[i].filter { cell -> cell.selected }.toList().size
            if (size == 5){
                return i
            }
        }

        return -1;
    }


    fun columnBingo(rowIndex: Int, colIndex: Int): Int{
        val arrayReversed = Array(5) {Array(5) { Cell(false, 0) } }


        for (i in 0 .. 4){
            for (j in array[i].indices){
                arrayReversed[i][j] = array[j][i]
            }
        }

        for (i in arrayReversed.indices){
            val size = arrayReversed[i].filter { cell -> cell.selected }.toList().size
            if (size == 5){
                return i
            }
        }
        return -1
    }

    fun sumRemainingWithoutRow(rowNumber: Int, excludeVal: Int): Int {
        var sum =0;
        loop1@ for (i in array.indices){
            if (i == rowNumber) continue@loop1
            loop2@ for (j in array[i].indices){
                if (!array[i][j].selected && !(array[i][j].value == excludeVal)){
                    sum+= array[i][j].value
                }
            }
        }
        return sum
    }

    fun sumRemainingWithoutRow(rowNumber: Int): Int {
        var sum =0;
        loop1@ for (i in array.indices){
            if (i == rowNumber) continue@loop1
            loop2@ for (j in array[i].indices){
                if (!array[i][j].selected){
                    sum+= array[i][j].value
                }
            }
        }
        return sum
    }
    fun sumRemainingWithoutColumn(colNumber: Int, excludeVal: Int): Int {
        val arrayReversed = Array(5) {Array(5) { Cell(false, 0) } }


        for (i in 0 .. 4){
            for (j in array[i].indices){
                arrayReversed[i][j] = array[j][i]
            }
        }

        var sum =0;
        loop1@ for (i in arrayReversed.indices){
            if (i == colNumber) continue@loop1
            loop2@ for (j in arrayReversed[i].indices){
                if (!arrayReversed[i][j].selected&& !(arrayReversed[i][j].value == excludeVal)){
                    sum+= arrayReversed[i][j].value
                }
            }
        }
        return sum
    }
    fun sumRemainingWithoutColumn(colNumber: Int): Int {
        val arrayReversed = Array(5) {Array(5) { Cell(false, 0) } }


        for (i in 0 .. 4){
            for (j in array[i].indices){
                arrayReversed[i][j] = array[j][i]
            }
        }

        var sum =0;
        loop1@ for (i in arrayReversed.indices){
            if (i == colNumber) continue@loop1
            loop2@ for (j in arrayReversed[i].indices){
                if (!arrayReversed[i][j].selected){
                    sum+= arrayReversed[i][j].value
                }
            }
        }
        return sum
    }

    fun printBoard(){
        array.forEach {
            ele ->
                run {
                    ele.forEach {
                        col -> run {
                                print(col.toString())
                            }
                    }
                    println()
                }
        }
    }

    fun setWinner(countWinner: Int) {
        winnerBoard = countWinner
        winner = true
    }

    fun getWinner(): Boolean {
        return this.winner
    }


}

class Cell (selected: Boolean, value: Int) {
    var selected = false
    var value = 0;

    init {
        this.selected = selected
        this.value = value
    }

    override fun toString(): String {
        return "Cell(selected=$selected, value=$value)"
    }
}