// 指令式
var filename = "default.txt"
if (!args.isEmpty)
  filename = args(0)
println(filename)

// 函数式
val filename2 = 
  if (!args.isEmpty) args(0)
  else "default.txt"
println(filename2)

// 简化
println(if (!args.isEmpty) args(0) else "default.txt")