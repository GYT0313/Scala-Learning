// a complicated example
TwoOp("+",
  TwoOp("*",
    TwoOp("+", Var("x"), Var("y")),
    Var("z")),
  Number(1))


Map(
  "|" -> 0, "||" -> 0,
  "&" -> 1, "&&" -> 1,
  ...
)


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
  // ......
}




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








// 合并
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

