import Element.elem
object Spiral {
  val space = elem(" ")
  val corner = elem("+")
  def spiral(nEdges: Int, direction: Int): Element = {
    if (nEdges == 1)
      elem("+")
    else {
      val sp = spiral(nEdges - 1, (direction + 3) % 4)
      def verticalBar = elem('|', 1, sp.height)
      def horizontalBar = elem('-', sp.width, 1)
      if (direction == 0) {
        val a = (corner beside horizontalBar) above (sp beside space)
        println(a.toString)
        println("\n\n")
        a
      }
      else if (direction == 1) {
        val b = (sp above space) beside (corner above verticalBar)
        println(b.toString)
        println("\n\n")
        b
      }

      else if (direction == 2) {
        val c = (space beside sp) above (horizontalBar beside corner)
        println(c.toString)
        println("\n\n")
        c
      }

      else {
        val d = (verticalBar above corner) beside (space above sp)
        println(d.toString)
        println("\n\n")
        d
      }

    }

  }
  def main(args: Array[String]) = {
    val nSides = 6
    println(spiral(nSides, 0))
  }
}


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