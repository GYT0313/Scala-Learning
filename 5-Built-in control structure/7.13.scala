import java.net._
def urlFor(path: String) = 
  try {
    new URL(path)
  } catch {
    case e: MalformedURLException =>
      new URL("http://www.scala-lang.org")
  }
println(urlFor("test"))