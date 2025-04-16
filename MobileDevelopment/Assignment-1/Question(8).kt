import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun getDayOfWeek(day: Int, month: Int, year: Int): String {
    return try {
        val date = LocalDate.of(year, month, day)
        date.dayOfWeek.toString()
    } catch (e: DateTimeParseException) {
        "Invalid date"
    }
}

fun main() {
    print("Enter the date (dd): ")
    val day = readLine()?.toIntOrNull() ?: 0

    print("Enter the month (mm): ")
    val month = readLine()?.toIntOrNull() ?: 0

    print("Enter the year (yyyy): ")
    val year = readLine()?.toIntOrNull() ?: 0

    if (day in 1..31 && month in 1..12 && year > 0) {
        val dayOfWeek = getDayOfWeek(day, month, year)
        println("The day of the week for $day/$month/$year is: $dayOfWeek")
    } else {
        println("Invalid input. Please enter a valid date.")
    }
}
