fun isPerfect(number: Int): Boolean {
    if (number < 2) return false
    var sum = 1
    for (i in 2..Math.sqrt(number.toDouble()).toInt()) {
        if (number % i == 0) {
            sum += i
            if (i != number / i) {
                sum += number / i
            }
        }
    }
    return sum == number
}

fun printPerfectNumbers(start: Int, end: Int) {
    val perfectNumbers = (start..end).filter { isPerfect(it) }
    if (perfectNumbers.isEmpty()) {
        println("No perfect numbers found in this range.")
    } else {
        println("Perfect numbers between $start and $end are: ${perfectNumbers.joinToString(", ")}")
    }
}

fun main() {
    print("Enter the starting number of range: ")
    val start = readLine()?.toIntOrNull() ?: return println("Invalid Input! Please enter a valid range.")

    print("Enter the ending number of range: ")
    val end = readLine()?.toIntOrNull() ?: return println("Invalid Input! Please enter a valid range.")

    if (start > 0 && end > start) {
        printPerfectNumbers(start, end)
    } else {
        println("Invalid Input! Please enter a valid range.")
    }
}
