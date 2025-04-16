fun isPrime(number: Int): Boolean {
    if (number <= 1) return false
    if (number == 2) return true
    if (number % 2 == 0) return false
    for (i in 3..Math.sqrt(number.toDouble()).toInt() step 2) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}

fun main() {
    print("Enter a number: ")
    val number = readLine()?.toIntOrNull() ?: 0

    if (number > 0) {
        if (isPrime(number)) {
            println("$number is a Prime Number.")
        } else {
            println("$number is not a Prime Number.")
        }
    } else {
        println("Invalid Input! Please enter a positive number.")
    }
}