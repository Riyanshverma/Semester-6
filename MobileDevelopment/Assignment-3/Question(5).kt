import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

// Make Circle2D open so it can be inherited from
open class Circle2D {
    // Properties for center and radius
    val x: Double
    val y: Double
    val radius: Double

    // No-arg constructor: defaults to (0,0) with radius 1
    constructor() {
        this.x = 0.0
        this.y = 0.0
        this.radius = 1.0
        // No need for separate validation here as defaults are valid
    }

    // Constructor with specified x, y, and radius
    constructor(x: Double, y: Double, radius: Double) {
        // Validate radius
        require(radius >= 0.0) { "Radius cannot be negative." }
        this.x = x
        this.y = y
        this.radius = radius
    }

    // Method to calculate and return the area
    // Mark as open for potential overriding (specifically for Sphere's surface area)
    open fun getArea(): Double {
        return PI * radius.pow(2)
    }

    // Method to calculate and return the perimeter (circumference)
    fun getPerimeter(): Double {
        return 2 * PI * radius
    }

    // Method to check if a point (px, py) is inside the circle
    fun contains(pointX: Double, pointY: Double): Boolean {
        // Calculate the squared distance from the center to the point
        val distanceSquared = (pointX - this.x).pow(2) + (pointY - this.y).pow(2)
        // Compare squared distance to squared radius (avoids sqrt)
        return distanceSquared <= this.radius.pow(2)
    }

    // Method to check if another circle is completely inside this circle
    fun contains(other: Circle2D): Boolean {
        // Calculate the distance between the centers
        val distanceBetweenCenters = sqrt((other.x - this.x).pow(2) + (other.y - this.y).pow(2))
        // The other circle is inside if the distance between centers plus the other's radius
        // is less than or equal to this circle's radius.
        // Also ensure the other circle's radius is not negative (though constructor prevents this)
        return distanceBetweenCenters + other.radius <= this.radius && other.radius >= 0.0
    }

    // Method to display circle information
    // Mark as open for overriding by Sphere
    open fun display() {
        println("--- Circle ---")
        println("  Center: (${String.format("%.2f", x)}, ${String.format("%.2f", y)})")
        println("  Radius: ${String.format("%.2f", radius)}")
        println("  Area: ${String.format("%.2f", getArea())}")
        println("  Perimeter: ${String.format("%.2f", getPerimeter())}")
    }
}

// Sphere class inheriting from Circle2D
class Sphere : Circle2D {

    // No-arg constructor for Sphere, calls Circle2D's no-arg constructor
    constructor() : super()

    // Constructor for Sphere with specified center and radius, calls Circle2D's constructor
    constructor(x: Double, y: Double, radius: Double) : super(x, y, radius)

    // Method to calculate the volume of the sphere
    fun getVolume(): Double {
        // Use 4.0/3.0 for floating-point division
        return (4.0 / 3.0) * PI * radius.pow(3)
    }

    // Override getArea to calculate the surface area of the sphere
    override fun getArea(): Double {
        return 4 * PI * radius.pow(2)
    }

    // Override display to show sphere-specific information
    override fun display() {
        println("--- Sphere ---")
        // Access inherited properties x, y, radius directly
        println("  Center: (${String.format("%.2f", x)}, ${String.format("%.2f", y)})")
        println("  Radius: ${String.format("%.2f", radius)}")
        // Calls the overridden getArea() for surface area
        println("  Surface Area: ${String.format("%.2f", getArea())}")
        println("  Volume: ${String.format("%.2f", getVolume())}")
    }
}

// Main function to demonstrate the classes
fun main() {
    println("--- Creating Circles ---")
    val defaultCircle = Circle2D()
    defaultCircle.display()
    println()

    val customCircle = Circle2D(2.5, -1.0, 5.0)
    customCircle.display()
    println()

    println("--- Circle Containment Checks ---")
    val pointInside = customCircle.contains(3.0, 0.0) // Should be true
    val pointOutside = customCircle.contains(10.0, 10.0) // Should be false
    println("Point (3.0, 0.0) inside customCircle? $pointInside")
    println("Point (10.0, 10.0) inside customCircle? $pointOutside")
    println()

    val smallCircleInside = Circle2D(3.0, -0.5, 1.0)
    val largeCircleOutside = Circle2D(8.0, 0.0, 4.0)
    val overlappingCircle = Circle2D(6.0, -1.0, 3.0) // Center distance + radius > outer radius

    val isSmallInside = customCircle.contains(smallCircleInside) // Should be true
    val isLargeInside = customCircle.contains(largeCircleOutside) // Should be false
    val isOverlappingInside = customCircle.contains(overlappingCircle) // Should be false

    println("Circle $smallCircleInside inside customCircle? $isSmallInside")
    println("Circle $largeCircleOutside inside customCircle? $isLargeInside")
    println("Circle $overlappingCircle inside customCircle? $isOverlappingInside")
    println()


    println("--- Creating Spheres ---")
    val defaultSphere = Sphere()
    defaultSphere.display() // Will show center(0,0), r=1, Surface Area, Volume
    println()

    val customSphere = Sphere(1.0, 2.0, 3.0)
    customSphere.display()
    println()

    // You can still call methods inherited from Circle2D on a Sphere if needed,
    // but getArea() will return the Sphere's surface area due to overriding.
    val spherePerimeter = customSphere.getPerimeter() // Still calculates 2*PI*r
    val sphereContainsPoint = customSphere.contains(1.5, 2.5) // Still checks if point is within radius distance
    println("Custom sphere perimeter (base circle): ${String.format("%.2f", spherePerimeter)}")
    println("Point (1.5, 2.5) inside custom sphere radius? $sphereContainsPoint")
}