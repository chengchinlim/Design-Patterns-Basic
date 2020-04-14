package structural

// Need to learn dependency injection, haven't learnt yet
// So cannot proceed

fun main() {
    val raster = RasterRenderer()
    val vector = VectorRenderer()
    val circle = Circle(vector, 5f)
    circle.draw()
    circle.resize(2f)
    circle.draw()
}

class Circle (r: Renderer, rad: Float): Shape(r) {
    private val renderer = r
    private var radius = rad
    override fun draw() {
        renderer.renderCircle(radius)
    }

    override fun resize(factor: Float) {
        radius *= factor
    }
}

class VectorRenderer: Renderer {
    override fun renderCircle(radius: Float) {
        println("VectorRenderCircle: radius is $radius")
    }
}

class RasterRenderer: Renderer {
    override fun renderCircle(radius: Float) {
        println("RasterRenderCircle: radius is $radius")
    }
}

interface Renderer {
    fun renderCircle(radius: Float)
}

abstract class Shape(r: Renderer) {
    abstract fun draw()
    abstract fun resize(factor: Float)
}