fun main() {
    print("Enter number of rows and columns (for square matrix): ")
    val n = readLine()!!.toInt()

    val matrix1 = Array(n) { IntArray(n) }
    val matrix2 = Array(n) { IntArray(n) }

    println("Enter elements of first matrix:")
    for (i in 0 until n) {
        for (j in 0 until n) {
            matrix1[i][j] = readLine()!!.toInt()
        }
    }

    println("Enter elements of second matrix:")
    for (i in 0 until n) {
        for (j in 0 until n) {
            matrix2[i][j] = readLine()!!.toInt()
        }
    }

    // a. Check Symmetric & Skew-Symmetric
    println("\nChecking First Matrix:")
    checkSymmetry(matrix1)
    println("\nChecking Second Matrix:")
    checkSymmetry(matrix2)

    // b. Addition of matrices
    val sumMatrix = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j]
        }
    }
    println("\nSum of matrices:")
    printMatrix(sumMatrix)

    // c. Product of matrices
    val productMatrix = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            productMatrix[i][j] = 0
            for (k in 0 until n) {
                productMatrix[i][j] += matrix1[i][k] * matrix2[k][j]
            }
        }
    }
    println("\nProduct of matrices:")
    printMatrix(productMatrix)

    // d. Transpose of first matrix
    val transposeMatrix = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            transposeMatrix[j][i] = matrix1[i][j]
        }
    }
    println("\nTranspose of first matrix:")
    printMatrix(transposeMatrix)

    // e. Special Third Matrix
    val specialMatrix = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            when {
                i > j -> specialMatrix[i][j] = matrix1[i][j]
                i < j -> specialMatrix[i][j] = matrix2[i][j]
                else -> specialMatrix[i][j] = 0
            }
        }
    }
    println("\nSpecial Matrix (Lower triangle from first, Upper from second, Diagonal 0):")
    printMatrix(specialMatrix)
}

fun checkSymmetry(matrix: Array<IntArray>) {
    val n = matrix.size
    var symmetric = true
    var skewSymmetric = true

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (matrix[i][j] != matrix[j][i]) symmetric = false
            if (matrix[i][j] != -matrix[j][i]) skewSymmetric = false
        }
    }

    if (symmetric) println("Matrix is Symmetric")
    else println("Matrix is NOT Symmetric")

    if (skewSymmetric) println("Matrix is Skew-Symmetric")
    else println("Matrix is NOT Skew-Symmetric")
}

fun printMatrix(matrix: Array<IntArray>) {
    for (row in matrix) {
        println(row.joinToString(" "))
    }
}
