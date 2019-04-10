
// 非字段或方法的代码被编译到主构造方法中
class Rational(n: Int, d: Int) {
  println("Created " + n + "/" + d)
}


// 重写
class Rational(n: Int, d: Int) {
  override def toString = n + "/" + d
}


// 检查前置条件
class Rational(n: Int, d: Int) {
  require(d != 0)
  override def toString = n + "/" + d
}

// 添加字段
class Rational(n: Int, d: Int) {
  require(d != 0)
  val number: Int = n
  val denom: Int = d
  override def toString = number + "/" + denom
}



// 添加add 方法
class Rational(n: Int, d: Int) {
  require(d != 0)
  val number: Int = n
  val denom: Int = d
  override def toString = number + "/" + denom
  def add(that: Rational): Rational =
    new Rational(
      number * that.denom + that.number * denom,
      denom * that.denom
    )
}


// 自引用
def lessThan(that: Rational) = 
  this.number * that.denom < that.number * this.denom



// 辅助构造方法
class Rational(n: Int, d: Int) {
  require(d != 0)
  val number: Int = n
  val denom: Int = d
  def this(n: Int) = this(n, 1) // 辅助构造方法
  override def toString = number + "/" + denom
  def add(that: Rational): Rational =
    new Rational(
      number * that.denom + that.number * denom,
      denom * that.denom
    )
}



// 私有字段和方法
class Rational(n: Int, d: Int) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val number: Int = n / g
  val denom: Int = d / g
  def this(n: Int) = this(n, 1)
  override def toString = number + "/" + denom
  def add(that: Rational): Rational =
    new Rational(
      number * that.denom + that.number * denom,
      denom * that.denom
    )
  // 递归求最大公约数
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}



// 定义操作符
class Rational(n: Int, d: Int) {
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
  def * (that: Rational): Rational =
    new Rational(
      number * that.number, denom * that.denom)
  // 递归求最大公约数
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}




// 方法重载
class Rational(n: Int, d: Int) {
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
}


// 隐式转换
implicit def intToRational(x: Int) = new Rational(x)



