import kotlin.math.abs

// Class to represent a rational number (fraction)
class Rational {
    // Properties are immutable after initialization
    val numerator: Int
    val denominator: Int

    // Private helper function for Greatest Common Divisor (GCD) using Euclidean algorithm
    private fun gcd(a: Int, b: Int): Int {
        var n1 = abs(a) // Work with positive numbers
        var n2 = abs(b)
        while (n2 != 0) {
            val temp = n1 % n2
            n1 = n2
            n2 = temp
        }
        // Handle case where both inputs might be 0, though denominator check prevents gcd(x, 0) mostly
        return if (n1 == 0) 1 else n1 
    }

    // Constructor: Initializes and simplifies the rational number
    constructor(num: Int, den: Int) {
        // 1. Check for zero denominator - throws an exception if invalid
        require(den != 0) { "Denominator cannot be zero." }

        // 2. Simplify the fraction by dividing by GCD
        val commonDivisor = gcd(num, den)
        var simplifiedNum = num / commonDivisor
        var simplifiedDen = den / commonDivisor

        // 3. Ensure the denominator is positive (sign is carried by the numerator)
        if (simplifiedDen < 0) {
            simplifiedNum = -simplifiedNum
            simplifiedDen = -simplifiedDen
        }

        // 4. Assign the final, simplified values
        this.numerator = simplifiedNum
        this.denominator = simplifiedDen
    }

    // Default constructor (represents 0/1 = 0) - Optional, but good practice
    constructor() : this(0, 1)

    // Private helper function for Least Common Multiple (LCM)
    // Formula: lcm(a, b) = |a * b| / gcd(a, b)
    // Use Long for intermediate multiplication to prevent potential overflow
    private fun lcm(a: Int, b: Int): Int {
        if (a == 0 || b == 0) return 0 // Or handle as needed, LCM with 0 is typically 0
        val product = abs(a.toLong() * b.toLong())
        val commonDivisor = gcd(a, b).toLong()
        val result = product / commonDivisor

        // Check if LCM exceeds Int range before casting back
        if (result > Int.MAX_VALUE) {
            throw ArithmeticException("LCM calculation resulted in integer overflow")
        }
        return result.toInt()
    }

    // Method to find LCM of the denominators of this and another Rational object
    fun findLcmOfDenominators(other: Rational): Int {
        return lcm(this.denominator, other.denominator)
    }

    // Method to add another Rational object to this one
    // Returns a new Rational object representing the sum
    fun add(other: Rational): Rational {
        val commonDenominator = lcm(this.denominator, other.denominator)
        // Calculate equivalent numerators for the common denominator
        val num1 = this.numerator * (commonDenominator / this.denominator)
        val num2 = other.numerator * (commonDenominator / other.denominator)
        // Create the new Rational object (constructor handles simplification)
        return Rational(num1 + num2, commonDenominator)
    }

    // Method to subtract another Rational object from this one
    // Returns a new Rational object representing the difference
    fun subtract(other: Rational): Rational {
        val commonDenominator = lcm(this.denominator, other.denominator)
        // Calculate equivalent numerators for the common denominator
        val num1 = this.numerator * (commonDenominator / this.denominator)
        val num2 = other.numerator * (commonDenominator / other.denominator)
        // Create the new Rational object (constructor handles simplification)
        return Rational(num1 - num2, commonDenominator)
    }

    // Method to display the rational number in x/y format
    fun show() {
        println(this.toString()) // Reuse the toString logic
    }

    // Override toString for idiomatic Kotlin string representation (used by println)
    override fun toString(): String {
        // If denominator is 1 after simplification, just show the numerator
        return if (denominator == 1) {
            "$numerator"
        } else {
            "$numerator/$denominator"
        }
    }

    // Optional: Override equals and hashCode for proper object comparison
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rational

        if (numerator != other.numerator) return false
        if (denominator != other.denominator) return false // Already simplified, direct compare is fine

        return true
    }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        return result
    }
}

// Main function to demonstrate the Rational class
fun main() {
    try {
        val r1 = Rational(1, 2) // 1/2
        val r2 = Rational(3, 4) // 3/4
        val r3 = Rational(2, -6) // Creates -1/3 after simplification and sign handling

        println("Rational 1 (r1): $r1") // Uses toString()
        print("Rational 2 (r2): "); r2.show() // Uses show()
        print("Rational 3 (r3): "); r3.show() // Uses show() -> -1/3

        // Find LCM of denominators
        val lcmDenominators = r1.findLcmOfDenominators(r2)
        println("\nLCM of denominators of r1 and r2: $lcmDenominators") // LCM(2, 4) = 4

        val lcmDenominators2 = r1.findLcmOfDenominators(r3)
        println("LCM of denominators of r1 and r3: $lcmDenominators2") // LCM(2, 3) = 6

        // Add rational numbers
        val sum_r1_r2 = r1.add(r2) // 1/2 + 3/4 = 2/4 + 3/4 = 5/4
        println("\nSum ($r1 + $r2): $sum_r1_r2")

        val sum_r1_r3 = r1.add(r3) // 1/2 + (-1/3) = 3/6 - 2/6 = 1/6
        println("Sum ($r1 + $r3): $sum_r1_r3")

        // Subtract rational numbers
        val diff_r2_r1 = r2.subtract(r1) // 3/4 - 1/2 = 3/4 - 2/4 = 1/4
        println("\nDifference ($r2 - $r1): $diff_r2_r1")

        val diff_r3_r1 = r3.subtract(r1) // -1/3 - 1/2 = -2/6 - 3/6 = -5/6
        println("Difference ($r3 - $r1): $diff_r3_r1")

        // Demonstrate simplification
        val r4 = Rational(10, 20) // Should become 1/2
        println("\nRational(10, 20) simplifies to: $r4")

        // Demonstrate default constructor
        val r_zero = Rational()
        println("Default Rational: $r_zero") // 0/1 -> 0

        // Demonstrate zero denominator exception
        println("\nAttempting to create Rational(5, 0)...")
        val r_invalid = Rational(5, 0) // This line will throw an exception

    } catch (e: IllegalArgumentException) {
        println("Caught expected exception: ${e.message}")
    } catch (e: ArithmeticException) {
        println("Caught arithmetic exception (e.g., LCM overflow): ${e.message}")
    }
}