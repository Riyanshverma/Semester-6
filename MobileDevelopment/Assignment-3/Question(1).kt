import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.atan2

// Class to represent a complex number
class cmplx(val real: Double = 0.0, val imaginary: Double = 0.0) { // Primary constructor with default values

    // Calculate the modulus (magnitude) of the complex number
    fun modulus(): Double {
        return sqrt(real.pow(2) + imaginary.pow(2))
    }

    // Calculate the argument (angle/phase) of the complex number in radians
    fun argument(): Double {
        return atan2(imaginary, real)
    }

    // Add another complex number to this one
    // Returns a new cmplx object representing the sum
    fun add(other: cmplx): cmplx {
        val newReal = this.real + other.real
        val newImaginary = this.imaginary + other.imaginary
        return cmplx(newReal, newImaginary)
    }

    // Multiply this complex number by another one
    // Returns a new cmplx object representing the product
    fun multiply(other: cmplx): cmplx {
        val newReal = (this.real * other.real) - (this.imaginary * other.imaginary)
        val newImaginary = (this.real * other.imaginary) + (this.imaginary * other.real)
        return cmplx(newReal, newImaginary)
    }

    // Display the complex number in x + iy format
    // Overriding toString is the idiomatic Kotlin way to control string representation
    override fun toString(): String {
        return when {
            imaginary == 0.0 -> "$real" // Purely real
            real == 0.0 -> "${imaginary}i" // Purely imaginary
            imaginary < 0 -> "$real - ${-imaginary}i" // Negative imaginary part
            else -> "$real + ${imaginary}i" // Standard case
        }
    }
}

// Main function to demonstrate the cmplx class
fun main() {
    // Using the constructor with initial values
    val complex1 = cmplx(3.0, 4.0)
    println("Complex Number 1: $complex1") // Uses toString() implicitly

    // Using the default constructor (0.0 + 0.0i)
    val complexZero = cmplx()
    println("Complex Number (Default): $complexZero")

    val complex2 = cmplx(1.0, -2.0)
    println("Complex Number 2: $complex2")

    // a. Calculate modulus
    println("Modulus of $complex1: ${complex1.modulus()}")
    println("Modulus of $complex2: ${complex2.modulus()}")

    // b. Calculate argument
    println("Argument of $complex1 (radians): ${complex1.argument()}")
    println("Argument of $complex2 (radians): ${complex2.argument()}")

    // c. Add complex numbers
    val sum = complex1.add(complex2)
    println("Sum of $complex1 and $complex2: $sum")

    // d. Multiply complex numbers
    val product = complex1.multiply(complex2)
    println("Product of $complex1 and $complex2: $product")

    // e. Display method is implicitly handled by toString
    println("Displaying complex1 again: $complex1")
    println("Displaying complex2 again: $complex2")
    println("Displaying sum: $sum")
    println("Displaying product: $product")
}