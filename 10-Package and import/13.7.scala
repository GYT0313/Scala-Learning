package gyt
abstract class Fruit(
  val name: String,
  val color: String
)
object Fruits {
  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit("orange", "orange")
  object Pear extends Fruit("pear", "yellowish")
  val menu = List(Apple, Orange, Pear)
}


// 引入Fruit
import gyt.Fruit
// 引入gyt的所有成员
import gyt._
// 引入Fruits的所有成员
import gyt.Fruits._



// 只引入Fruits对象中的Apple和Orange
import Fruits.{Apple, Orange}

// 只引入Fruits对象中的Apple和Orange, 并对Apple重命名为NewAp
import Fruits.{Apple=>NewAp, Orange}

// 引入Fruits的所有成员, 相当于import Fruits._
import Fruits.{_}

// 引入Fruits的所有成员, 并对Apple重命名为NewAp
import Fruits.{Apple=>NewAp,_}

// 引入Fruits的所有成员, 除了Apple
import Fruits.{Apple=>_,_}




