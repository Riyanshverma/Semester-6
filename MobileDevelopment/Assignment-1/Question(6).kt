fun generatePairNumbers(start: Int, end: Int): List<Pair<Int, Int>> {
    return (start..end).flatMap { i ->
        (i + 1..end).map { j ->
            i to j
        }
    }
}

fun printPairNumbers(start: Int, end: Int) {
    val pairs = generatePairNumbers(start, end)
    if (pairs.isNotEmpty()) {
        println("Pair numbers between $start and $end are:")
        pairs.forEach { (i, j) ->
            println("($i, $j)")
        }
    } else {
        println("No pairs found in this range.")
    }
}

fun main() {
    print("Enter the starting number of range: ")
    val start = readLine()?.toIntOrNull() ?: 0

    print("Enter the ending number of range: ")
    val end = readLine()?.toIntOrNull() ?: 0

    if (start > 0 && end > start) {
        printPairNumbers(start, end)
    } else {
        println("Invalid Input! Please enter a valid range.")
    }
}