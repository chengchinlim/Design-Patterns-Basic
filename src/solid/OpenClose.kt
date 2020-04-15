package solid

fun main() {
    val apple = Product("Apple", Color.GREEN, Size.SMALL)
    val tree = Product("Tree", Color.GREEN, Size.LARGE)
    val house = Product("House", Color.BLUE, Size.HUGE)

    val bf = BetterFilter()
    bf.filter(listOf(apple, tree, house), ColorSpecification(Color.GREEN)).forEach {
        println("${it.name} is green")
    }
    bf.filter(listOf(apple, tree, house), SizeSpecification(Size.HUGE)).forEach {
        println("${it.name} is huge")
    }
}

enum class Color {
    RED, GREEN, BLUE
}

enum class  Size {
    SMALL, MEDIUM, LARGE, HUGE
}

data class Product(val name: String, val color: Color, val size: Size)

class BetterFilter: Filter<Product> {
    override fun filter(items: List<Product>, specs: Specification<Product>): List<Product> {
        return items.filter { p -> specs.isSatisfied(p) }
    }

}

class ColorSpecification(c: Color): Specification<Product> {
    private val color = c
    override fun isSatisfied(item: Product): Boolean {
        return item.color == color
    }
}

class SizeSpecification(s: Size): Specification<Product> {
    private val size = s
    override fun isSatisfied(item: Product): Boolean {
        return item.size == size
    }
}

interface Specification<T> {
    fun isSatisfied(item: T): Boolean
}

interface Filter<T> {
    fun filter(items: List<T>, specs: Specification<T>): List<T>
}