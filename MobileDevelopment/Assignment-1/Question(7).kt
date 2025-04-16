fun countTriangles(sides: List<Int>): Int {
    val n = sides.size
    var count = 0
    
    // Sort the list of sides
    val sortedSides = sides.sorted()

    // Iterate through all possible triplets
    for (i in 0 until n - 2) {
        var k = i + 2
        for (j in i + 1 until n - 1) {
            while (k < n && sortedSides[i] + sortedSides[j] > sortedSides[k]) {
                k++
            }
            count += k - j - 1
        }
    }
    return count
}

fun main() {
    print("Enter the number of sides: ")
    val n = readLine()?.toIntOrNull() ?: 0

    if (n < 3) {
        println("At least 3 sides are required to form a triangle.")
        return
    }

    val sides = mutableListOf<Int>()
    println("Enter the lengths of the sides:")
    for (i in 1..n) {
        val side = readLine()?.toIntOrNull() ?: 0
        if (side > 0) {
            sides.add(side)
        } else {
            println("Invalid side length. Please enter positive integers.")
            return
        }
    }

    val result = countTriangles(sides)
    println("Number of possible triangles: $result")
}
