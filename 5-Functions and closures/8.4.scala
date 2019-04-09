// default parameter
def printTime(out: java.io.PrintStream = Console.out,
  divisor: Int = 1) = 
  out.println("time = " + System.currentTimeMillis() / divisor)
printTime()
printTime(divisor = 1000)