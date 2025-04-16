class myTime(initialHour: Int, initialMinute: Int, initialSecond: Int) {

    // Properties to store time components and day
    var hour: Int
    var minute: Int
    var second: Int
    var day: Int = 1 // Start at Day 1

    init {
        // Validate initial values in the init block
        require(initialHour in 0..23) { "Initial hour must be between 0 and 23." }
        require(initialMinute in 0..59) { "Initial minute must be between 0 and 59." }
        require(initialSecond in 0..59) { "Initial second must be between 0 and 59." }

        this.hour = initialHour
        this.minute = initialMinute
        this.second = initialSecond
        // No day rollover check needed for initial constructor based on constraints
    }

    // Helper function to normalize time after addition (handles rollovers)
    // This modifies the current object's state before creating the new one.
    // Or rather, let's calculate directly for the new object.

    // Method to add minutes (< 1440)
    // Returns a NEW myTime object with the result
    fun addMinutes(minutesToAdd: Int): myTime {
        require(minutesToAdd in 0..<1440) { "Minutes to add must be between 0 and 1439." }

        // Calculate total seconds represented by the time to add
        val secondsToAdd = minutesToAdd * 60

        // Calculate total seconds from midnight for the current time
        val currentTotalSecondsInDay = this.hour * 3600 + this.minute * 60 + this.second
        // Add seconds corresponding to full days already passed
        val currentAbsoluteSeconds = currentTotalSecondsInDay + (this.day - 1) * 86400

        // Calculate the new absolute total seconds
        val newAbsoluteSeconds = currentAbsoluteSeconds + secondsToAdd

        // Calculate new day, hour, minute, second from the new absolute total seconds
        val newDay = (newAbsoluteSeconds / 86400) + 1
        val secondsIntoNewDay = newAbsoluteSeconds % 86400
        val newHour = secondsIntoNewDay / 3600
        val newMinute = (secondsIntoNewDay % 3600) / 60
        val newSecond = secondsIntoNewDay % 60 // Seconds remain unchanged when adding minutes

        // Create and return the new time object
        val resultTime = myTime(newHour, newMinute, newSecond)
        resultTime.day = newDay // Set the correct day on the new object
        return resultTime
    }

    // Method to add hours (< 24)
    // Returns a NEW myTime object with the result
    fun addHours(hoursToAdd: Int): myTime {
        require(hoursToAdd in 0..<24) { "Hours to add must be between 0 and 23." }

        // Calculate total seconds represented by the time to add
        val secondsToAdd = hoursToAdd * 3600

        // Calculate total seconds from midnight for the current time
        val currentTotalSecondsInDay = this.hour * 3600 + this.minute * 60 + this.second
        // Add seconds corresponding to full days already passed
        val currentAbsoluteSeconds = currentTotalSecondsInDay + (this.day - 1) * 86400

        // Calculate the new absolute total seconds
        val newAbsoluteSeconds = currentAbsoluteSeconds + secondsToAdd

        // Calculate new day, hour, minute, second from the new absolute total seconds
        val newDay = (newAbsoluteSeconds / 86400) + 1
        val secondsIntoNewDay = newAbsoluteSeconds % 86400
        val newHour = secondsIntoNewDay / 3600
        // Minutes and seconds remain unchanged when adding whole hours relative to the start of the hour
        val newMinute = (secondsIntoNewDay % 3600) / 60 // Or just this.minute
        val newSecond = secondsIntoNewDay % 60         // Or just this.second


        // Create and return the new time object using the calculated hour,
        // but keeping the original minute and second relative to that hour.
        val resultTime = myTime(newHour, this.minute, this.second)
        resultTime.day = newDay // Set the correct day on the new object
        return resultTime
    }


    // Method to display the time in HH:MM:SS format, potentially with day prefix
    fun displayTime() {
        // Format H, M, S with leading zeros if necessary
        val hh = String.format("%02d", hour)
        val mm = String.format("%02d", minute)
        val ss = String.format("%02d", second)

        // Add day prefix only if it's Day 2 or later
        val prefix = if (day > 1) "[Day $day] " else ""

        println("$prefix$hh:$mm:$ss")
    }
}

// Main function to demonstrate the myTime class
fun main() {
    try {
        println("--- Initial Times ---")
        val time1 = myTime(10, 30, 15) // 10:30:15 on Day 1
        print("Time 1: "); time1.displayTime()

        val time2 = myTime(23, 50, 40) // 23:50:40 on Day 1
        print("Time 2: "); time2.displayTime()

        println("\n--- Adding Minutes ---")
        // Add 45 minutes to time1 (10:30:15 + 45m = 11:15:15)
        val time1_plus_min = time1.addMinutes(45)
        print("Time 1 + 45 min: "); time1_plus_min.displayTime() // Expected: 11:15:15

        // Add 30 minutes to time2 (23:50:40 + 30m = 00:20:40 on Day 2)
        val time2_plus_min = time2.addMinutes(30)
        print("Time 2 + 30 min: "); time2_plus_min.displayTime() // Expected: [Day 2] 00:20:40

        // Add 1000 minutes to time1 (10:30:15 + 16h 40m = 03:10:15 on Day 2)
        val time1_plus_1000min = time1.addMinutes(1000)
        print("Time 1 + 1000 min: "); time1_plus_1000min.displayTime() // Expected: [Day 2] 03:10:15


        println("\n--- Adding Hours ---")
        // Add 5 hours to time1 (10:30:15 + 5h = 15:30:15)
        val time1_plus_hr = time1.addHours(5)
        print("Time 1 + 5 hours: "); time1_plus_hr.displayTime() // Expected: 15:30:15

        // Add 2 hours to time2 (23:50:40 + 2h = 01:50:40 on Day 2)
        val time2_plus_hr = time2.addHours(2)
        print("Time 2 + 2 hours: "); time2_plus_hr.displayTime() // Expected: [Day 2] 01:50:40

        // Add 20 hours to time1_plus_hr (15:30:15 + 20h = 11:30:15 on Day 2)
        val time1_plus_hr_plus_more = time1_plus_hr.addHours(20)
        print("Time (15:30:15) + 20 hours: "); time1_plus_hr_plus_more.displayTime() // Expected: [Day 2] 11:30:15


        println("\n--- Testing Constraints ---")
        // print("Attempting time1.addMinutes(1500): ")
        // val invalidMinAdd = time1.addMinutes(1500) // Should throw exception

        // print("Attempting time1.addHours(25): ")
        // val invalidHourAdd = time1.addHours(25) // Should throw exception

        // println("\n--- Testing Invalid Initialisation ---")
        // print("Attempting myTime(25, 0, 0): ")
        // val invalidTime = myTime(25, 0, 0) // Should throw exception

    } catch (e: IllegalArgumentException) {
        println("\nCaught expected validation error: ${e.message}")
    } catch (e: Exception) {
        println("\nCaught unexpected error: ${e.message}")
    }
}