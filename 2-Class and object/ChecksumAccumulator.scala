import scala.collection.mutable

// 计算校验和
class ChecksumAccumulator {
  private var sum = 0
  def add(b: Byte): Unit = {sum += b}
  def checkSum(): Int = ~(sum & 0xFF) + 1
}

object ChecksumAccumulator {
  // 缓存，存储字符串和其校验和
  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int = {
    // 如果已存在，则返回校验和
    if (cache.contains(s))
      cache(s)
    else {
      val acc = new ChecksumAccumulator
      // 遍历字符串，计算校验和
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checkSum()
      // 加入到缓存
      cache += (s -> cs)
      cs
    }
  }
}