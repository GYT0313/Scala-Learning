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
  def above(that: Element): Element = {
    // above 只需要控制左右居中
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }
  def beside(that: Element): Element = {
    // beside 只需要控制上下居中
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem(
      for (
        (line1, line2) <- this1.contents zip that1.contents
      ) yield line1 + line2
    )
  }
  def widen(w: Int): Element =
    // 如果w <= width, 直接返回
    if (w <= width) this
    else {
      // left 和 right 保证短的元素左右居中
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }
  def heighten(h: Int): Element =
    if (h < height) this
    else {
      // top 和 bot 保证短的元素上下居中
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }
  override def toString = contents mkString "\n"
}

object Test {
  def main(args: Array[String]) {
    val str1 = "Here is Element.scala"
    val str2 = "Using scala"
    val str3 = "Combination and inheritance"
    println(elem(str1) above elem(str2) above elem(str3))
  }
}