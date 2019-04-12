// Ordered 
class Rational(n: Int, d: Int) extends Ordered[Rational] {
  // ...
  // 如果this > that 返回负值, this == that 返回0
  def compare(that: Rational) = 
    (this.numer * that.denom) - (that.numer * this.denom)
}



// 完整代码
class Rational(n: Int, d: Int) extends Ordered[Rational] {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val number: Int = n / g
  val denom: Int = d / g
  def this(n: Int) = this(n, 1)
  override def toString = number + "/" + denom
  def + (that: Rational): Rational =
    new Rational(
      number * that.denom + that.number * denom,
      denom * that.denom
    )
  def + (i: Int): Rational =
    new Rational(
      number + i * denom, denom)
  def - (that: Rational): Rational =
    new Rational(
      number * that.denom - that.number * denom,
      denom * that.denom
    )
  def - (i: Int): Rational =
    new Rational(
      number - i * denom, denom)

  def * (that: Rational): Rational =
    new Rational(
      number * that.number, denom * that.denom)
  def * (i: Int): Rational =
    new Rational(
      number * i, denom)
  def / (that: Rational): Rational =
    new Rational(
      number * that.denom, denom * that.number)
  def / (i: Int): Rational =
    new Rational(
      number, denom * i)
  // 递归求最大公约数
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  // 如果this > that 返回负值, this == that 返回0
  def compare(that: Rational) = 
    (this.number * that.denom) - (that.number * this.denom)
}


// 隐式转换
implicit def intToRational(x: Int) = new Rational(x)