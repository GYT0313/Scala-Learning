abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class OneOp(operator: String, arg: Expr) extends Expr
case class TwoOp(operator: String, left: Expr, right: Expr) extends Expr



def simplifyTop(expr: Expr): Expr = expr match {
  case OneOp("-", OneOp("-", e)) => e // 双重取负
  case TwoOp("+", e, Number(0)) => e // 加 0
  case TwoOp("*", e, Number(1)) => e // 乘 1
  case _ => expr
}


