// sealed class
sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class OneOp(operator: String, arg: Expr) extends Expr
case class TwoOp(operator: String, left: Expr, right: Expr) extends Expr


def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}


// only patter Number and Var
def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
  case _ => throw new RuntimeException  // 不会匹配, 多余但不得不写
}


// 
def describe(e: Expr): String = (e: @unchecked) match {
  case Number(_) => "a number"
  case Var(_) => "a variable"
}