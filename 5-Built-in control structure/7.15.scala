// match
val firstArg = if (args.length > 0) args(0) else ""
val friend = 
  firstArg match {
    case "salt" => "pepper"
    case "chips" => "salsa"
    case "eggs" => "bacon"
    case _ => "any?"
  }
println(friend)

import scala.util.control.Breaks._
import java.io._
val in = BufferedReader(new InputStreamReader(System.in))
breakable {
  while (true) {
    println("? ")
    if (in.readLine() == "") break
  }
}