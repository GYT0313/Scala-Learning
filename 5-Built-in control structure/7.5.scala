// 用 for 表达式列举目录中的文件清单
import java.io.File
val filesHere = new File(".").listFiles
for (file <- filesHere)
  println(file)