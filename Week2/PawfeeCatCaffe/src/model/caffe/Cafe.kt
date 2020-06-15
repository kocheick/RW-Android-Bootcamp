package model.caffe

import model.animals.Cat
import model.people.Employee
import model.people.Person

class Cafe {


    // cafe related stuff

    /**
     * To simplify it, let's imitate a week-long cafe turnaround.
     *UID.randomUUID().toString()UUID.randomUUID().toString()
     * Make sure to add your receipts to each set, with your data.
     * */
    private val receiptsByDay = mutableMapOf(
        "Monday" to mutableSetOf<Receipt>(),
        "Tuesday" to mutableSetOf<Receipt>(),
        "Wednesday" to mutableSetOf<Receipt>(),
        "Thursday" to mutableSetOf<Receipt>(),
        "Friday" to mutableSetOf<Receipt>(),
        "Saturday" to mutableSetOf<Receipt>(),
        "Sunday" to mutableSetOf<Receipt>()
    )

    // maybe as employees check in, you can add them to the list of working employees!
    private val employees = mutableSetOf<Employee>()
    private val customers = mutableSetOf<Person>()

    // make sure to add sponsorships and tie them to people!
    private val sponsorships = mutableSetOf<Sponsorship>()

    // TODO Add logic for checking in and checking out!
    fun checkInEmployee(employee: Employee) {
        employee.clockIn()
        employees.add(employee)
    }

    fun checkOutEmployee(employee: Employee) {
        employee.clockOut()
        employees.remove(employee)
    }

    fun showNumberOfReceiptsForDay(day: String) {
        val receiptForDay = receiptsByDay[day] ?: return // wrong day inserted!

        println("On $day you made ${receiptsByDay.size} transactions!")
    }

    fun getReceipt(items: List<Product>, customerId: String): Receipt {
        // TODO return a receipt! Also make sure to check if customer is also an employee
        var totalPrice = items.map { it.price }.sum()

        return Receipt( items = items,totalPrice = totalPrice, customerId = customerId)
    }

    fun addSponsorship(catId: String, personId: String) {
      val sponsorship = Sponsorship(personId, catId)
        sponsorships.add(sponsorship)
        println("Sponsorship added by #$personId for cat #$catId")
    }

    fun getWorkingEmployees(): Set<Employee> = employees

    fun getAdoptedCats(): Set<Cat> {
        val cats = (employees+customers).map { it.cats}
    }

    fun getSponsoredCats(): Set<Cat> {
        val cats = sponsorships.map { it.catId  }

    }

    fun getMostPopularCats(): Set<Cat> {

    }

    fun getTopSellingItems(): Set<Product> {

    }

    fun getAdopters(): List<Person> {
        return (employees + customers).filter { it.cats.isNotEmpty() }
    }
}