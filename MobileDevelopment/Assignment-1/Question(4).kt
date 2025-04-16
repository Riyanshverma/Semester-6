fun sieveOfEratosthenes(start: Int, end: Int): List<Int> {
    if (end < 2) {
        return emptyList()
    }

    val isPrime = BooleanArray(end + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    for (i in 2..Math.sqrt(end.toDouble()).toInt()) {
        if (isPrime[i]) {
            for (j in i * i..end step i) {
                isPrime[j] = false
            }
        }
    }

    return (start..end).filter { it > 1 && isPrime[it] }
}

fun main() {
    print("Enter the starting number of range: ")
    val start = readLine()?.toIntOrNull() ?: 0

    print("Enter the ending number of range: ")
    val end = readLine()?.toIntOrNull() ?: 0

    if (start > 0 && end > start) {
        val primes = sieveOfEratosthenes(start, end)
        if (primes.isEmpty()) {
            println("No prime numbers in this range.")
        } else {
            println("Prime numbers between $start and $end are: ${primes.joinToString(", ")}")
        }
    } else {
        println("Invalid Input! Please enter a valid range.")
    }
}
