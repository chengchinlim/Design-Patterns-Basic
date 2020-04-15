package behavioral

import java.lang.StringBuilder

fun main() {
    val tp = TextProcessor(OutputFormat.MARKDOWN)
    tp.appendList(listOf("apple", "orange", "banana"))
    println(tp)
    tp.clear()

    tp.setOutputFormat(OutputFormat.HTML)
    tp.appendList(listOf("pork", "beef", "salmon"))
    println(tp)
}

class TextProcessor(f: OutputFormat) {
    private val sb = StringBuilder()
    var listStrategy: ListStrategy? = null

    init {
        setOutputFormat(f) // implement strategy
        // change strategy at run time
    }

    fun setOutputFormat(f: OutputFormat) {
        listStrategy = when (f) {
            OutputFormat.MARKDOWN -> {
                MarkDownListStrategy()
            }
            OutputFormat.HTML -> {
                HtmlListStrategy()
            }
        }
    }

    fun appendList(items: List<String>) {
        listStrategy!!.start(sb)
        for (i in items) {
            listStrategy!!.addListItem(sb, i)
        }
        listStrategy!!.end(sb)
    }

    fun clear() {
        sb.setLength(0)
    }

    override fun toString(): String {
        return sb.toString()
    }
}

enum class OutputFormat {
    MARKDOWN, HTML
}

interface ListStrategy {
    fun start(sb: StringBuilder) {}
    fun addListItem(sb: StringBuilder, item: String)
    fun end(sb: StringBuilder) {}
}

class MarkDownListStrategy: ListStrategy {
    override fun addListItem(sb: StringBuilder, item: String) {
        sb.append("* ").append(item)
                .append(System.lineSeparator())
    }
}

class HtmlListStrategy: ListStrategy {
    override fun start(sb: StringBuilder) {
        sb.append("<ul>").append(System.lineSeparator())
    }
    override fun addListItem(sb: StringBuilder, item: String) {
        sb.append("  <li>").append(item)
                .append("</li>").append(System.lineSeparator())
    }

    override fun end(sb: StringBuilder) {
        sb.append("</ul>").append(System.lineSeparator())
    }
}