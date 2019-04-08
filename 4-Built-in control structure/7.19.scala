// 函数式实例
// 打印乘法表
// 以序列形式返回一行
def makeRowSeq(row: Int) = 
  for (col <- 1 to 10) yield {
    val prod = (row * col).toString
    val padding = " " * (4 - prod.length)
    padding + prod
  }
// 以字符串形式返回一行
def makeRow(row: Int) = makeRowSeq(row).mkString
// 以每行占一个文本行的字符串的形式返回表格
def multiTable() = {
  val tableSeq = // 行字符串序列
    for (row <- 1 to 10)
      yield makeRow(row)
  tableSeq.mkString("\n")
}
println(multiTable)