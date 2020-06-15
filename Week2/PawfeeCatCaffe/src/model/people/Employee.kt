package model.people

import java.text.SimpleDateFormat

class Employee(
    firstName: String,
    lastName: String,
    email: String,
    phoneNumber: String,
    val salary: Double,
    val socialSecurityNumber: String,
    val hireDate: String
) : Person(firstName = firstName, lastName = lastName, email = email, phoneNumber = phoneNumber) {

    override fun toString(): String {
        return "" // TODO format the data in any way you want! :]
    }

    /**
     * Prints a time of clocking in, in a nice format.
     *
     * Hint: to get time, you can create a `Date` object. Use SimpleDateFormatter to format the time!
     * */
    fun clockIn() {
        val date = SimpleDateFormat("yyyy.MM.dd G")
        println("Clocked in at $date")
    }

    // TODO same as above, but times for clocking out!
    fun clockOut() {
        val date = SimpleDateFormat("yyyy.MM.dd G")
        println("Clock-out <at $date")
    }
}