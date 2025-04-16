fun insertionSortAscending(arr: IntArray) {
    for (i in 1 until arr.size) {
        val key = arr[i]
        var j = i - 1
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        arr[j + 1] = key
    }
}

fun mergeDescending(arr1: IntArray, arr2: IntArray): IntArray {
    val merged = IntArray(arr1.size + arr2.size)
    var i = arr1.size - 1
    var j = arr2.size - 1
    var k = 0

    while (i >= 0 && j >= 0) {
        if (arr1[i] > arr2[j]) {
            merged[k++] = arr1[i--]
        } else {
            merged[k++] = arr2[j--]
        }
    }
    while (i >= 0) {
        merged[k++] = arr1[i--]
    }
    while (j >= 0) {
        merged[k++] = arr2[j--]
    }

    return merged
}

fun main() {
    print("Enter size of first array: ")
    val n1 = readLine()!!.toInt()
    val arr1 = IntArray(n1)
    println("Enter $n1 elements for first array:")
    for (i in 0 until n1) {
        arr1[i] = readLine()!!.toInt()
    }

    print("Enter size of second array: ")
    val n2 = readLine()!!.toInt()
    val arr2 = IntArray(n2)
    println("Enter $n2 elements for second array:")
    for (i in 0 until n2) {
        arr2[i] = readLine()!!.toInt()
    }

    insertionSortAscending(arr1)
    insertionSortAscending(arr2)

    println("First Array Sorted Ascending: ${arr1.joinToString()}")
    println("Second Array Sorted Ascending: ${arr2.joinToString()}")

    val mergedDesc = mergeDescending(arr1, arr2)
    println("Merged Array in Descending Order: ${mergedDesc.joinToString()}")
}
