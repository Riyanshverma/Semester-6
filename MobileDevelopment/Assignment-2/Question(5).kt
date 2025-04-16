fun main() {
    print("Enter the number of elements: ")
    val n = readLine()!!.toInt()
    val arr = IntArray(n)

    println("Enter $n numbers:")
    for (i in 0 until n) {
        arr[i] = readLine()!!.toInt()
    }

    println("\nOriginal Array: ${arr.joinToString()}")

    // a. Find all consecutive monotonically increasing sequences
    println("\nConsecutive Monotonically Increasing Sequences:")
    var i = 0
    while (i < n - 1) {
        if (arr[i] < arr[i + 1]) {
            print("${arr[i]} ")
            var j = i + 1
            while (j < n && arr[j - 1] < arr[j]) {
                print("${arr[j]} ")
                j++
            }
            println()
            i = j
        } else {
            i++
        }
    }

    // b. Find first and second maximum number without sorting
    var max1 = Int.MIN_VALUE
    var max2 = Int.MIN_VALUE
    for (num in arr) {
        if (num > max1) {
            max2 = max1
            max1 = num
        } else if (num > max2 && num != max1) {
            max2 = num
        }
    }
    println("\nFirst Maximum: $max1")
    println("Second Maximum: $max2")

    // c. Rearrange array: odd and even numbers alternately
    val oddList = mutableListOf<Int>()
    val evenList = mutableListOf<Int>()
    for (num in arr) {
        if (num % 2 == 0) {
            evenList.add(num)
        } else {
            oddList.add(num)
        }
    }

    val rearranged = mutableListOf<Int>()
    var oddIndex = 0
    var evenIndex = 0

    while (oddIndex < oddList.size && evenIndex < evenList.size) {
        rearranged.add(oddList[oddIndex++])
        rearranged.add(evenList[evenIndex++])
    }
    while (oddIndex < oddList.size) {
        rearranged.add(oddList[oddIndex++])
    }
    while (evenIndex < evenList.size) {
        rearranged.add(evenList[evenIndex++])
    }

    println("\nArray rearranged with odd and even numbers alternatively:")
    println(rearranged.joinToString())
}
