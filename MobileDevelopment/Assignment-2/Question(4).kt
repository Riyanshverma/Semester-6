fun main() {
    println("Enter number of elements for Set A:")
    val nA = readLine()!!.toInt()
    val setA = mutableSetOf<Int>()
    println("Enter $nA unique elements for Set A:")
    while (setA.size < nA) {
        val num = readLine()!!.toInt()
        if (!setA.add(num)) {
            println("Duplicate element detected! Enter a unique number:")
        }
    }

    println("\nEnter number of elements for Set B:")
    val nB = readLine()!!.toInt()
    val setB = mutableSetOf<Int>()
    println("Enter $nB unique elements for Set B:")
    while (setB.size < nB) {
        val num = readLine()!!.toInt()
        if (!setB.add(num)) {
            println("Duplicate element detected! Enter a unique number:")
        }
    }

    println("\nSet A: $setA")
    println("Set B: $setB")

    // a. Union
    val unionSet = setA.union(setB)
    println("\nUnion of Set A and Set B: $unionSet")

    // b. Intersection
    val intersectionSet = setA.intersect(setB)
    if (intersectionSet.isEmpty()) {
        println("\nSets are disjoint (No common elements)")
    } else {
        println("\nIntersection of Set A and Set B: $intersectionSet")
    }

    // c. Set A - Set B
    val differenceSet = setA.subtract(setB)
    println("\nSet Difference (SetA - SetB): $differenceSet")

    // d. Power Set of Set A
    println("\nPower Set of Set A:")
    val setAList = setA.toList()
    val powerSetSize = 1 shl setAList.size
    for (i in 0 until powerSetSize) {
        val subset = mutableListOf<Int>()
        for (j in setAList.indices) {
            if (i and (1 shl j) != 0) {
                subset.add(setAList[j])
            }
        }
        println(subset)
    }
}
