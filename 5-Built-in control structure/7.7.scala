// 多个过滤器
import java.io._
val filesHere = new File(".").listFiles
for (
  file <- filesHere
  if file.isFile
  if file.getName.endsWith(".scala")
) println(file)