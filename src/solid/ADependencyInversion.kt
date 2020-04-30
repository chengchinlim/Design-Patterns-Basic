package solid

fun main() {
    val parent = Person("John")
    val child1 = Person("Ray")
    val child2 = Person("Jack")

    val relationships = Relationships()
    relationships.addParentAndChild(parent, child1)
    relationships.addParentAndChild(parent, child2)

    Research(relationships, "John")
}

class Research(r: RelationshipBrowser, name: String) { // high level modules
    init {
        val children = r.findAllChildrenOf(name)
        for (child in children) {
            println("Child name: ${child.n}")
        }
    }
}

enum class Relationship {
    PARENT, CHILD, SIBLING
}

data class Person(val n: String)

class Relationships: RelationshipBrowser { // low level modules
    private val relations = ArrayList<Triple<Person, Relationship, Person>>()
    fun addParentAndChild(parent: Person, child: Person) {
        relations.add(Triple(parent, Relationship.PARENT, child))
        relations.add(Triple(child, Relationship.CHILD, parent))
    }

    override fun findAllChildrenOf(name: String): List<Person> {
        return relations.filter {
            x -> x.first.n == "John"
                && x.second == Relationship.PARENT
        }.map {
            it.third
        }
    }
}

interface RelationshipBrowser {
    fun findAllChildrenOf(name: String): List<Person>
}

