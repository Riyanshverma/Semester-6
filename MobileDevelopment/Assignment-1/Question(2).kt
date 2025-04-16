fun calculateElectricityBill(units: Int): Pair<Double, Int> {
    var totalAmount = 0.0
    val fixedCharges = when {
        units in 1..150 -> 230.0
        units in 151..300 -> 275.0
        units in 301..500 -> 345.0
        units > 500 -> 400.0
        else -> 0.0
    }

    totalAmount += fixedCharges

    totalAmount += when {
        units in 1..50 -> units * 5.00
        units in 51..150 -> 50 * 5.00 + (units - 50) * 6.50
        units in 151..300 -> 50 * 5.00 + 100 * 6.50 + (units - 150) * 7.50
        units in 301..500 -> 50 * 5.00 + 100 * 6.50 + 150 * 7.50 + (units - 300) * 7.85
        units > 500 -> 50 * 5.00 + 100 * 6.50 + 150 * 7.50 + 200 * 7.85 + (units - 500) * 8.00
        else -> 0.0
    }

    return Pair(totalAmount, units)
}

fun main() {
    print("Enter the number of units consumed: ")
    val units = readLine()?.toIntOrNull() ?: 0

    if (units > 0) {
        val (billAmount, consumedUnits) = calculateElectricityBill(units)
        println("Electricity Bill Amount: INR %.2f".format(billAmount))
        println("Number of Units Consumed: $consumedUnits")
    } else {
        println("Invalid Input! Please enter a valid number of units.")
    }
}
