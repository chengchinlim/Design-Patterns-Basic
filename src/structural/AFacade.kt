package structural

fun main() {
    val console = Console(30, 20).createNewConsole()
    console.render()
}

// Console has Viewports, which have buffer (very complicated for clients)

class Buffer(h: Int, w: Int) {
    private val width = w
    private val height = h
    private val characters = CharArray(width * height)
    fun charAt(x: Int, y: Int): Char {
        return characters[y * width + x]
    }
}

class Viewport(b: Buffer, w: Int, h: Int, offX: Int, offY: Int) {
    private val buffer = b
    private val offsetX = offX
    private val offsetY = offY
    fun charAt(x: Int, y: Int): Char {
        return buffer.charAt(x + offsetX, y + offsetY)
    }
}

class Console(w: Int, h: Int) {
    private val width = w
    private val height = h
    private val viewports = ArrayList<Viewport>()

    fun createNewConsole(): Console { // where facade patterns kicks in
        val buffer = Buffer(width, height)
        val viewport = Viewport(buffer, width, height, 0 , 0)
        val console = Console(width, height)
        console.addViewport(viewport)
        return console
    }

    private fun addViewport(v: Viewport) {
        viewports.add(v)
    }

    fun render() {
        for (y in 0 until height) {
           for (x in 0 until width) {
               for (vp in viewports) {
                   print(vp.charAt(x, y))
               }
           }
            println()
        }
    }
}