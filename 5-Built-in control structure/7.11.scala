// 捕获异常
import java.io._
try {
  val f = new FileReader("iniput.txt")
} catch {
  case ex: FileNotFoundException => println("not found")
  case ex: IOException => println("other exception")
}