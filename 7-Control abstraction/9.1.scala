// reduce repeat code
import java.io.File
object FileMatcher {
  private def filesHere = (new File(".")).listFiles
  def filesEnding(query: String) = 
    for (file <- filesHere; if file.getName.endsWith(query))
      yield file
  def filesContaining(query: String) = 
    for (file <- filesHere; if file.getName.contains(query))
      yield file
  def filesRegex(query: String) = 
    for (file <- filesHere; if file.getName.matches(query))
      yield file
}




object FileMatcher2 {
  private def filesHere = (new File(".")).listFiles
  // function helper
  def filesMatching(query: String, 
    matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query))
      yield file
  }
  def filesEnding(query: String) = 
    filesMatching(query, _.endsWith(_))
  def filesContaining(query: String) = 
    filesMatching(query, _.contains(_))
  def filesRegex(query: String) = 
    filesMatching(query, _.matches(_))
}


// closure
object FileMatcher3 {
  private def filesHere = (new File(".")).listFiles
  // function helper
  def filesMatching(matcher: String => Boolean) = {
    for (file <- filesHere; if matcher(file.getName))
      yield file
  }
  def filesEnding(query: String) = 
    filesMatching(_.endsWith(query))
  def filesContaining(query: String) = 
    filesMatching(_.contains(query))
  def filesRegex(query: String) = 
    filesMatching(_.matches(query))
}