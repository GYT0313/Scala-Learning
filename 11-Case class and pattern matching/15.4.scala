// pattern overlap
def simplifyAll(e: Expr): Expr = e match {
  case OneOp("-", OneOp("-", e)) => simplifyAll(e) // -是对自己的取反
  case TwoOp("+", e, Number(0)) => simplifyAll(e) // 0是+的中性元素
  case TwoOp("*", e, Number(1)) => simplifyAll(e) // 1是*的中性元素
  case OneOp(op, e) => OneOp(op, simplifyAll(e))
  case TwoOp(op, l, r) => TwoOp(op, simplifyAll(l), simplifyAll(r))
  case _ => e
}
simplifyAll(OneOp("-", OneOp("-", OneOp("-", OneOp("-", v)))))

