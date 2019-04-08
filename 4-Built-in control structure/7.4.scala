// 函数式求最大公约数
def gcd(x: Long, y: Long): Long =
  if (y == 0) x else gcd(y, x % y)

println(gcd(6, 9))