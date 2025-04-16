import kotlin.math.abs

// Data class to represent a point
data class Point(val x: Double, val y: Double) {
    override fun toString(): String {
        // Format for cleaner output
        return "(${String.format("%.2f", x)}, ${String.format("%.2f", y)})"
    }
}

// Class to represent a line segment
class LineSegment(val p1: Point, val p2: Point) {

    /**
     * Checks for intersection with another line segment and displays the result.
     * Based on the algorithm from Paul Bourke's geometry page.
     */
    fun intersect(other: LineSegment) {
        val x1 = this.p1.x
        val y1 = this.p1.y
        val x2 = this.p2.x
        val y2 = this.p2.y

        val x3 = other.p1.x
        val y3 = other.p1.y
        val x4 = other.p2.x
        val y4 = other.p2.y

        // Calculate the denominator for the parametric equations
        val denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1)

        // Use a small epsilon for floating-point comparison to zero
        val epsilon = 1E-10 // A small tolerance value

        // Check if the lines are parallel (denominator is close to zero)
        if (abs(denominator) < epsilon) {
            println("Result: Parallel lines - no unique intersection point.")
            // Note: This also covers collinear lines. A more complex check
            // could determine if collinear segments overlap, but the prompt
            // asks to report parallel in this case.
            return
        }

        // Calculate the numerators
        val uaNumerator = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)
        val ubNumerator = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)

        // Calculate the parameters ua and ub
        val ua = uaNumerator / denominator
        val ub = ubNumerator / denominator

        // Check if the intersection point lies on both line segments (0 <= ua <= 1 and 0 <= ub <= 1)
        if (ua >= 0.0 && ua <= 1.0 && ub >= 0.0 && ub <= 1.0) {
            // Calculate the intersection point coordinates
            val intersectionX = x1 + ua * (x2 - x1)
            val intersectionY = y1 + ua * (y2 - y1)
            val intersectionPoint = Point(intersectionX, intersectionY)
            println("Result: Segments intersect at $intersectionPoint")
        } else {
            println("Result: Segments do not intersect (lines intersect outside the segments).")
        }
    }

    override fun toString(): String {
        return "Segment[$p1 to $p2]"
    }
}

// Helper function to read a Point from user input
fun readPoint(promptMessage: String): Point {
    while (true) {
        print(promptMessage)
        val input = readlnOrNull()?.trim()
        if (input != null) {
            val parts = input.split(',', ';', ' ').filter { it.isNotBlank() } // Allow different separators
            if (parts.size == 2) {
                val x = parts[0].toDoubleOrNull()
                val y = parts[1].toDoubleOrNull()
                if (x != null && y != null) {
                    return Point(x, y)
                } else {
                    println("Invalid number format. Please enter numeric coordinates.")
                }
            } else {
                println("Invalid format. Please enter coordinates as 'x, y' (e.g., 1.5, 2.0).")
            }
        } else {
            println("Input cancelled.")
            // Decide how to handle cancellation - here we'll loop again
        }
    }
}

// Main function to run the program
fun main() {
    println("--- Line Segment Intersection Calculator ---")

    println("\nEnter endpoints for the first line segment:")
    val p1 = readPoint("  Enter endpoint 1 (x1, y1): ")
    val p2 = readPoint("  Enter endpoint 2 (x2, y2): ")
    val segment1 = LineSegment(p1, p2)
    println("First segment created: $segment1")


    println("\nEnter endpoints for the second line segment:")
    val p3 = readPoint("  Enter endpoint 3 (x3, y3): ")
    val p4 = readPoint("  Enter endpoint 4 (x4, y4): ")
    val segment2 = LineSegment(p3, p4)
    println("Second segment created: $segment2")

    println("\n--- Calculating Intersection ---")
    // Call the intersect method on the first segment, passing the second
    segment1.intersect(segment2)
}