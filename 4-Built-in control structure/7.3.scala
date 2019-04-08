// do while
import java.io._
val in = new BufferedReader(new InputStreamReader(System.in))
var line = ""
do {
  line = in.readLine()
  println("Read: " + line)
} while (line != "")
