// 使用过滤器
// 只打印.scala 结尾
import java.io._
val filesHere = new File(".").listFiles
for (file <- filesHere if file.getName.endsWith(".scala"))
  println(file)
