package structural

fun main() {
    val car = Car(Driver(12))
    car.drive() // without checking driver's age
    val car2 = CarProxy(Driver(12))
    car2.drive() // check driver's age
}

open class Car(d: Driver): Drivable {
    override fun drive() {
        println("Car being driven")
    }
}

class Driver(a: Int) {
    val age = a
}

class CarProxy(d: Driver): Car(d) { // implement proxy
    // extend functionality of base class
    private val driver = d
    override fun drive() {
        if (driver.age >= 17) {
            super.drive()
        } else {
            println("Too young to drive")
        }
    }
}

interface Drivable {
    fun drive()
}