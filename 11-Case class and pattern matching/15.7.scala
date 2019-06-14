val myTuple = (123, "abc")

val (number, string) = myTuple



// partial function
val withDefault: Option[Int] => Int = {
  case Some(x) => x
  case None => 0
}
withDefault(Some(10))
withDefault(None)


// return index 2
val second: List[Int] => Int = {
  case x::y::_ => y
}
second(List(1,2,3,4))
second(Nil)

val second: PartialFunction[List[Int], Int] = {
  case x::y::_ => y
}


// for 
val captials = Map("China"->"Beijing", "France"->"Paris")
for ((country, city) <- captials) {
  println(s"The capital of $country is $city")
}


val results = List(Some("Apple"), None, Some("Orange"))
for(Some(fruit) <- results) {
  println(fruit)
}