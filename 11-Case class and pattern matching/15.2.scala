v match {
  case Var("y") => println("get y")
  case _ => // 不做任何处理
}


v match {
  case Var("x") => println("get x")
  case _ => // 不做任何处理
}



// wild pattern
val expr = TwoOp("-", v, Number(0))
expr match {
  case TwoOp(_,_,_) => println(TwoOp + "is binary operation")
  case _ => println("something else")
}



// constant pattern
def describe(x: Any) = x match {
  case 5 => "five"
  case true => "truth"
  case "hello" => "hi"
  case Nil => "empty list"
  case _ => "something else"
}

describe(5)
describe(true)
describe("hello")
describe(Nil)
describe(List(1,2,3))


// variable pattern
v match {
  case Var("0") => "zero"
  case somethingElse => "not zero: " + somethingElse
}


import math.{E, Pi}
E match {
  case Pi => "get Pi = " + Pi
  case _ => "OK"
}


val pi = Pi
E match {
  case pi => "get Pi = " + pi
}

// warning: unreachable code due to variable pattern
E match {
  case pi => "get Pi = " + pi
  case _ => "OK"
}


E match {
  case `pi` => "get Pi = " + pi
  case _ => "OK"
}


// construction method pattern
expr match {
  case TwoOp("+", e, Number(0)) => println("a deep")
  case _ => 
}


// sequence pattern
val lyst = List(1,2,3)

lyst match {
  case List(1, _, _) => println("found it")
  case _ => 
}

lyst match {
  case List(1, _*) => println("found it")
  case _ => 
}


// tuple pattern
def tuplePattern(expr: Any) = expr match {
  case (a, b, c) => println("matched: " +  a  + b + c)
  case _ => 
}
tuplePattern(("a", 3, Nil))



// typed pattern
def generalSize(x: Any) = x match {
  case s: String => s.length
  case m: Map[_, _] => m.size
  case l: List[_] => l.length
  case _ => -1
}
generalSize("hello")
generalSize(Map(1->"a", 2->"b"))
generalSize(List(1,2,3))
generalSize(5)


def isIntIntMap(x: Any) = x match {
  case m: Map[Int, Int] => true
  case _ => false
}
isIntIntMap(Map(1->2))
isIntIntMap(Map("a"->"b"))


def isStringArray(x: Any) = x match {
  case a: Array[String] => "yes"
  case _ => "no"
}
isStringArray(Array(1, 2, 3))
isStringArray(Array("a", "b"))


OneOp("abs", OneOp("abs", v)) match {
  case OneOp("abs", e @ OneOp("abs", _)) => e
  case _ => 
}


