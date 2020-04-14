package structural

fun main() {
    val drawing = GraphicObject()
    drawing.name = "My Drawing"
    drawing.children.add(CompositeCircle("Red"))
    drawing.children.add(CompositeSquare("Yellow"))

    val group = GraphicObject()
    group.children.add(CompositeCircle("blue"))
    group.children.add(CompositeSquare("blue"))
    drawing.children.add(group)
}

class CompositeCircle(color: String): GraphicObject() {
    override var name: String = ""
        get() = "Circle"
}

class CompositeSquare(color: String): GraphicObject() {
    override var name: String = ""
        get() = "Square"
}

open class GraphicObject {
    open var name = "group"
    var color = "color"
    var children = ArrayList<GraphicObject>() // here comes the composite pattern
    // having a list of the same object as itself
}