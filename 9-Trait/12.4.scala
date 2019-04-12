// 5. 作为可叠加的特质
abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}


import scala.collection.mutable.ArrayBuffer
class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) = { buf += x }
}

// Doubling
trait Doubling extends IntQueue {
  abstract override def put(x: Int) = { super.put(x * 2) }
}

// +1
trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = { super.put(x + 1) }
}
// 过滤负数
trait Filtering extends IntQueue {
  abstract override def put(x: Int) = {
    if (x >= 0) super.put(x)
  }
}