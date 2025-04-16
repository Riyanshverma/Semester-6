fun calculateIncomeTax(grossIncome: Double): Pair<Double, Double> {
    val standardDeduction = 75000.0
    val netIncome = grossIncome - standardDeduction
    val incomeTax = calculateTax(netIncome)
    val totalTax = calculateTotalTax(incomeTax)
    val netIncomeAfterTax = netIncome - totalTax

    return Pair(totalTax, netIncomeAfterTax)
}

fun calculateTax(netIncome: Double): Double {
    return when {
        netIncome <= 400000 -> 0.0
        netIncome in 400001.0..800000.0 -> 0.05 * (netIncome - 300000)
        netIncome in 800001.0..1200000.0 -> 20000 + 0.10 * (netIncome - 800000)
        netIncome in 1200001.0..1600000.0 -> 60000 + 0.15 * (netIncome - 1200000)
        netIncome in 1600001.0..2000000.0 -> 120000 + 0.20 * (netIncome - 1600000)
        netIncome in 2000001.0..2400000.0 -> 200000 + 0.25 * (netIncome - 2000000)
        else -> 300000 + 0.30 * (netIncome - 2400000)
    }
}

fun calculateTotalTax(incomeTax: Double): Double {
    val educationCess = 0.02 * incomeTax
    val secondaryCess = 0.01 * incomeTax
    return incomeTax + educationCess + secondaryCess
}

fun main() {
    print("Enter Gross Income in INR: ")
    val grossIncome = readLine()?.toDoubleOrNull() ?: 0.0

    if (grossIncome > 0) {
        val (totalTax, netIncome) = calculateIncomeTax(grossIncome)
        println("Total Tax Deduction: INR %.2f".format(totalTax))
        println("Net Income After Tax: INR %.2f".format(netIncome))
    } else {
        println("Invalid Input! Please enter a valid amount.")
    }
}
