fun main() {
    print("Enter the number of elements: ")
    val n = readLine()!!.toInt()
    val arr = IntArray(n)

    println("Enter $n numbers (between 0 to 100):")
    for (i in 0 until n) {
        arr[i] = readLine()!!.toInt()
        if (arr[i] !in 0..100) {
            println("Number out of range! Please enter numbers between 0 and 100.")
            return
        }
    }

    val frequency = IntArray(10)

    for (num in arr) {
        if (num == 0) {
            println("Number 0 is not part of any interval, skipping.")
            continue
        }
        val index = if (num == 100) 9 else (num - 1) / 10
        frequency[index]++
    }

    println("\nFrequency Distribution:")
    for (i in 0 until 10) {
        val start = i * 10 + 1
        val end = (i + 1) * 10
        println("$start-$end : ${frequency[i]}")
    }
}