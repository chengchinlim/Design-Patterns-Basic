package structural

fun main() {
    val sparrow = Sparrow("Sparrow")
    val toy = MyToyDuck("Toy")
    sparrow.chirp()
    val modifiedToy = BirdAdapter(Sparrow("ToyX"))
    val toys = listOf(toy, modifiedToy)
    for (t in toys) {
        t.squeak()
    }
}

class BirdAdapter(private val bird: Bird): ToyDuck {
    override fun squeak() {
        bird.chirp()
    }
}

interface Bird {
    fun fly()
    fun chirp()
}

interface ToyDuck {
    fun squeak()
}

class Sparrow(private val name: String): Bird {
    override fun fly() {
        println("$name is flying")
    }

    override fun chirp() {
        println("$name is chirping")
    }
}

class MyToyDuck(private val name: String): ToyDuck {
    override fun squeak() {
        println("$name is squeaking")
    }
}