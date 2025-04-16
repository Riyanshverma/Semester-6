fun findElements(arr: IntArray) {
    if (arr.size < 2) {
        println("Array should have at least 2 elements")
        return
    }

    val sortedArr = arr.sorted()

    val smallest = sortedArr.first()
    val secondSmallest = sortedArr[1]
    val largest = sortedArr.last()
    val secondLargest = sortedArr[sortedArr.size - 2]

    println("Largest Element: $largest")
    println("Second Largest Element: $secondLargest")
    println("Smallest Element: $smallest")
    println("Second Smallest Element: $secondSmallest")
}

fun main() {
    print("Enter the number of elements: ")
    val n = readLine()?.toIntOrNull() ?: 0

    if (n < 2) {
        println("Please enter at least 2 elements")
        return
    }

    val arr = IntArray(n)
    println("Enter the elements:")
    for (i in 0 until n) {
        arr[i] = readLine()?.toIntOrNull() ?: 0
    }

    findElements(arr)
}
