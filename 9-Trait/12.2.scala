// 示例：矩形对象
class Point(val x: Int, val y: Int)
class Rectangle(val topLeft: Point, val bottomRight: Point) {
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // and so on
}

// 2D图形
abstract class Component {
  def topLeft: Point
  def bottomRight: Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // and so on
}

// trait
trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
  // and so on
}

class Rectangle(val topLeft: Point,
  val bottomRight: Point) extends Rectangular {
}