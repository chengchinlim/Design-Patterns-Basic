package structural

fun main() {
    val user1 = User2("Yao Lim")
    val user2 = User2("Cheng Lim")
}

class User(name: String) // normal example

class User2(name: String) { // implementing flyweight
    // 3 words will be saved to the array list instead of 4
    // "Lim" only saved once
    private val fullName = name
    private val strings = ArrayList<String>()
    var names = IntArray(10)

    init {
        val getOrAdd: (s: String) -> Int = {
            val index = strings.indexOf(it)
            if (index != -1) {
                index
            } else {
                strings.add(it)
                strings.size - 1
            }
        }
        names = fullName.split(" ")
                .map { getOrAdd(it) }.toIntArray()
    }

}