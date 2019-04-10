object Element {
  private class ArrayElement(
    val contents: Array[String]
  ) extends Element

  private class UniformElement(
    ch: Char,
    override val width: Int,
    override val height: Int
  ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }

  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)
  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)
  def elem(line: String): Element =
    new LineElement(line)
}

import Element.elem
abstract class Element {
  def contents: Array[String]
  def width: Int = 
    if (height == 0) 0 else contents(0).length
  def height: Int = contents.length
  def above(that: Element): Element = 
    elem(this.contents ++ that.contents)
  def beside(that: Element): Element =
    elem(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )
  override def toString = contents mkString "\n"
}

object Test {
  def main(args: Array[String]) {
    // ArrayElement
    println(elem(Array("hello", "world")).toString + "\n")
    // LineElement
    println(elem("hello").toString + "\n")
    // UniformElement
    println(elem('x', 4, 2).toString + "\n")
    // above  beside
    val column1 = elem("hello") above elem("***")
    val column2 = elem("***") above elem("world")
    println(column1 beside column2)
  }
}