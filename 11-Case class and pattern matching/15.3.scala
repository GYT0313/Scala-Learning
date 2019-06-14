// pattern guard
// error: x is already defined as value x
def simplifyAdd(e: Expr) = e match {
  case TwoOp("x", x, x) => TwoOp("*", x, Number(2))
  case _ => e
}


// OK
def simplifyAdd(e: Expr) = e match {
  case TwoOp("+", x, y) if x == y => TwoOp("*", x, Number(2))
  case _ => e 
}
simplifyAdd(TwoOp("+", v, v))


