// 1. 特质如何工作
trait Run {
  def running() = {
    println("I can running!")
  }
}

class Dog extends Run {
  override def toString = "Wang wang!"
}

class Animal
class Dog extends Animal with Run {
  override def toString = "Wang wang!"
}


class Animal
class HasLegs
class Dog extends Animal with Run with HasLegs {
  override def toString = "Wang wang!"
}


class Animal
class Dog extends Animal with Run {
  override def toString = "Wang wang!"
  override def running() = {
    println("I can running and " + toString)
  }
}