class Outer {
  class Inner {
    private def f() = println("hello")
    class InnerMore {
      f() // OK
    }
  }
  (new Inner).f() //错误, 无法访问
}


class father {
  protected def f() = println("hello")
}
class sun extends father {
  f() // OK
}
class other {
  f() // 错误
}


