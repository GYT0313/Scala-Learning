// 布局类库
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


// 算术表达式定义
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class OneOp(operator: String, arg: Expr) extends Expr
case class TwoOp(operator: String, left: Expr, right: Expr) extends Expr

class ExprFormatter {
  // 优先级递增的操作符分组
  private val opGroups = 
    Array(
      Set("|", "||"),
      Set("&", "&&"),
      Set("^"),
      Set("==", "!="),
      Set("<", "<=", ">", ">="),
      Set("+", "-"),
      Set("*", "%")
    )
  // 操作符到对应优先级的映射关系
  private val precedence = {
    val assocs = 
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i) // 嵌套生成器(双重for)
      } yield op -> i
    assocs.toMap
  }
  // 一元操作符优先级=opGroups.length
  // 分数优先级=-1
  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1
  
  private def format(e: Expr, enclPrec: Int): Element = 
    e match {
      case Var(name) =>
        elem(name)
      case Number(num) => 
        def stripDot(s: String) = 
          if (s.endsWith(".0")) s.substring(0, s.length-2)
          else s
        elem(stripDot(num.toString))
      case OneOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)
      case TwoOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot // 结果由上、线、下构成
        if (enclPrec != fractionPrecedence) frac // 不是分数直接返回
        else elem(" ") beside frac beside elem(" ") // 是分数, 结果两边各加一个空格
      case TwoOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec+1) // 优先级+1
        val oper = l beside elem(" " + op + " ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }
    // 公共的format
    def format(e: Expr): Element = format(e, 0)
}

object ComplcatedExample {
  def main(args: Array[String]): Unit = {
    val f = new ExprFormatter
    val e1 = TwoOp("*", TwoOp("/", Number(1), Number(2)),
                        TwoOp("+", Var("x"), Number(1)))
    val e2 = TwoOp("+", TwoOp("/", Var("x"), Number(2)),
                        TwoOp("/", Number(1.5), Var("x")))
    val e3 = TwoOp("/", e1, e2)
    def show(e: Expr) = println(f.format(e) + "\n\n")
    for (e <- Array(e1, e2, e3)) show(e)
  }
}