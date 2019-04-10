// 2. 抽象类
abstract class Element {
  def contents: Array[String]
}


// 3.定义无参方法
abstract class Element {
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
}


// 4.扩展类
class ArrayElement(conts: Array[String]) extends Element {
  def contents: Array[String] = conts
}


// 5. 重写方法和字段
class ArrayElement(conts: Array[String]) extends Element {
  val contents: Array[String] = conts
}



// 6. 定义参数化字段
class ArrayElement(
  val contents: Array[String]
) extends Element



// 7. 调用超类构造方法
class LineElement(s: String) extends ArrayElement(Array(s)) {
  override def width = s.length
  override def height = 1
}


// 8. 多态和动态绑定
class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}



// 10. 使用组合和继承
class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width = s.length
  override def height = 1
}


// 11. 实现above、beside和toString
def above(that: Element): Element = 
  new ArrayElement(this.contents ++ that.contents)

def beside(that: Element): Element = {
  val contents = new Array[String](this.contents.length)
  for (i <- 0 until this.contents.length)
    contents(i) = this.contents(i) + that.contents(i)
  new ArrayElement(contents)
}

// 更加简单的beside
def beside(that: Element): Element = {
  new ArrayElement(
    for (
      (line1, line2) <- this.contents zip that.contents
    ) yield line1 + line2
  )
}

// 重写toString
override def toString = contents mkString "\n"


// 现在的Element

abstract class Element {
  def contents: Array[String]
  def width: Int = 
    if (height == 0) 0 else contents(0).length
  def height: Int = contents.length
  def above(that: Element): Element = 
    new ArrayElement(this.contents ++ that.contents)
  def beside(that: Element): Element = 
    new ArrayElement(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )
  override def toString = contents mkString "\n"
}

// 现在的三个类如下
class ArrayElement(
  val contents: Array[String]
) extends Element

class UniformElement(
  ch: Char,
  override val width: Int,
  override val height: Int
) extends Element {
  private val line = ch.toString * width
  def contents = Array.fill(height)(line)
}

class LineElement(s: String) extends Element {
  val contents = Array(s)
  override def width = s.length
  override def height = 1
}


